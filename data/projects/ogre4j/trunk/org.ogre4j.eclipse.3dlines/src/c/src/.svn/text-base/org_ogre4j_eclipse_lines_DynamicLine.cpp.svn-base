#include <Ogre.h>
#include "DynamicLine.h"
#include "org_ogre4j_eclipse_lines_DynamicLine.h"

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    __createDynamicLine
* Signature: (J)V
*/
JNIEXPORT jlong JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1_1createDynamicLine
(JNIEnv *, jclass)
{
	Ogre4j::DynamicLine* ptr = new Ogre4j::DynamicLine();
	return reinterpret_cast<jlong>(ptr);
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    __delete
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1_1delete
(JNIEnv *, jobject, jlong _jni_pointer_)
{
	const Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<const Ogre4j::DynamicLine*>(_jni_pointer_); 
	delete _cpp_this;
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _addPoint__fff
* Signature: (JFFF)V
*/
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1addPoint_1_1fff
(JNIEnv *, jobject, jlong _jni_pointer_, jfloat x, jfloat y, jfloat z)
{
	Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<Ogre4j::DynamicLine*>(_jni_pointer_); 
	_cpp_this->addPoint(x, y, z);
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _addPoint__vector
* Signature: (JJ)V
*/
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1addPoint_1_1vector
(JNIEnv *, jobject, jlong _jni_pointer_, jlong jni_vector)
{
	const Ogre::Vector3* cpp_vector = reinterpret_cast<const Ogre::Vector3*>(jni_vector); 
	Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<Ogre4j::DynamicLine*>(_jni_pointer_); 
	_cpp_this->addPoint(*cpp_vector);
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _clear
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1clear
(JNIEnv *, jobject, jlong _jni_pointer_)
{
	Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<Ogre4j::DynamicLine*>(_jni_pointer_); 
	_cpp_this->clear();
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _getNumPoints
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1getNumPoints
(JNIEnv *, jobject, jlong _jni_pointer_)
{
	const Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<const Ogre4j::DynamicLine*>(_jni_pointer_); 
	return _cpp_this->getNumPoints();
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _getOperationType
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1getOperationType
(JNIEnv *, jobject, jlong _jni_pointer_)
{
	const Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<const Ogre4j::DynamicLine*>(_jni_pointer_); 
	return _cpp_this->getOperationType();
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _getPoint
* Signature: (JI)J
*/
JNIEXPORT jlong JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1getPoint
(JNIEnv *, jobject, jlong _jni_pointer_, jint index)
{
	const Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<const Ogre4j::DynamicLine*>(_jni_pointer_); 
	const Ogre::Vector3& result = _cpp_this->getPoint(index);
	return reinterpret_cast<jlong>(&result);
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _getSquaredViewDepth
* Signature: (JJ)F
*/
JNIEXPORT jfloat JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1getSquaredViewDepth
(JNIEnv *, jobject, jlong _jni_pointer_, jlong jni_cam)
{
	const Ogre::Camera* cpp_cam = reinterpret_cast<const Ogre::Camera*>(jni_cam); 
	const Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<const Ogre4j::DynamicLine*>(_jni_pointer_); 
	return _cpp_this->getSquaredViewDepth(cpp_cam);
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _initialize
* Signature: (JIZ)V
*/
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1initialize
(JNIEnv *, jobject, jlong _jni_pointer_, jint operationType, jboolean useIndices)
{
	Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<Ogre4j::DynamicLine*>(_jni_pointer_); 
	_cpp_this->initialize(
		(Ogre::RenderOperation::OperationType)operationType, 
		useIndices ? true : false
		);
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _setPoint
* Signature: (JIJ)V
*/
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1setPoint
(JNIEnv *, jobject, jlong _jni_pointer_, jint index, jlong jni_value)
{
	const Ogre::Vector3* cpp_value = reinterpret_cast<const Ogre::Vector3*>(jni_value); 
	Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<Ogre4j::DynamicLine*>(_jni_pointer_); 
	_cpp_this->setPoint(index, *cpp_value);
}

/*
* Class:     org_ogre4j_eclipse_lines_DynamicLine
* Method:    _update
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_org_ogre4j_eclipse_lines_DynamicLine__1update
(JNIEnv *, jobject, jlong _jni_pointer_)
{
	Ogre4j::DynamicLine* _cpp_this = reinterpret_cast<Ogre4j::DynamicLine*>(_jni_pointer_); 
	_cpp_this->update();
}
