///////////////////////////////////////////////////////////////////////////////
/// This source file is generated by XBiG (The XSLT Bindings Generator)
/// For the latest info, see http://sourceforge.net/projects/xbig/
///
/// Copyright (c) 2004-2008 NetAllied Systems GmbH, Ravensburg. All rights reserved.
/// Also see acknowledgements in Readme.html
///
/// This program is free software; you can redistribute it and/or modify it under
/// the terms of the GNU Lesser General Public License as published by the Free Software
/// Foundation; either version 2 of the License, or (at your option) any later
/// version.
///
/// This program is distributed in the hope that it will be useful, but WITHOUT
/// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
/// FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
///
/// You should have received a copy of the GNU Lesser General Public License along with
/// this program; if not, write to the Free Software Foundation, Inc., 59 Temple
/// Place - Suite 330, Boston, MA 02111-1307, USA, or go to
/// http://www.gnu.org/copyleft/lesser.txt.
///
/// Machine generated file
///////////////////////////////////////////////////////////////////////////////



#ifdef WIN32
	// disable warnings
	#pragma warning (disable : 4267) // conversion from 'size_t' to 'jint'
#else

#endif


#ifndef __Included_org_ogre4j_MovablePlane__
#define __Included_org_ogre4j_MovablePlane__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     MovablePlane()
 * Type:       constructor
 * Definition: Ogre::MovablePlane::MovablePlane
 * Signature:  (std_string)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1createMovablePlane_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     MovablePlane()
 * Type:       constructor
 * Definition: Ogre::MovablePlane::MovablePlane
 * Signature:  (Ogre_Plane)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1createMovablePlane_1_1PlaneR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong rhs
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     MovablePlane()
 * Type:       constructor
 * Definition: Ogre::MovablePlane::MovablePlane
 * Signature:  (Ogre_Vector3float)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1createMovablePlane_1_1Vector3RRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong rkNormal, 
  jfloat fConstant
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     MovablePlane()
 * Type:       constructor
 * Definition: Ogre::MovablePlane::MovablePlane
 * Signature:  (Ogre_Vector3Ogre_Vector3)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1createMovablePlane_1_1Vector3RVector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong rkNormal, 
  jlong rkPoint
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     MovablePlane()
 * Type:       constructor
 * Definition: Ogre::MovablePlane::MovablePlane
 * Signature:  (Ogre_Vector3Ogre_Vector3Ogre_Vector3)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1createMovablePlane_1_1Vector3RVector3RVector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong rkPoint0, 
  jlong rkPoint1, 
  jlong rkPoint2
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _notifyCurrentCamera()
 * Type:       virtual method
 * Definition: void Ogre::MovablePlane::_notifyCurrentCamera
 * Signature:  (Ogre_Camera)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1_1notifyCurrentCamera_1_1Camerap (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a1
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getBoundingBox()
 * Type:       virtual method
 * Definition: const AxisAlignedBox& Ogre::MovablePlane::getBoundingBox
 * Signature:  ()Ogre_AxisAlignedBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getBoundingBox_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getBoundingRadius()
 * Type:       virtual method
 * Definition: Real Ogre::MovablePlane::getBoundingRadius
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MovablePlane__1getBoundingRadius_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _updateRenderQueue()
 * Type:       virtual method
 * Definition: void Ogre::MovablePlane::_updateRenderQueue
 * Signature:  (Ogre_RenderQueue)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1_1updateRenderQueue_1_1RenderQueuep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a1
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getMovableType()
 * Type:       virtual method
 * Definition: const String& Ogre::MovablePlane::getMovableType
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_MovablePlane__1getMovableType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _getDerivedPlane()
 * Type:       non-virtual method
 * Definition: const Plane& Ogre::MovablePlane::_getDerivedPlane
 * Signature:  ()Ogre_Plane
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1getDerivedPlane_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     visitRenderables()
 * Type:       virtual method
 * Definition: void Ogre::MovablePlane::visitRenderables
 * Signature:  (Ogre_Renderable_VisitorZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1visitRenderables_1_1Renderable_1Visitorpbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong visitor, 
  jboolean debugRenderables
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getSide()
 * Type:       non-virtual method
 * Definition: Side Ogre::Plane::getSide
 * Signature:  (Ogre_Vector3)Ogre_Plane_Side
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MovablePlane__1getSide_1_1Vector3R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rkPoint
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getSide()
 * Type:       non-virtual method
 * Definition: Side Ogre::Plane::getSide
 * Signature:  (Ogre_AxisAlignedBox)Ogre_Plane_Side
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MovablePlane__1getSide_1_1AxisAlignedBoxR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rkBox
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getSide()
 * Type:       non-virtual method
 * Definition: Side Ogre::Plane::getSide
 * Signature:  (Ogre_Vector3Ogre_Vector3)Ogre_Plane_Side
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MovablePlane__1getSide_1_1Vector3RVector3R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong centre, 
  jlong halfSize
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getDistance()
 * Type:       non-virtual method
 * Definition: Real Ogre::Plane::getDistance
 * Signature:  (Ogre_Vector3)float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MovablePlane__1getDistance_1_1Vector3R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rkPoint
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     redefine()
 * Type:       non-virtual method
 * Definition: void Ogre::Plane::redefine
 * Signature:  (Ogre_Vector3Ogre_Vector3Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1redefine_1_1Vector3RVector3RVector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rkPoint0, 
  jlong rkPoint1, 
  jlong rkPoint2
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     redefine()
 * Type:       non-virtual method
 * Definition: void Ogre::Plane::redefine
 * Signature:  (Ogre_Vector3Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1redefine_1_1Vector3RVector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rkNormal, 
  jlong rkPoint
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     projectVector()
 * Type:       non-virtual method
 * Definition: Vector3 Ogre::Plane::projectVector
 * Signature:  (Ogre_Vector3)Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1projectVector_1_1Vector3R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     normalise()
 * Type:       non-virtual method
 * Definition: Real Ogre::Plane::normalise
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MovablePlane__1normalise (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     operator==()
 * Type:       non-virtual method
 * Definition: bool Ogre::Plane::operator==
 * Signature:  (Ogre_Plane)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1operatorEqual_1_1PlaneR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     operator!=()
 * Type:       non-virtual method
 * Definition: bool Ogre::Plane::operator!=
 * Signature:  (Ogre_Plane)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1operatorNotEqual_1_1PlaneR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _notifyCreator()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::_notifyCreator
 * Signature:  (Ogre_MovableObjectFactory)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1_1notifyCreator_1_1MovableObjectFactoryp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong fact
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _getCreator()
 * Type:       virtual method
 * Definition: virtual MovableObjectFactory* Ogre::MovableObject::_getCreator
 * Signature:  ()Ogre_MovableObjectFactory
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1getCreator_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _notifyManager()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::_notifyManager
 * Signature:  (Ogre_SceneManager)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1_1notifyManager_1_1SceneManagerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong man
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _getManager()
 * Type:       virtual method
 * Definition: virtual SceneManager* Ogre::MovableObject::_getManager
 * Signature:  ()Ogre_SceneManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1getManager_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getName()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::MovableObject::getName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_MovablePlane__1getName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getParentNode()
 * Type:       virtual method
 * Definition: virtual Node* Ogre::MovableObject::getParentNode
 * Signature:  ()Ogre_Node
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getParentNode_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getParentSceneNode()
 * Type:       virtual method
 * Definition: virtual SceneNode* Ogre::MovableObject::getParentSceneNode
 * Signature:  ()Ogre_SceneNode
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getParentSceneNode_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _notifyAttached()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::_notifyAttached
 * Signature:  (Ogre_NodeZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1_1notifyAttached_1_1Nodepbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong parent, 
  jboolean isTagPoint
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     isAttached()
 * Type:       virtual method
 * Definition: virtual bool Ogre::MovableObject::isAttached
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1isAttached_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     detatchFromParent()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::detatchFromParent
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1detatchFromParent (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     isInScene()
 * Type:       virtual method
 * Definition: virtual bool Ogre::MovableObject::isInScene
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1isInScene_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _notifyMoved()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::_notifyMoved
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1_1notifyMoved (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getWorldBoundingBox()
 * Type:       virtual method
 * Definition: virtual const AxisAlignedBox& Ogre::MovableObject::getWorldBoundingBox
 * Signature:  (Z)Ogre_AxisAlignedBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getWorldBoundingBox_1_1bv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean derive
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getWorldBoundingSphere()
 * Type:       virtual method
 * Definition: virtual const Sphere& Ogre::MovableObject::getWorldBoundingSphere
 * Signature:  (Z)Ogre_Sphere
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getWorldBoundingSphere_1_1bv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean derive
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setVisible()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setVisible
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setVisible_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean visible
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getVisible()
 * Type:       virtual method
 * Definition: virtual bool Ogre::MovableObject::getVisible
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1getVisible_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     isVisible()
 * Type:       virtual method
 * Definition: virtual bool Ogre::MovableObject::isVisible
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1isVisible_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setRenderingDistance()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setRenderingDistance
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setRenderingDistance_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat dist
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getRenderingDistance()
 * Type:       virtual method
 * Definition: virtual Real Ogre::MovableObject::getRenderingDistance
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MovablePlane__1getRenderingDistance_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setUserObject()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setUserObject
 * Signature:  (Ogre_UserDefinedObject)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setUserObject_1_1UserDefinedObjectp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong obj
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getUserObject()
 * Type:       virtual method
 * Definition: virtual UserDefinedObject* Ogre::MovableObject::getUserObject
 * Signature:  ()Ogre_UserDefinedObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getUserObject (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setUserAny()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setUserAny
 * Signature:  (Ogre_Any)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setUserAny_1_1AnyR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong anything
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getUserAny()
 * Type:       virtual method
 * Definition: virtual const Any& Ogre::MovableObject::getUserAny
 * Signature:  ()Ogre_Any
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getUserAny_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setRenderQueueGroup()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setRenderQueueGroup
 * Signature:  (unsigned_char)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setRenderQueueGroup_1_1uint8v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jshort queueID
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getRenderQueueGroup()
 * Type:       virtual method
 * Definition: virtual uint8 Ogre::MovableObject::getRenderQueueGroup
 * Signature:  ()unsigned_char
 */

JNIEXPORT jshort JNICALL Java_org_ogre4j_MovablePlane__1getRenderQueueGroup_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _getParentNodeFullTransform()
 * Type:       virtual method
 * Definition: virtual const Matrix4& Ogre::MovableObject::_getParentNodeFullTransform
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1getParentNodeFullTransform_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setQueryFlags()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setQueryFlags
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setQueryFlags_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     addQueryFlags()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::addQueryFlags
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1addQueryFlags_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     removeQueryFlags()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::removeQueryFlags
 * Signature:  (J)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1removeQueryFlags_1_1Lv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getQueryFlags()
 * Type:       virtual method
 * Definition: virtual uint32 Ogre::MovableObject::getQueryFlags
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getQueryFlags_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setVisibilityFlags()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setVisibilityFlags
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setVisibilityFlags_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     addVisibilityFlags()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::addVisibilityFlags
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1addVisibilityFlags_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     removeVisibilityFlags()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::removeVisibilityFlags
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1removeVisibilityFlags_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getVisibilityFlags()
 * Type:       virtual method
 * Definition: virtual uint32 Ogre::MovableObject::getVisibilityFlags
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getVisibilityFlags_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setListener()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setListener
 * Signature:  (Ogre_MovableObject_Listener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setListener_1_1Listenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getListener()
 * Type:       virtual method
 * Definition: virtual Listener* Ogre::MovableObject::getListener
 * Signature:  ()Ogre_MovableObject_Listener
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getListener_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     queryLights()
 * Type:       virtual method
 * Definition: virtual const LightList& Ogre::MovableObject::queryLights
 * Signature:  ()Ogre_LightList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1queryLights_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     _getLightList()
 * Type:       virtual method
 * Definition: virtual LightList* Ogre::MovableObject::_getLightList
 * Signature:  ()Ogre_LightList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1_1getLightList (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getEdgeList()
 * Type:       virtual method
 * Definition: EdgeData* Ogre::MovableObject::getEdgeList
 * Signature:  ()Ogre_EdgeData
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getEdgeList (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     hasEdgeList()
 * Type:       virtual method
 * Definition: bool Ogre::MovableObject::hasEdgeList
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1hasEdgeList (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getShadowVolumeRenderableIterator()
 * Type:       virtual method
 * Definition: ShadowRenderableListIterator Ogre::MovableObject::getShadowVolumeRenderableIterator
 * Signature:  (Ogre_ShadowTechniqueOgre_LightOgre_HardwareIndexBufferSharedPtrZfloatJ)Ogre_ShadowCaster_ShadowRenderableListIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getShadowVolumeRenderableIterator_1_1ShadowTechniquevLightPHardwareIndexBufferSharedPtrpbvRealvLv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint shadowTechnique, 
  jlong light, 
  jlong indexBuffer, 
  jboolean extrudeVertices, 
  jfloat extrusionDist, 
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getLightCapBounds()
 * Type:       virtual method
 * Definition: const AxisAlignedBox& Ogre::MovableObject::getLightCapBounds
 * Signature:  ()Ogre_AxisAlignedBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getLightCapBounds_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getDarkCapBounds()
 * Type:       virtual method
 * Definition: const AxisAlignedBox& Ogre::MovableObject::getDarkCapBounds
 * Signature:  (Ogre_Lightfloat)Ogre_AxisAlignedBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getDarkCapBounds_1_1LightRRealv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong light, 
  jfloat dirLightExtrusionDist
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setCastShadows()
 * Type:       non-virtual method
 * Definition: void Ogre::MovableObject::setCastShadows
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setCastShadows_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean enabled
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getCastShadows()
 * Type:       virtual method
 * Definition: bool Ogre::MovableObject::getCastShadows
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1getCastShadows_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getPointExtrusionDistance()
 * Type:       virtual method
 * Definition: Real Ogre::MovableObject::getPointExtrusionDistance
 * Signature:  (Ogre_Light)float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MovablePlane__1getPointExtrusionDistance_1_1LightP_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong l
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getTypeFlags()
 * Type:       virtual method
 * Definition: virtual uint32 Ogre::MovableObject::getTypeFlags
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getTypeFlags_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setDebugDisplayEnabled()
 * Type:       virtual method
 * Definition: virtual void Ogre::MovableObject::setDebugDisplayEnabled
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setDebugDisplayEnabled_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean enabled
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     isDebugDisplayEnabled()
 * Type:       virtual method
 * Definition: virtual bool Ogre::MovableObject::isDebugDisplayEnabled
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MovablePlane__1isDebugDisplayEnabled_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setDefaultQueryFlags()
 * Type:       static method
 * Definition: static void Ogre::MovableObject::setDefaultQueryFlags
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setDefaultQueryFlags_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getDefaultQueryFlags()
 * Type:       static method
 * Definition: static uint32 Ogre::MovableObject::getDefaultQueryFlags
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getDefaultQueryFlags (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setDefaultVisibilityFlags()
 * Type:       static method
 * Definition: static void Ogre::MovableObject::setDefaultVisibilityFlags
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setDefaultVisibilityFlags_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong flags
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getDefaultVisibilityFlags()
 * Type:       static method
 * Definition: static uint32 Ogre::MovableObject::getDefaultVisibilityFlags
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getDefaultVisibilityFlags (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     extrudeVertices()
 * Type:       static method
 * Definition: static void Ogre::ShadowCaster::extrudeVertices
 * Signature:  (Ogre_HardwareVertexBufferSharedPtrIOgre_Vector4float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1extrudeVertices_1_1HardwareVertexBufferSharedPtrRivVector4RRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong vertexBuffer, 
  jint originalVertexCount, 
  jlong lightPos, 
  jfloat extrudeDist
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getAnimableValueNames()
 * Type:       non-virtual method
 * Definition: const StringVector& Ogre::AnimableObject::getAnimableValueNames
 * Signature:  ()Ogre_StringVector
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getAnimableValueNames_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     createAnimableValue()
 * Type:       virtual method
 * Definition: virtual AnimableValuePtr Ogre::AnimableObject::createAnimableValue
 * Signature:  (std_string)Ogre_AnimableValuePtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1createAnimableValue_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring valueName
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::MovablePlane::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getnormal()
 * Type:       getter for public attribute
 * Definition: Vector3 Ogre::Plane::normal
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MovablePlane__1getnormal (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setnormal()
 * Type:       setter for public attribute
 * Definition: Vector3 Ogre::Plane::normal
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setnormal (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     getd()
 * Type:       getter for public attribute
 * Definition: Real Ogre::Plane::d
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MovablePlane__1getd (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MovablePlane
 * Method:     setd()
 * Type:       setter for public attribute
 * Definition: Real Ogre::Plane::d
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MovablePlane__1setd (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_MovablePlane__*/
