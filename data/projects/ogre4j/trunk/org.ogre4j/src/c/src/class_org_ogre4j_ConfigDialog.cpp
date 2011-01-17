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


// includes from config
#include <Ogre4JStableHeaders.h>

// use base library for cpp2j
#include "jni_base_all.h"

// import declaration of all functions
#include "class_org_ogre4j_ConfigDialog.h"

// import header files of original library
//#include <OgreConfigDialogImp.h>



/*
 * Class:      org.ogre4j.ConfigDialog
 * Method:     ConfigDialog()
 * Type:       constructor
 * Definition: Ogre::ConfigDialog::ConfigDialog
 * Signature:  ()V
 */

//JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigDialog__1_1createConfigDialog (
//  JNIEnv* _jni_env_, /* interface pointer */
//  jclass _jni_class_  /* class pointer */
//)
//{
//   // constructor of class Ogre::ConfigDialog 
//   
//   // parameter conversions 
//   
//   // create new instance of class Ogre::ConfigDialog 
//   Ogre::ConfigDialog* _cpp_this = new Ogre::ConfigDialog(); 
//   
//   // return casted pointer 
//   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
//   return _jni_pointer_;
//} /* Ogre::ConfigDialog::ConfigDialog */


/*
 * Class:      org.ogre4j.ConfigDialog
 * Method:     display()
 * Type:       non-virtual method
 * Definition: bool Ogre::ConfigDialog::display
 * Signature:  ()Z
 */

//JNIEXPORT jboolean JNICALL Java_org_ogre4j_ConfigDialog__1display (
//  JNIEnv* _jni_env_, /* interface pointer */
//  jobject _jni_this_, /* Java object */
//  jlong _jni_pointer_ /* C++ pointer */
//)
//{
//   // parameter conversions 
//   
//   // cast pointer to C++ object 
//   Ogre::ConfigDialog* _cpp_this = reinterpret_cast<Ogre::ConfigDialog*>(_jni_pointer_); 
//   
//   // call library method 
//   const bool _cpp_result = _cpp_this->display() ; 
//   return _cpp_result ? 1 : 0;
//} /* bool Ogre::ConfigDialog::display */


/*
 * Class:      org.ogre4j.ConfigDialog
 * Method:     initialise()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigDialog::initialise
 * Signature:  ()V
 */

//JNIEXPORT void JNICALL Java_org_ogre4j_ConfigDialog__1initialise (
//  JNIEnv* _jni_env_, /* interface pointer */
//  jobject _jni_this_, /* Java object */
//  jlong _jni_pointer_ /* C++ pointer */
//)
//{
//   // parameter conversions 
//   
//   // cast pointer to C++ object 
//   Ogre::ConfigDialog* _cpp_this = reinterpret_cast<Ogre::ConfigDialog*>(_jni_pointer_); 
//   
//   // call library method 
//   _cpp_this->initialise();
//} /* void Ogre::ConfigDialog::initialise */


/*
 * Class:      org.ogre4j.ConfigDialog
 * Method:     run()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigDialog::run
 * Signature:  ()V
 */

//JNIEXPORT void JNICALL Java_org_ogre4j_ConfigDialog__1run (
//  JNIEnv* _jni_env_, /* interface pointer */
//  jobject _jni_this_, /* Java object */
//  jlong _jni_pointer_ /* C++ pointer */
//)
//{
//   // parameter conversions 
//   
//   // cast pointer to C++ object 
//   Ogre::ConfigDialog* _cpp_this = reinterpret_cast<Ogre::ConfigDialog*>(_jni_pointer_); 
//   
//   // call library method 
//   _cpp_this->run();
//} /* void Ogre::ConfigDialog::run */


/*
 * Class:      org.ogre4j.ConfigDialog
 * Method:     cancel()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigDialog::cancel
 * Signature:  ()V
 */

//JNIEXPORT void JNICALL Java_org_ogre4j_ConfigDialog__1cancel (
//  JNIEnv* _jni_env_, /* interface pointer */
//  jobject _jni_this_, /* Java object */
//  jlong _jni_pointer_ /* C++ pointer */
//)
//{
//   // parameter conversions 
//   
//   // cast pointer to C++ object 
//   Ogre::ConfigDialog* _cpp_this = reinterpret_cast<Ogre::ConfigDialog*>(_jni_pointer_); 
//   
//   // call library method 
//   _cpp_this->cancel();
//} /* void Ogre::ConfigDialog::cancel */


/*
 * Class:      org.ogre4j.ConfigDialog
 * Method:     windowEventHandler()
 * Type:       static method
 * Definition: static pascal OSStatus Ogre::ConfigDialog::windowEventHandler
 * Signature:  (EventHandlerCallRefEventRefV)pascal_OSStatus
 */

//JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigDialog__1windowEventHandler_1_1EventHandlerCallRefvEventRefvvp (
//  JNIEnv* _jni_env_, /* interface pointer */
//  jclass _jni_class_,  /* class pointer */
//  jlong aNextHandler, 
//  jlong aEvent, 
//  jlong aUserData
//)
//{
//   // static method of class Ogre::ConfigDialog with return value 
//   // parameter conversions 
//  EventHandlerCallRef _cpp_aNextHandler = aNextHandler;
//  EventRef _cpp_aEvent = aEvent;
//  void* _cpp_aUserData = reinterpret_cast<void*>(aUserData); 
//   
//   // call library method 
//   const pascal OSStatus _cpp_result = Ogre::ConfigDialog::windowEventHandler(_cpp_aNextHandler, _cpp_aEvent, _cpp_aUserData); 
//   return _cpp_result;
//} /* static pascal OSStatus Ogre::ConfigDialog::windowEventHandler */


/*
 * Class:      org.ogre4j.ConfigDialog
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ConfigDialog::__delete
 * Signature:  ()V
 */

//JNIEXPORT void JNICALL Java_org_ogre4j_ConfigDialog__1_1delete (
//  JNIEnv* _jni_env_, /* interface pointer */
//  jobject _jni_this_, /* Java object */
//  jlong _jni_pointer_ /* C++ pointer */
//)
//{
//   // destructor of class Ogre::ConfigDialog 
//   // cast pointer to C++ object 
//   Ogre::ConfigDialog* _cpp_this = reinterpret_cast<Ogre::ConfigDialog*>(_jni_pointer_); 
//   // delete object if it exists 
//   if(_cpp_this != NULL) delete _cpp_this;
//} /* Ogre::ConfigDialog::__delete */
