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
#include "class_org_ogre4j_CompositionPassTranslator.h"

// import header files of original library
#include <OgreScriptTranslator.h>



/*
 * Class:      org.ogre4j.CompositionPassTranslator
 * Method:     CompositionPassTranslator()
 * Type:       constructor
 * Definition: Ogre::CompositionPassTranslator::CompositionPassTranslator
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionPassTranslator__1_1createCompositionPassTranslator (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::CompositionPassTranslator 
   
   // parameter conversions 
   
   // create new instance of class Ogre::CompositionPassTranslator 
   Ogre::CompositionPassTranslator* _cpp_this = new Ogre::CompositionPassTranslator(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::CompositionPassTranslator::CompositionPassTranslator */


/*
 * Class:      org.ogre4j.CompositionPassTranslator
 * Method:     translate()
 * Type:       virtual method
 * Definition: void Ogre::CompositionPassTranslator::translate
 * Signature:  (Ogre_ScriptCompilerOgre_AbstractNodePtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionPassTranslator__1translate_1_1ScriptCompilerpAbstractNodePtrR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong compiler, 
  jlong node
)
{
   // parameter conversions 
  Ogre::ScriptCompiler* _cpp_compiler = reinterpret_cast< Ogre::ScriptCompiler* >(compiler);
  const Ogre::AbstractNodePtr* _cpp_node = reinterpret_cast< const Ogre::AbstractNodePtr* >(node); 
   
   // cast pointer to C++ object 
   Ogre::CompositionPassTranslator* _cpp_this = reinterpret_cast<Ogre::CompositionPassTranslator*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->translate(_cpp_compiler, *_cpp_node);
} /* void Ogre::CompositionPassTranslator::translate */


/*
 * Class:      org.ogre4j.CompositionPassTranslator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::CompositionPassTranslator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionPassTranslator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::CompositionPassTranslator 
   // cast pointer to C++ object 
   Ogre::CompositionPassTranslator* _cpp_this = reinterpret_cast<Ogre::CompositionPassTranslator*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::CompositionPassTranslator::__delete */
