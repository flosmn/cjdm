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
#include "class_org_ogre4j_ResourceBackgroundQueue.h"

// import header files of original library
#include <OgreResourceBackgroundQueue.h>



/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     ResourceBackgroundQueue()
 * Type:       constructor
 * Definition: Ogre::ResourceBackgroundQueue::ResourceBackgroundQueue
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1_1createResourceBackgroundQueue (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::ResourceBackgroundQueue 
   
   // parameter conversions 
   
   // create new instance of class Ogre::ResourceBackgroundQueue 
   Ogre::ResourceBackgroundQueue* _cpp_this = new Ogre::ResourceBackgroundQueue(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::ResourceBackgroundQueue::ResourceBackgroundQueue */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     setStartBackgroundThread()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceBackgroundQueue::setStartBackgroundThread
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1setStartBackgroundThread_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean startThread
)
{
   // parameter conversions 
  bool _cpp_startThread = startThread ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setStartBackgroundThread(_cpp_startThread);
} /* void Ogre::ResourceBackgroundQueue::setStartBackgroundThread */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     getStartBackgroundThread()
 * Type:       non-virtual method
 * Definition: bool Ogre::ResourceBackgroundQueue::getStartBackgroundThread
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1getStartBackgroundThread (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->getStartBackgroundThread() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::ResourceBackgroundQueue::getStartBackgroundThread */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     initialise()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceBackgroundQueue::initialise
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1initialise (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->initialise();
} /* virtual void Ogre::ResourceBackgroundQueue::initialise */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     shutdown()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceBackgroundQueue::shutdown
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1shutdown (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->shutdown();
} /* virtual void Ogre::ResourceBackgroundQueue::shutdown */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     initialiseResourceGroup()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::initialiseResourceGroup
 * Signature:  (std_stringOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1initialiseResourceGroup_1_1StringRListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->initialiseResourceGroup(_cpp_name, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::initialiseResourceGroup */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     initialiseAllResourceGroups()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::initialiseAllResourceGroups
 * Signature:  (Ogre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1initialiseAllResourceGroups_1_1Listenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
)
{
   // parameter conversions 
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->initialiseAllResourceGroups(_cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::initialiseAllResourceGroups */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     prepareResourceGroup()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::prepareResourceGroup
 * Signature:  (std_stringOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1prepareResourceGroup_1_1StringRListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->prepareResourceGroup(_cpp_name, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::prepareResourceGroup */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     loadResourceGroup()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::loadResourceGroup
 * Signature:  (std_stringOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1loadResourceGroup_1_1StringRListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->loadResourceGroup(_cpp_name, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::loadResourceGroup */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     unload()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::unload
 * Signature:  (std_stringstd_stringOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1unload_1_1StringRStringRListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resType, 
  jstring name, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_resType = ""; org::xbig::jni::to_stdstring(_jni_env_, resType, _cpp_resType); // calls c-tor only. Not operator= .;
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->unload(_cpp_resType, _cpp_name, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::unload */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     unload()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::unload
 * Signature:  (std_stringunsigned_longOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1unload_1_1StringRResourceHandlevListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resType, 
  jlong handle, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_resType = ""; org::xbig::jni::to_stdstring(_jni_env_, resType, _cpp_resType); // calls c-tor only. Not operator= .;
  unsigned long _cpp_handle = handle;
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->unload(_cpp_resType, _cpp_handle, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::unload */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     unloadResourceGroup()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::unloadResourceGroup
 * Signature:  (std_stringOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1unloadResourceGroup_1_1StringRListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->unloadResourceGroup(_cpp_name, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::unloadResourceGroup */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     prepare()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::prepare
 * Signature:  (std_stringstd_stringstd_stringZOgre_ManualResourceLoaderOgre_NameValuePairListOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1prepare_1_1StringRStringRStringRbvManualResourceLoaderpNameValuePairListPListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resType, 
  jstring name, 
  jstring group, 
  jboolean isManual, 
  jlong loader, 
  jlong loadParams, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_resType = ""; org::xbig::jni::to_stdstring(_jni_env_, resType, _cpp_resType); // calls c-tor only. Not operator= .;
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  std::string _cpp_group = ""; org::xbig::jni::to_stdstring(_jni_env_, group, _cpp_group); // calls c-tor only. Not operator= .;
  bool _cpp_isManual = isManual ? true : false;
  Ogre::ManualResourceLoader* _cpp_loader = reinterpret_cast< Ogre::ManualResourceLoader* >(loader);
  const Ogre::NameValuePairList* _cpp_loadParams = reinterpret_cast< const Ogre::NameValuePairList* >(loadParams);
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->prepare(_cpp_resType, _cpp_name, _cpp_group, _cpp_isManual, _cpp_loader, _cpp_loadParams, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::prepare */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     load()
 * Type:       virtual method
 * Definition: virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::load
 * Signature:  (std_stringstd_stringstd_stringZOgre_ManualResourceLoaderOgre_NameValuePairListOgre_ResourceBackgroundQueue_Listener)unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1load_1_1StringRStringRStringRbvManualResourceLoaderpNameValuePairListPListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resType, 
  jstring name, 
  jstring group, 
  jboolean isManual, 
  jlong loader, 
  jlong loadParams, 
  jlong listener
)
{
   // parameter conversions 
  std::string _cpp_resType = ""; org::xbig::jni::to_stdstring(_jni_env_, resType, _cpp_resType); // calls c-tor only. Not operator= .;
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  std::string _cpp_group = ""; org::xbig::jni::to_stdstring(_jni_env_, group, _cpp_group); // calls c-tor only. Not operator= .;
  bool _cpp_isManual = isManual ? true : false;
  Ogre::ManualResourceLoader* _cpp_loader = reinterpret_cast< Ogre::ManualResourceLoader* >(loader);
  const Ogre::NameValuePairList* _cpp_loadParams = reinterpret_cast< const Ogre::NameValuePairList* >(loadParams);
  Ogre::ResourceBackgroundQueue::Listener* _cpp_listener = reinterpret_cast< Ogre::ResourceBackgroundQueue::Listener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->load(_cpp_resType, _cpp_name, _cpp_group, _cpp_isManual, _cpp_loader, _cpp_loadParams, _cpp_listener) ; 
   return _cpp_result;
} /* virtual BackgroundProcessTicket Ogre::ResourceBackgroundQueue::load */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     isProcessComplete()
 * Type:       virtual method
 * Definition: virtual bool Ogre::ResourceBackgroundQueue::isProcessComplete
 * Signature:  (unsigned_long)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1isProcessComplete_1_1BackgroundProcessTicketv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong ticket
)
{
   // parameter conversions 
  unsigned long _cpp_ticket = ticket; 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->isProcessComplete(_cpp_ticket) ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::ResourceBackgroundQueue::isProcessComplete */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     _doNextQueuedBackgroundProcess()
 * Type:       non-virtual method
 * Definition: bool Ogre::ResourceBackgroundQueue::_doNextQueuedBackgroundProcess
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1_1doNextQueuedBackgroundProcess (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->_doNextQueuedBackgroundProcess() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::ResourceBackgroundQueue::_doNextQueuedBackgroundProcess */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     _initThread()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceBackgroundQueue::_initThread
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1_1initThread (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_initThread();
} /* void Ogre::ResourceBackgroundQueue::_initThread */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     _queueFireBackgroundPreparingComplete()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceBackgroundQueue::_queueFireBackgroundPreparingComplete
 * Signature:  (Ogre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1_1queueFireBackgroundPreparingComplete_1_1Resourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong res
)
{
   // parameter conversions 
  Ogre::Resource* _cpp_res = reinterpret_cast< Ogre::Resource* >(res); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_queueFireBackgroundPreparingComplete(_cpp_res);
} /* virtual void Ogre::ResourceBackgroundQueue::_queueFireBackgroundPreparingComplete */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     _queueFireBackgroundLoadingComplete()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceBackgroundQueue::_queueFireBackgroundLoadingComplete
 * Signature:  (Ogre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1_1queueFireBackgroundLoadingComplete_1_1Resourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong res
)
{
   // parameter conversions 
  Ogre::Resource* _cpp_res = reinterpret_cast< Ogre::Resource* >(res); 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_queueFireBackgroundLoadingComplete(_cpp_res);
} /* virtual void Ogre::ResourceBackgroundQueue::_queueFireBackgroundLoadingComplete */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     _fireOnFrameCallbacks()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceBackgroundQueue::_fireOnFrameCallbacks
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1_1fireOnFrameCallbacks (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_fireOnFrameCallbacks();
} /* virtual void Ogre::ResourceBackgroundQueue::_fireOnFrameCallbacks */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     getSingleton()
 * Type:       static method
 * Definition: static ResourceBackgroundQueue& Ogre::ResourceBackgroundQueue::getSingleton
 * Signature:  ()Ogre_ResourceBackgroundQueue
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1getSingleton (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // static method of class Ogre::ResourceBackgroundQueue with return value 
   // parameter conversions 
   
   // call library method 
   const Ogre::ResourceBackgroundQueue* _cpp_result = & Ogre::ResourceBackgroundQueue::getSingleton(); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* static ResourceBackgroundQueue& Ogre::ResourceBackgroundQueue::getSingleton */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     getSingletonPtr()
 * Type:       static method
 * Definition: static ResourceBackgroundQueue* Ogre::ResourceBackgroundQueue::getSingletonPtr
 * Signature:  ()Ogre_ResourceBackgroundQueue
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1getSingletonPtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // static method of class Ogre::ResourceBackgroundQueue with return value 
   // parameter conversions 
   
   // call library method 
   const Ogre::ResourceBackgroundQueue* _cpp_result = Ogre::ResourceBackgroundQueue::getSingletonPtr(); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* static ResourceBackgroundQueue* Ogre::ResourceBackgroundQueue::getSingletonPtr */


/*
 * Class:      org.ogre4j.ResourceBackgroundQueue
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ResourceBackgroundQueue::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceBackgroundQueue__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::ResourceBackgroundQueue 
   // cast pointer to C++ object 
   Ogre::ResourceBackgroundQueue* _cpp_this = reinterpret_cast<Ogre::ResourceBackgroundQueue*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::ResourceBackgroundQueue::__delete */
