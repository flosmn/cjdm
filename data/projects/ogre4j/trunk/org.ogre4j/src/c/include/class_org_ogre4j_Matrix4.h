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


#ifndef __Included_org_ogre4j_Matrix4__
#define __Included_org_ogre4j_Matrix4__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     Matrix4()
 * Type:       constructor
 * Definition: Ogre::Matrix4::Matrix4
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1_1createMatrix4 (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     Matrix4()
 * Type:       constructor
 * Definition: Ogre::Matrix4::Matrix4
 * Signature:  (floatfloatfloatfloatfloatfloatfloatfloatfloatfloatfloatfloatfloatfloatfloatfloat)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1_1createMatrix4_1_1RealvRealvRealvRealvRealvRealvRealvRealvRealvRealvRealvRealvRealvRealvRealvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jfloat m00, 
  jfloat m01, 
  jfloat m02, 
  jfloat m03, 
  jfloat m10, 
  jfloat m11, 
  jfloat m12, 
  jfloat m13, 
  jfloat m20, 
  jfloat m21, 
  jfloat m22, 
  jfloat m23, 
  jfloat m30, 
  jfloat m31, 
  jfloat m32, 
  jfloat m33
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     Matrix4()
 * Type:       constructor
 * Definition: Ogre::Matrix4::Matrix4
 * Signature:  (Ogre_Matrix3)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1_1createMatrix4_1_1Matrix3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong m3x3
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     Matrix4()
 * Type:       constructor
 * Definition: Ogre::Matrix4::Matrix4
 * Signature:  (Ogre_Quaternion)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1_1createMatrix4_1_1QuaternionR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong rot
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator[]()
 * Type:       non-virtual method
 * Definition: Real* Ogre::Matrix4::operator[]
 * Signature:  (I)float
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorIndex_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint iRow
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator[]_const()
 * Type:       non-virtual method
 * Definition: const Real* const Ogre::Matrix4::operator[]
 * Signature:  (I)float
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorIndex_1const_1_1iv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint iRow
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     concatenate()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::concatenate
 * Signature:  (Ogre_Matrix4)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1concatenate_1_1Matrix4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m2
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator *()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::operator *
 * Signature:  (Ogre_Matrix4)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorMultiplication_1_1Matrix4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m2
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator *()
 * Type:       non-virtual method
 * Definition: Vector3 Ogre::Matrix4::operator *
 * Signature:  (Ogre_Vector3)Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorMultiplication_1_1Vector3R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator *()
 * Type:       non-virtual method
 * Definition: Vector4 Ogre::Matrix4::operator *
 * Signature:  (Ogre_Vector4)Ogre_Vector4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorMultiplication_1_1Vector4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator *()
 * Type:       non-virtual method
 * Definition: Plane Ogre::Matrix4::operator *
 * Signature:  (Ogre_Plane)Ogre_Plane
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorMultiplication_1_1PlaneR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong p
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator+()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::operator+
 * Signature:  (Ogre_Matrix4)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorAddition_1_1Matrix4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m2
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator-()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::operator-
 * Signature:  (Ogre_Matrix4)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorSubtraction_1_1Matrix4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m2
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator==()
 * Type:       non-virtual method
 * Definition: bool Ogre::Matrix4::operator==
 * Signature:  (Ogre_Matrix4)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Matrix4__1operatorEqual_1_1Matrix4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m2
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator!=()
 * Type:       non-virtual method
 * Definition: bool Ogre::Matrix4::operator!=
 * Signature:  (Ogre_Matrix4)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Matrix4__1operatorNotEqual_1_1Matrix4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m2
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::operator=
 * Signature:  (Ogre_Matrix3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1operatorAssignment_1_1Matrix3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong mat3
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     transpose()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::transpose
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1transpose_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     setTrans()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::setTrans
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1setTrans_1_1Vector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getTrans()
 * Type:       non-virtual method
 * Definition: Vector3 Ogre::Matrix4::getTrans
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getTrans_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     makeTrans()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::makeTrans
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1makeTrans_1_1Vector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     makeTrans()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::makeTrans
 * Signature:  (floatfloatfloat)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1makeTrans_1_1RealvRealvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat tx, 
  jfloat ty, 
  jfloat tz
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     setScale()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::setScale
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1setScale_1_1Vector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     extract3x3Matrix()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::extract3x3Matrix
 * Signature:  (Ogre_Matrix3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1extract3x3Matrix_1_1Matrix3r_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m3x3
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     hasScale()
 * Type:       non-virtual method
 * Definition: bool Ogre::Matrix4::hasScale
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Matrix4__1hasScale_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     hasNegativeScale()
 * Type:       non-virtual method
 * Definition: bool Ogre::Matrix4::hasNegativeScale
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Matrix4__1hasNegativeScale_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     extractQuaternion()
 * Type:       non-virtual method
 * Definition: Quaternion Ogre::Matrix4::extractQuaternion
 * Signature:  ()Ogre_Quaternion
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1extractQuaternion_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     operator *()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::operator *
 * Signature:  (float)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1operatorMultiplication_1_1Realv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat scalar
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     adjoint()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::adjoint
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1adjoint_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     determinant()
 * Type:       non-virtual method
 * Definition: Real Ogre::Matrix4::determinant
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_Matrix4__1determinant_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     inverse()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::inverse
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1inverse_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     makeTransform()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::makeTransform
 * Signature:  (Ogre_Vector3Ogre_Vector3Ogre_Quaternion)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1makeTransform_1_1Vector3RVector3RQuaternionR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong position, 
  jlong scale, 
  jlong orientation
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     makeInverseTransform()
 * Type:       non-virtual method
 * Definition: void Ogre::Matrix4::makeInverseTransform
 * Signature:  (Ogre_Vector3Ogre_Vector3Ogre_Quaternion)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1makeInverseTransform_1_1Vector3RVector3RQuaternionR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong position, 
  jlong scale, 
  jlong orientation
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     isAffine()
 * Type:       non-virtual method
 * Definition: bool Ogre::Matrix4::isAffine
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Matrix4__1isAffine_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     inverseAffine()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::inverseAffine
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1inverseAffine_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     concatenateAffine()
 * Type:       non-virtual method
 * Definition: Matrix4 Ogre::Matrix4::concatenateAffine
 * Signature:  (Ogre_Matrix4)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1concatenateAffine_1_1Matrix4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong m2
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     transformAffine()
 * Type:       non-virtual method
 * Definition: Vector3 Ogre::Matrix4::transformAffine
 * Signature:  (Ogre_Vector3)Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1transformAffine_1_1Vector3R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     transformAffine()
 * Type:       non-virtual method
 * Definition: Vector4 Ogre::Matrix4::transformAffine
 * Signature:  (Ogre_Vector4)Ogre_Vector4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1transformAffine_1_1Vector4R_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getTrans()
 * Type:       static method
 * Definition: static Matrix4 Ogre::Matrix4::getTrans
 * Signature:  (Ogre_Vector3)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getTrans_1_1Vector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getTrans()
 * Type:       static method
 * Definition: static Matrix4 Ogre::Matrix4::getTrans
 * Signature:  (floatfloatfloat)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getTrans_1_1RealvRealvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jfloat t_x, 
  jfloat t_y, 
  jfloat t_z
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getScale()
 * Type:       static method
 * Definition: static Matrix4 Ogre::Matrix4::getScale
 * Signature:  (Ogre_Vector3)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getScale_1_1Vector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getScale()
 * Type:       static method
 * Definition: static Matrix4 Ogre::Matrix4::getScale
 * Signature:  (floatfloatfloat)Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getScale_1_1RealvRealvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jfloat s_x, 
  jfloat s_y, 
  jfloat s_z
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Matrix4::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Matrix4__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);;;;;

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getZERO()
 * Type:       getter for public attribute
 * Definition: const Matrix4 Ogre::Matrix4::ZERO
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getZERO (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getIDENTITY()
 * Type:       getter for public attribute
 * Definition: const Matrix4 Ogre::Matrix4::IDENTITY
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getIDENTITY (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Matrix4
 * Method:     getCLIPSPACE2DTOIMAGESPACE()
 * Type:       getter for public attribute
 * Definition: const Matrix4 Ogre::Matrix4::CLIPSPACE2DTOIMAGESPACE
 * Signature:  ()Ogre_Matrix4
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Matrix4__1getCLIPSPACE2DTOIMAGESPACE (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_Matrix4__*/
