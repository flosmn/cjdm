#include "JNIOpcode.h"
#include "OgreOpcodeManager.h"

/*
 * Class:     de_netallied_opcode_Opcode
 * Method:    addEntity
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_opcode_Opcode_addEntity
(JNIEnv *env, jclass clazz, jlong pOgreEntity)
{
#ifdef _DEBUG
    std::cout << "Java_org_ogre4j_eclipse_opcode_Opcode_addEntity" << std::endl;
#endif

    Ogre::Entity * ogreEntity = reinterpret_cast<Ogre::Entity*>(pOgreEntity);
    Ogre::OpcodeManager::getSingleton().addEntity(ogreEntity);
}

/*
 * Class:     de_netallied_opcode_Opcode
 * Method:    getClosestEntity
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_eclipse_opcode_Opcode_getClosestEntity
(JNIEnv *, jclass, jlong pRaySceneQuery)
{
#ifdef _DEBUG
    std::cout << "Java_org_ogre4j_eclipse_opcode_Opcode_getClosestEntity" << std::endl;
#endif
    Ogre::RaySceneQuery * raySceneQuery = reinterpret_cast<Ogre::RaySceneQuery *>(pRaySceneQuery);
    Ogre::Entity * entity = Ogre::OpcodeManager::getSingleton().getClosestEntity(raySceneQuery);
    return reinterpret_cast<jint>(entity);
}

/*
 * Class:     de_netallied_opcode_Opcode
 * Method:    getClosestHit
 * Signature: (J)[F
 */
JNIEXPORT jfloatArray JNICALL Java_org_ogre4j_eclipse_opcode_Opcode_getClosestHit
(JNIEnv *env, jclass clazz, jlong pRaySceneQuery)
{
#ifdef _DEBUG
    std::cout << "Java_org_ogre4j_eclipse_opcode_Opcode_getClosestHit" << std::endl;
#endif
    Ogre::RaySceneQuery * raySceneQuery = reinterpret_cast<Ogre::RaySceneQuery *>(pRaySceneQuery);
    Ogre::Vector3 hitCoord, normal;
    bool success = Ogre::OpcodeManager::getSingleton().getClosestHit(raySceneQuery, hitCoord, normal);

    if(!success)
        return 0;

    jfloat values[6] = { hitCoord.x, hitCoord.y, hitCoord.z,
        normal.x, normal.y, normal.z };
    jfloatArray jArray = env->NewFloatArray( 6 );
    env->SetFloatArrayRegion( jArray, 0, 6, values);
    return jArray;
}

/*
 * Class:     de_netallied_opcode_Opcode
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_opcode_Opcode_init
  (JNIEnv *env, jclass clazz)
{
#ifdef _DEBUG
    std::cout << "Java_org_ogre4j_eclipse_opcode_Opcode_init" << std::endl;
#endif
    new Ogre::OpcodeManager();
}

/*
 * Class:     de_netallied_opcode_Opcode
 * Method:    ndispose
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_opcode_Opcode_ndispose
  (JNIEnv *env, jclass clazz)
{
#ifdef _DEBUG
    std::cout << "Java_org_ogre4j_eclipse_opcode_Opcode_ndispose" << std::endl;
#endif
    delete Ogre::OpcodeManager::getSingletonPtr();
}

/*
 * Class:     de_netallied_opcode_Opcode
 * Method:    removeEntity
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_opcode_Opcode_removeEntity
(JNIEnv *env, jclass clazz, jlong pOgreEntity)
{
#ifdef _DEBUG
    std::cout << "Java_org_ogre4j_eclipse_opcode_Opcode_removeEntity" << std::endl;
#endif
    Ogre::Entity * ogreEntity = reinterpret_cast<Ogre::Entity*>(pOgreEntity);
    Ogre::OpcodeManager::getSingleton().removeEntity(ogreEntity);
}

/*
* Class:     org_ogre4j_eclipse_opcode_Opcode
* Method:    executeQuery
* Signature: (J[F[F)J
*/
JNIEXPORT jlong JNICALL Java_org_ogre4j_eclipse_opcode_Opcode_executeQuery
(JNIEnv *env, jclass clazz, jlong pRaySceneQuery, jfloatArray pointArray, jfloatArray normalArray)
{
#ifdef _DEBUG
    std::cout << "Java_org_ogre4j_eclipse_opcode_Opcode_executeQuery" << std::endl;
#endif
    Ogre::RaySceneQuery * raySceneQuery = reinterpret_cast<Ogre::RaySceneQuery *>(pRaySceneQuery);
    Ogre::Vector3 hitCoord, normal;
    Ogre::Entity* entity = Ogre::OpcodeManager::getSingleton().executeQuery(raySceneQuery, hitCoord, normal);
    if(entity == 0){
        return 0;
    }

    env->SetFloatArrayRegion( pointArray, 0, 3, hitCoord.ptr());
    env->SetFloatArrayRegion( normalArray, 0, 3, normal.ptr());
    return reinterpret_cast<jlong>(entity);
}
