#include <Ogre4JStableHeaders.h>
#include "Ogre4JAwtCanvas.h"
#include "jawt_md.h"

HGLRC awtHGLRC;
HGLRC ogreHGLRC;
HDC ogreHDC;
bool isOpenGl = false;


JNIEXPORT jlong JNICALL Java_org_ogre4j_awt_OgreAwtCanvas_createRenderWindow(
	JNIEnv * env, jclass that, jobject canvasObject, jlong root)
{
	JAWT awt;
	JAWT_DrawingSurface* ds;
	JAWT_DrawingSurfaceInfo* dsi;
	jint lock;
	Ogre::RenderWindow *renderWindow;
	Ogre::NameValuePairList misc;
	Ogre::Root *nativeRoot = reinterpret_cast<Ogre::Root *>(root); 
	
	/* Get the AWT */
	awt.version = JAWT_VERSION_1_3;
	if (JAWT_GetAWT(env, &awt) == JNI_FALSE) 
	{
		printf("AWT Not found\n");
		return 0;
	}

	/* Get the drawing surface */
	ds = awt.GetDrawingSurface(env, canvasObject);
	if (ds == NULL) 
	{
		printf("NULL drawing surface\n");
		return 0;
	}
	

	/* Lock the drawing surface */
	lock = ds->Lock(ds);
	if((lock & JAWT_LOCK_ERROR) != 0) 
	{
		printf("Error locking surface\n");
		awt.FreeDrawingSurface(ds);
		return 0;
	}

	/* Get the drawing surface info */
	dsi = ds->GetDrawingSurfaceInfo(ds);
	if (dsi == NULL) 
	{
		printf("Error getting surface info\n");
		ds->Unlock(ds);
		awt.FreeDrawingSurface(ds);
		return 0;
	}
	
#ifdef WIN32

	jawt_Win32DrawingSurfaceInfo* dsi_win32;
	
	/* Get the platform-specific drawing info */
	dsi_win32 = (jawt_Win32DrawingSurfaceInfo*)dsi->platformInfo;
	int eHWND = reinterpret_cast<int>(dsi_win32->hwnd);
	static HDC hDC = dsi_win32->hdc;
	int iPixelFormat = 0;
	PIXELFORMATDESCRIPTOR pfd;
	
	iPixelFormat = DescribePixelFormat(hDC, iPixelFormat, sizeof(pfd), &pfd);

	// get the best available match of pixel format for the device context  
	
	SetPixelFormat(hDC, iPixelFormat, &pfd);

	
    // create and enable the render context (RC)
	awtHGLRC = wglCreateContext(hDC);

	
	
	misc["externalWindowHandle"] = Ogre::StringConverter::toString(eHWND);
	if(awtHGLRC)
	{
		misc["externalGLControl"] = Ogre::StringConverter::toString("True");
		misc["externalGLContext"] = Ogre::StringConverter::toString(reinterpret_cast<unsigned long>(awtHGLRC));
	}
	
#endif
	renderWindow = nativeRoot->createRenderWindow("Ogre4J", dsi->bounds.width, dsi->bounds.height, false, &misc);

	//if RenderSystem is OpenGL set isOpenGL true
	if(nativeRoot->getRenderSystem()->getName() == "OpenGL Rendering Subsystem")
	{
		isOpenGl = true;
		//get ogre Context
		ogreHDC = GetDC(dsi_win32->hwnd);
		ogreHGLRC = wglGetCurrentContext();
	}
	/* Free the drawing surface info */
	ds->FreeDrawingSurfaceInfo(dsi);

	/* Unlock the drawing surface */
	ds->Unlock(ds);

	/* Free the drawing surface */
	awt.FreeDrawingSurface(ds);

	return reinterpret_cast<jlong>(renderWindow);
}

/*
 * Class:     MyCanvas
 * Method:    paint
 * Signature: (Ljava/awt/Graphics;)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_awt_OgreAwtCanvas_paint
(JNIEnv* env, jobject canvas, jobject graphics)
{
    JAWT awt;
    JAWT_DrawingSurface* ds;
    JAWT_DrawingSurfaceInfo* dsi;
	jawt_Win32DrawingSurfaceInfo* dsi_win32;
    jint lock;

    /* Get the AWT */
    awt.version = JAWT_VERSION_1_3;
    if (JAWT_GetAWT(env, &awt) == JNI_FALSE) 
	{
        printf("AWT Not found\n");
        return;
    }

    /* Get the drawing surface */
    ds = awt.GetDrawingSurface(env, canvas);
    if (ds == NULL) 
	{
        printf("NULL drawing surface\n");
        return;
    }

    /* Lock the drawing surface */
    lock = ds->Lock(ds);
    if((lock & JAWT_LOCK_ERROR) != 0) 
	{
        printf("Error locking surface\n");
        awt.FreeDrawingSurface(ds);
        return;
    }

    /* Get the drawing surface info */
    dsi = ds->GetDrawingSurfaceInfo(ds);
    if (dsi == NULL) 
	{
        printf("Error getting surface info\n");
        ds->Unlock(ds);
        awt.FreeDrawingSurface(ds);
        return;
    }

    /* Get the platform-specific drawing info */
	dsi_win32 = (jawt_Win32DrawingSurfaceInfo*)dsi->platformInfo;

    //Switch to Ogre context
	if(isOpenGl)
	{
		if(!wglMakeCurrent(GetWindowDC(dsi_win32->hwnd), ogreHGLRC))
		{
			std::cout << "Error wglMakeCurrent(ogreDC, ogreHGLRC)!" << std::endl;
		}
	}
	//let Ogre render one frame
	if(!Ogre::Root::getSingletonPtr()->renderOneFrame())
	{
		std::cout << "Error while rendering!" << std::endl;
	}
	if(isOpenGl)
	{
		if(!SwapBuffers(dsi_win32->hdc))
			std::cout << "Can't swap buffers" << std::endl;
		//Switch to AWT context
	
		if(!wglMakeCurrent(dsi_win32->hdc, awtHGLRC))
			std::cout << "Error wglMakeCurrent(dsi_win32->hdc, awtHGLRC)!" << std::endl;
	}

    /* Free the drawing surface info */
    ds->FreeDrawingSurfaceInfo(dsi);

    /* Unlock the drawing surface */
    ds->Unlock(ds);

    /* Free the drawing surface */
    awt.FreeDrawingSurface(ds);
}
