if(typeof dojo == "undefined"){

/**
* @file bootstrap1.js
*
* summary: First file that is loaded that 'bootstraps' the entire dojo library suite.
* note:  Must run before hostenv_*.js file.
*
* @author  Copyright 2004 Mark D. Anderson (mda@discerning.com)
* TODOC: should the copyright be changed to Dojo Foundation?
* @license Licensed under the Academic Free License 2.1 http://www.opensource.org/licenses/afl-2.1.php
*
* $Id: bootstrap1.js 6824 2006-12-06 09:34:32Z alex $
*/

// TODOC: HOW TO DOC THE BELOW?
// @global: djConfig
// summary:
//		Application code can set the global 'djConfig' prior to loading
//		the library to override certain global settings for how dojo works.
// description:  The variables that can be set are as follows:
//			- isDebug: false
//			- allowQueryConfig: false
//			- baseScriptUri: ""
//			- baseRelativePath: ""
//			- libraryScriptUri: ""
//			- iePreventClobber: false
//			- ieClobberMinimal: true
//			- locale: undefined
//			- extraLocale: undefined
//			- preventBackButtonFix: true
//			- searchIds: []
//			- parseWidgets: true
// TODOC: HOW TO DOC THESE VARIABLES?
// TODOC: IS THIS A COMPLETE LIST?
// note:
//		'djConfig' does not exist under 'dojo.*' so that it can be set before the
//		'dojo' variable exists.
// note:
//		Setting any of these variables *after* the library has loaded does nothing at all.
// TODOC: is this still true?  Release notes for 0.3 indicated they could be set after load.
//


//TODOC:  HOW TO DOC THIS?
// @global: dj_global
// summary:
//		an alias for the top-level global object in the host environment
//		(e.g., the window object in a browser).
// description:
//		Refer to 'dj_global' rather than referring to window to ensure your
//		code runs correctly in contexts other than web browsers (eg: Rhino on a server).
var dj_global = this;

//TODOC:  HOW TO DOC THIS?
// @global: dj_currentContext
// summary:
//		Private global context object. Where 'dj_global' always refers to the boot-time
//    global context, 'dj_currentContext' can be modified for temporary context shifting.
//    dojo.global() returns dj_currentContext.
// description:
//		Refer to dojo.global() rather than referring to dj_global to ensure your
//		code runs correctly in managed contexts.
var dj_currentContext = this;


// ****************************************************************
// global public utils
// TODOC: DO WE WANT TO NOTE THAT THESE ARE GLOBAL PUBLIC UTILS?
// ****************************************************************

function dj_undef(/*String*/ name, /*Object?*/ object){
	//summary: Returns true if 'name' is defined on 'object' (or globally if 'object' is null).
	//description: Note that 'defined' and 'exists' are not the same concept.
	return (typeof (object || dj_currentContext)[name] == "undefined");	// Boolean
}

// make sure djConfig is defined
if(dj_undef("djConfig", this)){
	var djConfig = {};
}

//TODOC:  HOW TO DOC THIS?
// dojo is the root variable of (almost all) our public symbols -- make sure it is defined.
if(dj_undef("dojo", this)){
	var dojo = {};
}

dojo.global = function(){
	// summary:
	//		return the current global context object
	//		(e.g., the window object in a browser).
	// description:
	//		Refer to 'dojo.global()' rather than referring to window to ensure your
	//		code runs correctly in contexts other than web browsers (eg: Rhino on a server).
	return dj_currentContext;
}

// Override locale setting, if specified
dojo.locale  = djConfig.locale;

//TODOC:  HOW TO DOC THIS?
dojo.version = {
	// summary: version number of this instance of dojo.
	major: 0, minor: 4, patch: 1, flag: "",
	revision: Number("$Rev: 763175 $".match(/[0-9]+/)[0]),
	toString: function(){
		with(dojo.version){
			return major + "." + minor + "." + patch + flag + " (" + revision + ")";	// String
		}
	}
}

dojo.evalProp = function(/*String*/ name, /*Object*/ object, /*Boolean?*/ create){
	// summary: Returns 'object[name]'.  If not defined and 'create' is true, will return a new Object.
	// description:
	//		Returns null if 'object[name]' is not defined and 'create' is not true.
	// 		Note: 'defined' and 'exists' are not the same concept.
	if((!object)||(!name)) return undefined; // undefined
	if(!dj_undef(name, object)) return object[name]; // mixed
	return (create ? (object[name]={}) : undefined);	// mixed
}

dojo.parseObjPath = function(/*String*/ path, /*Object?*/ context, /*Boolean?*/ create){
	// summary: Parse string path to an object, and return corresponding object reference and property name.
	// description:
	//		Returns an object with two properties, 'obj' and 'prop'.
	//		'obj[prop]' is the reference indicated by 'path'.
	// path: Path to an object, in the form "A.B.C".
	// context: Object to use as root of path.  Defaults to 'dojo.global()'.
	// create: If true, Objects will be created at any point along the 'path' that is undefined.
	var object = (context || dojo.global());
	var names = path.split('.');
	var prop = names.pop();
	for (var i=0,l=names.length;i<l && object;i++){
		object = dojo.evalProp(names[i], object, create);
	}
	return {obj: object, prop: prop};	// Object: {obj: Object, prop: String}
}

dojo.evalObjPath = function(/*String*/ path, /*Boolean?*/ create){
	// summary: Return the value of object at 'path' in the global scope, without using 'eval()'.
	// path: Path to an object, in the form "A.B.C".
	// create: If true, Objects will be created at any point along the 'path' that is undefined.
	if(typeof path != "string"){
		return dojo.global();
	}
	// fast path for no periods
	if(path.indexOf('.') == -1){
		return dojo.evalProp(path, dojo.global(), create);		// mixed
	}

	//MOW: old 'with' syntax was confusing and would throw an error if parseObjPath returned null.
	var ref = dojo.parseObjPath(path, dojo.global(), create);
	if(ref){
		return dojo.evalProp(ref.prop, ref.obj, create);	// mixed
	}
	return null;
}

dojo.errorToString = function(/*Error*/ exception){
	// summary: Return an exception's 'message', 'description' or text.

	// TODO: overriding Error.prototype.toString won't accomplish this?
 	// 		... since natively generated Error objects do not always reflect such things?
	if(!dj_undef("message", exception)){
		return exception.message;		// String
	}else if(!dj_undef("description", exception)){
		return exception.description;	// String
	}else{
		return exception;				// Error
	}
}

dojo.raise = function(/*String*/ message, /*Error?*/ exception){
	// summary: Common point for raising exceptions in Dojo to enable logging.
	//	Throws an error message with text of 'exception' if provided, or
	//	rethrows exception object.

	if(exception){
		message = message + ": "+dojo.errorToString(exception);
	}else{
		message = dojo.errorToString(message);
	}

	// print the message to the user if hostenv.println is defined
	try { if(djConfig.isDebug){ dojo.hostenv.println("FATAL exception raised: "+message); } } catch (e) {}

	throw exception || Error(message);
}

//Stub functions so things don't break.
//TODOC:  HOW TO DOC THESE?
dojo.debug = function(){};
dojo.debugShallow = function(obj){};
dojo.profile = { start: function(){}, end: function(){}, stop: function(){}, dump: function(){} };

function dj_eval(/*String*/ scriptFragment){
	// summary: Perform an evaluation in the global scope.  Use this rather than calling 'eval()' directly.
	// description: Placed in a separate function to minimize size of trapped evaluation context.
	// note:
	//	 - JSC eval() takes an optional second argument which can be 'unsafe'.
	//	 - Mozilla/SpiderMonkey eval() takes an optional second argument which is the
	//  	 scope object for new symbols.
	return dj_global.eval ? dj_global.eval(scriptFragment) : eval(scriptFragment); 	// mixed
}

dojo.unimplemented = function(/*String*/ funcname, /*String?*/ extra){
	// summary: Throw an exception because some function is not implemented.
	// extra: Text to append to the exception message.
	var message = "'" + funcname + "' not implemented";
	if (extra != null) { message += " " + extra; }
	dojo.raise(message);
}

dojo.deprecated = function(/*String*/ behaviour, /*String?*/ extra, /*String?*/ removal){
	// summary: Log a debug message to indicate that a behavior has been deprecated.
	// extra: Text to append to the message.
	// removal: Text to indicate when in the future the behavior will be removed.
	var message = "DEPRECATED: " + behaviour;
	if(extra){ message += " " + extra; }
	if(removal){ message += " -- will be removed in version: " + removal; }
	dojo.debug(message);
}

dojo.render = (function(){
	//TODOC: HOW TO DOC THIS?
	// summary: Details rendering support, OS and browser of the current environment.
	// TODOC: is this something many folks will interact with?  If so, we should doc the structure created...
	function vscaffold(prefs, names){
		var tmp = {
			capable: false,
			support: {
				builtin: false,
				plugin: false
			},
			prefixes: prefs
		};
		for(var i=0; i<names.length; i++){
			tmp[names[i]] = false;
		}
		return tmp;
	}

	return {
		name: "",
		ver: dojo.version,
		os: { win: false, linux: false, osx: false },
		html: vscaffold(["html"], ["ie", "opera", "khtml", "safari", "moz"]),
		svg: vscaffold(["svg"], ["corel", "adobe", "batik"]),
		vml: vscaffold(["vml"], ["ie"]),
		swf: vscaffold(["Swf", "Flash", "Mm"], ["mm"]),
		swt: vscaffold(["Swt"], ["ibm"])
	};
})();

// ****************************************************************
// dojo.hostenv methods that must be defined in hostenv_*.js
// ****************************************************************

/**
 * The interface definining the interaction with the EcmaScript host environment.
*/

/*
 * None of these methods should ever be called directly by library users.
 * Instead public methods such as loadModule should be called instead.
 */
dojo.hostenv = (function(){
	// TODOC:  HOW TO DOC THIS?
	// summary: Provides encapsulation of behavior that changes across different 'host environments'
	//			(different browsers, server via Rhino, etc).
	// description: None of these methods should ever be called directly by library users.
	//				Use public methods such as 'loadModule' instead.

	// default configuration options
	var config = {
		isDebug: false,
		allowQueryConfig: false,
		baseScriptUri: "",
		baseRelativePath: "",
		libraryScriptUri: "",
		iePreventClobber: false,
		ieClobberMinimal: true,
		preventBackButtonFix: true,
		delayMozLoadingFix: false,
		searchIds: [],
		parseWidgets: true
	};

	if (typeof djConfig == "undefined") { djConfig = config; }
	else {
		for (var option in config) {
			if (typeof djConfig[option] == "undefined") {
				djConfig[option] = config[option];
			}
		}
	}

	return {
		name_: '(unset)',
		version_: '(unset)',


		getName: function(){
			// sumary: Return the name of the host environment.
			return this.name_; 	// String
		},


		getVersion: function(){
			// summary: Return the version of the hostenv.
			return this.version_; // String
		},

		getText: function(/*String*/ uri){
			// summary:	Read the plain/text contents at the specified 'uri'.
			// description:
			//			If 'getText()' is not implemented, then it is necessary to override
			//			'loadUri()' with an implementation that doesn't rely on it.

			dojo.unimplemented('getText', "uri=" + uri);
		}
	};
})();


dojo.hostenv.getBaseScriptUri = function(){
	// summary: Return the base script uri that other scripts are found relative to.
	// TODOC: HUH?  This comment means nothing to me.  What other scripts? Is this the path to other dojo libraries?
	//		MAYBE:  Return the base uri to scripts in the dojo library.	 ???
	// return: Empty string or a path ending in '/'.
	if(djConfig.baseScriptUri.length){
		return djConfig.baseScriptUri;
	}

	// MOW: Why not:
	//			uri = djConfig.libraryScriptUri || djConfig.baseRelativePath
	//		??? Why 'new String(...)'
	var uri = new String(djConfig.libraryScriptUri||djConfig.baseRelativePath);
	if (!uri) { dojo.raise("Nothing returned by getLibraryScriptUri(): " + uri); }

	// MOW: uri seems to not be actually used.  Seems to be hard-coding to djConfig.baseRelativePath... ???
	var lastslash = uri.lastIndexOf('/');		// MOW ???
	djConfig.baseScriptUri = djConfig.baseRelativePath;
	return djConfig.baseScriptUri;	// String
}

/*
 * loader.js - A bootstrap module.  Runs before the hostenv_*.js file. Contains all of the package loading methods.
 */

//A semi-colon is at the start of the line because after doing a build, this function definition
//get compressed onto the same line as the last line in bootstrap1.js. That list line is just a
//curly bracket, and the browser complains about that syntax. The semicolon fixes it. Putting it
//here instead of at the end of bootstrap1.js, since it is more of an issue for this file, (using
//the closure), and bootstrap1.js could change in the future.
;(function(){
	//Additional properties for dojo.hostenv
	var _addHostEnv = {
		pkgFileName: "__package__",

		// for recursion protection
		loading_modules_: {},
		loaded_modules_: {},
		addedToLoadingCount: [],
		removedFromLoadingCount: [],

		inFlightCount: 0,

		// FIXME: it should be possible to pull module prefixes in from djConfig
		modulePrefixes_: {
			dojo: {name: "dojo", value: "src"}
		},

		setModulePrefix: function(/*String*/module, /*String*/prefix){
			// summary: establishes module/prefix pair
			this.modulePrefixes_[module] = {name: module, value: prefix};
		},

		moduleHasPrefix: function(/*String*/module){
			// summary: checks to see if module has been established
			var mp = this.modulePrefixes_;
			return Boolean(mp[module] && mp[module].value); // Boolean
		},

		getModulePrefix: function(/*String*/module){
			// summary: gets the prefix associated with module
			if(this.moduleHasPrefix(module)){
				return this.modulePrefixes_[module].value; // String
			}
			return module; // String
		},

		getTextStack: [],
		loadUriStack: [],
		loadedUris: [],

		//WARNING: This variable is referenced by packages outside of bootstrap: FloatingPane.js and undo/browser.js
		post_load_: false,

		//Egad! Lots of test files push on this directly instead of using dojo.addOnLoad.
		modulesLoadedListeners: [],
		unloadListeners: [],
		loadNotifying: false
	};

	//Add all of these properties to dojo.hostenv
	for(var param in _addHostEnv){
		dojo.hostenv[param] = _addHostEnv[param];
	}
})();

dojo.hostenv.loadPath = function(/*String*/relpath, /*String?*/module, /*Function?*/cb){
// summary:
//	Load a Javascript module given a relative path
//
// description:
//	Loads and interprets the script located at relpath, which is relative to the
//	script root directory.  If the script is found but its interpretation causes
//	a runtime exception, that exception is not caught by us, so the caller will
//	see it.  We return a true value if and only if the script is found.
//
//	For now, we do not have an implementation of a true search path.  We
//	consider only the single base script uri, as returned by getBaseScriptUri().
//
// relpath: A relative path to a script (no leading '/', and typically
// 	ending in '.js').
// module: A module whose existance to check for after loading a path.
//	Can be used to determine success or failure of the load.
// cb: a callback function to pass the result of evaluating the script

	var uri;
	if(relpath.charAt(0) == '/' || relpath.match(/^\w+:/)){
		// dojo.raise("relpath '" + relpath + "'; must be relative");
		uri = relpath;
	}else{
		uri = this.getBaseScriptUri() + relpath;
	}
	if(djConfig.cacheBust && dojo.render.html.capable){
		uri += "?" + String(djConfig.cacheBust).replace(/\W+/g,"");
	}
	try{
		return !module ? this.loadUri(uri, cb) : this.loadUriAndCheck(uri, module, cb); // Boolean
	}catch(e){
		dojo.debug(e);
		return false; // Boolean
	}
}

dojo.hostenv.loadUri = function(/*String (URL)*/uri, /*Function?*/cb){
// summary:
//	Loads JavaScript from a URI
//
// description:
//	Reads the contents of the URI, and evaluates the contents.  This is used to load modules as well
//	as resource bundles.  Returns true if it succeeded. Returns false if the URI reading failed.
//	Throws if the evaluation throws.
//
// uri: a uri which points at the script to be loaded
// cb: a callback function to process the result of evaluating the script as an expression, typically
//	used by the resource bundle loader to load JSON-style resources

	if(this.loadedUris[uri]){
		return true; // Boolean
	}
	var contents = this.getText(uri, null, true);
	if(!contents){ return false; } // Boolean
	this.loadedUris[uri] = true;
	if(cb){ contents = '('+contents+')'; }
	var value = dj_eval(contents);
	if(cb){ cb(value); }
	return true; // Boolean
}

// FIXME: probably need to add logging to this method
dojo.hostenv.loadUriAndCheck = function(/*String (URL)*/uri, /*String*/moduleName, /*Function?*/cb){
	// summary: calls loadUri then findModule and returns true if both succeed
	var ok = true;
	try{
		ok = this.loadUri(uri, cb);
	}catch(e){
		dojo.debug("failed loading ", uri, " with error: ", e);
	}
	return Boolean(ok && this.findModule(moduleName, false)); // Boolean
}

dojo.loaded = function(){ }
dojo.unloaded = function(){ }

dojo.hostenv.loaded = function(){
	this.loadNotifying = true;
	this.post_load_ = true;
	var mll = this.modulesLoadedListeners;
	for(var x=0; x<mll.length; x++){
		mll[x]();
	}

	//Clear listeners so new ones can be added
	//For other xdomain package loads after the initial load.
	this.modulesLoadedListeners = [];
	this.loadNotifying = false;

	dojo.loaded();
}

dojo.hostenv.unloaded = function(){
	var mll = this.unloadListeners;
	while(mll.length){
		(mll.pop())();
	}
	dojo.unloaded();
}

dojo.addOnLoad = function(/*Object?*/obj, /*String|Function*/functionName) {
// summary:
//	Registers a function to be triggered after the DOM has finished loading
//	and widgets declared in markup have been instantiated.  Images and CSS files
//	may or may not have finished downloading when the specified function is called.
//	(Note that widgets' CSS and HTML code is guaranteed to be downloaded before said
//	widgets are instantiated.)
//
// usage:
//	dojo.addOnLoad(functionPointer)
//	dojo.addOnLoad(object, "functionName")

	var dh = dojo.hostenv;
	if(arguments.length == 1) {
		dh.modulesLoadedListeners.push(obj);
	} else if(arguments.length > 1) {
		dh.modulesLoadedListeners.push(function() {
			obj[functionName]();
		});
	}

	//Added for xdomain loading. dojo.addOnLoad is used to
	//indicate callbacks after doing some dojo.require() statements.
	//In the xdomain case, if all the requires are loaded (after initial
	//page load), then immediately call any listeners.
	if(dh.post_load_ && dh.inFlightCount == 0 && !dh.loadNotifying){
		dh.callLoaded();
	}
}

dojo.addOnUnload = function(/*Object?*/obj, /*String|Function?*/functionName){
// summary: registers a function to be triggered when the page unloads
//
// usage:
//	dojo.addOnLoad(functionPointer)
//	dojo.addOnLoad(object, "functionName")
	var dh = dojo.hostenv;
	if(arguments.length == 1){
		dh.unloadListeners.push(obj);
	} else if(arguments.length > 1) {
		dh.unloadListeners.push(function() {
			obj[functionName]();
		});
	}
}

dojo.hostenv.modulesLoaded = function(){
	if(this.post_load_){ return; }
	if(this.loadUriStack.length==0 && this.getTextStack.length==0){
		if(this.inFlightCount > 0){
			dojo.debug("files still in flight!");
			return;
		}
		dojo.hostenv.callLoaded();
	}
}

dojo.hostenv.callLoaded = function(){
	if(typeof setTimeout == "object"){
		setTimeout("dojo.hostenv.loaded();", 0);
	}else{
		dojo.hostenv.loaded();
	}
}

dojo.hostenv.getModuleSymbols = function(/*String*/modulename){
// summary:
//	Converts a module name in dotted JS notation to an array representing the path in the source tree
	var syms = modulename.split(".");
	for(var i = syms.length; i>0; i--){
		var parentModule = syms.slice(0, i).join(".");
		if((i==1) && !this.moduleHasPrefix(parentModule)){
			// Support default module directory (sibling of dojo) for top-level modules
			syms[0] = "../" + syms[0];
		}else{
			var parentModulePath = this.getModulePrefix(parentModule);
			if(parentModulePath != parentModule){
				syms.splice(0, i, parentModulePath);
				break;
			}
		}
	}
	return syms; // Array
}

dojo.hostenv._global_omit_module_check = false;
dojo.hostenv.loadModule = function(/*String*/moduleName, /*Boolean?*/exactOnly, /*Boolean?*/omitModuleCheck){
// summary:
//	loads a Javascript module from the appropriate URI
//
// description:
//	loadModule("A.B") first checks to see if symbol A.B is defined.
//	If it is, it is simply returned (nothing to do).
//
//	If it is not defined, it will look for "A/B.js" in the script root directory,
//	followed by "A.js".
//
//	It throws if it cannot find a file to load, or if the symbol A.B is not
//	defined after loading.
//
//	It returns the object A.B.
//
//	This does nothing about importing symbols into the current package.
//	It is presumed that the caller will take care of that. For example, to import
//	all symbols:
//
//	   with (dojo.hostenv.loadModule("A.B")) {
//	      ...
//	   }
//
//	And to import just the leaf symbol:
//
//	   var B = dojo.hostenv.loadModule("A.B");
//	   ...
//
//	dj_load is an alias for dojo.hostenv.loadModule

	if(!moduleName){ return; }
	omitModuleCheck = this._global_omit_module_check || omitModuleCheck;
	var module = this.findModule(moduleName, false);
	if(module){
		return module;
	}

	// protect against infinite recursion from mutual dependencies
	if(dj_undef(moduleName, this.loading_modules_)){
		this.addedToLoadingCount.push(moduleName);
	}
	this.loading_modules_[moduleName] = 1;

	// convert periods to slashes
	var relpath = moduleName.replace(/\./g, '/') + '.js';

	var nsyms = moduleName.split(".");

	// this line allowed loading of a module manifest as if it were a namespace
	// it's an interesting idea, but shouldn't be combined with 'namespaces' proper
	// and leads to unwanted dependencies
	// the effect can be achieved in other (albeit less-flexible) ways now, so I am
	// removing this pending further design work
	// perhaps we can explicitly define this idea of a 'module manifest', and subclass
	// 'namespace manifest' from that
	//dojo.getNamespace(nsyms[0]);

	var syms = this.getModuleSymbols(moduleName);
	var startedRelative = ((syms[0].charAt(0) != '/') && !syms[0].match(/^\w+:/));
	var last = syms[syms.length - 1];
	var ok;
	// figure out if we're looking for a full package, if so, we want to do
	// things slightly diffrently
	if(last=="*"){
		moduleName = nsyms.slice(0, -1).join('.');
		while(syms.length){
			syms.pop();
			syms.push(this.pkgFileName);
			relpath = syms.join("/") + '.js';
			if(startedRelative && relpath.charAt(0)=="/"){
				relpath = relpath.slice(1);
			}
			ok = this.loadPath(relpath, !omitModuleCheck ? moduleName : null);
			if(ok){ break; }
			syms.pop();
		}
	}else{
		relpath = syms.join("/") + '.js';
		moduleName = nsyms.join('.');
		var modArg = !omitModuleCheck ? moduleName : null;
		ok = this.loadPath(relpath, modArg);
		if(!ok && !exactOnly){
			syms.pop();
			while(syms.length){
				relpath = syms.join('/') + '.js';
				ok = this.loadPath(relpath, modArg);
				if(ok){ break; }
				syms.pop();
				relpath = syms.join('/') + '/'+this.pkgFileName+'.js';
				if(startedRelative && relpath.charAt(0)=="/"){
					relpath = relpath.slice(1);
				}
				ok = this.loadPath(relpath, modArg);
				if(ok){ break; }
			}
		}

		if(!ok && !omitModuleCheck){
			dojo.raise("Could not load '" + moduleName + "'; last tried '" + relpath + "'");
		}
	}

	// check that the symbol was defined
	//Don't bother if we're doing xdomain (asynchronous) loading.
	if(!omitModuleCheck && !this["isXDomain"]){
		// pass in false so we can give better error
		module = this.findModule(moduleName, false);
		if(!module){
			dojo.raise("symbol '" + moduleName + "' is not defined after loading '" + relpath + "'");
		}
	}

	return module;
}

dojo.hostenv.startPackage = function(/*String*/packageName){
// summary:
//	Creates a JavaScript package
//
// description:
//	startPackage("A.B") follows the path, and at each level creates a new empty
//	object or uses what already exists. It returns the result.
//
// packageName: the package to be created as a String in dot notation

	//Make sure we have a string.
	var fullPkgName = String(packageName);
	var strippedPkgName = fullPkgName;

	var syms = packageName.split(/\./);
	if(syms[syms.length-1]=="*"){
		syms.pop();
		strippedPkgName = syms.join(".");
	}
	var evaledPkg = dojo.evalObjPath(strippedPkgName, true);
	this.loaded_modules_[fullPkgName] = evaledPkg;
	this.loaded_modules_[strippedPkgName] = evaledPkg;

	return evaledPkg; // Object
}

dojo.hostenv.findModule = function(/*String*/moduleName, /*Boolean?*/mustExist){
// summary:
//	Returns the Object representing the module, if it exists, otherwise null.
//
// moduleName A fully qualified module including package name, like 'A.B'.
// mustExist Optional, default false. throw instead of returning null
//	if the module does not currently exist.

	var lmn = String(moduleName);

	if(this.loaded_modules_[lmn]){
		return this.loaded_modules_[lmn]; // Object
	}

	if(mustExist){
		dojo.raise("no loaded module named '" + moduleName + "'");
	}
	return null; // null
}

//Start of old bootstrap2:

dojo.kwCompoundRequire = function(/*Object containing Arrays*/modMap){
// description:
//	This method taks a "map" of arrays which one can use to optionally load dojo
//	modules. The map is indexed by the possible dojo.hostenv.name_ values, with
//	two additional values: "default" and "common". The items in the "default"
//	array will be loaded if none of the other items have been choosen based on
//	the hostenv.name_ item. The items in the "common" array will _always_ be
//	loaded, regardless of which list is chosen.  Here's how it's normally
//	called:
//
//	dojo.kwCompoundRequire({
//		browser: [
//			["foo.bar.baz", true, true], // an example that passes multiple args to loadModule()
//			"foo.sample.*",
//			"foo.test,
//		],
//		default: [ "foo.sample.*" ],
//		common: [ "really.important.module.*" ]
//	});

	var common = modMap["common"]||[];
	var result = modMap[dojo.hostenv.name_] ? common.concat(modMap[dojo.hostenv.name_]||[]) : common.concat(modMap["default"]||[]);

	for(var x=0; x<result.length; x++){
		var curr = result[x];
		if(curr.constructor == Array){
			dojo.hostenv.loadModule.apply(dojo.hostenv, curr);
		}else{
			dojo.hostenv.loadModule(curr);
		}
	}
}

dojo.require = function(/*String*/ resourceName){
	// summary
	//	Ensure that the given resource (ie, javascript
	//	source file) has been loaded.
	// description
	//	dojo.require() is similar to C's #include command or java's "import" command.
	//	You call dojo.require() to pull in the resources (ie, javascript source files)
	//	that define the functions you are using.
	//
	//	Note that in the case of a build, many resources have already been included
	//	into dojo.js (ie, many of the javascript source files have been compressed and
	//	concatened into dojo.js), so many dojo.require() calls will simply return
	//	without downloading anything.
	dojo.hostenv.loadModule.apply(dojo.hostenv, arguments);
}

dojo.requireIf = function(/*Boolean*/ condition, /*String*/ resourceName){
	// summary
	//	If the condition is true then call dojo.require() for the specified resource
	var arg0 = arguments[0];
	if((arg0 === true)||(arg0=="common")||(arg0 && dojo.render[arg0].capable)){
		var args = [];
		for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
		dojo.require.apply(dojo, args);
	}
}

dojo.requireAfterIf = dojo.requireIf;

dojo.provide = function(/*String*/ resourceName){
	// summary
	//	Each javascript source file must have (exactly) one dojo.provide()
	//	call at the top of the file, corresponding to the file name.
	//	For example, dojo/src/foo.js must have dojo.provide("dojo.foo"); at the top of the file.
	//
	// description
	//	Each javascript source file is called a resource.  When a resource
	//	is loaded by the browser, dojo.provide() registers that it has
	//	been loaded.
	//
	//	For backwards compatibility reasons, in addition to registering the resource,
	//	dojo.provide() also ensures that the javascript object for the module exists.  For
	//	example, dojo.provide("dojo.html.common"), in addition to registering that common.js
	//	is a resource for the dojo.html module, will ensure that the dojo.html javascript object
	//	exists, so that calls like dojo.html.foo = function(){ ... } don't fail.
	//
	//	In the case of a build (or in the future, a rollup), where multiple javascript source
	//	files are combined into one bigger file (similar to a .lib or .jar file), that file
	//	will contain multiple dojo.provide() calls, to note that it includes
	//	multiple resources.
	return dojo.hostenv.startPackage.apply(dojo.hostenv, arguments);
}

dojo.registerModulePath = function(/*String*/module, /*String*/prefix){
	// summary: maps a module name to a path
	// description: An unregistered module is given the default path of ../<module>,
	//	relative to Dojo root. For example, module acme is mapped to ../acme.
	//	If you want to use a different module name, use dojo.registerModulePath.
	return dojo.hostenv.setModulePrefix(module, prefix);
}

dojo.setModulePrefix = function(/*String*/module, /*String*/prefix){
	// summary: maps a module name to a path
	dojo.deprecated('dojo.setModulePrefix("' + module + '", "' + prefix + '")', "replaced by dojo.registerModulePath", "0.5");
	return dojo.registerModulePath(module, prefix);
}

dojo.exists = function(/*Object*/obj, /*String*/name){
	// summary: determine if an object supports a given method
	// description: useful for longer api chains where you have to test each object in the chain
	var p = name.split(".");
	for(var i = 0; i < p.length; i++){
		if(!obj[p[i]]){ return false; } // Boolean
		obj = obj[p[i]];
	}
	return true; // Boolean
}

// Localization routines

dojo.hostenv.normalizeLocale = function(/*String?*/locale){
//	summary:
//		Returns canonical form of locale, as used by Dojo.  All variants are case-insensitive and are separated by '-'
//		as specified in RFC 3066. If no locale is specified, the user agent's default is returned.

	var result = locale ? locale.toLowerCase() : dojo.locale;
	if(result == "root"){
		result = "ROOT";
	}
	return result;// String
};

dojo.hostenv.searchLocalePath = function(/*String*/locale, /*Boolean*/down, /*Function*/searchFunc){
//	summary:
//		A helper method to assist in searching for locale-based resources.  Will iterate through
//		the variants of a particular locale, either up or down, executing a callback function.
//		For example, "en-us" and true will try "en-us" followed by "en" and finally "ROOT".

	locale = dojo.hostenv.normalizeLocale(locale);

	var elements = locale.split('-');
	var searchlist = [];
	for(var i = elements.length; i > 0; i--){
		searchlist.push(elements.slice(0, i).join('-'));
	}
	searchlist.push(false);
	if(down){searchlist.reverse();}

	for(var j = searchlist.length - 1; j >= 0; j--){
		var loc = searchlist[j] || "ROOT";
		var stop = searchFunc(loc);
		if(stop){ break; }
	}
}

//These two functions are placed outside of preloadLocalizations
//So that the xd loading can use/override them.
dojo.hostenv.localesGenerated /***BUILD:localesGenerated***/; // value will be inserted here at build time, if necessary

dojo.hostenv.registerNlsPrefix = function(){
// summary:
//	Register module "nls" to point where Dojo can find pre-built localization files
	dojo.registerModulePath("nls","nls");
}

dojo.hostenv.preloadLocalizations = function(){
// summary:
//	Load built, flattened resource bundles, if available for all locales used in the page.
//	Execute only once.  Note that this is a no-op unless there is a build.

	if(dojo.hostenv.localesGenerated){
		dojo.hostenv.registerNlsPrefix();

		function preload(locale){
			locale = dojo.hostenv.normalizeLocale(locale);
			dojo.hostenv.searchLocalePath(locale, true, function(loc){
				for(var i=0; i<dojo.hostenv.localesGenerated.length;i++){
					if(dojo.hostenv.localesGenerated[i] == loc){
						dojo["require"]("nls.dojo_"+loc);
						return true; // Boolean
					}
				}
				return false; // Boolean
			});
		}
		preload();
		var extra = djConfig.extraLocale||[];
		for(var i=0; i<extra.length; i++){
			preload(extra[i]);
		}
	}
	dojo.hostenv.preloadLocalizations = function(){};
}

dojo.requireLocalization = function(/*String*/moduleName, /*String*/bundleName, /*String?*/locale, /*String?*/availableFlatLocales){
// summary:
//	Declares translated resources and loads them if necessary, in the same style as dojo.require.
//	Contents of the resource bundle are typically strings, but may be any name/value pair,
//	represented in JSON format.  See also dojo.i18n.getLocalization.
//
// moduleName: name of the package containing the "nls" directory in which the bundle is found
// bundleName: bundle name, i.e. the filename without the '.js' suffix
// locale: the locale to load (optional)  By default, the browser's user locale as defined by dojo.locale
// availableFlatLocales: A comma-separated list of the available, flattened locales for this bundle.
// This argument should only be set by the build process.
//
// description:
//	Load translated resource bundles provided underneath the "nls" directory within a package.
//	Translated resources may be located in different packages throughout the source tree.  For example,
//	a particular widget may define one or more resource bundles, structured in a program as follows,
//	where moduleName is mycode.mywidget and bundleNames available include bundleone and bundletwo:
//	...
//	mycode/
//	 mywidget/
//	  nls/
//	   bundleone.js (the fallback translation, English in this example)
//	   bundletwo.js (also a fallback translation)
//	   de/
//	    bundleone.js
//	    bundletwo.js
//	   de-at/
//	    bundleone.js
//	   en/
//	    (empty; use the fallback translation)
//	   en-us/
//	    bundleone.js
//	   en-gb/
//	    bundleone.js
//	   es/
//	    bundleone.js
//	    bundletwo.js
//	  ...etc
//	...
//	Each directory is named for a locale as specified by RFC 3066, (http://www.ietf.org/rfc/rfc3066.txt),
//	normalized in lowercase.  Note that the two bundles in the example do not define all the same variants.
//	For a given locale, bundles will be loaded for that locale and all more general locales above it, including
//	a fallback at the root directory.  For example, a declaration for the "de-at" locale will first
//	load nls/de-at/bundleone.js, then nls/de/bundleone.js and finally nls/bundleone.js.  The data will
//	be flattened into a single Object so that lookups will follow this cascading pattern.  An optional build
//	step can preload the bundles to avoid data redundancy and the multiple network hits normally required to
//	load these resources.

	dojo.hostenv.preloadLocalizations();
	var targetLocale = dojo.hostenv.normalizeLocale(locale);
 	var bundlePackage = [moduleName, "nls", bundleName].join(".");
//NOTE: When loading these resources, the packaging does not match what is on disk.  This is an
// implementation detail, as this is just a private data structure to hold the loaded resources.
// e.g. tests/hello/nls/en-us/salutations.js is loaded as the object tests.hello.nls.salutations.en_us={...}
// The structure on disk is intended to be most convenient for developers and translators, but in memory
// it is more logical and efficient to store in a different order.  Locales cannot use dashes, since the
// resulting path will not evaluate as valid JS, so we translate them to underscores.

	//Find the best-match locale to load if we have available flat locales.
	var bestLocale = "";
	if(availableFlatLocales){
		var flatLocales = availableFlatLocales.split(",");
		for(var i = 0; i < flatLocales.length; i++){
			//Locale must match from start of string.
			if(targetLocale.indexOf(flatLocales[i]) == 0){
				if(flatLocales[i].length > bestLocale.length){
					bestLocale = flatLocales[i];
				}
			}
		}
		if(!bestLocale){
			bestLocale = "ROOT";
		}
	}

	//See if the desired locale is already loaded.
	var tempLocale = availableFlatLocales ? bestLocale : targetLocale;
	var bundle = dojo.hostenv.findModule(bundlePackage);
	var localizedBundle = null;
	if(bundle){
		if(djConfig.localizationComplete && bundle._built){return;}
		var jsLoc = tempLocale.replace('-', '_');
		var translationPackage = bundlePackage+"."+jsLoc;
		localizedBundle = dojo.hostenv.findModule(translationPackage);
	}

	if(!localizedBundle){
		bundle = dojo.hostenv.startPackage(bundlePackage);
		var syms = dojo.hostenv.getModuleSymbols(moduleName);
		var modpath = syms.concat("nls").join("/");
		var parent;

		dojo.hostenv.searchLocalePath(tempLocale, availableFlatLocales, function(loc){
			var jsLoc = loc.replace('-', '_');
			var translationPackage = bundlePackage + "." + jsLoc;
			var loaded = false;
			if(!dojo.hostenv.findModule(translationPackage)){
				// Mark loaded whether it's found or not, so that further load attempts will not be made
				dojo.hostenv.startPackage(translationPackage);
				var module = [modpath];
				if(loc != "ROOT"){module.push(loc);}
				module.push(bundleName);
				var filespec = module.join("/") + '.js';
				loaded = dojo.hostenv.loadPath(filespec, null, function(hash){
					// Use singleton with prototype to point to parent bundle, then mix-in result from loadPath
					var clazz = function(){};
					clazz.prototype = parent;
					bundle[jsLoc] = new clazz();
					for(var j in hash){ bundle[jsLoc][j] = hash[j]; }
				});
			}else{
				loaded = true;
			}
			if(loaded && bundle[jsLoc]){
				parent = bundle[jsLoc];
			}else{
				bundle[jsLoc] = parent;
			}

			if(availableFlatLocales){
				//Stop the locale path searching if we know the availableFlatLocales, since
				//the first call to this function will load the only bundle that is needed.
				return true;
			}
		});
	}

	//Save the best locale bundle as the target locale bundle when we know the
	//the available bundles.
	if(availableFlatLocales && targetLocale != bestLocale){
		bundle[targetLocale.replace('-', '_')] = bundle[bestLocale.replace('-', '_')];
	}
};

(function(){
	// If other locales are used, dojo.requireLocalization should load them as well, by default.
	// Override dojo.requireLocalization to do load the default bundle, then iterate through the
	// extraLocale list and load those translations as well, unless a particular locale was requested.

	var extra = djConfig.extraLocale;
	if(extra){
		if(!extra instanceof Array){
			extra = [extra];
		}

		var req = dojo.requireLocalization;
		dojo.requireLocalization = function(m, b, locale, availableFlatLocales){
			req(m,b,locale, availableFlatLocales);
			if(locale){return;}
			for(var i=0; i<extra.length; i++){
				req(m,b,extra[i], availableFlatLocales);
			}
		};
	}
})();

};

if(typeof window != 'undefined'){

	// attempt to figure out the path to dojo if it isn't set in the config
	(function(){
		// before we get any further with the config options, try to pick them out
		// of the URL. Most of this code is from NW
		if(djConfig.allowQueryConfig){
			var baseUrl = document.location.toString(); // FIXME: use location.query instead?
			var params = baseUrl.split("?", 2);
			if(params.length > 1){
				var paramStr = params[1];
				var pairs = paramStr.split("&");
				for(var x in pairs){
					var sp = pairs[x].split("=");
					// FIXME: is this eval dangerous?
					if((sp[0].length > 9)&&(sp[0].substr(0, 9) == "djConfig.")){
						var opt = sp[0].substr(9);
						try{
							djConfig[opt]=eval(sp[1]);
						}catch(e){
							djConfig[opt]=sp[1];
						}
					}
				}
			}
		}

		if(
			((djConfig["baseScriptUri"] == "")||(djConfig["baseRelativePath"] == "")) &&
			(document && document.getElementsByTagName)
		){
			var scripts = document.getElementsByTagName("script");
			var rePkg = /(__package__|dojo|bootstrap1)\.js([\?\.]|$)/i;
			for(var i = 0; i < scripts.length; i++) {
				var src = scripts[i].getAttribute("src");
				if(!src) { continue; }
				var m = src.match(rePkg);
				if(m) {
					var root = src.substring(0, m.index);
					if(src.indexOf("bootstrap1") > -1) { root += "../"; }
					if(!this["djConfig"]) { djConfig = {}; }
					if(djConfig["baseScriptUri"] == "") { djConfig["baseScriptUri"] = root; }
					if(djConfig["baseRelativePath"] == "") { djConfig["baseRelativePath"] = root; }
					break;
				}
			}
		}

		// fill in the rendering support information in dojo.render.*
		var dr = dojo.render;
		var drh = dojo.render.html;
		var drs = dojo.render.svg;
		var dua = (drh.UA = navigator.userAgent);
		var dav = (drh.AV = navigator.appVersion);
		var t = true;
		var f = false;
		drh.capable = t;
		drh.support.builtin = t;

		dr.ver = parseFloat(drh.AV);
		dr.os.mac = dav.indexOf("Macintosh") >= 0;
		dr.os.win = dav.indexOf("Windows") >= 0;
		// could also be Solaris or something, but it's the same browser
		dr.os.linux = dav.indexOf("X11") >= 0;

		drh.opera = dua.indexOf("Opera") >= 0;
		drh.khtml = (dav.indexOf("Konqueror") >= 0)||(dav.indexOf("Safari") >= 0);
		drh.safari = dav.indexOf("Safari") >= 0;
		var geckoPos = dua.indexOf("Gecko");
		drh.mozilla = drh.moz = (geckoPos >= 0)&&(!drh.khtml);
		if (drh.mozilla) {
			// gecko version is YYYYMMDD
			drh.geckoVersion = dua.substring(geckoPos + 6, geckoPos + 14);
		}
		drh.ie = (document.all)&&(!drh.opera);
		drh.ie50 = drh.ie && dav.indexOf("MSIE 5.0")>=0;
		drh.ie55 = drh.ie && dav.indexOf("MSIE 5.5")>=0;
		drh.ie60 = drh.ie && dav.indexOf("MSIE 6.0")>=0;
		drh.ie70 = drh.ie && dav.indexOf("MSIE 7.0")>=0;

		var cm = document["compatMode"];
		drh.quirks = (cm == "BackCompat")||(cm == "QuirksMode")||drh.ie55||drh.ie50;

		// TODO: is the HTML LANG attribute relevant?
		dojo.locale = dojo.locale || (drh.ie ? navigator.userLanguage : navigator.language).toLowerCase();

		dr.vml.capable=drh.ie;
		drs.capable = f;
		drs.support.plugin = f;
		drs.support.builtin = f;
		var tdoc = window["document"];
		var tdi = tdoc["implementation"];

		if((tdi)&&(tdi["hasFeature"])&&(tdi.hasFeature("org.w3c.dom.svg", "1.0"))){
			drs.capable = t;
			drs.support.builtin = t;
			drs.support.plugin = f;
		}
		// webkits after 420 support SVG natively. The test string is "AppleWebKit/420+"
		if(drh.safari){
			var tmp = dua.split("AppleWebKit/")[1];
			var ver = parseFloat(tmp.split(" ")[0]);
			if(ver >= 420){
				drs.capable = t;
				drs.support.builtin = t;
				drs.support.plugin = f;
			}
		}else{
		}
	})();

	dojo.hostenv.startPackage("dojo.hostenv");

	dojo.render.name = dojo.hostenv.name_ = 'browser';
	dojo.hostenv.searchIds = [];

	// These are in order of decreasing likelihood; this will change in time.
	dojo.hostenv._XMLHTTP_PROGIDS = ['Msxml2.XMLHTTP', 'Microsoft.XMLHTTP', 'Msxml2.XMLHTTP.4.0'];

	dojo.hostenv.getXmlhttpObject = function(){
		// summary: does the work of portably generating a new XMLHTTPRequest object.
		var http = null;
		var last_e = null;
		try{ http = new XMLHttpRequest(); }catch(e){}
		if(!http){
			for(var i=0; i<3; ++i){
				var progid = dojo.hostenv._XMLHTTP_PROGIDS[i];
				try{
					http = new ActiveXObject(progid);
				}catch(e){
					last_e = e;
				}

				if(http){
					dojo.hostenv._XMLHTTP_PROGIDS = [progid];  // so faster next time
					break;
				}
			}

			/*if(http && !http.toString) {
				http.toString = function() { "[object XMLHttpRequest]"; }
			}*/
		}

		if(!http){
			return dojo.raise("XMLHTTP not available", last_e);
		}

		return http; // XMLHTTPRequest instance
	}

	dojo.hostenv._blockAsync = false;
	dojo.hostenv.getText = function(uri, async_cb, fail_ok){
		// summary: Read the contents of the specified uri and return those contents.
		// uri:
		//		A relative or absolute uri. If absolute, it still must be in
		//		the same "domain" as we are.
		// async_cb:
		//		If not specified, load synchronously. If specified, load
		//		asynchronously, and use async_cb as the progress handler which
		//		takes the xmlhttp object as its argument. If async_cb, this
		//		function returns null.
		// fail_ok:
		//		Default false. If fail_ok and !async_cb and loading fails,
		//		return null instead of throwing.

		// need to block async callbacks from snatching this thread as the result
		// of an async callback might call another sync XHR, this hangs khtml forever
		// hostenv._blockAsync must also be checked in BrowserIO's watchInFlight()
		// NOTE: must be declared before scope switches ie. this.getXmlhttpObject()
		if(!async_cb){ this._blockAsync = true; }

		var http = this.getXmlhttpObject();

		function isDocumentOk(http){
			var stat = http["status"];
			// allow a 304 use cache, needed in konq (is this compliant with the http spec?)
			return Boolean((!stat)||((200 <= stat)&&(300 > stat))||(stat==304));
		}

		if(async_cb){
			var _this = this, timer = null, gbl = dojo.global();
			var xhr = dojo.evalObjPath("dojo.io.XMLHTTPTransport");
			http.onreadystatechange = function(){
				if(timer){ gbl.clearTimeout(timer); timer = null; }
				if(_this._blockAsync || (xhr && xhr._blockAsync)){
					timer = gbl.setTimeout(function () { http.onreadystatechange.apply(this); }, 10);
				}else{
					if(4==http.readyState){
						if(isDocumentOk(http)){
							// dojo.debug("LOADED URI: "+uri);
							async_cb(http.responseText);
						}
					}
				}
			}
		}

		http.open('GET', uri, async_cb ? true : false);
		try{
			http.send(null);
			if(async_cb){
				return null;
			}
			if(!isDocumentOk(http)){
				var err = Error("Unable to load "+uri+" status:"+ http.status);
				err.status = http.status;
				err.responseText = http.responseText;
				throw err;
			}
		}catch(e){
			this._blockAsync = false;
			if((fail_ok)&&(!async_cb)){
				return null;
			}else{
				throw e;
			}
		}

		this._blockAsync = false;
		return http.responseText; // String
	}

	dojo.hostenv.defaultDebugContainerId = 'dojoDebug';
	dojo.hostenv._println_buffer = [];
	dojo.hostenv._println_safe = false;
	dojo.hostenv.println = function(/*String*/line){
		// summary:
		//		prints the provided line to whatever logging container is
		//		available. If the page isn't loaded yet, the line may be added
		//		to a buffer for printing later.
		if(!dojo.hostenv._println_safe){
			dojo.hostenv._println_buffer.push(line);
		}else{
			try {
				var console = document.getElementById(djConfig.debugContainerId ?
					djConfig.debugContainerId : dojo.hostenv.defaultDebugContainerId);
				if(!console) { console = dojo.body(); }

				var div = document.createElement("div");
				div.appendChild(document.createTextNode(line));
				console.appendChild(div);
			} catch (e) {
				try{
					// safari needs the output wrapped in an element for some reason
					document.write("<div>" + line + "</div>");
				}catch(e2){
					window.status = line;
				}
			}
		}
	}

	dojo.addOnLoad(function(){
		dojo.hostenv._println_safe = true;
		while(dojo.hostenv._println_buffer.length > 0){
			dojo.hostenv.println(dojo.hostenv._println_buffer.shift());
		}
	});

	function dj_addNodeEvtHdlr(/*DomNode*/node, /*String*/evtName, /*Function*/fp){
		// summary:
		//		non-destructively adds the specified function to the node's
		//		evtName handler.
		// node: the DomNode to add the handler to
		// evtName: should be in the form "click" for "onclick" handlers
		var oldHandler = node["on"+evtName] || function(){};
		node["on"+evtName] = function(){
			fp.apply(node, arguments);
			oldHandler.apply(node, arguments);
		}
		return true;
	}

	//	BEGIN DOMContentLoaded, from Dean Edwards (http://dean.edwards.name/weblog/2006/06/again/)
	function dj_load_init(e){
		// allow multiple calls, only first one will take effect
		// A bug in khtml calls events callbacks for document for event which isnt supported
		// for example a created contextmenu event calls DOMContentLoaded, workaround
		var type = (e && e.type) ? e.type.toLowerCase() : "load";
		if(arguments.callee.initialized || (type!="domcontentloaded" && type!="load")){ return; }
		arguments.callee.initialized = true;
		if(typeof(_timer) != 'undefined'){
			clearInterval(_timer);
			delete _timer;
		}

		var initFunc = function(){
			//perform initialization
			if(dojo.render.html.ie){
				dojo.hostenv.makeWidgets();
			}
		};

		if(dojo.hostenv.inFlightCount == 0){
			initFunc();
			dojo.hostenv.modulesLoaded();
		}else{
			//This else case should be xdomain loading.
			//Make sure this is the first thing in the load listener array.
			//Part of the dojo.addOnLoad guarantee is that when the listeners are notified,
			//It means the DOM (or page) has loaded and that widgets have been parsed.
			dojo.hostenv.modulesLoadedListeners.unshift(initFunc);
		}
	}

	//	START DOMContentLoaded
	// Mozilla and Opera 9 expose the event we could use
	if(document.addEventListener){
		if(dojo.render.html.opera || (dojo.render.html.moz && !djConfig.delayMozLoadingFix)){
			document.addEventListener("DOMContentLoaded", dj_load_init, null);
		}

		//	mainly for Opera 8.5, won't be fired if DOMContentLoaded fired already.
		//  also used for Mozilla because of trac #1640
		window.addEventListener("load", dj_load_init, null);
	}

	// 	for Internet Explorer. readyState will not be achieved on init call, but dojo doesn't need it
	//	however, we'll include it because we don't know if there are other functions added that might.
	//	Note that this has changed because the build process strips all comments--including conditional
	//		ones.
	if(dojo.render.html.ie && dojo.render.os.win){
		document.attachEvent("onreadystatechange", function(e){
			if(document.readyState == "complete"){
				dj_load_init();
			}
		});
	}

	if (/(WebKit|khtml)/i.test(navigator.userAgent)) { // sniff
		var _timer = setInterval(function() {
			if (/loaded|complete/.test(document.readyState)) {
				dj_load_init(); // call the onload handler
			}
		}, 10);
	}
	//	END DOMContentLoaded

	// IE WebControl hosted in an application can fire "beforeunload" and "unload"
	// events when control visibility changes, causing Dojo to unload too soon. The
	// following code fixes the problem
	// Reference: http://support.microsoft.com/default.aspx?scid=kb;en-us;199155
	if(dojo.render.html.ie){
		dj_addNodeEvtHdlr(window, "beforeunload", function(){
			dojo.hostenv._unloading = true;
			window.setTimeout(function() {
				dojo.hostenv._unloading = false;
			}, 0);
		});
	}

	dj_addNodeEvtHdlr(window, "unload", function(){
		dojo.hostenv.unloaded();
		if((!dojo.render.html.ie)||(dojo.render.html.ie && dojo.hostenv._unloading)){
			dojo.hostenv.unloaded();
		}
	});

	dojo.hostenv.makeWidgets = function(){
		// you can put searchIds in djConfig and dojo.hostenv at the moment
		// we should probably eventually move to one or the other
		var sids = [];
		if(djConfig.searchIds && djConfig.searchIds.length > 0) {
			sids = sids.concat(djConfig.searchIds);
		}
		if(dojo.hostenv.searchIds && dojo.hostenv.searchIds.length > 0) {
			sids = sids.concat(dojo.hostenv.searchIds);
		}

		if((djConfig.parseWidgets)||(sids.length > 0)){
			if(dojo.evalObjPath("dojo.widget.Parse")){
				// we must do this on a delay to avoid:
				//	http://www.shaftek.org/blog/archives/000212.html
				// (IE bug)
					var parser = new dojo.xml.Parse();
					if(sids.length > 0){
						for(var x=0; x<sids.length; x++){
							var tmpNode = document.getElementById(sids[x]);
							if(!tmpNode){ continue; }
							var frag = parser.parseElement(tmpNode, null, true);
							dojo.widget.getParser().createComponents(frag);
						}
					}else if(djConfig.parseWidgets){
						var frag  = parser.parseElement(dojo.body(), null, true);
						dojo.widget.getParser().createComponents(frag);
					}
			}
		}
	}

	dojo.addOnLoad(function(){
		if(!dojo.render.html.ie) {
			dojo.hostenv.makeWidgets();
		}
	});

	try{
		if(dojo.render.html.ie){
			document.namespaces.add("v","urn:schemas-microsoft-com:vml");
			document.createStyleSheet().addRule("v\\:*", "behavior:url(#default#VML)");
		}
	}catch(e){ }

	// stub, over-ridden by debugging code. This will at least keep us from
	// breaking when it's not included
	dojo.hostenv.writeIncludes = function(){}

	//TODOC:  HOW TO DOC THIS?
	// @global: dj_currentDocument
	// summary:
	//		Current document object. 'dj_currentDocument' can be modified for temporary context shifting.
	// description:
	//    dojo.doc() returns dojo.currentDocument.
	//		Refer to dojo.doc() rather than referring to 'window.document' to ensure your
	//		code runs correctly in managed contexts.
	if(!dj_undef("document", this)){
		dj_currentDocument = this.document;
	}

	dojo.doc = function(){
		// summary:
		//		return the document object associated with the dojo.global()
		return dj_currentDocument;
	}

	dojo.body = function(){
		// summary:
		//		return the body object associated with dojo.doc()
		// Note: document.body is not defined for a strict xhtml document
		return dojo.doc().body || dojo.doc().getElementsByTagName("body")[0];
	}

	dojo.byId = function(/*String*/id, /*DocumentElement*/doc){
		// summary:
		// 		similar to other library's "$" function, takes a string
		// 		representing a DOM id or a DomNode and returns the
		// 		corresponding DomNode. If a Node is passed, this function is a
		// 		no-op. Returns a single DOM node or null, working around
		// 		several browser-specific bugs to do so.
		// id: DOM id or DOM Node
		// doc:
		//		optional, defaults to the current value of dj_currentDocument.
		//		Can be used to retreive node references from other documents.
		if((id)&&((typeof id == "string")||(id instanceof String))){
			if(!doc){ doc = dj_currentDocument; }
			var ele = doc.getElementById(id);
			// workaround bug in IE and Opera 8.2 where getElementById returns wrong element
			if(ele && (ele.id != id) && doc.all){
				ele = null;
				// get all matching elements with this id
				eles = doc.all[id];
				if(eles){
					// if more than 1, choose first with the correct id
					if(eles.length){
						for(var i=0; i<eles.length; i++){
							if(eles[i].id == id){
								ele = eles[i];
								break;
							}
						}
					// return 1 and only element
					}else{
						ele = eles;
					}
				}
			}
			return ele; // DomNode
		}
		return id; // DomNode
	}

	dojo.setContext = function(/*Object*/globalObject, /*DocumentElement*/globalDocument){
		// summary:
		//		changes the behavior of many core Dojo functions that deal with
		//		namespace and DOM lookup, changing them to work in a new global
		//		context. The varibles dj_currentContext and dj_currentDocument
		//		are modified as a result of calling this function.
		dj_currentContext = globalObject;
		dj_currentDocument = globalDocument;
	};

	dojo._fireCallback = function(callback, context, cbArguments){
		if((context)&&((typeof callback == "string")||(callback instanceof String))){
			callback=context[callback];
		}
		return (context ? callback.apply(context, cbArguments || [ ]) : callback());
	}

	dojo.withGlobal = function(/*Object*/globalObject, /*Function*/callback, /*Object?*/thisObject, /*Array?*/cbArguments){
		// summary:
		//		Call callback with globalObject as dojo.global() and globalObject.document
		//		as dojo.doc(). If provided, globalObject will be executed in the context of
		//		object thisObject
		// description:
		//		When callback() returns or throws an error, the dojo.global() and dojo.doc() will
		//		be restored to its previous state.
		var rval;
		var oldGlob = dj_currentContext;
		var oldDoc = dj_currentDocument;
		try{
			dojo.setContext(globalObject, globalObject.document);
			rval = dojo._fireCallback(callback, thisObject, cbArguments);
		}finally{
			dojo.setContext(oldGlob, oldDoc);
		}
		return rval;
	}

	dojo.withDoc = function (/*Object*/documentObject, /*Function*/callback, /*Object?*/thisObject, /*Array?*/cbArguments) {
		// summary:
		//		Call callback with documentObject as dojo.doc(). If provided, callback will be executed
		//		in the context of object thisObject
		// description:
		//		When callback() returns or throws an error, the dojo.doc() will
		//		be restored to its previous state.
		var rval;
		var oldDoc = dj_currentDocument;
		try{
			dj_currentDocument = documentObject;
			rval = dojo._fireCallback(callback, thisObject, cbArguments);
		}finally{
			dj_currentDocument = oldDoc;
		}
		return rval;
	}

} //if (typeof window != 'undefined')

//Semicolon is for when this file is integrated with a custom build on one line
//with some other file's contents. Sometimes that makes things not get defined
//properly, particularly with the using the closure below to do all the work.
;(function(){
	//Don't do this work if dojo.js has already done it.
	if(typeof dj_usingBootstrap != "undefined"){
		return;
	}

	var isRhino = false;
	var isSpidermonkey = false;
	var isDashboard = false;
	if((typeof this["load"] == "function")&&((typeof this["Packages"] == "function")||(typeof this["Packages"] == "object"))){
		isRhino = true;
	}else if(typeof this["load"] == "function"){
		isSpidermonkey  = true;
	}else if(window.widget){
		isDashboard = true;
	}

	var tmps = [];
	if((this["djConfig"])&&((djConfig["isDebug"])||(djConfig["debugAtAllCosts"]))){
		tmps.push("debug.js");
	}

	if((this["djConfig"])&&(djConfig["debugAtAllCosts"])&&(!isRhino)&&(!isDashboard)){
		tmps.push("browser_debug.js");
	}

	var loaderRoot = djConfig["baseScriptUri"];
	if((this["djConfig"])&&(djConfig["baseLoaderUri"])){
		loaderRoot = djConfig["baseLoaderUri"];
	}

	for(var x=0; x < tmps.length; x++){
		var spath = loaderRoot+"src/"+tmps[x];
		if(isRhino||isSpidermonkey){
			load(spath);
		} else {
			try {
				document.write("<scr"+"ipt type='text/javascript' src='"+spath+"'></scr"+"ipt>");
			} catch (e) {
				var script = document.createElement("script");
				script.src = spath;
				document.getElementsByTagName("head")[0].appendChild(script);
			}
		}
	}
})();

dojo.provide("dojo.dom");

dojo.dom.ELEMENT_NODE                  = 1;
dojo.dom.ATTRIBUTE_NODE                = 2;
dojo.dom.TEXT_NODE                     = 3;
dojo.dom.CDATA_SECTION_NODE            = 4;
dojo.dom.ENTITY_REFERENCE_NODE         = 5;
dojo.dom.ENTITY_NODE                   = 6;
dojo.dom.PROCESSING_INSTRUCTION_NODE   = 7;
dojo.dom.COMMENT_NODE                  = 8;
dojo.dom.DOCUMENT_NODE                 = 9;
dojo.dom.DOCUMENT_TYPE_NODE            = 10;
dojo.dom.DOCUMENT_FRAGMENT_NODE        = 11;
dojo.dom.NOTATION_NODE                 = 12;

dojo.dom.dojoml = "http://www.dojotoolkit.org/2004/dojoml";

/**
 *	comprehensive list of XML namespaces
**/
dojo.dom.xmlns = {
	//	summary
	//	aliases for various common XML namespaces
	svg : "http://www.w3.org/2000/svg",
	smil : "http://www.w3.org/2001/SMIL20/",
	mml : "http://www.w3.org/1998/Math/MathML",
	cml : "http://www.xml-cml.org",
	xlink : "http://www.w3.org/1999/xlink",
	xhtml : "http://www.w3.org/1999/xhtml",
	xul : "http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul",
	xbl : "http://www.mozilla.org/xbl",
	fo : "http://www.w3.org/1999/XSL/Format",
	xsl : "http://www.w3.org/1999/XSL/Transform",
	xslt : "http://www.w3.org/1999/XSL/Transform",
	xi : "http://www.w3.org/2001/XInclude",
	xforms : "http://www.w3.org/2002/01/xforms",
	saxon : "http://icl.com/saxon",
	xalan : "http://xml.apache.org/xslt",
	xsd : "http://www.w3.org/2001/XMLSchema",
	dt: "http://www.w3.org/2001/XMLSchema-datatypes",
	xsi : "http://www.w3.org/2001/XMLSchema-instance",
	rdf : "http://www.w3.org/1999/02/22-rdf-syntax-ns#",
	rdfs : "http://www.w3.org/2000/01/rdf-schema#",
	dc : "http://purl.org/dc/elements/1.1/",
	dcq: "http://purl.org/dc/qualifiers/1.0",
	"soap-env" : "http://schemas.xmlsoap.org/soap/envelope/",
	wsdl : "http://schemas.xmlsoap.org/wsdl/",
	AdobeExtensions : "http://ns.adobe.com/AdobeSVGViewerExtensions/3.0/"
};

dojo.dom.isNode = function(/* object */wh){
	//	summary:
	//		checks to see if wh is actually a node.
	if(typeof Element == "function") {
		try {
			return wh instanceof Element;	//	boolean
		} catch(e) {}
	} else {
		// best-guess
		return wh && !isNaN(wh.nodeType);	//	boolean
	}
}

dojo.dom.getUniqueId = function(){
	//	summary:
	//		returns a unique string for use with any DOM element
	var _document = dojo.doc();
	do {
		var id = "dj_unique_" + (++arguments.callee._idIncrement);
	}while(_document.getElementById(id));
	return id;	//	string
}
dojo.dom.getUniqueId._idIncrement = 0;

dojo.dom.firstElement = dojo.dom.getFirstChildElement = function(/* Element */parentNode, /* string? */tagName){
	//	summary:
	//		returns the first child element matching tagName
	var node = parentNode.firstChild;
	while(node && node.nodeType != dojo.dom.ELEMENT_NODE){
		node = node.nextSibling;
	}
	if(tagName && node && node.tagName && node.tagName.toLowerCase() != tagName.toLowerCase()) {
		node = dojo.dom.nextElement(node, tagName);
	}
	return node;	//	Element
}

dojo.dom.lastElement = dojo.dom.getLastChildElement = function(/* Element */parentNode, /* string? */tagName){
	//	summary:
	//		returns the last child element matching tagName
	var node = parentNode.lastChild;
	while(node && node.nodeType != dojo.dom.ELEMENT_NODE) {
		node = node.previousSibling;
	}
	if(tagName && node && node.tagName && node.tagName.toLowerCase() != tagName.toLowerCase()) {
		node = dojo.dom.prevElement(node, tagName);
	}
	return node;	//	Element
}

dojo.dom.nextElement = dojo.dom.getNextSiblingElement = function(/* Node */node, /* string? */tagName){
	//	summary:
	//		returns the next sibling element matching tagName
	if(!node) { return null; }
	do {
		node = node.nextSibling;
	} while(node && node.nodeType != dojo.dom.ELEMENT_NODE);

	if(node && tagName && tagName.toLowerCase() != node.tagName.toLowerCase()) {
		return dojo.dom.nextElement(node, tagName);
	}
	return node;	//	Element
}

dojo.dom.prevElement = dojo.dom.getPreviousSiblingElement = function(/* Node */node, /* string? */tagName){
	//	summary:
	//		returns the previous sibling element matching tagName
	if(!node) { return null; }
	if(tagName) { tagName = tagName.toLowerCase(); }
	do {
		node = node.previousSibling;
	} while(node && node.nodeType != dojo.dom.ELEMENT_NODE);

	if(node && tagName && tagName.toLowerCase() != node.tagName.toLowerCase()) {
		return dojo.dom.prevElement(node, tagName);
	}
	return node;	//	Element
}

// TODO: hmph
/*this.forEachChildTag = function(node, unaryFunc) {
	var child = this.getFirstChildTag(node);
	while(child) {
		if(unaryFunc(child) == "break") { break; }
		child = this.getNextSiblingTag(child);
	}
}*/

dojo.dom.moveChildren = function(/*Element*/srcNode, /*Element*/destNode, /*boolean?*/trim){
	//	summary:
	//		Moves children from srcNode to destNode and returns the count of
	//		children moved; will trim off text nodes if trim == true
	var count = 0;
	if(trim) {
		while(srcNode.hasChildNodes() &&
			srcNode.firstChild.nodeType == dojo.dom.TEXT_NODE) {
			srcNode.removeChild(srcNode.firstChild);
		}
		while(srcNode.hasChildNodes() &&
			srcNode.lastChild.nodeType == dojo.dom.TEXT_NODE) {
			srcNode.removeChild(srcNode.lastChild);
		}
	}
	while(srcNode.hasChildNodes()){
		destNode.appendChild(srcNode.firstChild);
		count++;
	}
	return count;	//	number
}

dojo.dom.copyChildren = function(/*Element*/srcNode, /*Element*/destNode, /*boolean?*/trim){
	//	summary:
	//		Copies children from srcNde to destNode and returns the count of
	//		children copied; will trim off text nodes if trim == true
	var clonedNode = srcNode.cloneNode(true);
	return this.moveChildren(clonedNode, destNode, trim);	//	number
}

dojo.dom.replaceChildren = function(/*Element*/node, /*Node*/newChild){
	//	summary:
	//		Removes all children of node and appends newChild. All the existing
	//		children will be destroyed.
	// FIXME: what if newChild is an array-like object?
	var nodes = [];
	if(dojo.render.html.ie){
		for(var i=0;i<node.childNodes.length;i++){
			nodes.push(node.childNodes[i]);
		}
	}
	dojo.dom.removeChildren(node);
	node.appendChild(newChild);
	for(var i=0;i<nodes.length;i++){
		dojo.dom.destroyNode(nodes[i]);
	}
}

dojo.dom.removeChildren = function(/*Element*/node){
	//	summary:
	//		removes all children from node and returns the count of children removed.
	//		The children nodes are not destroyed. Be sure to call destroyNode on them
	//		after they are not used anymore.
	var count = node.childNodes.length;
	while(node.hasChildNodes()){ dojo.dom.removeNode(node.firstChild); }
	return count; // int
}

dojo.dom.replaceNode = function(/*Element*/node, /*Element*/newNode){
	//	summary:
	//		replaces node with newNode and returns a reference to the removed node.
	//		To prevent IE memory leak, call destroyNode on the returned node when
	//		it is no longer needed.
	return node.parentNode.replaceChild(newNode, node); // Node
}

dojo.dom.destroyNode = function(/*Node*/node){
	// summary:
	//		destroy a node (it can not be used any more). For IE, this is the
	//		right function to call to prevent memory leaks. While for other
	//		browsers, this is identical to dojo.dom.removeNode
	if(node.parentNode){
		node = dojo.dom.removeNode(node);
	}
	if(node.nodeType != 3){ // ingore TEXT_NODE
		if(dojo.evalObjPath("dojo.event.browser.clean", false)){
			dojo.event.browser.clean(node);
		}
		if(dojo.render.html.ie){
			node.outerHTML=''; //prevent ugly IE mem leak associated with Node.removeChild (ticket #1727)
		}
	}
}

dojo.dom.removeNode = function(/*Node*/node){
	// summary:
	//		if node has a parent, removes node from parent and returns a
	//		reference to the removed child.
	//		To prevent IE memory leak, call destroyNode on the returned node when
	//		it is no longer needed.
	//	node:
	//		the node to remove from its parent.

	if(node && node.parentNode){
		// return a ref to the removed child
		return node.parentNode.removeChild(node); //Node
	}
}

dojo.dom.getAncestors = function(/*Node*/node, /*function?*/filterFunction, /*boolean?*/returnFirstHit){
	//	summary:
	//		returns all ancestors matching optional filterFunction; will return
	//		only the first if returnFirstHit
	var ancestors = [];
	var isFunction = (filterFunction && (filterFunction instanceof Function || typeof filterFunction == "function"));
	while(node){
		if(!isFunction || filterFunction(node)){
			ancestors.push(node);
		}
		if(returnFirstHit && ancestors.length > 0){
			return ancestors[0]; 	//	Node
		}

		node = node.parentNode;
	}
	if(returnFirstHit){ return null; }
	return ancestors;	//	array
}

dojo.dom.getAncestorsByTag = function(/*Node*/node, /*String*/tag, /*boolean?*/returnFirstHit){
	//	summary:
	//		returns all ancestors matching tag (as tagName), will only return
	//		first one if returnFirstHit
	tag = tag.toLowerCase();
	return dojo.dom.getAncestors(node, function(el){
		return ((el.tagName)&&(el.tagName.toLowerCase() == tag));
	}, returnFirstHit);	//	Node || array
}

dojo.dom.getFirstAncestorByTag = function(/*Node*/node, /*string*/tag){
	//	summary:
	//		Returns first ancestor of node with tag tagName
	return dojo.dom.getAncestorsByTag(node, tag, true);	//	Node
}

dojo.dom.isDescendantOf = function(/* Node */node, /* Node */ancestor, /* boolean? */guaranteeDescendant){
	//	summary
	//	Returns boolean if node is a descendant of ancestor
	// guaranteeDescendant allows us to be a "true" isDescendantOf function
	if(guaranteeDescendant && node) { node = node.parentNode; }
	while(node) {
		if(node == ancestor){
			return true; 	//	boolean
		}
		node = node.parentNode;
	}
	return false;	//	boolean
}

dojo.dom.innerXML = function(/*Node*/node){
	//	summary:
	//		Implementation of MS's innerXML function.
	if(node.innerXML){
		return node.innerXML;	//	string
	}else if (node.xml){
		return node.xml;		//	string
	}else if(typeof XMLSerializer != "undefined"){
		return (new XMLSerializer()).serializeToString(node);	//	string
	}
}

dojo.dom.createDocument = function(){
	//	summary:
	//		cross-browser implementation of creating an XML document object.
	var doc = null;
	var _document = dojo.doc();

	if(!dj_undef("ActiveXObject")){
		var prefixes = [ "MSXML2", "Microsoft", "MSXML", "MSXML3" ];
		for(var i = 0; i<prefixes.length; i++){
			try{
				doc = new ActiveXObject(prefixes[i]+".XMLDOM");
			}catch(e){ /* squelch */ };

			if(doc){ break; }
		}
	}else if((_document.implementation)&&
		(_document.implementation.createDocument)){
		doc = _document.implementation.createDocument("", "", null);
	}

	return doc;	//	DOMDocument
}

dojo.dom.createDocumentFromText = function(/*string*/str, /*string?*/mimetype){
	//	summary:
	//		attempts to create a Document object based on optional mime-type,
	//		using str as the contents of the document
	if(!mimetype){ mimetype = "text/xml"; }
	if(!dj_undef("DOMParser")){
		var parser = new DOMParser();
		return parser.parseFromString(str, mimetype);	//	DOMDocument
	}else if(!dj_undef("ActiveXObject")){
		var domDoc = dojo.dom.createDocument();
		if(domDoc){
			domDoc.async = false;
			domDoc.loadXML(str);
			return domDoc;	//	DOMDocument
		}else{
			dojo.debug("toXml didn't work?");
		}
	/*
	}else if((dojo.render.html.capable)&&(dojo.render.html.safari)){
		// FIXME: this doesn't appear to work!
		// from: http://web-graphics.com/mtarchive/001606.php
		// var xml = '<?xml version="1.0"?>'+str;
		var mtype = "text/xml";
		var xml = '<?xml version="1.0"?>'+str;
		var url = "data:"+mtype+";charset=utf-8,"+encodeURIComponent(xml);
		var req = new XMLHttpRequest();
		req.open("GET", url, false);
		req.overrideMimeType(mtype);
		req.send(null);
		return req.responseXML;
	*/
	}else{
		var _document = dojo.doc();
		if(_document.createElement){
			// FIXME: this may change all tags to uppercase!
			var tmp = _document.createElement("xml");
			tmp.innerHTML = str;
			if(_document.implementation && _document.implementation.createDocument){
				var xmlDoc = _document.implementation.createDocument("foo", "", null);
				for(var i = 0; i < tmp.childNodes.length; i++) {
					xmlDoc.importNode(tmp.childNodes.item(i), true);
				}
				return xmlDoc;	//	DOMDocument
			}
			// FIXME: probably not a good idea to have to return an HTML fragment
			// FIXME: the tmp.doc.firstChild is as tested from IE, so it may not
			// work that way across the board
			return ((tmp.document)&&
				(tmp.document.firstChild ?  tmp.document.firstChild : tmp));	//	DOMDocument
		}
	}
	return null;
}

dojo.dom.prependChild = function(/*Element*/node, /*Element*/parent){
	//	summary:
	//		prepends node to parent's children nodes
	if(parent.firstChild) {
		parent.insertBefore(node, parent.firstChild);
	} else {
		parent.appendChild(node);
	}
	return true;	//	boolean
}

dojo.dom.insertBefore = function(/*Node*/node, /*Node*/ref, /*boolean?*/force){
	//	summary:
	//		Try to insert node before ref
	if(	(force != true)&&
		(node === ref || node.nextSibling === ref)){ return false; }
	var parent = ref.parentNode;
	parent.insertBefore(node, ref);
	return true;	//	boolean
}

dojo.dom.insertAfter = function(/*Node*/node, /*Node*/ref, /*boolean?*/force){
	//	summary:
	//		Try to insert node after ref
	var pn = ref.parentNode;
	if(ref == pn.lastChild){
		if((force != true)&&(node === ref)){
			return false;	//	boolean
		}
		pn.appendChild(node);
	}else{
		return this.insertBefore(node, ref.nextSibling, force);	//	boolean
	}
	return true;	//	boolean
}

dojo.dom.insertAtPosition = function(/*Node*/node, /*Node*/ref, /*string*/position){
	//	summary:
	//		attempt to insert node in relation to ref based on position
	if((!node)||(!ref)||(!position)){
		return false;	//	boolean
	}
	switch(position.toLowerCase()){
		case "before":
			return dojo.dom.insertBefore(node, ref);	//	boolean
		case "after":
			return dojo.dom.insertAfter(node, ref);		//	boolean
		case "first":
			if(ref.firstChild){
				return dojo.dom.insertBefore(node, ref.firstChild);	//	boolean
			}else{
				ref.appendChild(node);
				return true;	//	boolean
			}
			break;
		default: // aka: last
			ref.appendChild(node);
			return true;	//	boolean
	}
}

dojo.dom.insertAtIndex = function(/*Node*/node, /*Element*/containingNode, /*number*/insertionIndex){
	//	summary:
	//		insert node into child nodes nodelist of containingNode at
	//		insertionIndex. insertionIndex should be between 0 and
	//		the number of the childNodes in containingNode. insertionIndex
	//		specifys after how many childNodes in containingNode the node
	//		shall be inserted. If 0 is given, node will be appended to
	//		containingNode.
	var siblingNodes = containingNode.childNodes;

	// if there aren't any kids yet, just add it to the beginning

	if (!siblingNodes.length || siblingNodes.length == insertionIndex){
		containingNode.appendChild(node);
		return true;	//	boolean
	}

	if(insertionIndex == 0){
		return dojo.dom.prependChild(node, containingNode);	//	boolean
	}
	// otherwise we need to walk the childNodes
	// and find our spot

	return dojo.dom.insertAfter(node, siblingNodes[insertionIndex-1]);	//	boolean
}

dojo.dom.textContent = function(/*Node*/node, /*string*/text){
	//	summary:
	//		implementation of the DOM Level 3 attribute; scan node for text
	if (arguments.length>1) {
		var _document = dojo.doc();
		dojo.dom.replaceChildren(node, _document.createTextNode(text));
		return text;	//	string
	} else {
		if(node.textContent != undefined){ //FF 1.5
			return node.textContent;	//	string
		}
		var _result = "";
		if (node == null) { return _result; }
		for (var i = 0; i < node.childNodes.length; i++) {
			switch (node.childNodes[i].nodeType) {
				case 1: // ELEMENT_NODE
				case 5: // ENTITY_REFERENCE_NODE
					_result += dojo.dom.textContent(node.childNodes[i]);
					break;
				case 3: // TEXT_NODE
				case 2: // ATTRIBUTE_NODE
				case 4: // CDATA_SECTION_NODE
					_result += node.childNodes[i].nodeValue;
					break;
				default:
					break;
			}
		}
		return _result;	//	string
	}
}

dojo.dom.hasParent = function(/*Node*/node){
	//	summary:
	//		returns whether or not node is a child of another node.
	return Boolean(node && node.parentNode && dojo.dom.isNode(node.parentNode));	//	boolean
}

/**
 * Examples:
 *
 * myFooNode = <foo />
 * isTag(myFooNode, "foo"); // returns "foo"
 * isTag(myFooNode, "bar"); // returns ""
 * isTag(myFooNode, "FOO"); // returns ""
 * isTag(myFooNode, "hey", "foo", "bar"); // returns "foo"
**/
dojo.dom.isTag = function(/* Node */node /* ... */){
	//	summary:
	//		determines if node has any of the provided tag names and returns
	//		the tag name that matches, empty string otherwise.
	if(node && node.tagName) {
		for(var i=1; i<arguments.length; i++){
			if(node.tagName==String(arguments[i])){
				return String(arguments[i]);	//	string
			}
		}
	}
	return "";	//	string
}

dojo.dom.setAttributeNS = function(	/*Element*/elem, /*string*/namespaceURI,
									/*string*/attrName, /*string*/attrValue){
	//	summary:
	//		implementation of DOM2 setAttributeNS that works cross browser.
	if(elem == null || ((elem == undefined)&&(typeof elem == "undefined"))){
		dojo.raise("No element given to dojo.dom.setAttributeNS");
	}

	if(!((elem.setAttributeNS == undefined)&&(typeof elem.setAttributeNS == "undefined"))){ // w3c
		elem.setAttributeNS(namespaceURI, attrName, attrValue);
	}else{ // IE
		// get a root XML document
		var ownerDoc = elem.ownerDocument;
		var attribute = ownerDoc.createNode(
			2, // node type
			attrName,
			namespaceURI
		);

		// set value
		attribute.nodeValue = attrValue;

		// attach to element
		elem.setAttributeNode(attribute);
	}
}

dojo.provide("dojo.xml.Parse");

//TODO: determine dependencies
// currently has dependency on dojo.xml.DomUtil nodeTypes constants...

// using documentFragment nomenclature to generalize in case we don't want to require passing a collection of nodes with a single parent

dojo.xml.Parse = function(){
	// summary:
	//		generic class for taking a DOM node and parsing it into an object
	//		based on the "dojo tag name" of that node.
	//
	//		supported dojoTagName's:
	//			<prefix:tag> => prefix:tag
	//			<dojo:tag> => dojo:tag
	//			<dojoTag> => dojo:tag
	//			<tag dojoType="type"> => dojo:type
	//			<tag dojoType="prefix:type"> => prefix:type
	//			<tag dojo:type="type"> => dojo:type
	//			<tag class="classa dojo-type classb"> => dojo:type

	var isIE = ((dojo.render.html.capable)&&(dojo.render.html.ie));

	// get normalized (lowercase) tagName
	// some browsers report tagNames in lowercase no matter what
	function getTagName(node){
		/*
		return ((node)&&(node["tagName"]) ? node.tagName.toLowerCase() : '');
		*/
		try{
			return node.tagName.toLowerCase();
		}catch(e){
			return "";
		}
	}

	// locate dojo qualified tag name
	function getDojoTagName(node){
		var tagName = getTagName(node);
		if (!tagName){
				return '';
		}
		// any registered tag
		if((dojo.widget)&&(dojo.widget.tags[tagName])){
			return tagName;
		}
		// <prefix:tag> => prefix:tag
		var p = tagName.indexOf(":");
		if(p>=0){
			return tagName;
		}
		// <dojo:tag> => dojo:tag
		if(tagName.substr(0,5) == "dojo:"){
			return tagName;
		}
		if(dojo.render.html.capable && dojo.render.html.ie && node.scopeName != 'HTML'){
			return node.scopeName.toLowerCase() + ':' + tagName;
		}
		// <dojoTag> => dojo:tag
		if(tagName.substr(0,4) == "dojo"){
			// FIXME: this assumes tag names are always lower case
			return "dojo:" + tagName.substring(4);
		}
		// <tag dojoType="prefix:type"> => prefix:type
		// <tag dojoType="type"> => dojo:type
		var djt = node.getAttribute("dojoType") || node.getAttribute("dojotype");
		if(djt){
			if (djt.indexOf(":")<0){
				djt = "dojo:"+djt;
			}
			return djt.toLowerCase();
		}
		// <tag dojo:type="type"> => dojo:type
		djt = node.getAttributeNS && node.getAttributeNS(dojo.dom.dojoml,"type");
		if(djt){
			return "dojo:" + djt.toLowerCase();
		}
		// <tag dojo:type="type"> => dojo:type
		try{
			// FIXME: IE really really doesn't like this, so we squelch errors for it
			djt = node.getAttribute("dojo:type");
		}catch(e){
			// FIXME: log?
		}
		if(djt){ return "dojo:"+djt.toLowerCase(); }
		// <tag class="classa dojo-type classb"> => dojo:type
		if((dj_global["djConfig"])&&(!djConfig["ignoreClassNames"])){
			// FIXME: should we make this optionally enabled via djConfig?
			var classes = node.className||node.getAttribute("class");
			// FIXME: following line, without check for existence of classes.indexOf
			// breaks firefox 1.5's svg widgets
			if((classes )&&(classes.indexOf)&&(classes.indexOf("dojo-")!=-1)){
				var aclasses = classes.split(" ");
				for(var x=0, c=aclasses.length; x<c; x++){
					if(aclasses[x].slice(0, 5) == "dojo-"){
						return "dojo:"+aclasses[x].substr(5).toLowerCase();
					}
				}
			}
		}
		// no dojo-qualified name
		return '';
	}


	this.parseElement = function(	/*DomNode*/node,
									/*Boolean*/hasParentNodeSet,
									/*Boolean*/optimizeForDojoML,
									/*Integer*/thisIdx	){
		// summary:
		//		recursively parse the passed node, returning a normalized data
		//		structure that represents the "attributes of interest" of said
		//		elements. If optimizeForDojoML is true, only nodes that contain
		//		a "dojo tag name" will be inspected for attributes.
		// node: the DomNode to be treated as the root of inspection
		// hasParentNodeSet: no-op, please pass "null"
		// optimizeForDojoML: should we ignore non-Dojo nodes? Defaults to false.
		// thisIdx:
		//		a way to specify a synthetic "index" property in the resulting
		//		data structure. Otherwise the index property of the top-level
		//		return element is always "0".

		// TODOC: document return structure of a non-trivial element set

		// run shortcuts to bail out of processing up front to save time and
		// object alloc if possible.
		var tagName = getTagName(node);
		//There's a weird bug in IE where it counts end tags, e.g. </dojo:button> as nodes that should be parsed.  Ignore these
		if(isIE && tagName.indexOf("/")==0){ return null; }

		try{
			var attr = node.getAttribute("parseWidgets");
			if(attr && attr.toLowerCase() == "false"){
				return {};
			}
		}catch(e){/*continue*/}


		// look for a dojoml qualified name
		// process dojoml only when optimizeForDojoML is true
		var process = true;
		if(optimizeForDojoML){
			var dojoTagName = getDojoTagName(node);
			tagName = dojoTagName || tagName;
			process = Boolean(dojoTagName);
		}

		var parsedNodeSet = {};
		parsedNodeSet[tagName] = [];
		var pos = tagName.indexOf(":");
		if(pos>0){
			var ns = tagName.substring(0,pos);
			parsedNodeSet["ns"] = ns;
			// honor user namespace filters
			if((dojo.ns)&&(!dojo.ns.allow(ns))){process=false;}
		}

		if(process){
			var attributeSet = this.parseAttributes(node);
			for(var attr in attributeSet){
				if((!parsedNodeSet[tagName][attr])||(typeof parsedNodeSet[tagName][attr] != "array")){
					parsedNodeSet[tagName][attr] = [];
				}
				parsedNodeSet[tagName][attr].push(attributeSet[attr]);
			}
			// FIXME: we might want to make this optional or provide cloning instead of
			// referencing, but for now, we include a node reference to allow
			// instantiated components to figure out their "roots"
			parsedNodeSet[tagName].nodeRef = node;
			parsedNodeSet.tagName = tagName;
			parsedNodeSet.index = thisIdx||0;
		}

		var count = 0;
		for(var i = 0; i < node.childNodes.length; i++){
			var tcn = node.childNodes.item(i);
			switch(tcn.nodeType){
				case  dojo.dom.ELEMENT_NODE: // element nodes, call this function recursively
					var ctn = getDojoTagName(tcn) || getTagName(tcn);
					if(!parsedNodeSet[ctn]){
						parsedNodeSet[ctn] = [];
					}
					parsedNodeSet[ctn].push(this.parseElement(tcn, true, optimizeForDojoML, count));
					if(	(tcn.childNodes.length == 1)&&
						(tcn.childNodes.item(0).nodeType == dojo.dom.TEXT_NODE)){
						parsedNodeSet[ctn][parsedNodeSet[ctn].length-1].value = tcn.childNodes.item(0).nodeValue;
					}
					count++;
					break;
				case  dojo.dom.TEXT_NODE: // if a single text node is the child, treat it as an attribute
					if(node.childNodes.length == 1){
						parsedNodeSet[tagName].push({ value: node.childNodes.item(0).nodeValue });
					}
					break;
				default: break;
				/*
				case  dojo.dom.ATTRIBUTE_NODE: // attribute node... not meaningful here
					break;
				case  dojo.dom.CDATA_SECTION_NODE: // cdata section... not sure if this would ever be meaningful... might be...
					break;
				case  dojo.dom.ENTITY_REFERENCE_NODE: // entity reference node... not meaningful here
					break;
				case  dojo.dom.ENTITY_NODE: // entity node... not sure if this would ever be meaningful
					break;
				case  dojo.dom.PROCESSING_INSTRUCTION_NODE: // processing instruction node... not meaningful here
					break;
				case  dojo.dom.COMMENT_NODE: // comment node... not not sure if this would ever be meaningful
					break;
				case  dojo.dom.DOCUMENT_NODE: // document node... not sure if this would ever be meaningful
					break;
				case  dojo.dom.DOCUMENT_TYPE_NODE: // document type node... not meaningful here
					break;
				case  dojo.dom.DOCUMENT_FRAGMENT_NODE: // document fragment node... not meaningful here
					break;
				case  dojo.dom.NOTATION_NODE:// notation node... not meaningful here
					break;
				*/
			}
		}
		//return (hasParentNodeSet) ? parsedNodeSet[node.tagName] : parsedNodeSet;
		//if(parsedNodeSet.tagName)dojo.debug("parseElement: RETURNING NODE WITH TAGNAME "+parsedNodeSet.tagName);
		return parsedNodeSet;
	};


	/* parses a set of attributes on a node into an object tree */
	this.parseAttributes = function(/*DomNode*/node){
		// summary:
		// 		creates an attribute object that maps attribute values for the
		// 		passed node. Note that this is similar to creating a JSON
		// 		representation of a DOM node.
		// usage:
		//		a node with the following serialization:
		//			<div foo="bar" baz="thud">...</div>
		//		would yeild the following return structure when passed into this
		//		function:
		//			{
		//				"foo": {
		//					"value": "bar"
		//				},
		//				"baz": {
		//					"value": "thud"
		//				}
		//			}
		//
		var parsedAttributeSet = {};
		var atts = node.attributes;
		// TODO: should we allow for duplicate attributes at this point...
		// would any of the relevant dom implementations even allow this?
		var attnode, i=0;
		while((attnode=atts[i++])){
			if(isIE){
				if(!attnode){ continue; }
				if((typeof attnode == "object")&&
					(typeof attnode.nodeValue == 'undefined')||
					(attnode.nodeValue == null)||
					(attnode.nodeValue == '')){
					continue;
				}
			}

			var nn = attnode.nodeName.split(":");
			nn = (nn.length == 2) ? nn[1] : attnode.nodeName;

			parsedAttributeSet[nn] = {
				value: attnode.nodeValue
			};
		}
		return parsedAttributeSet;
	};
};

dojo.provide("dojo.lang.common");

dojo.lang.inherits = function(/*Function*/subclass, /*Function*/superclass){
	// summary: Set up inheritance between two classes.
	if(!dojo.lang.isFunction(superclass)){
		dojo.raise("dojo.inherits: superclass argument ["+superclass+"] must be a function (subclass: ["+subclass+"']");
	}
	subclass.prototype = new superclass();
	subclass.prototype.constructor = subclass;
	subclass.superclass = superclass.prototype;
	// DEPRECATED: super is a reserved word, use 'superclass'
	subclass['super'] = superclass.prototype;
}

dojo.lang._mixin = function(/*Object*/ obj, /*Object*/ props){
	// summary:
	//		Adds all properties and methods of props to obj. This addition is
	//		"prototype extension safe", so that instances of objects will not
	//		pass along prototype defaults.
	var tobj = {};
	for(var x in props){
		// the "tobj" condition avoid copying properties in "props"
		// inherited from Object.prototype.  For example, if obj has a custom
		// toString() method, don't overwrite it with the toString() method
		// that props inherited from Object.protoype
		if((typeof tobj[x] == "undefined") || (tobj[x] != props[x])){
			obj[x] = props[x];
		}
	}
	// IE doesn't recognize custom toStrings in for..in
	if(dojo.render.html.ie
		&& (typeof(props["toString"]) == "function")
		&& (props["toString"] != obj["toString"])
		&& (props["toString"] != tobj["toString"]))
	{
		obj.toString = props.toString;
	}
	return obj; // Object
}

dojo.lang.mixin = function(/*Object*/obj, /*Object...*/props){
	// summary:	Adds all properties and methods of props to obj.
	for(var i=1, l=arguments.length; i<l; i++){
		dojo.lang._mixin(obj, arguments[i]);
	}
	return obj; // Object
}

dojo.lang.extend = function(/*Object*/ constructor, /*Object...*/ props){
	// summary:
	//		Adds all properties and methods of props to constructor's
	//		prototype, making them available to all instances created with
	//		constructor.
	for(var i=1, l=arguments.length; i<l; i++){
		dojo.lang._mixin(constructor.prototype, arguments[i]);
	}
	return constructor; // Object
}

// Promote to dojo module
dojo.inherits = dojo.lang.inherits;
//dojo.lang._mixin = dojo.lang._mixin;
dojo.mixin = dojo.lang.mixin;
dojo.extend = dojo.lang.extend;

dojo.lang.find = function(	/*Array*/		array,
							/*Object*/		value,
							/*Boolean?*/	identity,
							/*Boolean?*/	findLast){
	// summary:
	//		Return the index of value in array, returning -1 if not found.
	// array: just what you think
	// value: the value to locate
	// identity:
	//		If true, matches with identity comparison (===). If false, uses
	//		normal comparison (==).
	// findLast:
	//		If true, returns index of last instance of value.
	// examples:
	//		find(array, value[, identity [findLast]]) // recommended
 	//		find(value, array[, identity [findLast]]) // deprecated

	// support both (array, value) and (value, array)
	if(!dojo.lang.isArrayLike(array) && dojo.lang.isArrayLike(value)) {
		dojo.deprecated('dojo.lang.find(value, array)', 'use dojo.lang.find(array, value) instead', "0.5");
		var temp = array;
		array = value;
		value = temp;
	}
	var isString = dojo.lang.isString(array);
	if(isString) { array = array.split(""); }

	if(findLast) {
		var step = -1;
		var i = array.length - 1;
		var end = -1;
	} else {
		var step = 1;
		var i = 0;
		var end = array.length;
	}
	if(identity){
		while(i != end) {
			if(array[i] === value){ return i; }
			i += step;
		}
	}else{
		while(i != end) {
			if(array[i] == value){ return i; }
			i += step;
		}
	}
	return -1;	// number
}

dojo.lang.indexOf = dojo.lang.find;

dojo.lang.findLast = function(/*Array*/array, /*Object*/value, /*boolean?*/identity){
	// summary:
	//		Return index of last occurance of value in array, returning -1 if
	//		not found. This is a shortcut for dojo.lang.find() with a true
	//		value for its "findLast" parameter.
	// identity:
	//		If true, matches with identity comparison (===). If false, uses
	//		normal comparison (==).
	return dojo.lang.find(array, value, identity, true); // number
}

dojo.lang.lastIndexOf = dojo.lang.findLast;

dojo.lang.inArray = function(array /*Array*/, value /*Object*/){
	// summary:	Return true if value is present in array.
	return dojo.lang.find(array, value) > -1; // boolean
}

/**
 * Partial implmentation of is* functions from
 * http://www.crockford.com/javascript/recommend.html
 * NOTE: some of these may not be the best thing to use in all situations
 * as they aren't part of core JS and therefore can't work in every case.
 * See WARNING messages inline for tips.
 *
 * The following is* functions are fairly "safe"
 */

dojo.lang.isObject = function(/*anything*/ it){
	// summary:	Return true if it is an Object, Array or Function.
	if(typeof it == "undefined"){ return false; }
	return (typeof it == "object" || it === null || dojo.lang.isArray(it) || dojo.lang.isFunction(it)); // Boolean
}

dojo.lang.isArray = function(/*anything*/ it){
	// summary:	Return true if it is an Array.
	return (it && it instanceof Array || typeof it == "array"); // Boolean
}

dojo.lang.isArrayLike = function(/*anything*/ it){
	// summary:
	//		Return true if it can be used as an array (i.e. is an object with
	//		an integer length property).
	if((!it)||(dojo.lang.isUndefined(it))){ return false; }
	if(dojo.lang.isString(it)){ return false; }
	if(dojo.lang.isFunction(it)){ return false; } // keeps out built-in constructors (Number, String, ...) which have length properties
	if(dojo.lang.isArray(it)){ return true; }
	// form node itself is ArrayLike, but not always iterable. Use form.elements instead.
	if((it.tagName)&&(it.tagName.toLowerCase()=='form')){ return false; }
	if(dojo.lang.isNumber(it.length) && isFinite(it.length)){ return true; }
	return false; // Boolean
}

dojo.lang.isFunction = function(/*anything*/ it){
	// summary:	Return true if it is a Function.
	return (it instanceof Function || typeof it == "function"); // Boolean
};

(function(){
	// webkit treats NodeList as a function, which is bad
	if((dojo.render.html.capable)&&(dojo.render.html["safari"])){
		dojo.lang.isFunction = function(/*anything*/ it){
			if((typeof(it) == "function") && (it == "[object NodeList]")) { return false; }
			return (it instanceof Function || typeof it == "function"); // Boolean
		}
	}
})();

dojo.lang.isString = function(/*anything*/ it){
	// summary:	Return true if it is a String.
	return (typeof it == "string" || it instanceof String);
}

dojo.lang.isAlien = function(/*anything*/ it){
	// summary: Return true if it is not a built-in function. False if not.
	if(!it){ return false; }
	return !dojo.lang.isFunction(it) && /\{\s*\[native code\]\s*\}/.test(String(it)); // Boolean
}

dojo.lang.isBoolean = function(/*anything*/ it){
	// summary:	Return true if it is a Boolean.
	return (it instanceof Boolean || typeof it == "boolean"); // Boolean
}

/**
 * The following is***() functions are somewhat "unsafe". Fortunately,
 * there are workarounds the the language provides and are mentioned
 * in the WARNING messages.
 *
 */
dojo.lang.isNumber = function(/*anything*/ it){
	// summary:	Return true if it is a number.
	// description:
	//		WARNING - In most cases, isNaN(it) is sufficient to determine whether or not
	// 		something is a number or can be used as such. For example, a number or string
	// 		can be used interchangably when accessing array items (array["1"] is the same as
	// 		array[1]) and isNaN will return false for both values ("1" and 1). However,
	// 		isNumber("1")  will return false, which is generally not too useful.
	// 		Also, isNumber(NaN) returns true, again, this isn't generally useful, but there
	// 		are corner cases (like when you want to make sure that two things are really
	// 		the same type of thing). That is really where isNumber "shines".
	//
	// Recommendation - Use isNaN(it) when possible

	return (it instanceof Number || typeof it == "number"); // Boolean
}

/*
 * FIXME: Should isUndefined go away since it is error prone?
 */
dojo.lang.isUndefined = function(/*anything*/ it){
	// summary: Return true if it is not defined.
	// description:
	//		WARNING - In some cases, isUndefined will not behave as you
	// 		might expect. If you do isUndefined(foo) and there is no earlier
	// 		reference to foo, an error will be thrown before isUndefined is
	// 		called. It behaves correctly if you scope yor object first, i.e.
	// 		isUndefined(foo.bar) where foo is an object and bar isn't a
	// 		property of the object.
	//
	// Recommendation - Use typeof foo == "undefined" when possible

	return ((typeof(it) == "undefined")&&(it == undefined)); // Boolean
}

// end Crockford functions

dojo.provide("dojo.lang.func");

dojo.lang.hitch = function(/*Object*/thisObject, /*Function|String*/method){
	// summary:
	//		Returns a function that will only ever execute in the a given scope
	//		(thisObject). This allows for easy use of object member functions
	//		in callbacks and other places in which the "this" keyword may
	//		otherwise not reference the expected scope. Note that the order of
	//		arguments may be reversed in a future version.
	// thisObject: the scope to run the method in
	// method:
	//		a function to be "bound" to thisObject or the name of the method in
	//		thisObject to be used as the basis for the binding
	// usage:
	//		dojo.lang.hitch(foo, "bar")(); // runs foo.bar() in the scope of foo
	//		dojo.lang.hitch(foo, myFunction); // returns a function that runs myFunction in the scope of foo

	// FIXME:
	//		should this be extended to "fixate" arguments in a manner similar
	//		to dojo.lang.curry, but without the default execution of curry()?
	var fcn = (dojo.lang.isString(method) ? thisObject[method] : method) || function(){};
	return function(){
		return fcn.apply(thisObject, arguments); // Function
	};
}

dojo.lang.anonCtr = 0;
dojo.lang.anon = {};

dojo.lang.nameAnonFunc = function(/*Function*/anonFuncPtr, /*Object*/thisObj, /*Boolean*/searchForNames){
	// summary:
	//		Creates a reference to anonFuncPtr in thisObj with a completely
	//		unique name. The new name is returned as a String.  If
	//		searchForNames is true, an effort will be made to locate an
	//		existing reference to anonFuncPtr in thisObj, and if one is found,
	//		the existing name will be returned instead. The default is for
	//		searchForNames to be false.
	var nso = (thisObj|| dojo.lang.anon);
	if( (searchForNames) ||
		((dj_global["djConfig"])&&(djConfig["slowAnonFuncLookups"] == true)) ){
		for(var x in nso){
			try{
				if(nso[x] === anonFuncPtr){
					return x;
				}
			}catch(e){} // window.external fails in IE embedded in Eclipse (Eclipse bug #151165)
		}
	}
	var ret = "__"+dojo.lang.anonCtr++;
	while(typeof nso[ret] != "undefined"){
		ret = "__"+dojo.lang.anonCtr++;
	}
	nso[ret] = anonFuncPtr;
	return ret; // String
}

dojo.lang.forward = function(funcName){
	// summary:
	// 		Returns a function that forwards a method call to
	// 		this.funcName(...).  Unlike dojo.lang.hitch(), the "this" scope is
	// 		not fixed on a single object. Ported from MochiKit.
	return function(){
		return this[funcName].apply(this, arguments);
	}; // Function
}

dojo.lang.curry = function(thisObj, func /* args ... */){
	// summary:
	//		similar to the curry() method found in many functional programming
	//		environments, this function returns an "argument accumulator"
	//		function, bound to a particular scope, and "primed" with a variable
	//		number of arguments. The curry method is unique in that it returns
	//		a function that may return other "partial" function which can be
	//		called repeatedly. New functions are returned until the arity of
	//		the original function is reached, at which point the underlying
	//		function (func) is called in the scope thisObj with all of the
	//		accumulated arguments (plus any extras) in positional order.
	// examples:
	//		assuming a function defined like this:
	//			var foo = {
	//				bar: function(arg1, arg2, arg3){
	//					dojo.debug.apply(dojo, arguments);
	//				}
	//			};
	//
	//		dojo.lang.curry() can be used most simply in this way:
	//
	//			tmp = dojo.lang.curry(foo, foo.bar, "arg one", "thinger");
	//			tmp("blah", "this is superfluous");
	//			// debugs: "arg one thinger blah this is superfluous"
	//			tmp("blah");
	//			// debugs: "arg one thinger blah"
	//			tmp();
	//			// returns a function exactly like tmp that expects one argument
	//
	//		other intermittent functions could be created until the 3
	//		positional arguments are filled:
	//
	//			tmp = dojo.lang.curry(foo, foo.bar, "arg one");
	//			tmp2 = tmp("arg two");
	//			tmp2("blah blah");
	//			// debugs: "arg one arg two blah blah"
	//			tmp2("oy");
	//			// debugs: "arg one arg two oy"
	//
	//		curry() can also be used to call the function if enough arguments
	//		are passed in the initial invocation:
	//
	//			dojo.lang.curry(foo, foo.bar, "one", "two", "three", "four");
	//			// debugs: "one two three four"
	//			dojo.lang.curry(foo, foo.bar, "one", "two", "three");
	//			// debugs: "one two three"


	// FIXME: the order of func and thisObj should be changed!!!
	var outerArgs = [];
	thisObj = thisObj||dj_global;
	if(dojo.lang.isString(func)){
		func = thisObj[func];
	}
	for(var x=2; x<arguments.length; x++){
		outerArgs.push(arguments[x]);
	}
	// since the event system replaces the original function with a new
	// join-point runner with an arity of 0, we check to see if it's left us
	// any clues about the original arity in lieu of the function's actual
	// length property
	var ecount = (func["__preJoinArity"]||func.length) - outerArgs.length;
	// borrowed from svend tofte
	function gather(nextArgs, innerArgs, expected){
		var texpected = expected;
		var totalArgs = innerArgs.slice(0); // copy
		for(var x=0; x<nextArgs.length; x++){
			totalArgs.push(nextArgs[x]);
		}
		// check the list of provided nextArgs to see if it, plus the
		// number of innerArgs already supplied, meets the total
		// expected.
		expected = expected-nextArgs.length;
		if(expected<=0){
			var res = func.apply(thisObj, totalArgs);
			expected = texpected;
			return res;
		}else{
			return function(){
				return gather(arguments,// check to see if we've been run
										// with enough args
							totalArgs,	// a copy
							expected);	// how many more do we need to run?;
			};
		}
	}
	return gather([], outerArgs, ecount);
}

dojo.lang.curryArguments = function(/*Object*/thisObj, /*Function*/func, /*Array*/args, /*Integer, optional*/offset){
	// summary:
	//		similar to dojo.lang.curry(), except that a list of arguments to
	//		start the curry with may be provided as an array instead of as
	//		positional arguments. An offset may be specified from the 0 index
	//		to skip some elements in args.
	var targs = [];
	var x = offset||0;
	for(x=offset; x<args.length; x++){
		targs.push(args[x]); // ensure that it's an arr
	}
	return dojo.lang.curry.apply(dojo.lang, [thisObj, func].concat(targs));
}

dojo.lang.tryThese = function(/*...*/){
	// summary:
	//		executes each function argument in turn, returning the return value
	//		from the first one which does not throw an exception in execution.
	//		Any number of functions may be passed.
	for(var x=0; x<arguments.length; x++){
		try{
			if(typeof arguments[x] == "function"){
				var ret = (arguments[x]());
				if(ret){
					return ret;
				}
			}
		}catch(e){
			dojo.debug(e);
		}
	}
}

dojo.lang.delayThese = function(/*Array*/farr, /*Function, optional*/cb, /*Integer*/delay, /*Function, optional*/onend){
	// summary:
	//		executes a series of functions contained in farr, but spaces out
	//		calls to each function by the millisecond delay provided. If cb is
	//		provided, it will be called directly after each item in farr is
	//		called and if onend is passed, it will be called when all items
	//		have completed executing.

	/**
	 * alternate: (array funcArray, function callback, function onend)
	 * alternate: (array funcArray, function callback)
	 * alternate: (array funcArray)
	 */
	if(!farr.length){
		if(typeof onend == "function"){
			onend();
		}
		return;
	}
	if((typeof delay == "undefined")&&(typeof cb == "number")){
		delay = cb;
		cb = function(){};
	}else if(!cb){
		cb = function(){};
		if(!delay){ delay = 0; }
	}
	setTimeout(function(){
		(farr.shift())();
		cb();
		dojo.lang.delayThese(farr, cb, delay, onend);
	}, delay);
}

dojo.provide("dojo.lang.array");


// FIXME: Is this worthless since you can do: if(name in obj)
// is this the right place for this?

dojo.lang.mixin(dojo.lang, {
	has: function(/*Object*/obj, /*String*/name){
		// summary: is there a property with the passed name in obj?
		try{
			return typeof obj[name] != "undefined"; // Boolean
		}catch(e){ return false; } // Boolean
	},

	isEmpty: function(/*Object*/obj){
		// summary:
		//		can be used to determine if the passed object is "empty". In
		//		the case of array-like objects, the length, property is
		//		examined, but for other types of objects iteration is used to
		//		examine the iterable "surface area" to determine if any
		//		non-prototypal properties have been assigned. This iteration is
		//		prototype-extension safe.
		if(dojo.lang.isObject(obj)){
			var tmp = {};
			var count = 0;
			for(var x in obj){
				if(obj[x] && (!tmp[x])){
					count++;
					break;
				}
			}
			return count == 0; // boolean
		}else if(dojo.lang.isArrayLike(obj) || dojo.lang.isString(obj)){
			return obj.length == 0; // boolean
		}
	},

	map: function(/*Array*/arr, /*Object|Function*/obj, /*Function?*/unary_func){
		// summary:
		//		returns a new array constituded from the return values of
		//		passing each element of arr into unary_func. The obj parameter
		//		may be passed to enable the passed function to be called in
		//		that scope. In environments that support JavaScript 1.6, this
		//		function is a passthrough to the built-in map() function
		//		provided by Array instances. For details on this, see:
		// 			http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Global_Objects:Array:map
		// examples:
		//		dojo.lang.map([1, 2, 3, 4], function(item){ return item+1 });
		//		// returns [2, 3, 4, 5]
		var isString = dojo.lang.isString(arr);
		if(isString){
			// arr: String
			arr = arr.split("");
		}
		if(dojo.lang.isFunction(obj)&&(!unary_func)){
			unary_func = obj;
			obj = dj_global;
		}else if(dojo.lang.isFunction(obj) && unary_func){
			// ff 1.5 compat
			var tmpObj = obj;
			obj = unary_func;
			unary_func = tmpObj;
		}
		if(Array.map){
			var outArr = Array.map(arr, unary_func, obj);
		}else{
			var outArr = [];
			for(var i=0;i<arr.length;++i){
				outArr.push(unary_func.call(obj, arr[i]));
			}
		}
		if(isString) {
			return outArr.join(""); // String
		} else {
			return outArr; // Array
		}
	},

	reduce: function(/*Array*/arr, initialValue, /*Object|Function*/obj, /*Function*/binary_func){
		// summary:
		// 		similar to Python's builtin reduce() function. The result of
		// 		the previous computation is passed as the first argument to
		// 		binary_func along with the next value from arr. The result of
		// 		this call is used along with the subsequent value from arr, and
		// 		this continues until arr is exhausted. The return value is the
		// 		last result. The "obj" and "initialValue" parameters may be
		// 		safely omitted and the order of obj and binary_func may be
		// 		reversed. The default order of the obj and binary_func argument
		// 		will probably be reversed in a future release, and this call
		// 		order is supported today.
		// examples:
		//		dojo.lang.reduce([1, 2, 3, 4], function(last, next){ return last+next});
		//		returns 10
		var reducedValue = initialValue;
		if(arguments.length == 1){
			dojo.debug("dojo.lang.reduce called with too few arguments!");
			return false;
		}else if(arguments.length == 2){
			binary_func = initialValue;
			reducedValue = arr.shift();
		}else if(arguments.lenght == 3){
			if(dojo.lang.isFunction(obj)){
				binary_func = obj;
				obj = null;
			}
		}else{
			// un-fsck the default order
			// FIXME:
			//		could be wrong for some strange function object cases. Not
			//		sure how to test for them.
			if(dojo.lang.isFunction(obj)){
				var tmp = binary_func;
				binary_func = obj;
				obj = tmp;
			}
		}

		var ob = obj ? obj : dj_global;
		dojo.lang.map(arr,
			function(val){
				reducedValue = binary_func.call(ob, reducedValue, val);
			}
		);
		return reducedValue;
	},

	forEach: function(/*Array*/anArray, /*Function*/callback, /*Object?*/thisObject){
		// summary:
		//		for every item in anArray, call callback with that item as its
		//		only parameter. Return values are ignored. This funciton
		//		corresponds (and wraps) the JavaScript 1.6 forEach method. For
		//		more details, see:
		//			http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Global_Objects:Array:forEach
		if(dojo.lang.isString(anArray)){
			// anArray: String
			anArray = anArray.split("");
		}
		if(Array.forEach){
			Array.forEach(anArray, callback, thisObject);
		}else{
			// FIXME: there are several ways of handilng thisObject. Is dj_global always the default context?
			if(!thisObject){
				thisObject=dj_global;
			}
			for(var i=0,l=anArray.length; i<l; i++){
				callback.call(thisObject, anArray[i], i, anArray);
			}
		}
	},

	_everyOrSome: function(/*Boolean*/every, /*Array*/arr, /*Function*/callback, /*Object?*/thisObject){
		if(dojo.lang.isString(arr)){
			//arr: String
			arr = arr.split("");
		}
		if(Array.every){
			return Array[ every ? "every" : "some" ](arr, callback, thisObject);
		}else{
			if(!thisObject){
				thisObject = dj_global;
			}
			for(var i=0,l=arr.length; i<l; i++){
				var result = callback.call(thisObject, arr[i], i, arr);
				if(every && !result){
					return false; // Boolean
				}else if((!every)&&(result)){
					return true; // Boolean
				}
			}
			return Boolean(every); // Boolean
		}
	},

	every: function(/*Array*/arr, /*Function*/callback, /*Object?*/thisObject){
		// summary:
		//		determines whether or not every item in the array satisfies the
		//		condition implemented by callback. thisObject may be used to
		//		scope the call to callback. The function signature is derived
		//		from the JavaScript 1.6 Array.every() function. More
		//		information on this can be found here:
		//			http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Global_Objects:Array:every
		// examples:
		//		dojo.lang.every([1, 2, 3, 4], function(item){ return item>1; });
		//		// returns false
		//		dojo.lang.every([1, 2, 3, 4], function(item){ return item>0; });
		//		// returns true
		return this._everyOrSome(true, arr, callback, thisObject); // Boolean
	},

	some: function(/*Array*/arr, /*Function*/callback, /*Object?*/thisObject){
		// summary:
		//		determines whether or not any item in the array satisfies the
		//		condition implemented by callback. thisObject may be used to
		//		scope the call to callback. The function signature is derived
		//		from the JavaScript 1.6 Array.some() function. More
		//		information on this can be found here:
		//			http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Global_Objects:Array:some
		// examples:
		//		dojo.lang.some([1, 2, 3, 4], function(item){ return item>1; });
		//		// returns true
		//		dojo.lang.some([1, 2, 3, 4], function(item){ return item<1; });
		//		// returns false
		return this._everyOrSome(false, arr, callback, thisObject); // Boolean
	},

	filter: function(/*Array*/arr, /*Function*/callback, /*Object?*/thisObject){
		// summary:
		//		returns a new Array with those items from arr that match the
		//		condition implemented by callback.thisObject may be used to
		//		scope the call to callback. The function signature is derived
		//		from the JavaScript 1.6 Array.filter() function, although
		//		special accomidation is made in our implementation for strings.
		//		More information on the JS 1.6 API can be found here:
		//			http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Global_Objects:Array:filter
		// examples:
		//		dojo.lang.some([1, 2, 3, 4], function(item){ return item>1; });
		//		// returns [2, 3, 4]
		var isString = dojo.lang.isString(arr);
		if(isString){ /*arr: String*/arr = arr.split(""); }
		var outArr;
		if(Array.filter){
			outArr = Array.filter(arr, callback, thisObject);
		}else{
			if(!thisObject){
				if(arguments.length >= 3){ dojo.raise("thisObject doesn't exist!"); }
				thisObject = dj_global;
			}

			outArr = [];
			for(var i = 0; i < arr.length; i++){
				if(callback.call(thisObject, arr[i], i, arr)){
					outArr.push(arr[i]);
				}
			}
		}
		if(isString){
			return outArr.join(""); // String
		} else {
			return outArr; // Array
		}
	},

	unnest: function(/* ... */){
		// summary:
		//		Creates a 1-D array out of all the arguments passed,
		//		unravelling any array-like objects in the process
		// usage:
		//		unnest(1, 2, 3) ==> [1, 2, 3]
		//		unnest(1, [2, [3], [[[4]]]]) ==> [1, 2, 3, 4]

		var out = [];
		for(var i = 0; i < arguments.length; i++){
			if(dojo.lang.isArrayLike(arguments[i])){
				var add = dojo.lang.unnest.apply(this, arguments[i]);
				out = out.concat(add);
			}else{
				out.push(arguments[i]);
			}
		}
		return out; // Array
	},

	toArray: function(/*Object*/arrayLike, /*Number*/startOffset){
		// summary:
		//		Converts an array-like object (i.e. arguments, DOMCollection)
		//		to an array. Returns a new Array object.
		var array = [];
		for(var i = startOffset||0; i < arrayLike.length; i++){
			array.push(arrayLike[i]);
		}
		return array; // Array
	}
});

dojo.provide("dojo.lang.extras");


dojo.lang.setTimeout = function(/*Function*/func, /*int*/delay /*, ...*/){
	// summary:
	//		Sets a timeout in milliseconds to execute a function in a given
	//		context with optional arguments.
	// usage:
	//		dojo.lang.setTimeout(Object context, function func, number delay[, arg1[, ...]]);
	//		dojo.lang.setTimeout(function func, number delay[, arg1[, ...]]);

	var context = window, argsStart = 2;
	if(!dojo.lang.isFunction(func)){
		context = func;
		func = delay;
		delay = arguments[2];
		argsStart++;
	}

	if(dojo.lang.isString(func)){
		func = context[func];
	}

	var args = [];
	for (var i = argsStart; i < arguments.length; i++){
		args.push(arguments[i]);
	}
	return dojo.global().setTimeout(function(){ func.apply(context, args); }, delay); // int
}

dojo.lang.clearTimeout = function(/*int*/timer){
	// summary: clears timer by number from the execution queue

	// FIXME:
	//		why do we have this function? It's not portable outside of browser
	//		environments and it's a stupid wrapper on something that browsers
	//		provide anyway.
	dojo.global().clearTimeout(timer);
}

dojo.lang.getNameInObj = function(/*Object*/ns, /*unknown*/item){
	// summary:
	//		looks for a value in the object ns with a value matching item and
	//		returns the property name
	// ns: if null, dj_global is used
	// item: value to return a name for
	if(!ns){ ns = dj_global; }

	for(var x in ns){
		if(ns[x] === item){
			return new String(x); // String
		}
	}
	return null; // null
}

dojo.lang.shallowCopy = function(/*Object*/obj, /*Boolean?*/deep){
	// summary:
	//		copies object obj one level deep, or full depth if deep is true
	var i, ret;

	if(obj === null){ /*obj: null*/ return null; } // null

	if(dojo.lang.isObject(obj)){
		// obj: Object
		ret = new obj.constructor();
		for(i in obj){
			if(dojo.lang.isUndefined(ret[i])){
				ret[i] = deep ? dojo.lang.shallowCopy(obj[i], deep) : obj[i];
			}
		}
	}else if(dojo.lang.isArray(obj)){
		// obj: Array
		ret = [];
		for(i=0; i<obj.length; i++){
			ret[i] = deep ? dojo.lang.shallowCopy(obj[i], deep) : obj[i];
		}
	}else{
		// obj: Object
		ret = obj;
	}

	return ret; // Object
}

dojo.lang.firstValued = function(/* ... */){
	// summary: Return the first argument that isn't undefined

	for(var i = 0; i < arguments.length; i++){
		if(typeof arguments[i] != "undefined"){
			return arguments[i]; // Object
		}
	}
	return undefined; // undefined
}

dojo.lang.getObjPathValue = function(/*String*/objpath, /*Object?*/context, /*Boolean?*/create){
	// summary:
	//		Gets a value from a reference specified as a string descriptor,
	//		(e.g. "A.B") in the given context.
	// context: if not specified, dj_global is used
	// create: if true, undefined objects in the path are created.
	with(dojo.parseObjPath(objpath, context, create)){
		return dojo.evalProp(prop, obj, create); // Object
	}
}

dojo.lang.setObjPathValue = function(/*String*/objpath, /*anything*/value, /*Object?*/context, /*Boolean?*/create){
	// summary:
	//		Sets a value on a reference specified as a string descriptor.
	//		(e.g. "A.B") in the given context. This is similar to straight
	//		assignment, except that the object structure in question can
	//		optionally be created if it does not exist.
	//	context: if not specified, dj_global is used
	//	create: if true, undefined objects in the path are created.

	// FIXME: why is this function valuable? It should be scheduled for
	// removal on the grounds that dojo.parseObjPath does most of it's work and
	// is more straightforward and has fewer dependencies. Also, the order of
	// arguments is bone-headed. "context" should clearly come after "create".
	// *sigh*
	dojo.deprecated("dojo.lang.setObjPathValue", "use dojo.parseObjPath and the '=' operator", "0.6");

	if(arguments.length < 4){
		create = true;
	}
	with(dojo.parseObjPath(objpath, context, create)){
		if(obj && (create || (prop in obj))){
			obj[prop] = value;
		}
	}
}

dojo.provide("dojo.lang.declare");


dojo.lang.declare = function(	/*String*/ className,
								/*Function|Array*/ superclass,
								/*Function?*/ init,
								/*Object|Array*/ props){
	/*
	 *	summary: Create a feature-rich constructor with a compact notation
	 *	className: the name of the constructor (loosely, a "class")
	 * 	superclass:
	 *		may be a Function, or an Array of Functions. If "superclass" is an
	 *		array, the first element is used as the prototypical ancestor and
	 *		any following Functions become mixin ancestors.
	 *	init: an initializer function
	 *	props:
	 *		an object (or array of objects) whose properties are copied to the
	 *		created prototype
	 *	description:
	 *		Create a constructor using a compact notation for inheritance and
	 *		prototype extension. "superclass" argument may be a Function, or an
	 *		array of Functions.
	 *
	 *		If "superclass" is an array, the first element is used as the
	 *		prototypical ancestor and any following Functions become mixin
	 *		ancestors.
	 *
	 *		All "superclass(es)" must be Functions (not mere Objects).
	 *
	 *		Using mixin ancestors provides a type of multiple inheritance.
	 *		Mixin ancestors prototypical properties are copied to the subclass,
	 *		and any inializater/constructor is invoked.
	 *
	 *		Properties of object "props" are copied to the constructor
	 *		prototype. If "props" is an array, properties of each object in the
	 *		array are copied to the constructor prototype.
	 *
	 *		name of the class ("className" argument) is stored in
	 *		"declaredClass" property
	 *
	 *		Initializer functions are called when an object is instantiated
	 *		from this constructor.
	 *
	 *		Aliased as "dojo.declare"
	 *
	 * Usage:
	 *
	 *		dojo.declare("my.classes.bar", my.classes.foo,
	 *			function(){
	 *				// initialization function
	 *				this.myComplicatedObject = new ReallyComplicatedObject();
	 *			},
	 *			{ // properties to be added to the class prototype
	 *				someValue: 2,
	 *				someMethod: function(){
	 *					doStuff();
	 *				}
	 *			}
	 *		);
	 *
	 */
	if((dojo.lang.isFunction(props))||((!props)&&(!dojo.lang.isFunction(init)))){
		// parameter juggling to support omitting init param (also allows
		// reordering init and props arguments)
		var temp = props;
		props = init;
		init = temp;
	}
	var mixins = [ ];
	if(dojo.lang.isArray(superclass)){
		mixins = superclass;
		superclass = mixins.shift();
	}
	if(!init){
		init = dojo.evalObjPath(className, false);
		if((init)&&(!dojo.lang.isFunction(init))){ init = null };
	}
	var ctor = dojo.lang.declare._makeConstructor();
	var scp = (superclass ? superclass.prototype : null);
	if(scp){
		scp.prototyping = true;
		ctor.prototype = new superclass();
		scp.prototyping = false;
	}
	ctor.superclass = scp;
	ctor.mixins = mixins;
	for(var i=0,l=mixins.length; i<l; i++){
		dojo.lang.extend(ctor, mixins[i].prototype);
	}
	ctor.prototype.initializer = null;
	ctor.prototype.declaredClass = className;
	if(dojo.lang.isArray(props)){
		dojo.lang.extend.apply(dojo.lang, [ctor].concat(props));
	}else{
		dojo.lang.extend(ctor, (props)||{});
	}
	dojo.lang.extend(ctor, dojo.lang.declare._common);
	ctor.prototype.constructor = ctor;
	ctor.prototype.initializer = (ctor.prototype.initializer)||(init)||(function(){});
	var created = dojo.parseObjPath(className, null, true);
	created.obj[created.prop] = ctor;
	return ctor; // Function
}

dojo.lang.declare._makeConstructor = function(){
	return function(){
		// get the generational context (which object [or prototype] should be constructed)
		var self = this._getPropContext();
		var s = self.constructor.superclass;
		if((s)&&(s.constructor)){
			if(s.constructor==arguments.callee){
				// if this constructor is invoked directly (my.ancestor.call(this))
				this._inherited("constructor", arguments);
			}else{
				this._contextMethod(s, "constructor", arguments);
			}
		}
		var ms = (self.constructor.mixins)||([]);
		for(var i=0, m; (m=ms[i]); i++) {
			(((m.prototype)&&(m.prototype.initializer))||(m)).apply(this, arguments);
		}
		if((!this.prototyping)&&(self.initializer)){
			self.initializer.apply(this, arguments);
		}
	}
}

dojo.lang.declare._common = {
	_getPropContext: function(){ return (this.___proto||this); },
	// caches ptype context and calls method on it
	_contextMethod: function(ptype, method, args){
		var result, stack = this.___proto;
		this.___proto = ptype;
		try { result = ptype[method].apply(this,(args||[])); }
		catch(e) { throw e; }
		finally { this.___proto = stack; }
		return result;
	},
	_inherited: function(prop, args){
		// summary:
		//		Searches backward thru prototype chain to find nearest
		//		ancestral instance of prop. Internal use only.
		var p = this._getPropContext();
		do{
			if((!p.constructor)||(!p.constructor.superclass)){ return; }
			p = p.constructor.superclass;
		}while(!(prop in p));
		return (dojo.lang.isFunction(p[prop]) ? this._contextMethod(p, prop, args) : p[prop]);
	},
	inherited: function(prop, args){
		dojo.deprecated("'inherited' method is dangerous, do not up-call! 'inherited' is slated for removal in 0.5; name your super class (or use superclass property) instead.", "0.5");
		this._inherited(prop, args);
	}
}

dojo.declare = dojo.lang.declare;

dojo.provide("dojo.ns");

dojo.ns = {
	// summary: private object that implements widget namespace management
	namespaces: {},
	failed: {},
	loading: {},
	loaded: {},
	register: function(/*String*/name, /*String*/module, /*Function?*/resolver, /*Boolean?*/noOverride){
		// summary: creates and registers a dojo.ns.Ns object
		if(!noOverride || !this.namespaces[name]){
			this.namespaces[name] = new dojo.ns.Ns(name, module, resolver);
		}
	},
	allow: function(/*String*/name){
		// summary: Returns false if 'name' is filtered by configuration or has failed to load, true otherwise
		if(this.failed[name]){return false;} // Boolean
		if((djConfig.excludeNamespace)&&(dojo.lang.inArray(djConfig.excludeNamespace, name))){return false;} // Boolean
		// If the namespace is "dojo", or the user has not specified allowed namespaces return true.
		// Otherwise, if the user has specifically allowed this namespace, return true, otherwise false.
		return((name==this.dojo)||(!djConfig.includeNamespace)||(dojo.lang.inArray(djConfig.includeNamespace, name))); // Boolean
	},
	get: function(/*String*/name){
		// summary
		//  Return Ns object registered to 'name', if any
		return this.namespaces[name]; // Ns
	},
	require: function(/*String*/name){
		// summary
  	//  Try to ensure that 'name' is registered, loading a namespace manifest if necessary
		var ns = this.namespaces[name];
		if((ns)&&(this.loaded[name])){return ns;} // Ns
		if(!this.allow(name)){return false;} // Boolean
 		if(this.loading[name]){
			// FIXME: do we really ever have re-entrancy situation? this would appear to be really bad
			// original code did not throw an exception, although that seems the only course
			// adding debug output here to track if this occurs.
			dojo.debug('dojo.namespace.require: re-entrant request to load namespace "' + name + '" must fail.');
			return false; // Boolean
		}
		// workaround so we don't break the build system
		var req = dojo.require;
		this.loading[name] = true;
		try {
			//dojo namespace file is always in the Dojo namespaces folder, not any custom folder
			if(name=="dojo"){
				req("dojo.namespaces.dojo");
			}else{
				// if no registered module prefix, use ../<name> by convention
				if(!dojo.hostenv.moduleHasPrefix(name)){
					dojo.registerModulePath(name, "../" + name);
				}
				req([name, 'manifest'].join('.'), false, true);
			}
			if(!this.namespaces[name]){
				this.failed[name] = true; //only look for a namespace once
			}
		}finally{
			this.loading[name]=false;
		}
		return this.namespaces[name]; // Ns
	}
}

dojo.ns.Ns = function(/*String*/name, /*String*/module, /*Function?*/resolver){
	// summary: this object simply encapsulates namespace data
	this.name = name;
	this.module = module;
	this.resolver = resolver;
	this._loaded = [ ];
	this._failed = [ ];
}

dojo.ns.Ns.prototype.resolve = function(/*String*/name, /*String*/domain, /*Boolean?*/omitModuleCheck){
	//summary: map component with 'name' and 'domain' to a module via namespace resolver, if specified
	if(!this.resolver || djConfig["skipAutoRequire"]){return false;} // Boolean
	var fullName = this.resolver(name, domain);
	//only load a widget once. This is a quicker check than dojo.require does
	if((fullName)&&(!this._loaded[fullName])&&(!this._failed[fullName])){
		//workaround so we don't break the build system
		var req = dojo.require;
		req(fullName, false, true); //omit the module check, we'll do it ourselves.
		if(dojo.hostenv.findModule(fullName, false)){
			this._loaded[fullName] = true;
		}else{
			if(!omitModuleCheck){dojo.raise("dojo.ns.Ns.resolve: module '" + fullName + "' not found after loading via namespace '" + this.name + "'");}
			this._failed[fullName] = true;
		}
	}
	return Boolean(this._loaded[fullName]); // Boolean
}

dojo.registerNamespace = function(/*String*/name, /*String*/module, /*Function?*/resolver){
	// summary: maps a module name to a namespace for widgets, and optionally maps widget names to modules for auto-loading
	// description: An unregistered namespace is mapped to an eponymous module.
	//	For example, namespace acme is mapped to module acme, and widgets are
	//	assumed to belong to acme.widget. If you want to use a different widget
	//	module, use dojo.registerNamespace.
	dojo.ns.register.apply(dojo.ns, arguments);
}

dojo.registerNamespaceResolver = function(/*String*/name, /*Function*/resolver){
	// summary: a resolver function maps widget names to modules, so the
	//	widget manager can auto-load needed widget implementations
	//
	// description: The resolver provides information to allow Dojo
	//	to load widget modules on demand. When a widget is created,
	//	a namespace resolver can tell Dojo what module to require
	//	to ensure that the widget implementation code is loaded.
	//
	// name: will always be lower-case.
	//
	// example:
	//  dojo.registerNamespaceResolver("acme",
	//    function(name){
	//      return "acme.widget."+dojo.string.capitalize(name);
	//    }
	//  );
	var n = dojo.ns.namespaces[name];
	if(n){
		n.resolver = resolver;
	}
}

dojo.registerNamespaceManifest = function(/*String*/module, /*String*/path, /*String*/name, /*String*/widgetModule, /*Function?*/resolver){
	// summary: convenience function to register a module path, a namespace, and optionally a resolver all at once.
	dojo.registerModulePath(name, path);
	dojo.registerNamespace(name, widgetModule, resolver);
}

// NOTE: rather put this in dojo.widget.Widget, but that fubars debugAtAllCosts
dojo.registerNamespace("dojo", "dojo.widget");

dojo.provide("dojo.event.common");


// TODO: connection filter functions
//			these are functions that accept a method invocation (like around
//			advice) and return a boolean based on it. That value determines
//			whether or not the connection proceeds. It could "feel" like around
//			advice for those who know what it is (calling proceed() or not),
//			but I think presenting it as a "filter" and/or calling it with the
//			function args and not the MethodInvocation might make it more
//			palletable to "normal" users than around-advice currently is
// TODO: execution scope mangling
//			YUI's event facility by default executes listeners in the context
//			of the source object. This is very odd, but should probably be
//			supported as an option (both for the source and for the dest). It
//			can be thought of as a connection-specific hitch().
// TODO: more resiliency for 4+ arguments to connect()

dojo.event = new function(){
	this._canTimeout = dojo.lang.isFunction(dj_global["setTimeout"])||dojo.lang.isAlien(dj_global["setTimeout"]);

	// FIXME: where should we put this method (not here!)?
	function interpolateArgs(args, searchForNames){
		var dl = dojo.lang;
		var ao = {
			srcObj: dj_global,
			srcFunc: null,
			adviceObj: dj_global,
			adviceFunc: null,
			aroundObj: null,
			aroundFunc: null,
			adviceType: (args.length>2) ? args[0] : "after",
			precedence: "last",
			once: false,
			delay: null,
			rate: 0,
			adviceMsg: false
		};

		switch(args.length){
			case 0: return;
			case 1: return;
			case 2:
				ao.srcFunc = args[0];
				ao.adviceFunc = args[1];
				break;
			case 3:
				if((dl.isObject(args[0]))&&(dl.isString(args[1]))&&(dl.isString(args[2]))){
					ao.adviceType = "after";
					ao.srcObj = args[0];
					ao.srcFunc = args[1];
					ao.adviceFunc = args[2];
				}else if((dl.isString(args[1]))&&(dl.isString(args[2]))){
					ao.srcFunc = args[1];
					ao.adviceFunc = args[2];
				}else if((dl.isObject(args[0]))&&(dl.isString(args[1]))&&(dl.isFunction(args[2]))){
					ao.adviceType = "after";
					ao.srcObj = args[0];
					ao.srcFunc = args[1];
					var tmpName  = dl.nameAnonFunc(args[2], ao.adviceObj, searchForNames);
					ao.adviceFunc = tmpName;
				}else if((dl.isFunction(args[0]))&&(dl.isObject(args[1]))&&(dl.isString(args[2]))){
					ao.adviceType = "after";
					ao.srcObj = dj_global;
					var tmpName  = dl.nameAnonFunc(args[0], ao.srcObj, searchForNames);
					ao.srcFunc = tmpName;
					ao.adviceObj = args[1];
					ao.adviceFunc = args[2];
				}
				break;
			case 4:
				if((dl.isObject(args[0]))&&(dl.isObject(args[2]))){
					// we can assume that we've got an old-style "connect" from
					// the sigslot school of event attachment. We therefore
					// assume after-advice.
					ao.adviceType = "after";
					ao.srcObj = args[0];
					ao.srcFunc = args[1];
					ao.adviceObj = args[2];
					ao.adviceFunc = args[3];
				}else if((dl.isString(args[0]))&&(dl.isString(args[1]))&&(dl.isObject(args[2]))){
					ao.adviceType = args[0];
					ao.srcObj = dj_global;
					ao.srcFunc = args[1];
					ao.adviceObj = args[2];
					ao.adviceFunc = args[3];
				}else if((dl.isString(args[0]))&&(dl.isFunction(args[1]))&&(dl.isObject(args[2]))){
					ao.adviceType = args[0];
					ao.srcObj = dj_global;
					var tmpName  = dl.nameAnonFunc(args[1], dj_global, searchForNames);
					ao.srcFunc = tmpName;
					ao.adviceObj = args[2];
					ao.adviceFunc = args[3];
				}else if((dl.isString(args[0]))&&(dl.isObject(args[1]))&&(dl.isString(args[2]))&&(dl.isFunction(args[3]))){
					ao.srcObj = args[1];
					ao.srcFunc = args[2];
					var tmpName  = dl.nameAnonFunc(args[3], dj_global, searchForNames);
					ao.adviceObj = dj_global;
					ao.adviceFunc = tmpName;
				}else if(dl.isObject(args[1])){
					ao.srcObj = args[1];
					ao.srcFunc = args[2];
					ao.adviceObj = dj_global;
					ao.adviceFunc = args[3];
				}else if(dl.isObject(args[2])){
					ao.srcObj = dj_global;
					ao.srcFunc = args[1];
					ao.adviceObj = args[2];
					ao.adviceFunc = args[3];
				}else{
					ao.srcObj = ao.adviceObj = ao.aroundObj = dj_global;
					ao.srcFunc = args[1];
					ao.adviceFunc = args[2];
					ao.aroundFunc = args[3];
				}
				break;
			case 6:
				ao.srcObj = args[1];
				ao.srcFunc = args[2];
				ao.adviceObj = args[3]
				ao.adviceFunc = args[4];
				ao.aroundFunc = args[5];
				ao.aroundObj = dj_global;
				break;
			default:
				ao.srcObj = args[1];
				ao.srcFunc = args[2];
				ao.adviceObj = args[3]
				ao.adviceFunc = args[4];
				ao.aroundObj = args[5];
				ao.aroundFunc = args[6];
				ao.once = args[7];
				ao.delay = args[8];
				ao.rate = args[9];
				ao.adviceMsg = args[10];
				break;
		}

		if(dl.isFunction(ao.aroundFunc)){
			var tmpName  = dl.nameAnonFunc(ao.aroundFunc, ao.aroundObj, searchForNames);
			ao.aroundFunc = tmpName;
		}

		if(dl.isFunction(ao.srcFunc)){
			ao.srcFunc = dl.getNameInObj(ao.srcObj, ao.srcFunc);
		}

		if(dl.isFunction(ao.adviceFunc)){
			ao.adviceFunc = dl.getNameInObj(ao.adviceObj, ao.adviceFunc);
		}

		if((ao.aroundObj)&&(dl.isFunction(ao.aroundFunc))){
			ao.aroundFunc = dl.getNameInObj(ao.aroundObj, ao.aroundFunc);
		}

		if(!ao.srcObj){
			dojo.raise("bad srcObj for srcFunc: "+ao.srcFunc);
		}
		if(!ao.adviceObj){
			dojo.raise("bad adviceObj for adviceFunc: "+ao.adviceFunc);
		}

		if(!ao.adviceFunc){
			dojo.debug("bad adviceFunc for srcFunc: "+ao.srcFunc);
			dojo.debugShallow(ao);
		}

		return ao;
	}

	this.connect = function(/*...*/){
		// summary:
		//		dojo.event.connect is the glue that holds most Dojo-based
		//		applications together. Most combinations of arguments are
		//		supported, with the connect() method attempting to disambiguate
		//		the implied types of positional parameters. The following will
		//		all work:
		//			dojo.event.connect("globalFunctionName1", "globalFunctionName2");
		//			dojo.event.connect(functionReference1, functionReference2);
		//			dojo.event.connect("globalFunctionName1", functionReference2);
		//			dojo.event.connect(functionReference1, "globalFunctionName2");
		//			dojo.event.connect(scope1, "functionName1", "globalFunctionName2");
		//			dojo.event.connect("globalFunctionName1", scope2, "functionName2");
		//			dojo.event.connect(scope1, "functionName1", scope2, "functionName2");
		//			dojo.event.connect("after", scope1, "functionName1", scope2, "functionName2");
		//			dojo.event.connect("before", scope1, "functionName1", scope2, "functionName2");
		//			dojo.event.connect("around", 	scope1, "functionName1",
		//											scope2, "functionName2",
		//											aroundFunctionReference);
		//			dojo.event.connect("around", 	scope1, "functionName1",
		//											scope2, "functionName2",
		//											scope3, "aroundFunctionName");
		//			dojo.event.connect("before-around", 	scope1, "functionName1",
		//													scope2, "functionName2",
		//													aroundFunctionReference);
		//			dojo.event.connect("after-around", 		scope1, "functionName1",
		//													scope2, "functionName2",
		//													aroundFunctionReference);
		//			dojo.event.connect("after-around", 		scope1, "functionName1",
		//													scope2, "functionName2",
		//													scope3, "aroundFunctionName");
		//			dojo.event.connect("around", 	scope1, "functionName1",
		//											scope2, "functionName2",
		//											scope3, "aroundFunctionName", true, 30);
		//			dojo.event.connect("around", 	scope1, "functionName1",
		//											scope2, "functionName2",
		//											scope3, "aroundFunctionName", null, null, 10);
		// adviceType:
		//		Optional. String. One of "before", "after", "around",
		//		"before-around", or "after-around". FIXME
		// srcObj:
		//		the scope in which to locate/execute the named srcFunc. Along
		//		with srcFunc, this creates a way to dereference the function to
		//		call. So if the function in question is "foo.bar", the
		//		srcObj/srcFunc pair would be foo and "bar", where "bar" is a
		//		string and foo is an object reference.
		// srcFunc:
		//		the name of the function to connect to. When it is executed,
		//		the listener being registered with this call will be called.
		//		The adviceType defines the call order between the source and
		//		the target functions.
		// adviceObj:
		//		the scope in which to locate/execute the named adviceFunc.
		// adviceFunc:
		//		the name of the function being conected to srcObj.srcFunc
		// aroundObj:
		//		the scope in which to locate/execute the named aroundFunc.
		// aroundFunc:
		//		the name of, or a reference to, the function that will be used
		//		to mediate the advice call. Around advice requires a special
		//		unary function that will be passed a "MethodInvocation" object.
		//		These objects have several important properties, namely:
		//			- args
		//				a mutable array of arguments to be passed into the
		//				wrapped function
		//			- proceed
		//				a function that "continues" the invocation. The result
		//				of this function is the return of the wrapped function.
		//				You can then manipulate this return before passing it
		//				back out (or take further action based on it).
		// once:
		//		boolean that determines whether or not this connect() will
		//		create a new connection if an identical connect() has already
		//		been made. Defaults to "false".
		// delay:
		//		an optional delay (in ms), as an integer, for dispatch of a
		//		listener after the source has been fired.
		// rate:
		//		an optional rate throttling parameter (integer, in ms). When
		//		specified, this particular connection will not fire more than
		//		once in the interval specified by the rate
		// adviceMsg:
		//		boolean. Should the listener have all the parameters passed in
		//		as a single argument?

		/*
				ao.adviceType = args[0];
				ao.srcObj = args[1];
				ao.srcFunc = args[2];
				ao.adviceObj = args[3]
				ao.adviceFunc = args[4];
				ao.aroundObj = args[5];
				ao.aroundFunc = args[6];
				ao.once = args[7];
				ao.delay = args[8];
				ao.rate = args[9];
				ao.adviceMsg = args[10];
		*/
		if(arguments.length == 1){
			var ao = arguments[0];
		}else{
			var ao = interpolateArgs(arguments, true);
		}
		if(dojo.lang.isString(ao.srcFunc) && (ao.srcFunc.toLowerCase() == "onkey") ){
			if(dojo.render.html.ie){
				ao.srcFunc = "onkeydown";
				this.connect(ao);
			}
			ao.srcFunc = "onkeypress";
		}


		if(dojo.lang.isArray(ao.srcObj) && ao.srcObj!=""){
			var tmpAO = {};
			for(var x in ao){
				tmpAO[x] = ao[x];
			}
			var mjps = [];
			dojo.lang.forEach(ao.srcObj, function(src){
				if((dojo.render.html.capable)&&(dojo.lang.isString(src))){
					src = dojo.byId(src);
					// dojo.debug(src);
				}
				tmpAO.srcObj = src;
				// dojo.debug(tmpAO.srcObj, tmpAO.srcFunc);
				// dojo.debug(tmpAO.adviceObj, tmpAO.adviceFunc);
				mjps.push(dojo.event.connect.call(dojo.event, tmpAO));
			});
			return mjps;
		}

		// FIXME: just doing a "getForMethod()" seems to be enough to put this into infinite recursion!!
		var mjp = dojo.event.MethodJoinPoint.getForMethod(ao.srcObj, ao.srcFunc);
		if(ao.adviceFunc){
			var mjp2 = dojo.event.MethodJoinPoint.getForMethod(ao.adviceObj, ao.adviceFunc);
		}

		mjp.kwAddAdvice(ao);

		// advanced users might want to fsck w/ the join point manually
		return mjp; // a MethodJoinPoint object
	}

	this.log = function(/*object or funcName*/ a1, /*funcName*/ a2){
		// summary:
		//		a function that will wrap and log all calls to the specified
		//		a1.a2() function. If only a1 is passed, it'll be used as a
		//		function or function name on the global context. Logging will
		//		be sent to dojo.debug
		// a1:
		//		if a2 is passed, this should be an object. If not, it can be a
		//		function or function name.
		// a2:
		//		a function name
		var kwArgs;
		if((arguments.length == 1)&&(typeof a1 == "object")){
			kwArgs = a1;
		}else{
			kwArgs = {
				srcObj: a1,
				srcFunc: a2
			};
		}
		kwArgs.adviceFunc = function(){
			var argsStr = [];
			for(var x=0; x<arguments.length; x++){
				argsStr.push(arguments[x]);
			}
			dojo.debug("("+kwArgs.srcObj+")."+kwArgs.srcFunc, ":", argsStr.join(", "));
		}
		this.kwConnect(kwArgs);
	}

	this.connectBefore = function(){
		// summary:
		//	 	takes the same parameters as dojo.event.connect(), except that
		//	 	the advice type will always be "before"
		var args = ["before"];
		for(var i = 0; i < arguments.length; i++){ args.push(arguments[i]); }
		return this.connect.apply(this, args); // a MethodJoinPoint object
	}

	this.connectAround = function(){
		// summary:
		//	 	takes the same parameters as dojo.event.connect(), except that
		//	 	the advice type will always be "around"
		var args = ["around"];
		for(var i = 0; i < arguments.length; i++){ args.push(arguments[i]); }
		return this.connect.apply(this, args); // a MethodJoinPoint object
	}

	this.connectOnce = function(){
		// summary:
		//	 	takes the same parameters as dojo.event.connect(), except that
		//	 	the "once" flag will always be set to "true"
		var ao = interpolateArgs(arguments, true);
		ao.once = true;
		return this.connect(ao); // a MethodJoinPoint object
	}

	this._kwConnectImpl = function(kwArgs, disconnect){
		var fn = (disconnect) ? "disconnect" : "connect";
		if(typeof kwArgs["srcFunc"] == "function"){
			kwArgs.srcObj = kwArgs["srcObj"]||dj_global;
			var tmpName  = dojo.lang.nameAnonFunc(kwArgs.srcFunc, kwArgs.srcObj, true);
			kwArgs.srcFunc = tmpName;
		}
		if(typeof kwArgs["adviceFunc"] == "function"){
			kwArgs.adviceObj = kwArgs["adviceObj"]||dj_global;
			var tmpName  = dojo.lang.nameAnonFunc(kwArgs.adviceFunc, kwArgs.adviceObj, true);
			kwArgs.adviceFunc = tmpName;
		}
		kwArgs.srcObj = kwArgs["srcObj"]||dj_global;
		kwArgs.adviceObj = kwArgs["adviceObj"]||kwArgs["targetObj"]||dj_global;
		kwArgs.adviceFunc = kwArgs["adviceFunc"]||kwArgs["targetFunc"];
		// pass kwargs to avoid unrolling/repacking
		return dojo.event[fn](kwArgs);
	}

	this.kwConnect = function(/*Object*/ kwArgs){
		// summary:
		//		A version of dojo.event.connect() that takes a map of named
		//		parameters instead of the positional parameters that
		//		dojo.event.connect() uses. For many advanced connection types,
		//		this can be a much more readable (and potentially faster)
		//		alternative.
		// kwArgs:
		// 		An object that can have the following properties:
		//			- adviceType
		//			- srcObj
		//			- srcFunc
		//			- adviceObj
		//			- adviceFunc
		//			- aroundObj
		//			- aroundFunc
		//			- once
		//			- delay
		//			- rate
		//			- adviceMsg
		//		As with connect, only srcFunc and adviceFunc are generally
		//		required

		return this._kwConnectImpl(kwArgs, false); // a MethodJoinPoint object

	}

	this.disconnect = function(){
		// summary:
		//		Takes the same parameters as dojo.event.connect() but destroys
		//		an existing connection instead of building a new one. For
		//		multiple identical connections, multiple disconnect() calls
		//		will unroll one each time it's called.
		if(arguments.length == 1){
			var ao = arguments[0];
		}else{
			var ao = interpolateArgs(arguments, true);
		}
		if(!ao.adviceFunc){ return; } // nothing to disconnect
		if(dojo.lang.isString(ao.srcFunc) && (ao.srcFunc.toLowerCase() == "onkey") ){
			if(dojo.render.html.ie){
				ao.srcFunc = "onkeydown";
				this.disconnect(ao);
			}
			ao.srcFunc = "onkeypress";
		}
		if(!ao.srcObj[ao.srcFunc]){ return null; } // prevent un-necessaray joinpoint creation
		var mjp = dojo.event.MethodJoinPoint.getForMethod(ao.srcObj, ao.srcFunc, true);
		mjp.removeAdvice(ao.adviceObj, ao.adviceFunc, ao.adviceType, ao.once); // a MethodJoinPoint object
		return mjp;
	}

	this.kwDisconnect = function(kwArgs){
		// summary:
		//		Takes the same parameters as dojo.event.kwConnect() but
		//		destroys an existing connection instead of building a new one.
		return this._kwConnectImpl(kwArgs, true);
	}
}

// exactly one of these is created whenever a method with a joint point is run,
// if there is at least one 'around' advice.
dojo.event.MethodInvocation = function(/*dojo.event.MethodJoinPoint*/join_point, /*Object*/obj, /*Array*/args){
	// summary:
	//		a class the models the call into a function. This is used under the
	//		covers for all method invocations on both ends of a
	//		connect()-wrapped function dispatch. This allows us to "pickle"
	//		calls, such as in the case of around advice.
	// join_point:
	//		a dojo.event.MethodJoinPoint object that represents a connection
	// obj:
	//		the scope the call will execute in
	// args:
	//		an array of parameters that will get passed to the callee
	this.jp_ = join_point;
	this.object = obj;
	this.args = [];
	// make sure we don't lock into a mutable object which can change under us.
	// It's ok if the individual items change, though.
	for(var x=0; x<args.length; x++){
		this.args[x] = args[x];
	}
	// the index of the 'around' that is currently being executed.
	this.around_index = -1;
}

dojo.event.MethodInvocation.prototype.proceed = function(){
	// summary:
	//		proceed with the method call that's represented by this invocation
	//		object
	this.around_index++;
	if(this.around_index >= this.jp_.around.length){
		return this.jp_.object[this.jp_.methodname].apply(this.jp_.object, this.args);
		// return this.jp_.run_before_after(this.object, this.args);
	}else{
		var ti = this.jp_.around[this.around_index];
		var mobj = ti[0]||dj_global;
		var meth = ti[1];
		return mobj[meth].call(mobj, this);
	}
}


dojo.event.MethodJoinPoint = function(/*Object*/obj, /*String*/funcName){
	this.object = obj||dj_global;
	this.methodname = funcName;
	this.methodfunc = this.object[funcName];
	this.squelch = false;
	// this.before = [];
	// this.after = [];
	// this.around = [];
}

dojo.event.MethodJoinPoint.getForMethod = function(/*Object*/obj, /*String*/funcName){
	// summary:
	//		"static" class function for returning a MethodJoinPoint from a
	//		scoped function. If one doesn't exist, one is created.
	// obj:
	//		the scope to search for the function in
	// funcName:
	//		the name of the function to return a MethodJoinPoint for
	if(!obj){ obj = dj_global; }
	if(!obj[funcName]){
		// supply a do-nothing method implementation
		obj[funcName] = function(){};
		if(!obj[funcName]){
			// e.g. cannot add to inbuilt objects in IE6
			dojo.raise("Cannot set do-nothing method on that object "+funcName);
		}
	}else if((!dojo.lang.isFunction(obj[funcName]))&&(!dojo.lang.isAlien(obj[funcName]))){
		// FIXME: should we throw an exception here instead?
		return null;
	}
	// we hide our joinpoint instance in obj[funcName + '$joinpoint']
	var jpname = funcName + "$joinpoint";
	var jpfuncname = funcName + "$joinpoint$method";
	var joinpoint = obj[jpname];
	if(!joinpoint){
		var isNode = false;
		if(dojo.event["browser"]){
			if( (obj["attachEvent"])||
				(obj["nodeType"])||
				(obj["addEventListener"]) ){
				isNode = true;
				dojo.event.browser.addClobberNodeAttrs(obj, [jpname, jpfuncname, funcName]);
			}
		}
		var origArity = obj[funcName].length;
		obj[jpfuncname] = obj[funcName];
		// joinpoint = obj[jpname] = new dojo.event.MethodJoinPoint(obj, funcName);
		joinpoint = obj[jpname] = new dojo.event.MethodJoinPoint(obj, jpfuncname);
		obj[funcName] = function(){
			var args = [];

			if((isNode)&&(!arguments.length)){
				var evt = null;
				try{
					if(obj.ownerDocument){
						evt = obj.ownerDocument.parentWindow.event;
					}else if(obj.documentElement){
						evt = obj.documentElement.ownerDocument.parentWindow.event;
					}else if(obj.event){ //obj is a window
						evt = obj.event;
					}else{
						evt = window.event;
					}
				}catch(e){
					evt = window.event;
				}

				if(evt){
					args.push(dojo.event.browser.fixEvent(evt, this));
				}
			}else{
				for(var x=0; x<arguments.length; x++){
					if((x==0)&&(isNode)&&(dojo.event.browser.isEvent(arguments[x]))){
						args.push(dojo.event.browser.fixEvent(arguments[x], this));
					}else{
						args.push(arguments[x]);
					}
				}
			}
			// return joinpoint.run.apply(joinpoint, arguments);
			return joinpoint.run.apply(joinpoint, args);
		}
		obj[funcName].__preJoinArity = origArity;
	}
	return joinpoint; // dojo.event.MethodJoinPoint
}

dojo.lang.extend(dojo.event.MethodJoinPoint, {
	unintercept: function(){
		// summary:
		//		destroy the connection to all listeners that may have been
		//		registered on this joinpoint
		this.object[this.methodname] = this.methodfunc;
		this.before = [];
		this.after = [];
		this.around = [];
	},

	disconnect: dojo.lang.forward("unintercept"),

	run: function(){
		// summary:
		//		execute the connection represented by this join point. The
		//		arguments passed to run() will be passed to the function and
		//		its listeners.
		var obj = this.object||dj_global;
		var args = arguments;

		// optimization. We only compute once the array version of the arguments
		// pseudo-arr in order to prevent building it each time advice is unrolled.
		var aargs = [];
		for(var x=0; x<args.length; x++){
			aargs[x] = args[x];
		}

		var unrollAdvice  = function(marr){
			if(!marr){
				dojo.debug("Null argument to unrollAdvice()");
				return;
			}

			var callObj = marr[0]||dj_global;
			var callFunc = marr[1];

			if(!callObj[callFunc]){
				dojo.raise("function \"" + callFunc + "\" does not exist on \"" + callObj + "\"");
			}

			var aroundObj = marr[2]||dj_global;
			var aroundFunc = marr[3];
			var msg = marr[6];
			var undef;

			var to = {
				args: [],
				jp_: this,
				object: obj,
				proceed: function(){
					return callObj[callFunc].apply(callObj, to.args);
				}
			};
			to.args = aargs;

			var delay = parseInt(marr[4]);
			var hasDelay = ((!isNaN(delay))&&(marr[4]!==null)&&(typeof marr[4] != "undefined"));
			if(marr[5]){
				var rate = parseInt(marr[5]);
				var cur = new Date();
				var timerSet = false;
				if((marr["last"])&&((cur-marr.last)<=rate)){
					if(dojo.event._canTimeout){
						if(marr["delayTimer"]){
							clearTimeout(marr.delayTimer);
						}
						var tod = parseInt(rate*2); // is rate*2 naive?
						var mcpy = dojo.lang.shallowCopy(marr);
						marr.delayTimer = setTimeout(function(){
							// FIXME: on IE at least, event objects from the
							// browser can go out of scope. How (or should?) we
							// deal with it?
							mcpy[5] = 0;
							unrollAdvice(mcpy);
						}, tod);
					}
					return;
				}else{
					marr.last = cur;
				}
			}

			// FIXME: need to enforce rates for a connection here!

			if(aroundFunc){
				// NOTE: around advice can't delay since we might otherwise depend
				// on execution order!
				aroundObj[aroundFunc].call(aroundObj, to);
			}else{
				// var tmjp = dojo.event.MethodJoinPoint.getForMethod(obj, methname);
				if((hasDelay)&&((dojo.render.html)||(dojo.render.svg))){  // FIXME: the render checks are grotty!
					dj_global["setTimeout"](function(){
						if(msg){
							callObj[callFunc].call(callObj, to);
						}else{
							callObj[callFunc].apply(callObj, args);
						}
					}, delay);
				}else{ // many environments can't support delay!
					if(msg){
						callObj[callFunc].call(callObj, to);
					}else{
						callObj[callFunc].apply(callObj, args);
					}
				}
			}
		}

		var unRollSquelch = function(){
			if(this.squelch){
				try{
					return unrollAdvice.apply(this, arguments);
				}catch(e){
					dojo.debug(e);
				}
			}else{
				return unrollAdvice.apply(this, arguments);
			}
		}

		if((this["before"])&&(this.before.length>0)){
			// pass a cloned array, if this event disconnects this event forEach on this.before wont work
			dojo.lang.forEach(this.before.concat(new Array()), unRollSquelch);
		}

		var result;
		try{
			if((this["around"])&&(this.around.length>0)){
				var mi = new dojo.event.MethodInvocation(this, obj, args);
				result = mi.proceed();
			}else if(this.methodfunc){
				result = this.object[this.methodname].apply(this.object, args);
			}
		}catch(e){
			if(!this.squelch){
				dojo.debug(e,"when calling",this.methodname,"on",this.object,"with arguments",args);
				dojo.raise(e);
			}
		}

		if((this["after"])&&(this.after.length>0)){
			// see comment on this.before above
			dojo.lang.forEach(this.after.concat(new Array()), unRollSquelch);
		}

		return (this.methodfunc) ? result : null;
	},

	getArr: function(/*String*/kind){
		// summary: return a list of listeners of the past "kind"
		// kind:
		//		can be one of: "before", "after", "around", "before-around", or
		//		"after-around"
		var type = "after";
		// FIXME: we should be able to do this through props or Array.in()
		if((typeof kind == "string")&&(kind.indexOf("before")!=-1)){
			type = "before";
		}else if(kind=="around"){
			type = "around";
		}
		if(!this[type]){ this[type] = []; }
		return this[type]; // Array
	},

	kwAddAdvice: function(/*Object*/args){
		// summary:
		//		adds advice to the joinpoint with arguments in a map
		// args:
		// 		An object that can have the following properties:
		//			- adviceType
		//			- adviceObj
		//			- adviceFunc
		//			- aroundObj
		//			- aroundFunc
		//			- once
		//			- delay
		//			- rate
		//			- adviceMsg
		this.addAdvice(	args["adviceObj"], args["adviceFunc"],
						args["aroundObj"], args["aroundFunc"],
						args["adviceType"], args["precedence"],
						args["once"], args["delay"], args["rate"],
						args["adviceMsg"]);
	},

	addAdvice: function(	thisAdviceObj, thisAdvice,
							thisAroundObj, thisAround,
							adviceType, precedence,
							once, delay, rate, asMessage){
		// summary:
		//		add advice to this joinpoint using positional parameters
		// thisAdviceObj:
		//		the scope in which to locate/execute the named adviceFunc.
		// thisAdviceFunc:
		//		the name of the function being conected
		// thisAroundObj:
		//		the scope in which to locate/execute the named aroundFunc.
		// thisAroundFunc:
		//		the name of the function that will be used to mediate the
		//		advice call.
		// adviceType:
		//		Optional. String. One of "before", "after", "around",
		//		"before-around", or "after-around". FIXME
		// once:
		//		boolean that determines whether or not this advice will create
		//		a new connection if an identical advice set has already been
		//		provided. Defaults to "false".
		// delay:
		//		an optional delay (in ms), as an integer, for dispatch of a
		//		listener after the source has been fired.
		// rate:
		//		an optional rate throttling parameter (integer, in ms). When
		//		specified, this particular connection will not fire more than
		//		once in the interval specified by the rate
		// adviceMsg:
		//		boolean. Should the listener have all the parameters passed in
		//		as a single argument?
		var arr = this.getArr(adviceType);
		if(!arr){
			dojo.raise("bad this: " + this);
		}

		var ao = [thisAdviceObj, thisAdvice, thisAroundObj, thisAround, delay, rate, asMessage];

		if(once){
			if(this.hasAdvice(thisAdviceObj, thisAdvice, adviceType, arr) >= 0){
				return;
			}
		}

		if(precedence == "first"){
			arr.unshift(ao);
		}else{
			arr.push(ao);
		}
	},

	hasAdvice: function(thisAdviceObj, thisAdvice, adviceType, arr){
		// summary:
		//		returns the array index of the first existing connection
		//		betweened the passed advice and this joinpoint. Will be -1 if
		//		none exists.
		// thisAdviceObj:
		//		the scope in which to locate/execute the named adviceFunc.
		// thisAdviceFunc:
		//		the name of the function being conected
		// adviceType:
		//		Optional. String. One of "before", "after", "around",
		//		"before-around", or "after-around". FIXME
		// arr:
		//		Optional. The list of advices to search. Will be found via
		//		adviceType if not passed
		if(!arr){ arr = this.getArr(adviceType); }
		var ind = -1;
		for(var x=0; x<arr.length; x++){
			var aao = (typeof thisAdvice == "object") ? (new String(thisAdvice)).toString() : thisAdvice;
			var a1o = (typeof arr[x][1] == "object") ? (new String(arr[x][1])).toString() : arr[x][1];
			if((arr[x][0] == thisAdviceObj)&&(a1o == aao)){
				ind = x;
			}
		}
		return ind; // Integer
	},

	removeAdvice: function(thisAdviceObj, thisAdvice, adviceType, once){
		// summary:
		//		returns the array index of the first existing connection
		//		betweened the passed advice and this joinpoint. Will be -1 if
		//		none exists.
		// thisAdviceObj:
		//		the scope in which to locate/execute the named adviceFunc.
		// thisAdviceFunc:
		//		the name of the function being conected
		// adviceType:
		//		Optional. String. One of "before", "after", "around",
		//		"before-around", or "after-around". FIXME
		// once:
		//		Optional. Should this only remove the first occurance of the
		//		connection?
		var arr = this.getArr(adviceType);
		var ind = this.hasAdvice(thisAdviceObj, thisAdvice, adviceType, arr);
		if(ind == -1){
			return false;
		}
		while(ind != -1){
			arr.splice(ind, 1);
			if(once){ break; }
			ind = this.hasAdvice(thisAdviceObj, thisAdvice, adviceType, arr);
		}
		return true;
	}
});

dojo.provide("dojo.event.topic");

dojo.event.topic = new function(){
	this.topics = {};

	this.getTopic = function(/*String*/topic){
		// summary:
		//		returns a topic implementation object of type
		//		dojo.event.topic.TopicImpl
		// topic:
		//		a unique, opaque string that names the topic
		if(!this.topics[topic]){
			this.topics[topic] = new this.TopicImpl(topic);
		}
		return this.topics[topic]; // a dojo.event.topic.TopicImpl object
	}

	this.registerPublisher = function(/*String*/topic, /*Object*/obj, /*String*/funcName){
		// summary:
		//		registers a function as a publisher on a topic. Subsequent
		//		calls to the function will cause a publish event on the topic
		//		with the arguments passed to the function passed to registered
		//		listeners.
		// topic:
		//		a unique, opaque string that names the topic
		// obj:
		//		the scope to locate the function in
		// funcName:
		//		the name of the function to register
		var topic = this.getTopic(topic);
		topic.registerPublisher(obj, funcName);
	}

	this.subscribe = function(/*String*/topic, /*Object*/obj, /*String*/funcName){
		// summary:
		//		susbscribes the function to the topic. Subsequent events
		//		dispached to the topic will create a function call for the
		//		obj.funcName() function.
		// topic:
		//		a unique, opaque string that names the topic
		// obj:
		//		the scope to locate the function in
		// funcName:
		//		the name of the function to being registered as a listener
		var topic = this.getTopic(topic);
		topic.subscribe(obj, funcName);
	}

	this.unsubscribe = function(/*String*/topic, /*Object*/obj, /*String*/funcName){
		// summary:
		//		unsubscribes the obj.funcName() from the topic
		// topic:
		//		a unique, opaque string that names the topic
		// obj:
		//		the scope to locate the function in
		// funcName:
		//		the name of the function to being unregistered as a listener
		var topic = this.getTopic(topic);
		topic.unsubscribe(obj, funcName);
	}

	this.destroy = function(/*String*/topic){
		// summary:
		//		destroys the topic and unregisters all listeners
		// topic:
		//		a unique, opaque string that names the topic
		this.getTopic(topic).destroy();
		delete this.topics[topic];
	}

	this.publishApply = function(/*String*/topic, /*Array*/args){
		// summary:
		//		dispatches an event to the topic using the args array as the
		//		source for the call arguments to each listener. This is similar
		//		to JavaScript's built-in Function.apply()
		// topic:
		//		a unique, opaque string that names the topic
		// args:
		//		the arguments to be passed into listeners of the topic
		var topic = this.getTopic(topic);
		topic.sendMessage.apply(topic, args);
	}

	this.publish = function(/*String*/topic, /*Object*/message){
		// summary:
		//		manually "publish" to the passed topic
		// topic:
		//		a unique, opaque string that names the topic
		// message:
		//		can be an array of parameters (similar to publishApply), or
		//		will be treated as one of many arguments to be passed along in
		//		a "flat" unrolling
		var topic = this.getTopic(topic);
		// if message is an array, we treat it as a set of arguments,
		// otherwise, we just pass on the arguments passed in as-is
		var args = [];
		// could we use concat instead here?
		for(var x=1; x<arguments.length; x++){
			args.push(arguments[x]);
		}
		topic.sendMessage.apply(topic, args);
	}
}

dojo.event.topic.TopicImpl = function(topicName){
	// summary: a class to represent topics

	this.topicName = topicName;

	this.subscribe = function(/*Object*/listenerObject, /*Function or String*/listenerMethod){
		// summary:
		//		use dojo.event.connect() to attach the passed listener to the
		//		topic represented by this object
		// listenerObject:
		//		if a string and listenerMethod is ommitted, this is treated as
		//		the name of a function in the global namespace. If
		//		listenerMethod is provided, this is the scope to find/execute
		//		the function in.
		// listenerMethod:
		//		Optional. The function to register.
		var tf = listenerMethod||listenerObject;
		var to = (!listenerMethod) ? dj_global : listenerObject;
		return dojo.event.kwConnect({ // dojo.event.MethodJoinPoint
			srcObj:		this,
			srcFunc:	"sendMessage",
			adviceObj:	to,
			adviceFunc: tf
		});
	}

	this.unsubscribe = function(/*Object*/listenerObject, /*Function or String*/listenerMethod){
		// summary:
		//		use dojo.event.disconnect() to attach the passed listener to the
		//		topic represented by this object
		// listenerObject:
		//		if a string and listenerMethod is ommitted, this is treated as
		//		the name of a function in the global namespace. If
		//		listenerMethod is provided, this is the scope to find the
		//		function in.
		// listenerMethod:
		//		Optional. The function to unregister.
		var tf = (!listenerMethod) ? listenerObject : listenerMethod;
		var to = (!listenerMethod) ? null : listenerObject;
		return dojo.event.kwDisconnect({ // dojo.event.MethodJoinPoint
			srcObj:		this,
			srcFunc:	"sendMessage",
			adviceObj:	to,
			adviceFunc: tf
		});
	}

	this._getJoinPoint = function(){
		return dojo.event.MethodJoinPoint.getForMethod(this, "sendMessage");
	}

	this.setSquelch = function(/*Boolean*/shouldSquelch){
		// summary:
		//		determine whether or not exceptions in the calling of a
		//		listener in the chain should stop execution of the chain.
		this._getJoinPoint().squelch = shouldSquelch;
	}

	this.destroy = function(){
		// summary: disconnects all listeners from this topic
		this._getJoinPoint().disconnect();
	}

	this.registerPublisher = function(	/*Object*/publisherObject,
										/*Function or String*/publisherMethod){
		// summary:
		//		registers the passed function as a publisher on this topic.
		//		Each time the function is called, an event will be published on
		//		this topic.
		// publisherObject:
		//		if a string and listenerMethod is ommitted, this is treated as
		//		the name of a function in the global namespace. If
		//		listenerMethod is provided, this is the scope to find the
		//		function in.
		// publisherMethod:
		//		Optional. The function to register.
		dojo.event.connect(publisherObject, publisherMethod, this, "sendMessage");
	}

	this.sendMessage = function(message){
		// summary: a stub to be called when a message is sent to the topic.

		// The message has been propagated
	}
}


dojo.provide("dojo.event.browser");

// FIXME: any particular reason this is in the global scope?
dojo._ie_clobber = new function(){
	this.clobberNodes = [];

	function nukeProp(node, prop){
		// try{ node.removeAttribute(prop); 	}catch(e){ /* squelch */ }
		try{ node[prop] = null; 			}catch(e){ /* squelch */ }
		try{ delete node[prop]; 			}catch(e){ /* squelch */ }
		// FIXME: JotLive needs this, but I'm not sure if it's too slow or not
		try{ node.removeAttribute(prop);	}catch(e){ /* squelch */ }
	}

	this.clobber = function(nodeRef){
		var na;
		var tna;
		if(nodeRef){
			tna = nodeRef.all || nodeRef.getElementsByTagName("*");
			na = [nodeRef];
			for(var x=0; x<tna.length; x++){
				// if we're gonna be clobbering the thing, at least make sure
				// we aren't trying to do it twice
				if(tna[x]["__doClobber__"]){
					na.push(tna[x]);
				}
			}
		}else{
			try{ window.onload = null; }catch(e){}
			na = (this.clobberNodes.length) ? this.clobberNodes : document.all;
		}
		tna = null;
		var basis = {};
		for(var i = na.length-1; i>=0; i=i-1){
			var el = na[i];
			try{
				if(el && el["__clobberAttrs__"]){
					for(var j=0; j<el.__clobberAttrs__.length; j++){
						nukeProp(el, el.__clobberAttrs__[j]);
					}
					nukeProp(el, "__clobberAttrs__");
					nukeProp(el, "__doClobber__");
				}
			}catch(e){ /* squelch! */};
		}
		na = null;
	}
}

if(dojo.render.html.ie){
	dojo.addOnUnload(function(){
		dojo._ie_clobber.clobber();
		try{
			if((dojo["widget"])&&(dojo.widget["manager"])){
				dojo.widget.manager.destroyAll();
			}
		}catch(e){}

		// Workaround for IE leak recommended in ticket #1727 by schallm
		if(dojo.widget){
			for(var name in dojo.widget._templateCache){
				if(dojo.widget._templateCache[name].node){
					dojo.dom.destroyNode(dojo.widget._templateCache[name].node);
					dojo.widget._templateCache[name].node = null;
					delete dojo.widget._templateCache[name].node;
				}
			}
		}

		try{ window.onload = null; }catch(e){}
		try{ window.onunload = null; }catch(e){}
		dojo._ie_clobber.clobberNodes = [];
		// CollectGarbage();
	});
}

dojo.event.browser = new function(){

	var clobberIdx = 0;

	this.normalizedEventName = function(/*String*/eventName){
		switch(eventName){
			case "CheckboxStateChange":
			case "DOMAttrModified":
			case "DOMMenuItemActive":
			case "DOMMenuItemInactive":
			case "DOMMouseScroll":
			case "DOMNodeInserted":
			case "DOMNodeRemoved":
			case "RadioStateChange":
				return eventName;
				break;
			default:
				return eventName.toLowerCase();
				break;
		}
	}

	this.clean = function(/*DOMNode*/node){
		// summary:
		//		removes native event handlers so that destruction of the node
		//		will not leak memory. On most browsers this is a no-op, but
		//		it's critical for manual node removal on IE.
		// node:
		//		A DOM node. All of it's children will also be cleaned.
		if(dojo.render.html.ie){
			dojo._ie_clobber.clobber(node);
		}
	}

	this.addClobberNode = function(/*DOMNode*/node){
		// summary:
		//		register the passed node to support event stripping
		// node:
		//		A DOM node
		if(!dojo.render.html.ie){ return; }
		if(!node["__doClobber__"]){
			node.__doClobber__ = true;
			dojo._ie_clobber.clobberNodes.push(node);
			// this might not be the most efficient thing to do, but it's
			// much less error prone than other approaches which were
			// previously tried and failed
			node.__clobberAttrs__ = [];
		}
	}

	this.addClobberNodeAttrs = function(/*DOMNode*/node, /*Array*/props){
		// summary:
		//		register the passed node to support event stripping
		// node:
		//		A DOM node to stip properties from later
		// props:
		//		A list of propeties to strip from the node
		if(!dojo.render.html.ie){ return; }
		this.addClobberNode(node);
		for(var x=0; x<props.length; x++){
			node.__clobberAttrs__.push(props[x]);
		}
	}

	this.removeListener = function(	/*DOMNode*/ node,
									/*String*/	evtName,
									/*Function*/fp,
									/*Boolean*/	capture){
		// summary:
		//		clobbers the listener from the node
		// evtName:
		//		the name of the handler to remove the function from
		// node:
		//		DOM node to attach the event to
		// fp:
		//		the function to register
		// capture:
		//		Optional. should this listener prevent propigation?
		if(!capture){ var capture = false; }
		evtName = dojo.event.browser.normalizedEventName(evtName);
		if( (evtName == "onkey") || (evtName == "key") ){
			if(dojo.render.html.ie){
				this.removeListener(node, "onkeydown", fp, capture);
			}
			evtName = "onkeypress";
		}
		if(evtName.substr(0,2)=="on"){ evtName = evtName.substr(2); }
		// FIXME: this is mostly a punt, we aren't actually doing anything on IE
		if(node.removeEventListener){
			node.removeEventListener(evtName, fp, capture);
		}
	}

	this.addListener = function(/*DOMNode*/node, /*String*/evtName, /*Function*/fp, /*Boolean*/capture, /*Boolean*/dontFix){
		// summary:
		//		adds a listener to the node
		// evtName:
		//		the name of the handler to add the listener to can be either of
		//		the form "onclick" or "click"
		// node:
		//		DOM node to attach the event to
		// fp:
		//		the function to register
		// capture:
		//		Optional. Should this listener prevent propigation?
		// dontFix:
		//		Optional. Should we avoid registering a new closure around the
		//		listener to enable fixEvent for dispatch of the registered
		//		function?
		if(!node){ return; } // FIXME: log and/or bail?
		if(!capture){ var capture = false; }
		evtName = dojo.event.browser.normalizedEventName(evtName);
		if( (evtName == "onkey") || (evtName == "key") ){
			if(dojo.render.html.ie){
				this.addListener(node, "onkeydown", fp, capture, dontFix);
			}
			evtName = "onkeypress";
		}
		if(evtName.substr(0,2)!="on"){ evtName = "on"+evtName; }

		if(!dontFix){
			// build yet another closure around fp in order to inject fixEvent
			// around the resulting event
			var newfp = function(evt){
				if(!evt){ evt = window.event; }
				var ret = fp(dojo.event.browser.fixEvent(evt, this));
				if(capture){
					dojo.event.browser.stopEvent(evt);
				}
				return ret;
			}
		}else{
			newfp = fp;
		}

		if(node.addEventListener){
			node.addEventListener(evtName.substr(2), newfp, capture);
			return newfp;
		}else{
			if(typeof node[evtName] == "function" ){
				var oldEvt = node[evtName];
				node[evtName] = function(e){
					oldEvt(e);
					return newfp(e);
				}
			}else{
				node[evtName]=newfp;
			}
			if(dojo.render.html.ie){
				this.addClobberNodeAttrs(node, [evtName]);
			}
			return newfp;
		}
	}

	this.isEvent = function(/*Object*/obj){
		// summary:
		//		Tries to determine whether or not the object is a DOM event.

		// FIXME: event detection hack ... could test for additional attributes
		// if necessary
		return (typeof obj != "undefined")&&(obj)&&(typeof Event != "undefined")&&(obj.eventPhase); // Boolean
		// Event does not support instanceof in Opera, otherwise:
		//return (typeof Event != "undefined")&&(obj instanceof Event);
	}

	this.currentEvent = null;

	this.callListener = function(/*Function*/listener, /*DOMNode*/curTarget){
		// summary:
		//		calls the specified listener in the context of the passed node
		//		with the current DOM event object as the only parameter
		// listener:
		//		the function to call
		// curTarget:
		//		the Node to call the function in the scope of
		if(typeof listener != 'function'){
			dojo.raise("listener not a function: " + listener);
		}
		dojo.event.browser.currentEvent.currentTarget = curTarget;
		return listener.call(curTarget, dojo.event.browser.currentEvent);
	}

	this._stopPropagation = function(){
		dojo.event.browser.currentEvent.cancelBubble = true;
	}

	this._preventDefault = function(){
		dojo.event.browser.currentEvent.returnValue = false;
	}

	this.keys = {
		KEY_BACKSPACE: 8,
		KEY_TAB: 9,
		KEY_CLEAR: 12,
		KEY_ENTER: 13,
		KEY_SHIFT: 16,
		KEY_CTRL: 17,
		KEY_ALT: 18,
		KEY_PAUSE: 19,
		KEY_CAPS_LOCK: 20,
		KEY_ESCAPE: 27,
		KEY_SPACE: 32,
		KEY_PAGE_UP: 33,
		KEY_PAGE_DOWN: 34,
		KEY_END: 35,
		KEY_HOME: 36,
		KEY_LEFT_ARROW: 37,
		KEY_UP_ARROW: 38,
		KEY_RIGHT_ARROW: 39,
		KEY_DOWN_ARROW: 40,
		KEY_INSERT: 45,
		KEY_DELETE: 46,
		KEY_HELP: 47,
		KEY_LEFT_WINDOW: 91,
		KEY_RIGHT_WINDOW: 92,
		KEY_SELECT: 93,
		KEY_NUMPAD_0: 96,
		KEY_NUMPAD_1: 97,
		KEY_NUMPAD_2: 98,
		KEY_NUMPAD_3: 99,
		KEY_NUMPAD_4: 100,
		KEY_NUMPAD_5: 101,
		KEY_NUMPAD_6: 102,
		KEY_NUMPAD_7: 103,
		KEY_NUMPAD_8: 104,
		KEY_NUMPAD_9: 105,
		KEY_NUMPAD_MULTIPLY: 106,
		KEY_NUMPAD_PLUS: 107,
		KEY_NUMPAD_ENTER: 108,
		KEY_NUMPAD_MINUS: 109,
		KEY_NUMPAD_PERIOD: 110,
		KEY_NUMPAD_DIVIDE: 111,
		KEY_F1: 112,
		KEY_F2: 113,
		KEY_F3: 114,
		KEY_F4: 115,
		KEY_F5: 116,
		KEY_F6: 117,
		KEY_F7: 118,
		KEY_F8: 119,
		KEY_F9: 120,
		KEY_F10: 121,
		KEY_F11: 122,
		KEY_F12: 123,
		KEY_F13: 124,
		KEY_F14: 125,
		KEY_F15: 126,
		KEY_NUM_LOCK: 144,
		KEY_SCROLL_LOCK: 145
	};

	// reverse lookup
	this.revKeys = [];
	for(var key in this.keys){
		this.revKeys[this.keys[key]] = key;
	}

	this.fixEvent = function(/*Event*/evt, /*DOMNode*/sender){
		// summary:
		//		normalizes properties on the event object including event
		//		bubbling methods, keystroke normalization, and x/y positions
		// evt: the native event object
		// sender: the node to treat as "currentTarget"
		if(!evt){
			if(window["event"]){
				evt = window.event;
			}
		}

		if((evt["type"])&&(evt["type"].indexOf("key") == 0)){ // key events
			evt.keys = this.revKeys;
			// FIXME: how can we eliminate this iteration?
			for(var key in this.keys){
				evt[key] = this.keys[key];
			}
			if(evt["type"] == "keydown" && dojo.render.html.ie){
				switch(evt.keyCode){
					case evt.KEY_SHIFT:
					case evt.KEY_CTRL:
					case evt.KEY_ALT:
					case evt.KEY_CAPS_LOCK:
					case evt.KEY_LEFT_WINDOW:
					case evt.KEY_RIGHT_WINDOW:
					case evt.KEY_SELECT:
					case evt.KEY_NUM_LOCK:
					case evt.KEY_SCROLL_LOCK:
					// I'll get these in keypress after the OS munges them based on numlock
					case evt.KEY_NUMPAD_0:
					case evt.KEY_NUMPAD_1:
					case evt.KEY_NUMPAD_2:
					case evt.KEY_NUMPAD_3:
					case evt.KEY_NUMPAD_4:
					case evt.KEY_NUMPAD_5:
					case evt.KEY_NUMPAD_6:
					case evt.KEY_NUMPAD_7:
					case evt.KEY_NUMPAD_8:
					case evt.KEY_NUMPAD_9:
					case evt.KEY_NUMPAD_PERIOD:
						break; // just ignore the keys that can morph
					case evt.KEY_NUMPAD_MULTIPLY:
					case evt.KEY_NUMPAD_PLUS:
					case evt.KEY_NUMPAD_ENTER:
					case evt.KEY_NUMPAD_MINUS:
					case evt.KEY_NUMPAD_DIVIDE:
						break; // I could handle these but just pick them up in keypress
					case evt.KEY_PAUSE:
					case evt.KEY_TAB:
					case evt.KEY_BACKSPACE:
					case evt.KEY_ENTER:
					case evt.KEY_ESCAPE:
					case evt.KEY_PAGE_UP:
					case evt.KEY_PAGE_DOWN:
					case evt.KEY_END:
					case evt.KEY_HOME:
					case evt.KEY_LEFT_ARROW:
					case evt.KEY_UP_ARROW:
					case evt.KEY_RIGHT_ARROW:
					case evt.KEY_DOWN_ARROW:
					case evt.KEY_INSERT:
					case evt.KEY_DELETE:
					case evt.KEY_F1:
					case evt.KEY_F2:
					case evt.KEY_F3:
					case evt.KEY_F4:
					case evt.KEY_F5:
					case evt.KEY_F6:
					case evt.KEY_F7:
					case evt.KEY_F8:
					case evt.KEY_F9:
					case evt.KEY_F10:
					case evt.KEY_F11:
					case evt.KEY_F12:
					case evt.KEY_F12:
					case evt.KEY_F13:
					case evt.KEY_F14:
					case evt.KEY_F15:
					case evt.KEY_CLEAR:
					case evt.KEY_HELP:
						evt.key = evt.keyCode;
						break;
					default:
						if(evt.ctrlKey || evt.altKey){
							var unifiedCharCode = evt.keyCode;
							// if lower case but keycode is uppercase, convert it
							if(unifiedCharCode >= 65 && unifiedCharCode <= 90 && evt.shiftKey == false){
								unifiedCharCode += 32;
							}
							if(unifiedCharCode >= 1 && unifiedCharCode <= 26 && evt.ctrlKey){
								unifiedCharCode += 96; // 001-032 = ctrl+[a-z]
							}
							evt.key = String.fromCharCode(unifiedCharCode);
						}
				}
			} else if(evt["type"] == "keypress"){
				if(dojo.render.html.opera){
					if(evt.which == 0){
						evt.key = evt.keyCode;
					}else if(evt.which > 0){
						switch(evt.which){
							case evt.KEY_SHIFT:
							case evt.KEY_CTRL:
							case evt.KEY_ALT:
							case evt.KEY_CAPS_LOCK:
							case evt.KEY_NUM_LOCK:
							case evt.KEY_SCROLL_LOCK:
								break;
							case evt.KEY_PAUSE:
							case evt.KEY_TAB:
							case evt.KEY_BACKSPACE:
							case evt.KEY_ENTER:
							case evt.KEY_ESCAPE:
								evt.key = evt.which;
								break;
							default:
								var unifiedCharCode = evt.which;
								if((evt.ctrlKey || evt.altKey || evt.metaKey) && (evt.which >= 65 && evt.which <= 90 && evt.shiftKey == false)){
									unifiedCharCode += 32;
								}
								evt.key = String.fromCharCode(unifiedCharCode);
						}
					}
				}else if(dojo.render.html.ie){ // catch some IE keys that are hard to get in keyDown
					// key combinations were handled in onKeyDown
					if(!evt.ctrlKey && !evt.altKey && evt.keyCode >= evt.KEY_SPACE){
						evt.key = String.fromCharCode(evt.keyCode);
					}
				}else if(dojo.render.html.safari){
					switch(evt.keyCode){
						case 25: evt.key = evt.KEY_TAB; evt.shift = true;break;
						case 63232: evt.key = evt.KEY_UP_ARROW; break;
						case 63233: evt.key = evt.KEY_DOWN_ARROW; break;
						case 63234: evt.key = evt.KEY_LEFT_ARROW; break;
						case 63235: evt.key = evt.KEY_RIGHT_ARROW; break;
						case 63236: evt.key = evt.KEY_F1; break;
						case 63237: evt.key = evt.KEY_F2; break;
						case 63238: evt.key = evt.KEY_F3; break;
						case 63239: evt.key = evt.KEY_F4; break;
						case 63240: evt.key = evt.KEY_F5; break;
						case 63241: evt.key = evt.KEY_F6; break;
						case 63242: evt.key = evt.KEY_F7; break;
						case 63243: evt.key = evt.KEY_F8; break;
						case 63244: evt.key = evt.KEY_F9; break;
						case 63245: evt.key = evt.KEY_F10; break;
						case 63246: evt.key = evt.KEY_F11; break;
						case 63247: evt.key = evt.KEY_F12; break;
						case 63250: evt.key = evt.KEY_PAUSE; break;
						case 63272: evt.key = evt.KEY_DELETE; break;
						case 63273: evt.key = evt.KEY_HOME; break;
						case 63275: evt.key = evt.KEY_END; break;
						case 63276: evt.key = evt.KEY_PAGE_UP; break;
						case 63277: evt.key = evt.KEY_PAGE_DOWN; break;
						case 63302: evt.key = evt.KEY_INSERT; break;
						case 63248://prtscr
						case 63249://scrolllock
						case 63289://numlock
							break;
						default:
							evt.key = evt.charCode >= evt.KEY_SPACE ? String.fromCharCode(evt.charCode) : evt.keyCode;
					}
				}else{
					evt.key = evt.charCode > 0 ? String.fromCharCode(evt.charCode) : evt.keyCode;
				}
			}
		}
		if(dojo.render.html.ie){
			if(!evt.target){ evt.target = evt.srcElement; }
			if(!evt.currentTarget){ evt.currentTarget = (sender ? sender : evt.srcElement); }
			if(!evt.layerX){ evt.layerX = evt.offsetX; }
			if(!evt.layerY){ evt.layerY = evt.offsetY; }
			// FIXME: scroll position query is duped from dojo.html to avoid dependency on that entire module
			// DONOT replace the following to use dojo.body(), in IE, document.documentElement should be used
			// here rather than document.body
			var doc = (evt.srcElement && evt.srcElement.ownerDocument) ? evt.srcElement.ownerDocument : document;
			var docBody = ((dojo.render.html.ie55)||(doc["compatMode"] == "BackCompat")) ? doc.body : doc.documentElement;
			if(!evt.pageX){ evt.pageX = evt.clientX + (docBody.scrollLeft || 0) }
			if(!evt.pageY){ evt.pageY = evt.clientY + (docBody.scrollTop || 0) }
			// mouseover
			if(evt.type == "mouseover"){ evt.relatedTarget = evt.fromElement; }
			// mouseout
			if(evt.type == "mouseout"){ evt.relatedTarget = evt.toElement; }
			this.currentEvent = evt;
			evt.callListener = this.callListener;
			evt.stopPropagation = this._stopPropagation;
			evt.preventDefault = this._preventDefault;
		}
		return evt; // Event
	}

	this.stopEvent = function(/*Event*/evt){
		// summary:
		//		prevents propigation and clobbers the default action of the
		//		passed event
		// evt: Optional for IE. The native event object.
		if(window.event){
			evt.cancelBubble = true;
			evt.returnValue = false;
		}else{
			evt.preventDefault();
			evt.stopPropagation();
		}
	}
}

dojo.provide("dojo.event.*");

dojo.provide("dojo.widget.Manager");

// summary
//	Manager class for the widgets.
//	This is an internal class used by dojo; users shouldn't call this class directly.
dojo.widget.manager = new function(){
	this.widgets = [];
	this.widgetIds = [];

	// map of widgetId-->widget for widgets without parents (top level widgets)
	this.topWidgets = {};

	var widgetTypeCtr = {};
	var renderPrefixCache = [];

	this.getUniqueId = function (widgetType) {
		var widgetId;
		do{
			widgetId = widgetType + "_" + (widgetTypeCtr[widgetType] != undefined ?
			++widgetTypeCtr[widgetType] : widgetTypeCtr[widgetType] = 0);
		}while(this.getWidgetById(widgetId));
		return widgetId;
	}

	this.add = function(widget){
		//dojo.profile.start("dojo.widget.manager.add");
		this.widgets.push(widget);
		// Opera9 uses ID (caps)
		if(!widget.extraArgs["id"]){
			widget.extraArgs["id"] = widget.extraArgs["ID"];
		}
		// FIXME: the rest of this method is very slow!
		if(widget.widgetId == ""){
			if(widget["id"]){
				widget.widgetId = widget["id"];
			}else if(widget.extraArgs["id"]){
				widget.widgetId = widget.extraArgs["id"];
			}else{
				widget.widgetId = this.getUniqueId(widget.ns+'_'+widget.widgetType);
			}
		}
		if(this.widgetIds[widget.widgetId]){
			dojo.debug("widget ID collision on ID: "+widget.widgetId);
		}
		this.widgetIds[widget.widgetId] = widget;
		// Widget.destroy already calls removeById(), so we don't need to
		// connect() it here
		//dojo.profile.end("dojo.widget.manager.add");
	}

	this.destroyAll = function(){
		for(var x=this.widgets.length-1; x>=0; x--){
			try{
				// this.widgets[x].destroyChildren();
				this.widgets[x].destroy(true);
				delete this.widgets[x];
			}catch(e){ }
		}
	}

	// FIXME: we should never allow removal of the root widget until all others
	// are removed!
	this.remove = function(widgetIndex){
		if(dojo.lang.isNumber(widgetIndex)){
			var tw = this.widgets[widgetIndex].widgetId;
			delete this.widgetIds[tw];
			this.widgets.splice(widgetIndex, 1);
		}else{
			this.removeById(widgetIndex);
		}
	}

	// FIXME: suboptimal performance
	this.removeById = function(id) {
		if(!dojo.lang.isString(id)){
			id = id["widgetId"];
			if(!id){ dojo.debug("invalid widget or id passed to removeById"); return; }
		}
		for (var i=0; i<this.widgets.length; i++){
			if(this.widgets[i].widgetId == id){
				this.remove(i);
				break;
			}
		}
	}

	this.getWidgetById = function(id){
		if(dojo.lang.isString(id)){
			return this.widgetIds[id];
		}
		return id;
	}

	this.getWidgetsByType = function(type){
		var lt = type.toLowerCase();
		var getType = (type.indexOf(":") < 0 ?
			function(x) { return x.widgetType.toLowerCase(); } :
			function(x) { return x.getNamespacedType(); }
		);
		var ret = [];
		dojo.lang.forEach(this.widgets, function(x){
			if(getType(x) == lt){ret.push(x);}
		});
		return ret;
	}

	this.getWidgetsByFilter = function(unaryFunc, onlyOne){
		var ret = [];
		dojo.lang.every(this.widgets, function(x){
			if(unaryFunc(x)){
				ret.push(x);
				if(onlyOne){return false;}
			}
			return true;
		});
		return (onlyOne ? ret[0] : ret);
	}

	this.getAllWidgets = function() {
		return this.widgets.concat();
	}

	//	added, trt 2006-01-20
	this.getWidgetByNode = function(/* DOMNode */ node){
		var w=this.getAllWidgets();
		node = dojo.byId(node);
		for(var i=0; i<w.length; i++){
			if(w[i].domNode==node){
				return w[i];
			}
		}
		return null;
	}

	// shortcuts, baby
	this.byId = this.getWidgetById;
	this.byType = this.getWidgetsByType;
	this.byFilter = this.getWidgetsByFilter;
	this.byNode = this.getWidgetByNode;

	// map of previousally discovered implementation names to constructors
	var knownWidgetImplementations = {};

	// support manually registered widget packages
	var widgetPackages = ["dojo.widget"];
	for (var i=0; i<widgetPackages.length; i++) {
		// convenience for checking if a package exists (reverse lookup)
		widgetPackages[widgetPackages[i]] = true;
	}

	this.registerWidgetPackage = function(pname) {
		if(!widgetPackages[pname]){
			widgetPackages[pname] = true;
			widgetPackages.push(pname);
		}
	}

	this.getWidgetPackageList = function() {
		return dojo.lang.map(widgetPackages, function(elt) { return(elt!==true ? elt : undefined); });
	}

	this.getImplementation = function(widgetName, ctorObject, mixins, ns){
		// try and find a name for the widget
		var impl = this.getImplementationName(widgetName, ns);
		if(impl){
			// var tic = new Date();
			var ret = ctorObject ? new impl(ctorObject) : new impl();
			// dojo.debug(new Date() - tic);
			return ret;
		}
	}

	function buildPrefixCache() {
		for(var renderer in dojo.render){
			if(dojo.render[renderer]["capable"] === true){
				var prefixes = dojo.render[renderer].prefixes;
				for(var i=0; i<prefixes.length; i++){
					renderPrefixCache.push(prefixes[i].toLowerCase());
				}
			}
		}
		// make sure we don't HAVE to prefix widget implementation names
		// with anything to get them to render
		//renderPrefixCache.push("");
		// empty prefix is included automatically
	}

	var findImplementationInModule = function(lowerCaseWidgetName, module){
		if(!module){return null;}
		for(var i=0, l=renderPrefixCache.length, widgetModule; i<=l; i++){
			widgetModule = (i<l ? module[renderPrefixCache[i]] : module);
			if(!widgetModule){continue;}
			for(var name in widgetModule){
				if(name.toLowerCase() == lowerCaseWidgetName){
					return widgetModule[name];
				}
			}
		}
		return null;
	}

	var findImplementation = function(lowerCaseWidgetName, moduleName){
		// locate registered widget module
		var module = dojo.evalObjPath(moduleName, false);
		// locate a widget implementation in the registered module for our current rendering environment
		return (module ? findImplementationInModule(lowerCaseWidgetName, module) : null);
	}

	this.getImplementationName = function(widgetName, ns){
		/*
		 * Locate an implementation (constructor) for 'widgetName' in namespace 'ns'
		 * widgetNames are case INSENSITIVE
		 *
		 * 1. Return value from implementation cache, if available, for quick turnaround.
		 * 2. Locate a namespace registration for 'ns'
		 * 3. If no namespace found, register the conventional one (ns.widget)
		 * 4. Allow the namespace resolver (if any) to load a module for this widget.
		 * 5. Permute the widget name and capable rendering prefixes to locate, cache, and return
		 *    an appropriate widget implementation.
		 * 6. If no implementation is found, attempt to load the namespace manifest,
		 *    and then look again for an implementation to cache and return.
		 * 7. Use the deprecated widgetPackages registration system to attempt to locate the widget
		 * 8. Fail
		 */
		var lowerCaseWidgetName = widgetName.toLowerCase();

		// default to dojo namespace
		ns=ns||"dojo";
		// use cache if available
		var imps = knownWidgetImplementations[ns] || (knownWidgetImplementations[ns]={});
		//if(!knownWidgetImplementations[ns]){knownWidgetImplementations[ns]={};}
		var impl = imps[lowerCaseWidgetName];
		if(impl){
			return impl;
		}

		// (one time) store a list of the render prefixes we are capable of rendering
		if(!renderPrefixCache.length){
			buildPrefixCache();
		}

		// lookup namespace
		var nsObj = dojo.ns.get(ns);
		if(!nsObj){
			// default to <ns>.widget by convention
			dojo.ns.register(ns, ns + '.widget');
			nsObj = dojo.ns.get(ns);
		}

		// allow the namespace to resolve the widget module
		if(nsObj){nsObj.resolve(widgetName);}

		// locate a widget implementation in the registered module for our current rendering environment
		impl = findImplementation(lowerCaseWidgetName, nsObj.module);
		if(impl){return(imps[lowerCaseWidgetName] = impl)};

		// try to load a manifest to resolve this implemenation
		nsObj = dojo.ns.require(ns);
		if((nsObj)&&(nsObj.resolver)){
			nsObj.resolve(widgetName);
			impl = findImplementation(lowerCaseWidgetName, nsObj.module);
			if(impl){return(imps[lowerCaseWidgetName] = impl)};
		}

		// this is an error condition under new rules
		dojo.deprecated('dojo.widget.Manager.getImplementationName',
			'Could not locate widget implementation for "' + widgetName + '" in "' + nsObj.module + '" registered to namespace "' + nsObj.name + '". '
			+ "Developers must specify correct namespaces for all non-Dojo widgets", "0.5");

		// backward compat: if the user has not specified any namespace and their widget is not in dojo.widget.*
		// search registered widget packages [sic]
		// note: registerWidgetPackage itself is now deprecated
		for(var i=0; i<widgetPackages.length; i++){
			impl = findImplementation(lowerCaseWidgetName, widgetPackages[i]);
			if(impl){return(imps[lowerCaseWidgetName] = impl)};
		}

		throw new Error('Could not locate widget implementation for "' + widgetName + '" in "' + nsObj.module + '" registered to namespace "' + nsObj.name + '"');
	}

	// FIXME: does it even belong in this module?
	// NOTE: this method is implemented by DomWidget.js since not all
	// hostenv's would have an implementation.
	/*this.getWidgetFromPrimitive = function(baseRenderType){
		dojo.unimplemented("dojo.widget.manager.getWidgetFromPrimitive");
	}

	this.getWidgetFromEvent = function(nativeEvt){
		dojo.unimplemented("dojo.widget.manager.getWidgetFromEvent");
	}*/

	// Catch window resize events and notify top level widgets
	this.resizing=false;
	this.onWindowResized = function(){
		if(this.resizing){
			return;	// duplicate event
		}
		try{
			this.resizing=true;
			for(var id in this.topWidgets){
				var child = this.topWidgets[id];
				if(child.checkSize ){
					child.checkSize();
				}
			}
		}catch(e){
		}finally{
			this.resizing=false;
		}
	}
	if(typeof window != "undefined") {
		dojo.addOnLoad(this, 'onWindowResized');							// initial sizing
		dojo.event.connect(window, 'onresize', this, 'onWindowResized');	// window resize
	}

	// FIXME: what else?
};

(function(){
	var dw = dojo.widget;
	var dwm = dw.manager;
	var h = dojo.lang.curry(dojo.lang, "hitch", dwm);
	var g = function(oldName, newName){
		dw[(newName||oldName)] = h(oldName);
	}
	// copy the methods from the default manager (this) to the widget namespace
	g("add", "addWidget");
	g("destroyAll", "destroyAllWidgets");
	g("remove", "removeWidget");
	g("removeById", "removeWidgetById");
	g("getWidgetById");
	g("getWidgetById", "byId");
	g("getWidgetsByType");
	g("getWidgetsByFilter");
	g("getWidgetsByType", "byType");
	g("getWidgetsByFilter", "byFilter");
	g("getWidgetByNode", "byNode");
	dw.all = function(n){
		var widgets = dwm.getAllWidgets.apply(dwm, arguments);
		if(arguments.length > 0) {
			return widgets[n];
		}
		return widgets;
	}
	g("registerWidgetPackage");
	g("getImplementation", "getWidgetImplementation");
	g("getImplementationName", "getWidgetImplementationName");

	dw.widgets = dwm.widgets;
	dw.widgetIds = dwm.widgetIds;
	dw.root = dwm.root;
})();

dojo.provide("dojo.uri.Uri");

dojo.uri = new function() {
	this.dojoUri = function (/*dojo.uri.Uri||String*/uri) {
		// summary: returns a Uri object resolved relative to the dojo root
		return new dojo.uri.Uri(dojo.hostenv.getBaseScriptUri(), uri);
	}

	this.moduleUri = function(/*String*/module, /*dojo.uri.Uri||String*/uri){
		// summary: returns a Uri object relative to a module
		// description: Examples: dojo.uri.moduleUri("dojo","Editor"), or dojo.uri.moduleUri("acme","someWidget")
		var loc = dojo.hostenv.getModuleSymbols(module).join('/');
		//var loc = dojo.hostenv.getModulePrefix(module);
		if(!loc){return null;}
		if(loc.lastIndexOf("/") != loc.length-1){loc += "/";}
		return new dojo.uri.Uri(dojo.hostenv.getBaseScriptUri()+loc,uri);
	}

	this.Uri = function (/*dojo.uri.Uri||String...*/) {
		// summary: Constructor to create an object representing a URI.
		// description:
		//  Each argument is evaluated in order relative to the next until
		//  a canonical uri is produced. To get an absolute Uri relative
		//  to the current document use
		//      new dojo.uri.Uri(document.baseURI, uri)

		// TODO: support for IPv6, see RFC 2732

		// resolve uri components relative to each other
		var uri = arguments[0];
		for (var i = 1; i < arguments.length; i++) {
			if(!arguments[i]) { continue; }

			// Safari doesn't support this.constructor so we have to be explicit
			var relobj = new dojo.uri.Uri(arguments[i].toString());
			var uriobj = new dojo.uri.Uri(uri.toString());

			if ((relobj.path=="")&&(relobj.scheme==null)&&(relobj.authority==null)&&(relobj.query==null)) {
				if (relobj.fragment != null) { uriobj.fragment = relobj.fragment; }
				relobj = uriobj;
			} else if (relobj.scheme == null) {
				relobj.scheme = uriobj.scheme;

				if (relobj.authority == null) {
					relobj.authority = uriobj.authority;

					if (relobj.path.charAt(0) != "/") {
						var path = uriobj.path.substring(0,
							uriobj.path.lastIndexOf("/") + 1) + relobj.path;

						var segs = path.split("/");
						for (var j = 0; j < segs.length; j++) {
							if (segs[j] == ".") {
								if (j == segs.length - 1) { segs[j] = ""; }
								else { segs.splice(j, 1); j--; }
							} else if (j > 0 && !(j == 1 && segs[0] == "") &&
								segs[j] == ".." && segs[j-1] != "..") {

								if (j == segs.length - 1) { segs.splice(j, 1); segs[j - 1] = ""; }
								else { segs.splice(j - 1, 2); j -= 2; }
							}
						}
						relobj.path = segs.join("/");
					}
				}
			}

			uri = "";
			if (relobj.scheme != null) { uri += relobj.scheme + ":"; }
			if (relobj.authority != null) { uri += "//" + relobj.authority; }
			uri += relobj.path;
			if (relobj.query != null) { uri += "?" + relobj.query; }
			if (relobj.fragment != null) { uri += "#" + relobj.fragment; }
		}

		this.uri = uri.toString();

		// break the uri into its main components
		var regexp = "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?$";
		var r = this.uri.match(new RegExp(regexp));

		this.scheme = r[2] || (r[1] ? "" : null);
		this.authority = r[4] || (r[3] ? "" : null);
		this.path = r[5]; // can never be undefined
		this.query = r[7] || (r[6] ? "" : null);
		this.fragment  = r[9] || (r[8] ? "" : null);

		if (this.authority != null) {
			// server based naming authority
			regexp = "^((([^:]+:)?([^@]+))@)?([^:]*)(:([0-9]+))?$";
			r = this.authority.match(new RegExp(regexp));

			this.user = r[3] || null;
			this.password = r[4] || null;
			this.host = r[5];
			this.port = r[7] || null;
		}

		this.toString = function(){ return this.uri; }
	}
};

dojo.provide("dojo.uri.*");

dojo.provide("dojo.html.common");

dojo.lang.mixin(dojo.html, dojo.dom);

dojo.html.body = function(){
	dojo.deprecated("dojo.html.body() moved to dojo.body()", "0.5");
	return dojo.body();
}

// FIXME: we are going to assume that we can throw any and every rendering
// engine into the IE 5.x box model. In Mozilla, we do this w/ CSS.
// Need to investigate for KHTML and Opera

dojo.html.getEventTarget = function(/* DOMEvent */evt){
	//	summary
	//	Returns the target of an event
	if(!evt) { evt = dojo.global().event || {} };
	var t = (evt.srcElement ? evt.srcElement : (evt.target ? evt.target : null));
	while((t)&&(t.nodeType!=1)){ t = t.parentNode; }
	return t;	//	HTMLElement
}

dojo.html.getViewport = function(){
	//	summary
	//	Returns the dimensions of the viewable area of a browser window
	var _window = dojo.global();
	var _document = dojo.doc();
	var w = 0;
	var h = 0;

	if(dojo.render.html.mozilla){
		// mozilla
		w = _document.documentElement.clientWidth;
		h = _window.innerHeight;
	}else if(!dojo.render.html.opera && _window.innerWidth){
		//in opera9, dojo.body().clientWidth should be used, instead
		//of window.innerWidth/document.documentElement.clientWidth
		//so we have to check whether it is opera
		w = _window.innerWidth;
		h = _window.innerHeight;
	} else if (!dojo.render.html.opera && dojo.exists(_document, "documentElement.clientWidth")){
		// IE6 Strict
		var w2 = _document.documentElement.clientWidth;
		// this lets us account for scrollbars
		if(!w || w2 && w2 < w) {
			w = w2;
		}
		h = _document.documentElement.clientHeight;
	} else if (dojo.body().clientWidth){
		// IE, Opera
		w = dojo.body().clientWidth;
		h = dojo.body().clientHeight;
	}
	return { width: w, height: h };	//	object
}

dojo.html.getScroll = function(){
	//	summary
	//	Returns the scroll position of the document
	var _window = dojo.global();
	var _document = dojo.doc();
	var top = _window.pageYOffset || _document.documentElement.scrollTop || dojo.body().scrollTop || 0;
	var left = _window.pageXOffset || _document.documentElement.scrollLeft || dojo.body().scrollLeft || 0;
	return {
		top: top,
		left: left,
		offset:{ x: left, y: top }	//	note the change, NOT an Array with added properties.
	};	//	object
}

dojo.html.getParentByType = function(/* HTMLElement */node, /* string */type) {
	//	summary
	//	Returns the first ancestor of node with tagName type.
	var _document = dojo.doc();
	var parent = dojo.byId(node);
	type = type.toLowerCase();
	while((parent)&&(parent.nodeName.toLowerCase()!=type)){
		if(parent==(_document["body"]||_document["documentElement"])){
			return null;
		}
		parent = parent.parentNode;
	}
	return parent;	//	HTMLElement
}

dojo.html.getAttribute = function(/* HTMLElement */node, /* string */attr){
	//	summary
	//	Returns the value of attribute attr from node.
	node = dojo.byId(node);
	// FIXME: need to add support for attr-specific accessors
	if((!node)||(!node.getAttribute)){
		// if(attr !== 'nwType'){
		//	alert("getAttr of '" + attr + "' with bad node");
		// }
		return null;
	}
	var ta = typeof attr == 'string' ? attr : new String(attr);

	// first try the approach most likely to succeed
	var v = node.getAttribute(ta.toUpperCase());
	if((v)&&(typeof v == 'string')&&(v!="")){
		return v;	//	string
	}

	// try returning the attributes value, if we couldn't get it as a string
	if(v && v.value){
		return v.value;	//	string
	}

	// this should work on Opera 7, but it's a little on the crashy side
	if((node.getAttributeNode)&&(node.getAttributeNode(ta))){
		return (node.getAttributeNode(ta)).value;	//	string
	}else if(node.getAttribute(ta)){
		return node.getAttribute(ta);	//	string
	}else if(node.getAttribute(ta.toLowerCase())){
		return node.getAttribute(ta.toLowerCase());	//	string
	}
	return null;	//	string
}

dojo.html.hasAttribute = function(/* HTMLElement */node, /* string */attr){
	//	summary
	//	Determines whether or not the specified node carries a value for the attribute in question.
	return dojo.html.getAttribute(dojo.byId(node), attr) ? true : false;	//	boolean
}

dojo.html.getCursorPosition = function(/* DOMEvent */e){
	//	summary
	//	Returns the mouse position relative to the document (not the viewport).
	//	For example, if you have a document that is 10000px tall,
	//	but your browser window is only 100px tall,
	//	if you scroll to the bottom of the document and call this function it
	//	will return {x: 0, y: 10000}
	//	NOTE: for events delivered via dojo.event.connect() and/or dojoAttachEvent (for widgets),
	//	you can just access evt.pageX and evt.pageY, rather than calling this function.
	e = e || dojo.global().event;
	var cursor = {x:0, y:0};
	if(e.pageX || e.pageY){
		cursor.x = e.pageX;
		cursor.y = e.pageY;
	}else{
		var de = dojo.doc().documentElement;
		var db = dojo.body();
		cursor.x = e.clientX + ((de||db)["scrollLeft"]) - ((de||db)["clientLeft"]);
		cursor.y = e.clientY + ((de||db)["scrollTop"]) - ((de||db)["clientTop"]);
	}
	return cursor;	//	object
}

dojo.html.isTag = function(/* HTMLElement */node) {
	//	summary
	//	Like dojo.dom.isTag, except case-insensitive
	node = dojo.byId(node);
	if(node && node.tagName) {
		for (var i=1; i<arguments.length; i++){
			if (node.tagName.toLowerCase()==String(arguments[i]).toLowerCase()){
				return String(arguments[i]).toLowerCase();	//	string
			}
		}
	}
	return "";	//	string
}

//define dojo.html.createExternalElement for IE to workaround the annoying activation "feature" in new IE
//details: http://msdn.microsoft.com/library/default.asp?url=/workshop/author/dhtml/overview/activating_activex.asp
if(dojo.render.html.ie && !dojo.render.html.ie70){
	//only define createExternalElement for IE in none https to avoid "mixed content" warning dialog
	if(window.location.href.substr(0,6).toLowerCase() != "https:"){
		(function(){
			// FIXME: this seems not to work correctly on IE 7!!

			//The trick is to define a function in a script.src property:
			// <script src="javascript:'function createExternalElement(){...}'"></script>,
			//which will be treated as an external javascript file in IE
			var xscript = dojo.doc().createElement('script');
			xscript.src = "javascript:'dojo.html.createExternalElement=function(doc, tag){ return doc.createElement(tag); }'";
			dojo.doc().getElementsByTagName("head")[0].appendChild(xscript);
		})();
	}
}else{
	//for other browsers, simply use document.createElement
	//is enough
	dojo.html.createExternalElement = function(/* HTMLDocument */doc, /* string */tag){
		//	summary
		//	Creates an element in the HTML document, here for ActiveX activation workaround.
		return doc.createElement(tag);	//	HTMLElement
	}
}

dojo.html._callDeprecated = function(inFunc, replFunc, args, argName, retValue){
	dojo.deprecated("dojo.html." + inFunc,
					"replaced by dojo.html." + replFunc + "(" + (argName ? "node, {"+ argName + ": " + argName + "}" : "" ) + ")" + (retValue ? "." + retValue : ""), "0.5");
	var newArgs = [];
	if(argName){ var argsIn = {}; argsIn[argName] = args[1]; newArgs.push(args[0]); newArgs.push(argsIn); }
	else { newArgs = args }
	var ret = dojo.html[replFunc].apply(dojo.html, args);
	if(retValue){ return ret[retValue]; }
	else { return ret; }
}

dojo.html.getViewportWidth = function(){
	return dojo.html._callDeprecated("getViewportWidth", "getViewport", arguments, null, "width");
}
dojo.html.getViewportHeight = function(){
	return dojo.html._callDeprecated("getViewportHeight", "getViewport", arguments, null, "height");
}
dojo.html.getViewportSize = function(){
	return dojo.html._callDeprecated("getViewportSize", "getViewport", arguments);
}
dojo.html.getScrollTop = function(){
	return dojo.html._callDeprecated("getScrollTop", "getScroll", arguments, null, "top");
}
dojo.html.getScrollLeft = function(){
	return dojo.html._callDeprecated("getScrollLeft", "getScroll", arguments, null, "left");
}
dojo.html.getScrollOffset = function(){
	return dojo.html._callDeprecated("getScrollOffset", "getScroll", arguments, null, "offset");
}

dojo.provide("dojo.a11y");


dojo.a11y = {
	// imgPath: String path to the test image for determining if images are displayed or not
	// doAccessibleCheck: Boolean if true will perform check for need to create accessible widgets
	// accessible: Boolean uninitialized when null (accessible check has not been performed)
	//   if true generate accessible widgets
	imgPath:dojo.uri.dojoUri("src/widget/templates/images"),
	doAccessibleCheck: true,
	accessible: null,

	checkAccessible: function(){
	// summary:
	//		perform check for accessibility if accessibility checking is turned
	//		on and the accessibility test has not been performed yet
		if(this.accessible === null){
			this.accessible = false; //default
			if(this.doAccessibleCheck == true){
				this.accessible = this.testAccessible();
			}
		}
		return this.accessible; /* Boolean */
	},

	testAccessible: function(){
	// summary:
	//		Always perform the accessibility check to determine if high
	//		contrast mode is on or display of images are turned off. Currently only checks
	//		in IE and Mozilla.
		this.accessible = false; //default
		if (dojo.render.html.ie || dojo.render.html.mozilla){
			var div = document.createElement("div");
			//div.style.color="rgb(153,204,204)";
			div.style.backgroundImage = "url(\"" + this.imgPath + "/tab_close.gif\")";
			// must add to hierarchy before can view currentStyle below
			dojo.body().appendChild(div);
			// in FF and IE the value for the current background style of the added div
			// will be "none" in high contrast mode
			// in FF the return value will be url(invalid-url:) when running over http
			var bkImg = null;
			if (window.getComputedStyle  ) {
				var cStyle = getComputedStyle(div, "");
				bkImg = cStyle.getPropertyValue("background-image");
			}else{
				bkImg = div.currentStyle.backgroundImage;
			}
			var bUseImgElem = false;
			if (bkImg != null && (bkImg == "none" || bkImg == "url(invalid-url:)" )) {
				this.accessible = true;
			}
			/*
			if(this.accessible == false && document.images){
				// test if images are off in IE
				var testImg = new Image();
				if(testImg.fileSize) {
					testImg.src = this.imgPath + "/tab_close.gif";
					if(testImg.fileSize < 0){
						this.accessible = true;
					}
				}
			}*/
			dojo.body().removeChild(div);
		}
		return this.accessible; /* Boolean */
	},

	setCheckAccessible: function(/* Boolean */ bTest){
	// summary:
	//		Set whether or not to check for accessibility mode.  Default value
	//		of module is true - perform check for accessibility modes.
	//		bTest: Boolean - true to check; false to turn off checking
		this.doAccessibleCheck = bTest;
	},

	setAccessibleMode: function(){
	// summary:
	//		perform the accessibility check and sets the correct mode to load
	//		a11y widgets. Only runs if test for accessiiblity has not been performed yet.
	//		Call testAccessible() to force the test.
		if (this.accessible === null){
			if (this.checkAccessible()){
				dojo.render.html.prefixes.unshift("a11y");
			}
		}
		return this.accessible; /* Boolean */
	}
};

//dojo.hostenv.modulesLoadedListeners.unshift(function() { dojo.a11y.setAccessibleMode(); });
//dojo.event.connect("before", dojo.hostenv, "makeWidgets", dojo.a11y, "setAccessibleMode");

dojo.provide("dojo.widget.Widget");


dojo.declare("dojo.widget.Widget", null,
	function(){
		// these properties aren't primitives and need to be created on a per-item
		// basis.

		// children: Array
		//		a list of all of the widgets that have been added as children of
		//		this component. Should only have values if isContainer is true.
		this.children = [];

		// extraArgs: Object
		//		a map of properties which the widget system tried to assign from
		//		user input but did not correspond to any of the properties set on
		//		the class prototype. These names will also be available in all
		//		lower-case form in this map
		this.extraArgs = {};
	},
{
	// parent: Widget
	//		the parent of this widget
	parent: null,

	// isTopLevel: Boolean
	//		should this widget eat all events that bubble up to it?
	//		obviously, top-level and modal widgets should set these appropriately
	isTopLevel:  false,

	// disabled: Boolean
	//		should this widget respond to user input?
	//		in markup, this is specified as "disabled='disabled'", or just "disabled"
	disabled: false,

	// isContainer: Boolean
	//		can this widget contain other widgets?
	isContainer: false,

	// widgetId: String
	//		a unique, opaque ID string that can be assigned by users or by the
	//		system. If the developer passes an ID which is known not to be
	//		unique, the specified ID is ignored and the system-generated ID is
	//		used instead.
	widgetId: "",

	// widgetType: String
	//		used for building generic widgets
	widgetType: "Widget",

	// ns: String
	//		defaults to 'dojo'.  "namespace" is a reserved word in JavaScript, so we abbreviate
	ns: "dojo",

	getNamespacedType: function(){
		// summary:
		//		get the "full" name of the widget. If the widget comes from the
		//		"dojo" namespace and is a Button, calling this method will
		//		return "dojo:button", all lower-case
		return (this.ns ? this.ns + ":" + this.widgetType : this.widgetType).toLowerCase(); // String
	},

	toString: function(){
		// summary:
		//		returns a string that represents the widget. When a widget is
		//		cast to a string, this method will be used to generate the
		//		output. Currently, it does not implement any sort of reversable
		//		serialization.
		return '[Widget ' + this.getNamespacedType() + ', ' + (this.widgetId || 'NO ID') + ']'; // String
	},

	repr: function(){
		// summary: returns the string representation of the widget.
		return this.toString(); // String
	},

	enable: function(){
		// summary:
		//		enables the widget, usually involving unmasking inputs and
		//		turning on event handlers. Not implemented here.
		this.disabled = false;
	},

	disable: function(){
		// summary:
		//		disables the widget, usually involves masking inputs and
		//		unsetting event handlers. Not implemented here.
		this.disabled = true;
	},

	// TODO:
	//	1) this would be better in HtmlWidget rather than here?
	//	2) since many widgets don't care if they've been resized, maybe this should be a mixin?
	onResized: function(){
		// summary:
		//		A signal that widgets will call when they have been resized.
		//		Can be connected to for determining if a layout needs to be
		//		reflowed. Clients should override this function to do special
		//		processing, then call this.notifyChildrenOfResize() to notify
		//		children of resize.
		this.notifyChildrenOfResize();
	},

	notifyChildrenOfResize: function(){
		// summary: dispatches resized events to all children of this widget
		for(var i=0; i<this.children.length; i++){
			var child = this.children[i];
			//dojo.debug(this.widgetId + " resizing child " + child.widgetId);
			if( child.onResized ){
				child.onResized();
			}
		}
	},

	create: function(args, fragment, parent, ns){
		// summary:
		//		'create' manages the initialization part of the widget
		//		lifecycle. It's called implicitly when any widget is created.
		//		All other initialization functions for widgets, except for the
		//		constructor, are called as a result of 'create' being fired.
		// args: Object
		//		a normalized view of the parameters that the widget should take
		// fragment: Object
		//		if the widget is being instantiated from markup, this object
		// parent: Widget?
		//		the widget, if any, that this widget will be the child of.  If
		//		none is passed, the global default widget is used.
		// ns: String?
		//		what namespace the widget belongs to
		// description:
		//		to understand the process by which widgets are instantiated, it
		//		is critical to understand what other methods 'create' calls and
		//		which of them you'll want to over-ride. Of course, adventurous
		//		developers could over-ride 'create' entirely, but this should
		//		only be done as a last resort.
		//
		//		Below is a list of the methods that are called, in the order
		//		they are fired, along with notes about what they do and if/when
		//		you should over-ride them in your widget:
		//
		//			mixInProperties:
		//				takes the args and does lightweight type introspection
		//				on pre-existing object properties to initialize widget
		//				values by casting the values that are passed in args
		//			postMixInProperties:
		//				a stub function that you can over-ride to modify
		//				variables that may have been naively assigned by
		//				mixInProperties
		//			# widget is added to manager object here
		//			buildRendering
		//				subclasses use this method to handle all UI initialization
		//			initialize:
		//				a stub function that you can over-ride.
		//			postInitialize:
		//				a stub function that you can over-ride.
		//			postCreate
		//				a stub function that you can over-ride to modify take
		//				actions once the widget has been placed in the UI
		//
		//		all of these functions are passed the same arguments as are
		//		passed to 'create'

		//dojo.profile.start(this.widgetType + " create");
		if(ns){
			this.ns = ns;
		}
		// dojo.debug(this.widgetType, "create");
		//dojo.profile.start(this.widgetType + " satisfyPropertySets");
		this.satisfyPropertySets(args, fragment, parent);
		//dojo.profile.end(this.widgetType + " satisfyPropertySets");
		// dojo.debug(this.widgetType, "-> mixInProperties");
		//dojo.profile.start(this.widgetType + " mixInProperties");
		this.mixInProperties(args, fragment, parent);
		//dojo.profile.end(this.widgetType + " mixInProperties");
		// dojo.debug(this.widgetType, "-> postMixInProperties");
		//dojo.profile.start(this.widgetType + " postMixInProperties");
		this.postMixInProperties(args, fragment, parent);
		//dojo.profile.end(this.widgetType + " postMixInProperties");
		// dojo.debug(this.widgetType, "-> dojo.widget.manager.add");
		dojo.widget.manager.add(this);
		// dojo.debug(this.widgetType, "-> buildRendering");
		//dojo.profile.start(this.widgetType + " buildRendering");
		this.buildRendering(args, fragment, parent);
		//dojo.profile.end(this.widgetType + " buildRendering");
		// dojo.debug(this.widgetType, "-> initialize");
		//dojo.profile.start(this.widgetType + " initialize");
		this.initialize(args, fragment, parent);
		//dojo.profile.end(this.widgetType + " initialize");
		// dojo.debug(this.widgetType, "-> postInitialize");
		// postinitialize includes subcomponent creation
		// profile is put directly to function
		this.postInitialize(args, fragment, parent);
		// dojo.debug(this.widgetType, "-> postCreate");
		//dojo.profile.start(this.widgetType + " postCreate");
		this.postCreate(args, fragment, parent);
		//dojo.profile.end(this.widgetType + " postCreate");
		// dojo.debug(this.widgetType, "done!");

		//dojo.profile.end(this.widgetType + " create");

		return this;
	},

	destroy: function(finalize){
		// summary:
		// 		Destroy this widget and it's descendants. This is the generic
		// 		"destructor" function that all widget users should call to
		// 		clealy discard with a widget. Once a widget is destroyed, it's
		// 		removed from the manager object.
		// finalize: Boolean
		//		is this function being called part of global environment
		//		tear-down?

		// FIXME: this is woefully incomplete
		if(this.parent){
			this.parent.removeChild(this);
		}
		this.destroyChildren();
		this.uninitialize();
		this.destroyRendering(finalize);
		dojo.widget.manager.removeById(this.widgetId);
	},

	destroyChildren: function(){
		// summary:
		//		Recursively destroy the children of this widget and their
		//		descendents.
		var widget;
		var i=0;
		while(this.children.length > i){
			widget = this.children[i];
			if (widget instanceof dojo.widget.Widget) { // find first widget
				this.removeChild(widget);
				widget.destroy();
				continue;
			}

			i++; // skip data object
		}

	},

	getChildrenOfType: function(/*String*/type, recurse){
		// summary:
		//		return an array of descendant widgets who match the passed type
		// recurse: Boolean
		//		should we try to get all descendants that match? Defaults to
		//		false.
		var ret = [];
		var isFunc = dojo.lang.isFunction(type);
		if(!isFunc){
			type = type.toLowerCase();
		}
		for(var x=0; x<this.children.length; x++){
			if(isFunc){
				if(this.children[x] instanceof type){
					ret.push(this.children[x]);
				}
			}else{
				if(this.children[x].widgetType.toLowerCase() == type){
					ret.push(this.children[x]);
				}
			}
			if(recurse){
				ret = ret.concat(this.children[x].getChildrenOfType(type, recurse));
			}
		}
		return ret; // Array
	},

	getDescendants: function(){
		// returns: a flattened array of all direct descendants including self
		var result = [];
		var stack = [this];
		var elem;
		while ((elem = stack.pop())){
			result.push(elem);
			// a child may be data object without children field set (not widget)
			if (elem.children) {
				dojo.lang.forEach(elem.children, function(elem) { stack.push(elem); });
			}
		}
		return result; // Array
	},


	isFirstChild: function(){
		return this === this.parent.children[0]; // Boolean
	},

	isLastChild: function() {
		return this === this.parent.children[this.parent.children.length-1]; // Boolean
	},

	satisfyPropertySets: function(args){
		// summary: not implemented!

		// dojo.profile.start("satisfyPropertySets");
		// get the default propsets for our component type
		/*
		var typePropSets = []; // FIXME: need to pull these from somewhere!
		var localPropSets = []; // pull out propsets from the parser's return structure

		// for(var x=0; x<args.length; x++){
		// }

		for(var x=0; x<typePropSets.length; x++){
		}

		for(var x=0; x<localPropSets.length; x++){
		}
		*/
		// dojo.profile.end("satisfyPropertySets");

		return args;
	},

	mixInProperties: function(args, /*Object*/frag){
		// summary:
		// 		takes the list of properties listed in args and sets values of
		// 		the current object based on existence of properties with the
		// 		same name (case insensitive) and the type of the pre-existing
		// 		property. This is a lightweight conversion and is not intended
		// 		to capture custom type semantics.
		// args: Object
		//		A map of properties and values to set on the current object. By
		//		default it is assumed that properties in args are in string
		//		form and need to be converted. However, if there is a
		//		'fastMixIn' property with the value 'true' in the args param,
		//		this assumption is ignored and all values in args are copied
		//		directly to the current object without any form of type
		//		casting.
		// description:
		//		The mix-in code attempts to do some type-assignment based on
		//		PRE-EXISTING properties of the "this" object. When a named
		//		property of args is located, it is first tested to make
		//		sure that the current object already "has one". Properties
		//		which are undefined in the base widget are NOT settable here.
		//		The next step is to try to determine type of the pre-existing
		//		property. If it's a string, the property value is simply
		//		assigned. If a function, it is first cast using "new
		//		Function()" and the execution scope modified such that it
		//		always evaluates in the context of the current object. This
		//		listener is then added to the original function via
		//		dojo.event.connect(). If an Array, the system attempts to split
		//		the string value on ";" chars, and no further processing is
		//		attempted (conversion of array elements to a integers, for
		//		instance). If the property value is an Object
		//		(testObj.constructor === Object), the property is split first
		//		on ";" chars, secondly on ":" chars, and the resulting
		//		key/value pairs are assigned to an object in a map style. The
		//		onus is on the property user to ensure that all property values
		//		are converted to the expected type before usage. Properties
		//		which do not occur in the "this" object are assigned to the
		//		this.extraArgs map using both the original name and the
		//		lower-case name of the property. This allows for consistent
		//		access semantics regardless of the case preservation of the
		//		source of the property names.

		if((args["fastMixIn"])||(frag["fastMixIn"])){
			// dojo.profile.start("mixInProperties_fastMixIn");
			// fast mix in assumes case sensitivity, no type casting, etc...
			// dojo.lang.mixin(this, args);
			for(var x in args){
				this[x] = args[x];
			}
			// dojo.profile.end("mixInProperties_fastMixIn");
			return;
		}
		// dojo.profile.start("mixInProperties");

		var undef;

		// NOTE: we cannot assume that the passed properties are case-correct
		// (esp due to some browser bugs). Therefore, we attempt to locate
		// properties for assignment regardless of case. This may cause
		// problematic assignments and bugs in the future and will need to be
		// documented with big bright neon lights.

		// FIXME: fails miserably if a mixin property has a default value of null in
		// a widget

		// NOTE: caching lower-cased args in the prototype is only
		// acceptable if the properties are invariant.
		// if we have a name-cache, get it
		var lcArgs = dojo.widget.lcArgsCache[this.widgetType];
		if ( lcArgs == null ){
			// build a lower-case property name cache if we don't have one
			lcArgs = {};
			for(var y in this){
				lcArgs[((new String(y)).toLowerCase())] = y;
			}
			dojo.widget.lcArgsCache[this.widgetType] = lcArgs;
		}
		var visited = {};
		for(var x in args){
			if(!this[x]){ // check the cache for properties
				var y = lcArgs[(new String(x)).toLowerCase()];
				if(y){
					args[y] = args[x];
					x = y;
				}
			}
			if(visited[x]){ continue; }
			visited[x] = true;
			if((typeof this[x]) != (typeof undef)){
				if(typeof args[x] != "string"){
					this[x] = args[x];
				}else{
					if(dojo.lang.isString(this[x])){
						this[x] = args[x];
					}else if(dojo.lang.isNumber(this[x])){
						this[x] = new Number(args[x]); // FIXME: what if NaN is the result?
					}else if(dojo.lang.isBoolean(this[x])){
						this[x] = (args[x].toLowerCase()=="false") ? false : true;
					}else if(dojo.lang.isFunction(this[x])){

						// FIXME: need to determine if always over-writing instead
						// of attaching here is appropriate. I suspect that we
						// might want to only allow attaching w/ action items.

						// RAR, 1/19/05: I'm going to attach instead of
						// over-write here. Perhaps function objects could have
						// some sort of flag set on them? Or mixed-into objects
						// could have some list of non-mutable properties
						// (although I'm not sure how that would alleviate this
						// particular problem)?

						// this[x] = new Function(args[x]);

						// after an IRC discussion last week, it was decided
						// that these event handlers should execute in the
						// context of the widget, so that the "this" pointer
						// takes correctly.

						// argument that contains no punctuation other than . is
						// considered a function spec, not code
						if(args[x].search(/[^\w\.]+/i) == -1){
							this[x] = dojo.evalObjPath(args[x], false);
						}else{
							var tn = dojo.lang.nameAnonFunc(new Function(args[x]), this);
							dojo.event.kwConnect({
								srcObj: this,
								srcFunc: x,
								adviceObj: this,
								adviceFunc: tn
							});
						}
					}else if(dojo.lang.isArray(this[x])){ // typeof [] == "object"
						this[x] = args[x].split(";");
					} else if (this[x] instanceof Date) {
						this[x] = new Date(Number(args[x])); // assume timestamp
					}else if(typeof this[x] == "object"){
						// FIXME: should we be allowing extension here to handle
						// other object types intelligently?

						// if a plain string is passed to a property of type dojo.uri.Uri,
						// we assume it is relative to root of dojo
						if (this[x] instanceof dojo.uri.Uri){
							this[x] = dojo.uri.dojoUri(args[x]);
						}else{
							// FIXME: unlike all other types, we do not replace the
							// object with a new one here. Should we change that?
							var pairs = args[x].split(";");
							for(var y=0; y<pairs.length; y++){
								var si = pairs[y].indexOf(":");
								if((si != -1)&&(pairs[y].length>si)){
									this[x][pairs[y].substr(0, si).replace(/^\s+|\s+$/g, "")] = pairs[y].substr(si+1);
								}
							}
						}
					}else{
						// the default is straight-up string assignment. When would
						// we ever hit this?
						this[x] = args[x];
					}
				}
			}else{
				// collect any extra 'non mixed in' args
				this.extraArgs[x.toLowerCase()] = args[x];
			}
		}
		// dojo.profile.end("mixInProperties");
	},

	postMixInProperties: function(/*Object*/args, /*Object*/frag, /*Widget*/parent){
		// summary
		//	Called after the parameters to the widget have been read-in,
		//	but before the widget template is instantiated.
		//	Especially useful to set properties that are referenced in the widget template.
	},

	initialize: function(/*Object*/args, /*Object*/frag, /*Widget*/parent){
		// summary: stub function.
		return false;
		// dojo.unimplemented("dojo.widget.Widget.initialize");
	},

	postInitialize: function(/*Object*/args, /*Object*/frag, /*Widget*/parent){
		// summary: stub function.
		return false;
	},

	postCreate: function(/*Object*/args, /*Object*/frag, /*Widget*/parent){
		// summary: stub function.
		return false;
	},

	uninitialize: function(){
		// summary:
		//		stub function. Over-ride to implement custom widget tear-down
		//		behavior.
		return false;
	},

	buildRendering: function(/*Object*/args, /*Object*/frag, /*Widget*/parent){
		// summary: stub function. SUBCLASSES MUST IMPLEMENT
		dojo.unimplemented("dojo.widget.Widget.buildRendering, on "+this.toString()+", ");
		return false;
	},

	destroyRendering: function(){
		// summary: stub function. SUBCLASSES MUST IMPLEMENT
		dojo.unimplemented("dojo.widget.Widget.destroyRendering");
		return false;
	},

	addedTo: function(parent){
		// summary:
		//		stub function this is just a signal that can be caught
		// parent: Widget
		//		instance of dojo.widget.Widget that we were added to
	},

	addChild: function(child){
		// summary: stub function. SUBCLASSES MUST IMPLEMENT
		dojo.unimplemented("dojo.widget.Widget.addChild");
		return false;
	},

	// Detach the given child widget from me, but don't destroy it
	removeChild: function(/*Widget*/widget){
		// summary:
		//		removes the passed widget instance from this widget but does
		//		not destroy it
		for(var x=0; x<this.children.length; x++){
			if(this.children[x] === widget){
				this.children.splice(x, 1);
				widget.parent=null;
				break;
			}
		}
		return widget; // Widget
	},

	getPreviousSibling: function(){
		// summary:
		//		returns null if this is the first child of the parent,
		//		otherwise returns the next sibling to the "left".
		var idx = this.getParentIndex();

		 // first node is idx=0 not found is idx<0
		if (idx<=0) return null;

		return this.parent.children[idx-1]; // Widget
	},

	getSiblings: function(){
		// summary: gets an array of all children of our parent, including "this"
		return this.parent.children; // Array
	},

	getParentIndex: function(){
		// summary: what index are we at in the parent's children array?
		return dojo.lang.indexOf(this.parent.children, this, true); // int
	},

	getNextSibling: function(){
		// summary:
		//		returns null if this is the last child of the parent,
		//		otherwise returns the next sibling to the "right".

		var idx = this.getParentIndex();

		if (idx == this.parent.children.length-1){return null;} // last node
		if (idx < 0){return null;} // not found

		return this.parent.children[idx+1]; // Widget
	}
});

// Lower case name cache: listing of the lower case elements in each widget.
// We can't store the lcArgs in the widget itself because if B subclasses A,
// then B.prototype.lcArgs might return A.prototype.lcArgs, which is not what we
// want
dojo.widget.lcArgsCache = {};

// TODO: should have a more general way to add tags or tag libraries?
// TODO: need a default tags class to inherit from for things like getting propertySets
// TODO: parse properties/propertySets into component attributes
// TODO: parse subcomponents
// TODO: copy/clone raw markup fragments/nodes as appropriate
dojo.widget.tags = {};
dojo.widget.tags.addParseTreeHandler = function(/*String*/type){
	// summary: deprecated!
	dojo.deprecated("addParseTreeHandler", ". ParseTreeHandlers are now reserved for components. Any unfiltered DojoML tag without a ParseTreeHandler is assumed to be a widget", "0.5");
	/*
	var ltype = type.toLowerCase();
	this[ltype] = function(fragment, widgetParser, parentComp, insertionIndex, localProps){
		var _ltype = ltype;
		dojo.profile.start(_ltype);
		var n = dojo.widget.buildWidgetFromParseTree(ltype, fragment, widgetParser, parentComp, insertionIndex, localProps);
		dojo.profile.end(_ltype);
		return n;
	}
	*/
}

//dojo.widget.tags.addParseTreeHandler("dojo:widget");

dojo.widget.tags["dojo:propertyset"] = function(fragment, widgetParser, parentComp){
	// FIXME: Is this needed?
	// FIXME: Not sure that this parses into the structure that I want it to parse into...
	// FIXME: add support for nested propertySets
	var properties = widgetParser.parseProperties(fragment["dojo:propertyset"]);
}

// FIXME: need to add the <dojo:connect />
dojo.widget.tags["dojo:connect"] = function(fragment, widgetParser, parentComp){
	var properties = widgetParser.parseProperties(fragment["dojo:connect"]);
}

// FIXME: if we know the insertion point (to a reasonable location), why then do we:
//	- create a template node
//	- clone the template node
//	- render the clone and set properties
//	- remove the clone from the render tree
//	- place the clone
// this is quite dumb
dojo.widget.buildWidgetFromParseTree = function(/*String*/				type,
												/*Object*/				frag,
												/*dojo.widget.Parse*/	parser,
												/*Widget, optional*/	parentComp,
												/*int, optional*/		insertionIndex,
												/*Object*/				localProps){

	// summary: creates a tree of widgets from the data structure produced by the first-pass parser (frag)

	// test for accessibility mode
	dojo.a11y.setAccessibleMode();
	//dojo.profile.start("buildWidgetFromParseTree");
	// FIXME: for codepath from createComponentFromScript, we are now splitting a path
	// that we already split and then joined
	var stype = type.split(":");
	stype = (stype.length == 2) ? stype[1] : type;

	// FIXME: we don't seem to be doing anything with this!
	// var propertySets = parser.getPropertySets(frag);
	var localProperties = localProps || parser.parseProperties(frag[frag["ns"]+":"+stype]);
	var twidget = dojo.widget.manager.getImplementation(stype,null,null,frag["ns"]);
	if(!twidget){
		throw new Error('cannot find "' + type + '" widget');
	}else if (!twidget.create){
		throw new Error('"' + type + '" widget object has no "create" method and does not appear to implement *Widget');
	}
	localProperties["dojoinsertionindex"] = insertionIndex;
	// FIXME: we lose no less than 5ms in construction!
	var ret = twidget.create(localProperties, frag, parentComp, frag["ns"]);
	// dojo.profile.end("buildWidgetFromParseTree");
	return ret;
}

dojo.widget.defineWidget = function(widgetClass, renderer, superclasses, init, props){
	// summary: Create a widget constructor function (aka widgetClass)
	// widgetClass: String
	//		the location in the object hierarchy to place the new widget class constructor
	// renderer: String
	//		usually "html", determines when this delcaration will be used
	// superclasses: Function||Function[]
	//		can be either a single function or an array of functions to be
	//		mixed in as superclasses. If an array, only the first will be used
	//		to set prototype inheritance.
	// init: Function
	//		an optional constructor function. Will be called after superclasses are mixed in.
	// props: Object
	//		a map of properties and functions to extend the class prototype with

	// This meta-function does parameter juggling for backward compat and overloading
	// if 4th argument is a string, we are using the old syntax
	// old sig: widgetClass, superclasses, props (object), renderer (string), init (function)
	if(dojo.lang.isString(arguments[3])){
		dojo.widget._defineWidget(arguments[0], arguments[3], arguments[1], arguments[4], arguments[2]);
	}else{
		// widgetClass
		var args = [ arguments[0] ], p = 3;
		if(dojo.lang.isString(arguments[1])){
			// renderer, superclass
			args.push(arguments[1], arguments[2]);
		}else{
			// superclass
			args.push('', arguments[1]);
			p = 2;
		}
		if(dojo.lang.isFunction(arguments[p])){
			// init (function), props (object)
			args.push(arguments[p], arguments[p+1]);
		}else{
			// props (object)
			args.push(null, arguments[p]);
		}
		dojo.widget._defineWidget.apply(this, args);
	}
}

dojo.widget.defineWidget.renderers = "html|svg|vml";

dojo.widget._defineWidget = function(widgetClass /*string*/, renderer /*string*/, superclasses /*function||array*/, init /*function*/, props /*object*/){
	// FIXME: uncomment next line to test parameter juggling ... remove when confidence improves
	// dojo.debug('(c:)' + widgetClass + '\n\n(r:)' + renderer + '\n\n(i:)' + init + '\n\n(p:)' + props);
	// widgetClass takes the form foo.bar.baz<.renderer>.WidgetName (e.g. foo.bar.baz.WidgetName or foo.bar.baz.html.WidgetName)
	var module = widgetClass.split(".");
	var type = module.pop(); // type <= WidgetName, module <= foo.bar.baz<.renderer>
	var regx = "\\.(" + (renderer ? renderer + '|' : '') + dojo.widget.defineWidget.renderers + ")\\.";
	var r = widgetClass.search(new RegExp(regx));
	module = (r < 0 ? module.join(".") : widgetClass.substr(0, r));

	// deprecated in favor of namespace system, remove for 0.5
	dojo.widget.manager.registerWidgetPackage(module);

	var pos = module.indexOf(".");
	var nsName = (pos > -1) ? module.substring(0,pos) : module;

	// FIXME: hrm, this might make things simpler
	//dojo.widget.tags.addParseTreeHandler(nsName+":"+type.toLowerCase());

	props=(props)||{};
	props.widgetType = type;
	if((!init)&&(props["classConstructor"])){
		init = props.classConstructor;
		delete props.classConstructor;
	}
	dojo.declare(widgetClass, superclasses, init, props);
}

dojo.provide("dojo.widget.Parse");

//
// dojoML parser should be moved out of 'widget', codifying the difference between a 'component'
// and a 'widget'. A 'component' being anything that can be generated from a tag.
//
// a particular dojoML tag would be handled by a registered tagHandler with a hook for a default handler
// if the widget system is loaded, a widget builder would be attach itself as the default handler
//
// widget tags are no longer registered themselves:
// they are now arbitrarily namespaced, so we cannot register them all, and the non-prefixed portions
// are no longer guaranteed unique
//
// therefore dojo.widget.tags should go with this parser code out of the widget module
//

dojo.widget.Parse = function(/*Object*/fragment){
	this.propertySetsList = [];
	this.fragment = fragment;

	this.createComponents = function(/*Object*/frag, /*Object*/parentComp){
		var comps = [];
		var built = false;
		// if we have items to parse/create at this level, do it!
		try{
			if(frag && frag.tagName && (frag != frag.nodeRef)){

				// these are in fact, not ever for widgets per-se anymore,
				// but for other markup elements (aka components)
				var djTags = dojo.widget.tags;

				// we split so that you can declare multiple
				// non-destructive components from the same ctor node
				var tna = String(frag.tagName).split(";");
				for(var x=0; x<tna.length; x++){
					var ltn = tna[x].replace(/^\s+|\s+$/g, "").toLowerCase();
					// FIXME: unsure what this does
					frag.tagName = ltn;
					var ret;
					if(djTags[ltn]){
						built = true;
						ret = djTags[ltn](frag, this, parentComp, frag.index);
						comps.push(ret);
					}else{
						// we require a namespace prefix, default to dojo:
						if(ltn.indexOf(":") == -1){
							ltn = "dojo:"+ltn;
						}
						// FIXME: handling failure condition correctly?
						// ret = djTags[ltn](frag, this, parentComp, frag.index);
						ret = dojo.widget.buildWidgetFromParseTree(ltn, frag, this, parentComp, frag.index);
						if(ret){
							built = true;
							comps.push(ret);
						}
					}
				}
			}
		}catch(e){
			dojo.debug("dojo.widget.Parse: error:", e);
			// note, commenting out the next line is breaking several widgets for me
			// throw e;
			// IE is such a pain sometimes
		}
		// if there's a sub-frag, build widgets from that too
		if(!built){
			comps = comps.concat(this.createSubComponents(frag, parentComp));
		}
		return comps; // Array
	}

	this.createSubComponents = function(/*Object*/fragment, /*Object*/parentComp){
		// summary: recurses over a raw JavaScript object structure,
		// and calls the corresponding handler for its normalized tagName if it exists

		var frag, comps = [];
		for(var item in fragment){
			frag = fragment[item];
			if(frag && typeof frag == "object"
				&&(frag!=fragment.nodeRef)
				&&(frag!=fragment.tagName)
				&&(!dojo.dom.isNode(frag))){// needed in IE when we have event.connected to the domNode
				comps = comps.concat(this.createComponents(frag, parentComp));
			}
		}
		return comps; // Array
	}

	this.parsePropertySets = function(/*Object*/fragment){
		// summary: checks the top level of a raw JavaScript object
		//	structure for any propertySets.  It stores an array of references to
		//	propertySets that it finds.
		return [];
		/*
		var propertySets = [];
		for(var item in fragment){
			if((fragment[item]["tagName"] == "dojo:propertyset")){
				propertySets.push(fragment[item]);
			}
		}
		// FIXME: should we store these propertySets somewhere for later retrieval
		this.propertySetsList.push(propertySets);
		return propertySets;
		*/
	}

	this.parseProperties = function(/*Object*/fragment){
		// summary: parseProperties checks a raw JavaScript object structure for
		//	properties, and returns a hash of properties that it finds.
		var properties = {};
		for(var item in fragment){
			// FIXME: need to check for undefined?
			// case: its a tagName or nodeRef
			if((fragment[item] == fragment.tagName)||(fragment[item] == fragment.nodeRef)){
				// do nothing
			}else{
				var frag = fragment[item];
				if(frag.tagName && dojo.widget.tags[frag.tagName.toLowerCase()]){
					// TODO: it isn't a property or property set, it's a fragment,
					// so do something else
					// FIXME: needs to be a better/stricter check
					// TODO: handle xlink:href for external property sets
				}else if(frag[0] && frag[0].value!="" && frag[0].value!=null){
					try{
						// FIXME: need to allow more than one provider
						if(item.toLowerCase() == "dataprovider"){
							var _this = this;
							this.getDataProvider(_this, frag[0].value);
							properties.dataProvider = this.dataProvider;
						}
						properties[item] = frag[0].value;
						var nestedProperties = this.parseProperties(frag);
						// FIXME: this kind of copying is expensive and inefficient!
						for(var property in nestedProperties){
							properties[property] = nestedProperties[property];
						}
					}catch(e){ dojo.debug(e); }
				}
				switch(item.toLowerCase()){
				case "checked":
				case "disabled":
					if (typeof properties[item] != "boolean"){
						properties[item] = true;
					}
					break;
				}
			}
		}
		return properties; // Object
	}

	this.getDataProvider = function(/*Object*/objRef, /*String*/dataUrl){
		// FIXME: this is currently sync.  To make this async, we made need to move
		//this step into the widget ctor, so that it is loaded when it is needed
		// to populate the widget
		dojo.io.bind({
			url: dataUrl,
			load: function(type, evaldObj){
				if(type=="load"){
					objRef.dataProvider = evaldObj;
				}
			},
			mimetype: "text/javascript",
			sync: true
		});
	}

	this.getPropertySetById = function(propertySetId){
		// summary: returns the propertySet that matches the provided id
		for(var x = 0; x < this.propertySetsList.length; x++){
			if(propertySetId == this.propertySetsList[x]["id"][0].value){
				return this.propertySetsList[x];
			}
		}
		return ""; // String
	}

	//FIXME: doesn't use the componentType param?
	this.getPropertySetsByType = function(componentType){
		// summary: returns the propertySet(s) that match(es) the
	 	// provided componentClass

		var propertySets = [];
		for(var x=0; x < this.propertySetsList.length; x++){
			var cpl = this.propertySetsList[x];
			var cpcc = cpl.componentClass || cpl.componentType || null; //FIXME: is componentType supposed to be an indirect reference?
			var propertySetId = this.propertySetsList[x]["id"][0].value;
			if(cpcc && (propertySetId == cpcc[0].value)){
				propertySets.push(cpl);
			}
		}
		return propertySets; // Array
	}

	this.getPropertySets = function(/*Object*/fragment){
		// summary: returns the propertySet for a given component fragment

		var ppl = "dojo:propertyproviderlist";
		var propertySets = [];
		var tagname = fragment.tagName;
		if(fragment[ppl]){
			var propertyProviderIds = fragment[ppl].value.split(" ");
			// FIXME: should the propertyProviderList attribute contain #
			// 		  syntax for reference to ids or not?
			// FIXME: need a better test to see if this is local or external
			// FIXME: doesn't handle nested propertySets, or propertySets that
			// 		  just contain information about css documents, etc.
			for(var propertySetId in propertyProviderIds){
				if((propertySetId.indexOf("..")==-1)&&(propertySetId.indexOf("://")==-1)){
					// get a reference to a propertySet within the current parsed structure
					var propertySet = this.getPropertySetById(propertySetId);
					if(propertySet != ""){
						propertySets.push(propertySet);
					}
				}else{
					// FIXME: add code to parse and return a propertySet from
					// another document
					// alex: is this even necessaray? Do we care? If so, why?
				}
			}
		}
		// we put the typed ones first so that the parsed ones override when
		// iteration happens.
		return this.getPropertySetsByType(tagname).concat(propertySets); // Array
	}

	this.createComponentFromScript = function(/*Node*/nodeRef, /*String*/componentName, /*Object*/properties, /*String?*/ns){
		// summary:
		// nodeRef: the node to be replaced... in the future, we might want to add
		// an alternative way to specify an insertion point
		// componentName: the expected dojo widget name, i.e. Button of ContextMenu
		// properties: an object of name value pairs
		// ns: the namespace of the widget.  Defaults to "dojo"

		properties.fastMixIn = true;
		// FIXME: we pulled it apart and now we put it back together ...
		var ltn = (ns || "dojo") + ":" + componentName.toLowerCase();
		if(dojo.widget.tags[ltn]){
			return [dojo.widget.tags[ltn](properties, this, null, null, properties)]; // Array
		}
		return [dojo.widget.buildWidgetFromParseTree(ltn, properties, this, null, null, properties)]; // Array
	}
}

dojo.widget._parser_collection = {"dojo": new dojo.widget.Parse() };

dojo.widget.getParser = function(/*String?*/name){
	if(!name){ name = "dojo"; }
	if(!this._parser_collection[name]){
		this._parser_collection[name] = new dojo.widget.Parse();
	}
	return this._parser_collection[name];
}

dojo.widget.createWidget = function(/*String*/name, /*String*/props, /*Node*/refNode, /*String*/position){
	// summary: Creates widget
	// name: The name of the widget to create with optional namespace prefix,
	//	e.g."ns:widget", namespace defaults to "dojo".
	// props: Key-Value pairs of properties of the widget
	// refNode: If the position argument is specified, this node is used as
	//	a reference for inserting this node into a DOM tree; else
	//	the widget becomes the domNode
	// position: The position to insert this widget's node relative to the
	//	refNode argument

	var isNode = false;
	var isNameStr = (typeof name == "string");
	if(isNameStr){
		var pos = name.indexOf(":");
		var ns = (pos > -1) ? name.substring(0,pos) : "dojo";
		if(pos > -1){ name = name.substring(pos+1); }
		var lowerCaseName = name.toLowerCase();
		var namespacedName = ns + ":" + lowerCaseName;
		isNode = (dojo.byId(name) && !dojo.widget.tags[namespacedName]);
	}

	if((arguments.length == 1) && (isNode || !isNameStr)){
		// we got a DOM node
		var xp = new dojo.xml.Parse();
		// FIXME: we should try to find the parent!
		var tn = isNode ? dojo.byId(name) : name;
		return dojo.widget.getParser().createComponents(xp.parseElement(tn, null, true))[0];
	}

	function fromScript(placeKeeperNode, name, props, ns){
		props[namespacedName] = {
			dojotype: [{value: lowerCaseName}],
			nodeRef: placeKeeperNode,
			fastMixIn: true
		};
		props.ns = ns;
		return dojo.widget.getParser().createComponentFromScript(placeKeeperNode, name, props, ns);
	}

	props = props||{};
	var notRef = false;
	var tn = null;
	var h = dojo.render.html.capable;
	if(h){
		tn = document.createElement("span");
	}
	if(!refNode){
		notRef = true;
		refNode = tn;
		if(h){
			dojo.body().appendChild(refNode);
		}
	}else if(position){
		dojo.dom.insertAtPosition(tn, refNode, position);
	}else{ // otherwise don't replace, but build in-place
		tn = refNode;
	}
	var widgetArray = fromScript(tn, name.toLowerCase(), props, ns);
	if(	(!widgetArray)||(!widgetArray[0])||
		(typeof widgetArray[0].widgetType == "undefined") ){
		throw new Error("createWidget: Creation of \"" + name + "\" widget failed.");
	}
	try{
		if(notRef && widgetArray[0].domNode.parentNode){
			widgetArray[0].domNode.parentNode.removeChild(widgetArray[0].domNode);
		}
	}catch(e){
		/* squelch for Safari */
		dojo.debug(e);
	}
	return widgetArray[0]; // Widget
}

dojo.provide("dojo.html.style");

dojo.html.getClass = function(/* HTMLElement */node){
	//	summary
	//	Returns the string value of the list of CSS classes currently assigned directly
	//	to the node in question. Returns an empty string if no class attribute is found;
	node = dojo.byId(node);
	if(!node){ return ""; }
	var cs = "";
	if(node.className){
		cs = node.className;
	}else if(dojo.html.hasAttribute(node, "class")){
		cs = dojo.html.getAttribute(node, "class");
	}
	return cs.replace(/^\s+|\s+$/g, "");	//	string
}

dojo.html.getClasses = function(/* HTMLElement */node) {
	//	summary
	//	Returns an array of CSS classes currently assigned directly to the node in question.
	//	Returns an empty array if no classes are found;
	var c = dojo.html.getClass(node);
	return (c == "") ? [] : c.split(/\s+/g);	//	array
}

dojo.html.hasClass = function(/* HTMLElement */node, /* string */classname){
	//	summary
	//	Returns whether or not the specified classname is a portion of the
	//	class list currently applied to the node. Does not cover cascaded
	//	styles, only classes directly applied to the node.
	return (new RegExp('(^|\\s+)'+classname+'(\\s+|$)')).test(dojo.html.getClass(node))	//	boolean
}

dojo.html.prependClass = function(/* HTMLElement */node, /* string */classStr){
	//	summary
	//	Adds the specified class to the beginning of the class list on the
	//	passed node. This gives the specified class the highest precidence
	//	when style cascading is calculated for the node. Returns true or
	//	false; indicating success or failure of the operation, respectively.
	classStr += " " + dojo.html.getClass(node);
	return dojo.html.setClass(node, classStr);	//	boolean
}

dojo.html.addClass = function(/* HTMLElement */node, /* string */classStr){
	//	summary
	//	Adds the specified class to the end of the class list on the
	//	passed &node;. Returns &true; or &false; indicating success or failure.
	if (dojo.html.hasClass(node, classStr)) {
	  return false;
	}
	classStr = (dojo.html.getClass(node) + " " + classStr).replace(/^\s+|\s+$/g,"");
	return dojo.html.setClass(node, classStr);	//	boolean
}

dojo.html.setClass = function(/* HTMLElement */node, /* string */classStr){
	//	summary
	//	Clobbers the existing list of classes for the node, replacing it with
	//	the list given in the 2nd argument. Returns true or false
	//	indicating success or failure.
	node = dojo.byId(node);
	var cs = new String(classStr);
	try{
		if(typeof node.className == "string"){
			node.className = cs;
		}else if(node.setAttribute){
			node.setAttribute("class", classStr);
			node.className = cs;
		}else{
			return false;
		}
	}catch(e){
		dojo.debug("dojo.html.setClass() failed", e);
	}
	return true;
}

dojo.html.removeClass = function(/* HTMLElement */node, /* string */classStr, /* boolean? */allowPartialMatches){
	//	summary
	//	Removes the className from the node;. Returns true or false indicating success or failure.
	try{
		if (!allowPartialMatches) {
			var newcs = dojo.html.getClass(node).replace(new RegExp('(^|\\s+)'+classStr+'(\\s+|$)'), "$1$2");
		} else {
			var newcs = dojo.html.getClass(node).replace(classStr,'');
		}
		dojo.html.setClass(node, newcs);
	}catch(e){
		dojo.debug("dojo.html.removeClass() failed", e);
	}
	return true;	//	boolean
}

dojo.html.replaceClass = function(/* HTMLElement */node, /* string */newClass, /* string */oldClass) {
	//	summary
	//	Replaces 'oldClass' and adds 'newClass' to node
	dojo.html.removeClass(node, oldClass);
	dojo.html.addClass(node, newClass);
}

// Enum type for getElementsByClass classMatchType arg:
dojo.html.classMatchType = {
	ContainsAll : 0, // all of the classes are part of the node's class (default)
	ContainsAny : 1, // any of the classes are part of the node's class
	IsOnly : 2 // only all of the classes are part of the node's class
}


dojo.html.getElementsByClass = function(
	/* string */classStr,
	/* HTMLElement? */parent,
	/* string? */nodeType,
	/* integer? */classMatchType,
	/* boolean? */useNonXpath
){
	//	summary
	//	Returns an array of nodes for the given classStr, children of a
	//	parent, and optionally of a certain nodeType
	// FIXME: temporarily set to false because of several dojo tickets related
	// to the xpath version not working consistently in firefox.
	useNonXpath = false;
	var _document = dojo.doc();
	parent = dojo.byId(parent) || _document;
	var classes = classStr.split(/\s+/g);
	var nodes = [];
	if( classMatchType != 1 && classMatchType != 2 ) classMatchType = 0; // make it enum
	var reClass = new RegExp("(\\s|^)((" + classes.join(")|(") + "))(\\s|$)");
	var srtLength = classes.join(" ").length;
	var candidateNodes = [];

	if(!useNonXpath && _document.evaluate) { // supports dom 3 xpath
		var xpath = ".//" + (nodeType || "*") + "[contains(";
		if(classMatchType != dojo.html.classMatchType.ContainsAny){
			xpath += "concat(' ',@class,' '), ' " +
			classes.join(" ') and contains(concat(' ',@class,' '), ' ") +
			" ')";
			if (classMatchType == 2) {
				xpath += " and string-length(@class)="+srtLength+"]";
			}else{
				xpath += "]";
			}
		}else{
			xpath += "concat(' ',@class,' '), ' " +
			classes.join(" ') or contains(concat(' ',@class,' '), ' ") +
			" ')]";
		}
		var xpathResult = _document.evaluate(xpath, parent, null, XPathResult.ANY_TYPE, null);
		var result = xpathResult.iterateNext();
		while(result){
			try{
				candidateNodes.push(result);
				result = xpathResult.iterateNext();
			}catch(e){ break; }
		}
		return candidateNodes;	//	NodeList
	}else{
		if(!nodeType){
			nodeType = "*";
		}
		candidateNodes = parent.getElementsByTagName(nodeType);

		var node, i = 0;
		outer:
		while(node = candidateNodes[i++]){
			var nodeClasses = dojo.html.getClasses(node);
			if(nodeClasses.length == 0){ continue outer; }
			var matches = 0;

			for(var j = 0; j < nodeClasses.length; j++){
				if(reClass.test(nodeClasses[j])){
					if(classMatchType == dojo.html.classMatchType.ContainsAny){
						nodes.push(node);
						continue outer;
					}else{
						matches++;
					}
				}else{
					if(classMatchType == dojo.html.classMatchType.IsOnly){
						continue outer;
					}
				}
			}

			if(matches == classes.length){
				if(	(classMatchType == dojo.html.classMatchType.IsOnly)&&
					(matches == nodeClasses.length)){
					nodes.push(node);
				}else if(classMatchType == dojo.html.classMatchType.ContainsAll){
					nodes.push(node);
				}
			}
		}
		return nodes;	//	NodeList
	}
}
dojo.html.getElementsByClassName = dojo.html.getElementsByClass;

dojo.html.toCamelCase = function(/* string */selector){
	//	summary
	//	Translates a CSS selector string to a camel-cased one.
	var arr = selector.split('-'), cc = arr[0];
	for(var i = 1; i < arr.length; i++) {
		cc += arr[i].charAt(0).toUpperCase() + arr[i].substring(1);
	}
	return cc;	//	string
}

dojo.html.toSelectorCase = function(/* string */selector){
	//	summary
	//	Translates a camel cased string to a selector cased one.
	return selector.replace(/([A-Z])/g, "-$1" ).toLowerCase();	//	string
}

dojo.html.getComputedStyle = function(/* HTMLElement */node, /* string */cssSelector, /* integer? */inValue){
	//	summary
	//	Returns the computed style of cssSelector on node.
	node = dojo.byId(node);
	// cssSelector may actually be in camel case, so force selector version
	var cssSelector = dojo.html.toSelectorCase(cssSelector);
	var property = dojo.html.toCamelCase(cssSelector);
	if(!node || !node.style){
		return inValue;
	} else if (document.defaultView && dojo.html.isDescendantOf(node, node.ownerDocument)){ // W3, gecko, KHTML
		try{
			// mozilla segfaults when margin-* and node is removed from doc
			// FIXME: need to figure out a if there is quicker workaround
			var cs = document.defaultView.getComputedStyle(node, "");
			if(cs){
				return cs.getPropertyValue(cssSelector);	//	integer
			}
		}catch(e){ // reports are that Safari can throw an exception above
			if(node.style.getPropertyValue){ // W3
				return node.style.getPropertyValue(cssSelector);	//	integer
			} else {
				return inValue;	//	integer
			}
		}
	} else if(node.currentStyle){ // IE
		return node.currentStyle[property];	//	integer
	}

	if(node.style.getPropertyValue){ // W3
		return node.style.getPropertyValue(cssSelector);	//	integer
	}else{
		return inValue;	//	integer
	}
}

dojo.html.getStyleProperty = function(/* HTMLElement */node, /* string */cssSelector){
	//	summary
	//	Returns the value of the passed style
	node = dojo.byId(node);
	return (node && node.style ? node.style[dojo.html.toCamelCase(cssSelector)] : undefined);	//	string
}

dojo.html.getStyle = function(/* HTMLElement */node, /* string */cssSelector){
	//	summary
	//	Returns the computed value of the passed style
	var value = dojo.html.getStyleProperty(node, cssSelector);
	return (value ? value : dojo.html.getComputedStyle(node, cssSelector));	//	string || integer
}

dojo.html.setStyle = function(/* HTMLElement */node, /* string */cssSelector, /* string */value){
	//	summary
	//	Set the value of passed style on node
	node = dojo.byId(node);
	if(node && node.style){
		var camelCased = dojo.html.toCamelCase(cssSelector);
		node.style[camelCased] = value;
	}
}

dojo.html.setStyleText = function (/* HTMLElement */target, /* string */text) {
	//	summary
	//	Try to set the entire cssText property of the passed target; equiv of setting style attribute.
	try {
	 	target.style.cssText = text;
	} catch (e) {
		target.setAttribute("style", text);
	}
}

dojo.html.copyStyle = function(/* HTMLElement */target, /* HTMLElement */source){
	//	summary
	// work around for opera which doesn't have cssText, and for IE which fails on setAttribute
	if(!source.style.cssText){
		target.setAttribute("style", source.getAttribute("style"));
	}else{
		target.style.cssText = source.style.cssText;
	}
	dojo.html.addClass(target, dojo.html.getClass(source));
}

dojo.html.getUnitValue = function(/* HTMLElement */node, /* string */cssSelector, /* boolean? */autoIsZero){
	//	summary
	//	Get the value of passed selector, with the specific units used
	var s = dojo.html.getComputedStyle(node, cssSelector);
	if((!s)||((s == 'auto')&&(autoIsZero))){
		return { value: 0, units: 'px' };	//	object
	}
	// FIXME: is regex inefficient vs. parseInt or some manual test?
	var match = s.match(/(\-?[\d.]+)([a-z%]*)/i);
	if (!match){return dojo.html.getUnitValue.bad;}
	return { value: Number(match[1]), units: match[2].toLowerCase() };	//	object
}
dojo.html.getUnitValue.bad = { value: NaN, units: '' };

dojo.html.getPixelValue = function(/* HTMLElement */node, /* string */cssSelector, /* boolean? */autoIsZero){
	//	summary
	//	Get the value of passed selector in pixels.
	var result = dojo.html.getUnitValue(node, cssSelector, autoIsZero);
	// FIXME: there is serious debate as to whether or not this is the right solution
	if(isNaN(result.value)){
		return 0; //	integer
	}
	// FIXME: code exists for converting other units to px (see Dean Edward's IE7)
	// but there are cross-browser complexities
	if((result.value)&&(result.units != 'px')){
		return NaN;	//	integer
	}
	return result.value;	//	integer
}

dojo.html.setPositivePixelValue = function(/* HTMLElement */node, /* string */selector, /* integer */value){
	//	summary
	//	Attempt to set the value of selector on node as a positive pixel value.
	if(isNaN(value)){return false;}
	node.style[selector] = Math.max(0, value) + 'px';
	return true;	//	boolean
}

dojo.html.styleSheet = null;

// FIXME: this is a really basic stub for adding and removing cssRules, but
// it assumes that you know the index of the cssRule that you want to add
// or remove, making it less than useful.  So we need something that can
// search for the selector that you you want to remove.
dojo.html.insertCssRule = function(/* string */selector, /* string */declaration, /* integer? */index) {
	//	summary
	//	Attempt to insert declaration as selector on the internal stylesheet; if index try to set it there.
	if (!dojo.html.styleSheet) {
		if (document.createStyleSheet) { // IE
			dojo.html.styleSheet = document.createStyleSheet();
		} else if (document.styleSheets[0]) { // rest
			// FIXME: should create a new style sheet here
			// fall back on an exsiting style sheet
			dojo.html.styleSheet = document.styleSheets[0];
		} else {
			return null;	//	integer
		} // fail
	}

	if (arguments.length < 3) { // index may == 0
		if (dojo.html.styleSheet.cssRules) { // W3
			index = dojo.html.styleSheet.cssRules.length;
		} else if (dojo.html.styleSheet.rules) { // IE
			index = dojo.html.styleSheet.rules.length;
		} else {
			return null;	//	integer
		} // fail
	}

	if (dojo.html.styleSheet.insertRule) { // W3
		var rule = selector + " { " + declaration + " }";
		return dojo.html.styleSheet.insertRule(rule, index);	//	integer
	} else if (dojo.html.styleSheet.addRule) { // IE
		return dojo.html.styleSheet.addRule(selector, declaration, index);	//	integer
	} else {
		return null; // integer
	} // fail
}

dojo.html.removeCssRule = function(/* integer? */index){
	//	summary
	//	Attempt to remove the rule at index.
	if(!dojo.html.styleSheet){
		dojo.debug("no stylesheet defined for removing rules");
		return false;
	}
	if(dojo.render.html.ie){
		if(!index){
			index = dojo.html.styleSheet.rules.length;
			dojo.html.styleSheet.removeRule(index);
		}
	}else if(document.styleSheets[0]){
		if(!index){
			index = dojo.html.styleSheet.cssRules.length;
		}
		dojo.html.styleSheet.deleteRule(index);
	}
	return true;	//	boolean
}

dojo.html._insertedCssFiles = []; // cache container needed because IE reformats cssText when added to DOM
dojo.html.insertCssFile = function(/* string */URI, /* HTMLDocument? */doc, /* boolean? */checkDuplicates, /* boolean */fail_ok){
	//	summary
	// calls css by XmlHTTP and inserts it into DOM as <style [widgetType="widgetType"]> *downloaded cssText*</style>
	if(!URI){ return; }
	if(!doc){ doc = document; }
	var cssStr = dojo.hostenv.getText(URI, false, fail_ok);
	if(cssStr===null){ return; }
	cssStr = dojo.html.fixPathsInCssText(cssStr, URI);

	if(checkDuplicates){
		var idx = -1, node, ent = dojo.html._insertedCssFiles;
		for(var i = 0; i < ent.length; i++){
			if((ent[i].doc == doc) && (ent[i].cssText == cssStr)){
				idx = i; node = ent[i].nodeRef;
				break;
			}
		}
		// make sure we havent deleted our node
		if(node){
			var styles = doc.getElementsByTagName("style");
			for(var i = 0; i < styles.length; i++){
				if(styles[i] == node){
					return;
				}
			}
			// delete this entry
			dojo.html._insertedCssFiles.shift(idx, 1);
		}
	}

	var style = dojo.html.insertCssText(cssStr, doc);
	dojo.html._insertedCssFiles.push({'doc': doc, 'cssText': cssStr, 'nodeRef': style});

	// insert custom attribute ex dbgHref="../foo.css" usefull when debugging in DOM inspectors, no?
	if(style && djConfig.isDebug){
		style.setAttribute("dbgHref", URI);
	}
	return style;	//	HTMLStyleElement
}

dojo.html.insertCssText = function(/* string */cssStr, /* HTMLDocument? */doc, /* string? */URI){
	//	summary
	//	Attempt to insert CSS rules into the document through inserting a style element
	// DomNode Style  = insertCssText(String ".dojoMenu {color: green;}"[, DomDoc document, dojo.uri.Uri Url ])
	if(!cssStr){
		return; //	HTMLStyleElement
	}
	if(!doc){ doc = document; }
	if(URI){// fix paths in cssStr
		cssStr = dojo.html.fixPathsInCssText(cssStr, URI);
	}
	var style = doc.createElement("style");
	style.setAttribute("type", "text/css");
	// IE is b0rken enough to require that we add the element to the doc
	// before changing it's properties
	var head = doc.getElementsByTagName("head")[0];
	if(!head){ // must have a head tag
		dojo.debug("No head tag in document, aborting styles");
		return;	//	HTMLStyleElement
	}else{
		head.appendChild(style);
	}
	if(style.styleSheet){// IE
		var setFunc = function(){
			try{
				style.styleSheet.cssText = cssStr;
			}catch(e){ dojo.debug(e); }
		};
		if(style.styleSheet.disabled){
			setTimeout(setFunc, 10);
		}else{
			setFunc();
		}
	}else{ // w3c
		var cssText = doc.createTextNode(cssStr);
		style.appendChild(cssText);
	}
	return style;	//	HTMLStyleElement
}

dojo.html.fixPathsInCssText = function(/* string */cssStr, /* string */URI){
	//	summary
	// usage: cssText comes from dojoroot/src/widget/templates/Foobar.css
	// 	it has .dojoFoo { background-image: url(images/bar.png);} then uri should point to dojoroot/src/widget/templates/
	if(!cssStr || !URI){ return; }
	var match, str = "", url = "", urlChrs = "[\\t\\s\\w\\(\\)\\/\\.\\\\'\"-:#=&?~]+";
	var regex = new RegExp('url\\(\\s*('+urlChrs+')\\s*\\)');
	var regexProtocol = /(file|https?|ftps?):\/\//;
	regexTrim = new RegExp("^[\\s]*(['\"]?)("+urlChrs+")\\1[\\s]*?$");
	if(dojo.render.html.ie55 || dojo.render.html.ie60){
		var regexIe = new RegExp("AlphaImageLoader\\((.*)src\=['\"]("+urlChrs+")['\"]");
		// TODO: need to decide how to handle relative paths and AlphaImageLoader see #1441
		// current implementation breaks on build with intern_strings
		while(match = regexIe.exec(cssStr)){
			url = match[2].replace(regexTrim, "$2");
			if(!regexProtocol.exec(url)){
				url = (new dojo.uri.Uri(URI, url).toString());
			}
			str += cssStr.substring(0, match.index) + "AlphaImageLoader(" + match[1] + "src='" + url + "'";
			cssStr = cssStr.substr(match.index + match[0].length);
		}
		cssStr = str + cssStr;
		str = "";
	}

	while(match = regex.exec(cssStr)){
		url = match[1].replace(regexTrim, "$2");
		if(!regexProtocol.exec(url)){
			url = (new dojo.uri.Uri(URI, url).toString());
		}
		str += cssStr.substring(0, match.index) + "url(" + url + ")";
		cssStr = cssStr.substr(match.index + match[0].length);
	}
	return str + cssStr;	//	string
}

dojo.html.setActiveStyleSheet = function(/* string */title){
	//	summary
	//	Activate style sheet with specified title.
	var i = 0, a, els = dojo.doc().getElementsByTagName("link");
	while (a = els[i++]) {
		if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")){
			a.disabled = true;
			if (a.getAttribute("title") == title) { a.disabled = false; }
		}
	}
}

dojo.html.getActiveStyleSheet = function(){
	//	summary
	//	return the title of the currently active stylesheet
	var i = 0, a, els = dojo.doc().getElementsByTagName("link");
	while (a = els[i++]) {
		if (a.getAttribute("rel").indexOf("style") != -1
			&& a.getAttribute("title")
			&& !a.disabled
		){
			return a.getAttribute("title");	//	string
		}
	}
	return null;	//	string
}

dojo.html.getPreferredStyleSheet = function(){
	//	summary
	//	Return the preferred stylesheet title (i.e. link without alt attribute)
	var i = 0, a, els = dojo.doc().getElementsByTagName("link");
	while (a = els[i++]) {
		if(a.getAttribute("rel").indexOf("style") != -1
			&& a.getAttribute("rel").indexOf("alt") == -1
			&& a.getAttribute("title")
		){
			return a.getAttribute("title"); 	//	string
		}
	}
	return null;	//	string
}

dojo.html.applyBrowserClass = function(/* HTMLElement */node){
	//	summary
	//	Applies pre-set class names based on browser & version to the passed node.
	//	Modified version of Morris' CSS hack.
	var drh=dojo.render.html;
	var classes = {
		dj_ie: drh.ie,
		dj_ie55: drh.ie55,
		dj_ie6: drh.ie60,
		dj_ie7: drh.ie70,
		dj_iequirks: drh.ie && drh.quirks,
		dj_opera: drh.opera,
		dj_opera8: drh.opera && (Math.floor(dojo.render.version)==8),
		dj_opera9: drh.opera && (Math.floor(dojo.render.version)==9),
		dj_khtml: drh.khtml,
		dj_safari: drh.safari,
		dj_gecko: drh.mozilla
	}; // no dojo unsupported browsers
	for(var p in classes){
		if(classes[p]){
			dojo.html.addClass(node, p);
		}
	}
};

dojo.provide("dojo.widget.DomWidget");


dojo.widget._cssFiles = {};
dojo.widget._cssStrings = {};
dojo.widget._templateCache = {};

dojo.widget.defaultStrings = {
	// summary: a mapping of strings that are used in template variable replacement
	dojoRoot: dojo.hostenv.getBaseScriptUri(),
	baseScriptUri: dojo.hostenv.getBaseScriptUri()
};

dojo.widget.fillFromTemplateCache = function(obj, templatePath, templateString, avoidCache){
	// summary:
	//		static method to build from a template w/ or w/o a real widget in
	//		place
	// obj: DomWidget
	//		an instance of dojo.widget.DomWidget to initialize the template for
	// templatePath: String
	//		the URL to get the template from. dojo.uri.Uri is often passed as well.
	// templateString: String?
	//		a string to use in lieu of fetching the template from a URL
	// avoidCache: Boolean?
	//		should the template system not use whatever is in the cache and
	//		always use the passed templatePath or templateString?

	// dojo.debug("avoidCache:", avoidCache);
	var tpath = templatePath || obj.templatePath;

	var tmplts = dojo.widget._templateCache;
	if(!tpath && !obj["widgetType"]) { // don't have a real template here
		do {
			var dummyName = "__dummyTemplate__" + dojo.widget._templateCache.dummyCount++;
		} while(tmplts[dummyName]);
		obj.widgetType = dummyName;
	}
	var wt = tpath?tpath.toString():obj.widgetType;

	var ts = tmplts[wt];
	if(!ts){
		tmplts[wt] = {"string": null, "node": null};
		if(avoidCache){
			ts = {};
		}else{
			ts = tmplts[wt];
		}
	}
	if((!obj.templateString)&&(!avoidCache)){
		obj.templateString = templateString || ts["string"];
	}
	if((!obj.templateNode)&&(!avoidCache)){
		obj.templateNode = ts["node"];
	}
	if((!obj.templateNode)&&(!obj.templateString)&&(tpath)){
		// fetch a text fragment and assign it to templateString
		// NOTE: we rely on blocking IO here!
		var tstring = dojo.hostenv.getText(tpath);
		if(tstring){
			// strip <?xml ...?> declarations so that external SVG and XML
			// documents can be added to a document without worry
			tstring = tstring.replace(/^\s*<\?xml(\s)+version=[\'\"](\d)*.(\d)*[\'\"](\s)*\?>/im, "");
			var matches = tstring.match(/<body[^>]*>\s*([\s\S]+)\s*<\/body>/im);
			if(matches){
				tstring = matches[1];
			}
		}else{
			tstring = "";
		}

		obj.templateString = tstring;
		if(!avoidCache){
			tmplts[wt]["string"] = tstring;
		}
	}
	if((!ts["string"])&&(!avoidCache)){
		ts.string = obj.templateString;
	}
}
dojo.widget._templateCache.dummyCount = 0;

// Array: list of properties to search for node-to-property mappings
dojo.widget.attachProperties = ["dojoAttachPoint", "id"];

// String: name of the property to use for mapping DOM events to widget functions
dojo.widget.eventAttachProperty = "dojoAttachEvent";

// String: property name of code to evaluate when the widget is constructed
dojo.widget.onBuildProperty = "dojoOnBuild";

// Array:  possible accessibility values to set on widget elements - role or state
dojo.widget.waiNames  = ["waiRole", "waiState"];

dojo.widget.wai = {
	// summary: Contains functions to set accessibility roles and states
	//		onto widget elements
	waiRole: {
				// name: String:
				//		information for mapping accessibility role
				name: "waiRole",
				// namespace: String:
				//		URI of the namespace for the set of roles
				"namespace": "http://www.w3.org/TR/xhtml2",
				// alias: String:
				//		The alias to assign the namespace
				alias: "x2",
				// prefix: String:
				//		The prefix to assign to the role value
				prefix: "wairole:"
	},
	waiState: {
				// name: String:
				//		information for mapping accessibility state
				name: "waiState",
				// namespace: String:
				//		URI of the namespace for the set of states
				"namespace": "http://www.w3.org/2005/07/aaa",
				// alias: String:
				//		The alias to assign the namespace
				alias: "aaa",
				// prefix: String:
				//		empty string - state value does not require prefix
				prefix: ""
	},
	setAttr: function(/*DomNode*/node, /*String*/ ns, /*String*/ attr, /*String|Boolean*/value){
		// summary: Use appropriate API to set the role or state attribute onto the element.
		// description: In IE use the generic setAttribute() api.  Append a namespace
		//   alias to the attribute name and appropriate prefix to the value.
		//   Otherwise, use the setAttribueNS api to set the namespaced attribute. Also
		//   add the appropriate prefix to the attribute value.
		if(dojo.render.html.ie){
			node.setAttribute(this[ns].alias+":"+ attr, this[ns].prefix+value);
		}else{
			node.setAttributeNS(this[ns]["namespace"], attr, this[ns].prefix+value);
		}
	},

	getAttr: function(/*DomNode*/ node, /*String*/ ns, /*String|Boolena*/ attr){
		// Summary:  Use the appropriate API to retrieve the role or state value
		// Description: In IE use the generic getAttribute() api.  An alias value
		// 	was added to the attribute name to simulate a namespace when the attribute
		//  was set.  Otherwise use the getAttributeNS() api to retrieve the state value
		if(dojo.render.html.ie){
			return node.getAttribute(this[ns].alias+":"+attr);
		}else{
			return node.getAttributeNS(this[ns]["namespace"], attr);
		}
	},
	removeAttr: function(/*DomNode*/ node, /*String*/ ns, /*String|Boolena*/ attr){
		// summary:  Use the appropriate API to remove the role or state value
		// description: In IE use the generic removeAttribute() api.  An alias value
		// 	was added to the attribute name to simulate a namespace when the attribute
		//  was set.  Otherwise use the removeAttributeNS() api to remove the state value
		var success = true; //only IE returns a value
		if(dojo.render.html.ie){
			 success = node.removeAttribute(this[ns].alias+":"+attr);
		}else{
			node.removeAttributeNS(this[ns]["namespace"], attr);
		}
		return success;
	}
};

dojo.widget.attachTemplateNodes = function(rootNode, /*Widget*/ targetObj, events ){
	// summary:
	//		map widget properties and functions to the handlers specified in
	//		the dom node and it's descendants. This function iterates over all
	//		nodes and looks for these properties:
	//			* dojoAttachPoint
	//			* dojoAttachEvent
	//			* waiRole
	//			* waiState
	//			* any "dojoOn*" proprties passed in the events array
	// rootNode: DomNode
	//		the node to search for properties. All children will be searched.
	// events: Array
	//		a list of properties generated from getDojoEventsFromStr.

	// FIXME: this method is still taking WAAAY too long. We need ways of optimizing:
	//	a.) what we are looking for on each node
	//	b.) the nodes that are subject to interrogation (use xpath instead?)
	//	c.) how expensive event assignment is (less eval(), more connect())
	// var start = new Date();
	var elementNodeType = dojo.dom.ELEMENT_NODE;

	function trim(str){
		return str.replace(/^\s+|\s+$/g, "");
	}

	if(!rootNode){
		rootNode = targetObj.domNode;
	}

	if(rootNode.nodeType != elementNodeType){
		return;
	}
	// alert(events.length);

	var nodes = rootNode.all || rootNode.getElementsByTagName("*");
	var _this = targetObj;
	for(var x=-1; x<nodes.length; x++){
		var baseNode = (x == -1) ? rootNode : nodes[x];
		// FIXME: is this going to have capitalization problems?  Could use getAttribute(name, 0); to get attributes case-insensitve
		var attachPoint = [];
		if(!targetObj.widgetsInTemplate || !baseNode.getAttribute('dojoType')){
			for(var y=0; y<this.attachProperties.length; y++){
				var tmpAttachPoint = baseNode.getAttribute(this.attachProperties[y]);
				if(tmpAttachPoint){
					attachPoint = tmpAttachPoint.split(";");
					for(var z=0; z<attachPoint.length; z++){
						if(dojo.lang.isArray(targetObj[attachPoint[z]])){
							targetObj[attachPoint[z]].push(baseNode);
						}else{
							targetObj[attachPoint[z]]=baseNode;
						}
					}
					break;
				}
			}

			var attachEvent = baseNode.getAttribute(this.eventAttachProperty);
			if(attachEvent){
				// NOTE: we want to support attributes that have the form
				// "domEvent: nativeEvent; ..."
				var evts = attachEvent.split(";");
				for(var y=0; y<evts.length; y++){
					if((!evts[y])||(!evts[y].length)){ continue; }
					var thisFunc = null;
					var tevt = trim(evts[y]);
					if(evts[y].indexOf(":") >= 0){
						// oh, if only JS had tuple assignment
						var funcNameArr = tevt.split(":");
						tevt = trim(funcNameArr[0]);
						thisFunc = trim(funcNameArr[1]);
					}
					if(!thisFunc){
						thisFunc = tevt;
					}

					var tf = function(){
						var ntf = new String(thisFunc);
						return function(evt){
							if(_this[ntf]){
								_this[ntf](dojo.event.browser.fixEvent(evt, this));
							}
						};
					}();
					dojo.event.browser.addListener(baseNode, tevt, tf, false, true);
					// dojo.event.browser.addListener(baseNode, tevt, dojo.lang.hitch(_this, thisFunc));
				}
			}

			for(var y=0; y<events.length; y++){
				//alert(events[x]);
				var evtVal = baseNode.getAttribute(events[y]);
				if((evtVal)&&(evtVal.length)){
					var thisFunc = null;
					var domEvt = events[y].substr(4); // clober the "dojo" prefix
					thisFunc = trim(evtVal);
					var funcs = [thisFunc];
					if(thisFunc.indexOf(";")>=0){
						funcs = dojo.lang.map(thisFunc.split(";"), trim);
					}
					for(var z=0; z<funcs.length; z++){
						if(!funcs[z].length){ continue; }
						var tf = function(){
							var ntf = new String(funcs[z]);
							return function(evt){
								if(_this[ntf]){
									_this[ntf](dojo.event.browser.fixEvent(evt, this));
								}
							}
						}();
						dojo.event.browser.addListener(baseNode, domEvt, tf, false, true);
						// dojo.event.browser.addListener(baseNode, domEvt, dojo.lang.hitch(_this, funcs[z]));
					}
				}
			}
		}
		// continue;

		// FIXME: we need to put this into some kind of lookup structure
		// instead of direct assignment
		var tmpltPoint = baseNode.getAttribute(this.templateProperty);
		if(tmpltPoint){
			targetObj[tmpltPoint]=baseNode;
		}

		dojo.lang.forEach(dojo.widget.waiNames, function(name){
			var wai = dojo.widget.wai[name];
			var val = baseNode.getAttribute(wai.name);
			if(val){
				if(val.indexOf('-') == -1){
					dojo.widget.wai.setAttr(baseNode, wai.name, "role", val);
				}else{
					// this is a state-value pair
					var statePair = val.split('-');
					dojo.widget.wai.setAttr(baseNode, wai.name, statePair[0], statePair[1]);
				}
			}
		}, this);

		var onBuild = baseNode.getAttribute(this.onBuildProperty);
		if(onBuild){
			eval("var node = baseNode; var widget = targetObj; "+onBuild);
		}
	}

}

dojo.widget.getDojoEventsFromStr = function(str){
	// summary:
	//		generates a list of properties with names that match the form
	//		dojoOn*
	// str: String
	//		the template string to search

	// var lstr = str.toLowerCase();
	var re = /(dojoOn([a-z]+)(\s?))=/gi;
	var evts = str ? str.match(re)||[] : [];
	var ret = [];
	var lem = {};
	for(var x=0; x<evts.length; x++){
		if(evts[x].length < 1){ continue; }
		var cm = evts[x].replace(/\s/, "");
		cm = (cm.slice(0, cm.length-1));
		if(!lem[cm]){
			lem[cm] = true;
			ret.push(cm);
		}
	}
	return ret; // Array
}

dojo.declare("dojo.widget.DomWidget",
	dojo.widget.Widget,
	function(){
		// summary:
		//		dojo.widget.DomWidget is the superclass that provides behavior for all
		//		DOM-based renderers, including HtmlWidget and SvgWidget. DomWidget
		//		implements the templating system that most widget authors use to define
		//		the UI for their widgets.
		if((arguments.length>0)&&(typeof arguments[0] == "object")){
			this.create(arguments[0]);
		}
	},
	{
		// templateNode: DomNode
		//		a node that represents the widget template. Pre-empts both templateString and templatePath.
		templateNode: null,

		// templateString String:
		//		a string that represents the widget template. Pre-empts the
		//		templatePath. In builds that have their strings "interned", the
		//		templatePath is converted to an inline templateString, thereby
		//		preventing a synchronous network call.
		templateString: null,

		// templateCssString String:
		//		a string that represents the CSS for the widgettemplate.
		//		Pre-empts the templateCssPath. In builds that have their
		//		strings "interned", the templateCssPath is converted to an
		//		inline templateCssString, thereby preventing a synchronous
		//		network call.
		templateCssString: null,

		// preventClobber Boolean:
		//		should the widget not replace the node from which it was
		//		constructed? Widgets that apply behaviors to pre-existing parts
		//		of a page can be implemented easily by setting this to "true".
		//		In these cases, the domNode property will point to the node
		//		which the widget was created from.
		preventClobber: false,

		// domNode DomNode:
		//		this is our visible representation of the widget! Other DOM
		//		Nodes may by assigned to other properties, usually through the
		//		template system's dojoAttachPonit syntax, but the domNode
		//		property is the canonical "top level" node in widget UI.
		domNode: null,

		// containerNode DomNode:
		//		holds child elements. "containerNode" is generally set via a
		//		dojoAttachPoint assignment and it designates where widgets that
		//		are defined as "children" of the parent will be placed
		//		visually.
		containerNode: null,

		// widgetsInTemplate Boolean:
		//		should we parse the template to find widgets that might be
		//		declared in markup inside it? false by default.
		widgetsInTemplate: false,

		addChild: function(/*Widget*/	widget, overrideContainerNode, pos, ref, insertIndex){
			// summary:
			//		Process the given child widget, inserting it's dom node as
			//		a child of our dom node
			// overrideContainerNode: DomNode?
			//		a non-default container node for the widget
			// pos: String?
			//		can be one of "before", "after", "first", or "last". This
			//		has the same meaning as in dojo.dom.insertAtPosition()
			// ref: DomNode?
			//		a node to place the widget relative to
			// insertIndex: int?
			//		DOM index, same meaning as in dojo.dom.insertAtIndex()
			// returns: the widget that was inserted

			// FIXME: should we support addition at an index in the children arr and
			// order the display accordingly? Right now we always append.
			if(!this.isContainer){ // we aren't allowed to contain other widgets, it seems
				dojo.debug("dojo.widget.DomWidget.addChild() attempted on non-container widget");
				return null;
			}else{
				if(insertIndex == undefined){
					insertIndex = this.children.length;
				}
				this.addWidgetAsDirectChild(widget, overrideContainerNode, pos, ref, insertIndex);
				this.registerChild(widget, insertIndex);
			}
			return widget; // Widget
		},

		addWidgetAsDirectChild: function(/*Widget*/	widget, overrideContainerNode, pos, ref, insertIndex){
			// summary:
			//		Process the given child widget, inserting it's dom node as
			//		a child of our dom node
			// overrideContainerNode: DomNode
			//		a non-default container node for the widget
			// pos: String?
			//		can be one of "before", "after", "first", or "last". This
			//		has the same meaning as in dojo.dom.insertAtPosition()
			// ref: DomNode?
			//		a node to place the widget relative to
			// insertIndex: int?
			//		DOM index, same meaning as in dojo.dom.insertAtIndex()
			if((!this.containerNode)&&(!overrideContainerNode)){
				this.containerNode = this.domNode;
			}
			var cn = (overrideContainerNode) ? overrideContainerNode : this.containerNode;
			if(!pos){ pos = "after"; }
			if(!ref){
				if(!cn){ cn = dojo.body(); }
				ref = cn.lastChild;
			}
			if(!insertIndex) { insertIndex = 0; }
			widget.domNode.setAttribute("dojoinsertionindex", insertIndex);

			// insert the child widget domNode directly underneath my domNode, in the
			// specified position (by default, append to end)
			if(!ref){
				cn.appendChild(widget.domNode);
			}else{
				// FIXME: was this meant to be the (ugly hack) way to support insert @ index?
				//dojo.dom[pos](widget.domNode, ref, insertIndex);

				// CAL: this appears to be the intended way to insert a node at a given position...
				if (pos == 'insertAtIndex'){
					// dojo.debug("idx:", insertIndex, "isLast:", ref === cn.lastChild);
					dojo.dom.insertAtIndex(widget.domNode, ref.parentNode, insertIndex);
				}else{
					// dojo.debug("pos:", pos, "isLast:", ref === cn.lastChild);
					if((pos == "after")&&(ref === cn.lastChild)){
						cn.appendChild(widget.domNode);
					}else{
						dojo.dom.insertAtPosition(widget.domNode, cn, pos);
					}
				}
			}
		},

		registerChild: function(widget, insertionIndex){
			// summary: record that given widget descends from me
			// widget: Widget
			//		the widget that is now a child
			// insertionIndex: int
			//		where in the children[] array to place it

			// we need to insert the child at the right point in the parent's
			// 'children' array, based on the insertionIndex

			widget.dojoInsertionIndex = insertionIndex;

			var idx = -1;
			for(var i=0; i<this.children.length; i++){

				//This appears to fix an out of order issue in the case of mixed
				//markup and programmatically added children.  Previously, if a child
				//existed from markup, and another child was addChild()d without specifying
				//any additional parameters, it would end up first in the list, when in fact
				//it should be after.  I can't see cases where this would break things, but
				//I could see no other obvious solution. -dustin

				if (this.children[i].dojoInsertionIndex <= insertionIndex){
					idx = i;
				}
			}

			this.children.splice(idx+1, 0, widget);

			widget.parent = this;
			widget.addedTo(this, idx+1);

			// If this widget was created programatically, then it was erroneously added
			// to dojo.widget.manager.topWidgets.  Fix that here.
			delete dojo.widget.manager.topWidgets[widget.widgetId];
		},

		removeChild: function(/*Widget*/ widget){
			// summary: detach child domNode from parent domNode
			dojo.dom.removeNode(widget.domNode);

			// remove child widget from parent widget
			return dojo.widget.DomWidget.superclass.removeChild.call(this, widget); // Widget
		},

		getFragNodeRef: function(frag){
			// summary:
			//		returns the source node, if any, that the widget was
			//		declared from
			// frag: Object
			//		an opaque data structure generated by the first-pass parser
			if(!frag){return null;} // null
			if(!frag[this.getNamespacedType()]){
				dojo.raise("Error: no frag for widget type " + this.getNamespacedType()
					+ ", id " + this.widgetId
					+ " (maybe a widget has set it's type incorrectly)");
			}
			return frag[this.getNamespacedType()]["nodeRef"]; // DomNode
		},

		postInitialize: function(/*Object*/ args, /*Object*/ frag, /*Widget*/ parentComp){
			// summary:
			//		Replace the source domNode with the generated dom
			//		structure, and register the widget with its parent.
			//		This is an implementation of the stub function defined in
			//		dojo.widget.Widget.

			//dojo.profile.start(this.widgetType + " postInitialize");

			var sourceNodeRef = this.getFragNodeRef(frag);
			// Stick my generated dom into the output tree
			//alert(this.widgetId + ": replacing " + sourceNodeRef + " with " + this.domNode.innerHTML);
			if (parentComp && (parentComp.snarfChildDomOutput || !sourceNodeRef)){
				// Add my generated dom as a direct child of my parent widget
				// This is important for generated widgets, and also cases where I am generating an
				// <li> node that can't be inserted back into the original DOM tree
				parentComp.addWidgetAsDirectChild(this, "", "insertAtIndex", "",  args["dojoinsertionindex"], sourceNodeRef);
			} else if (sourceNodeRef){
				// Do in-place replacement of the my source node with my generated dom
				if(this.domNode && (this.domNode !== sourceNodeRef)){
					this._sourceNodeRef = dojo.dom.replaceNode(sourceNodeRef, this.domNode);
				}
			}

			// Register myself with my parent, or with the widget manager if
			// I have no parent
			// TODO: the code below erroneously adds all programatically generated widgets
			// to topWidgets (since we don't know who the parent is until after creation finishes)
			if ( parentComp ) {
				parentComp.registerChild(this, args.dojoinsertionindex);
			} else {
				dojo.widget.manager.topWidgets[this.widgetId]=this;
			}

			if(this.widgetsInTemplate){
				var parser = new dojo.xml.Parse();

				var subContainerNode;
				//TODO: use xpath here?
				var subnodes = this.domNode.getElementsByTagName("*");
				for(var i=0;i<subnodes.length;i++){
					if(subnodes[i].getAttribute('dojoAttachPoint') == 'subContainerWidget'){
						subContainerNode = subnodes[i];
//						break;
					}
					if(subnodes[i].getAttribute('dojoType')){
						subnodes[i].setAttribute('isSubWidget', true);
					}
				}
				if (this.isContainer && !this.containerNode){
					//no containerNode is available, which means a widget is used as a container. find it here and move
					//all dom nodes defined in the main html page as children of this.domNode into the actual container
					//widget's node (at this point, the subwidgets defined in the template file is not parsed yet)
					if(subContainerNode){
						var src = this.getFragNodeRef(frag);
						if (src){
							dojo.dom.moveChildren(src, subContainerNode);
							//do not need to follow children nodes in the main html page, as they
							//will be dealt with in the subContainerWidget
							frag['dojoDontFollow'] = true;
						}
					}else{
						dojo.debug("No subContainerWidget node can be found in template file for widget "+this);
					}
				}

				var templatefrag = parser.parseElement(this.domNode, null, true);
				// createSubComponents not createComponents because frag has already been created
				dojo.widget.getParser().createSubComponents(templatefrag, this);

				//find all the sub widgets defined in the template file of this widget
				var subwidgets = [];
				var stack = [this];
				var w;
				while((w = stack.pop())){
					for(var i = 0; i < w.children.length; i++){
						var cwidget = w.children[i];
						if(cwidget._processedSubWidgets || !cwidget.extraArgs['issubwidget']){ continue; }
						subwidgets.push(cwidget);
						if(cwidget.isContainer){
							stack.push(cwidget);
						}
					}
				}

				//connect event to this widget/attach dom node
				for(var i = 0; i < subwidgets.length; i++){
					var widget = subwidgets[i];
					if(widget._processedSubWidgets){
						dojo.debug("This should not happen: widget._processedSubWidgets is already true!");
						return;
					}
					widget._processedSubWidgets = true;
					if(widget.extraArgs['dojoattachevent']){
						var evts = widget.extraArgs['dojoattachevent'].split(";");
						for(var j=0; j<evts.length; j++){
							var thisFunc = null;
							var tevt = dojo.string.trim(evts[j]);
							if(tevt.indexOf(":") >= 0){
								// oh, if only JS had tuple assignment
								var funcNameArr = tevt.split(":");
								tevt = dojo.string.trim(funcNameArr[0]);
								thisFunc = dojo.string.trim(funcNameArr[1]);
							}
							if(!thisFunc){
								thisFunc = tevt;
							}
							if(dojo.lang.isFunction(widget[tevt])){
								dojo.event.kwConnect({
									srcObj: widget,
									srcFunc: tevt,
									targetObj: this,
									targetFunc: thisFunc
								});
							}else{
								alert(tevt+" is not a function in widget "+widget);
							}
						}
					}

					if(widget.extraArgs['dojoattachpoint']){
						//don't attach widget.domNode here, as we do not know which
						//dom node we should connect to (in checkbox widget case,
						//it is inputNode). So we make the widget itself available
						this[widget.extraArgs['dojoattachpoint']] = widget;
					}
				}
			}

			//dojo.profile.end(this.widgetType + " postInitialize");

			// Expand my children widgets
			/* dojoDontFollow is important for a very special case
			 * basically if you have a widget that you instantiate from script
			 * and that widget is a container, and it contains a reference to a parent
			 * instance, the parser will start recursively parsing until the browser
			 * complains.  So the solution is to set an initialization property of
			 * dojoDontFollow: true and then it won't recurse where it shouldn't
			 */
			if(this.isContainer && !frag["dojoDontFollow"]){
				//alert("recurse from " + this.widgetId);
				// build any sub-components with us as the parent
				dojo.widget.getParser().createSubComponents(frag, this);
			}
		},

		// method over-ride
		buildRendering: function(/*Object*/ args, /*Object*/ frag){
			// summary:
			//		Construct the UI for this widget, generally from a
			//		template. This can be over-ridden for custom UI creation to
			//		to side-step the template system.  This is an
			//		implementation of the stub function defined in
			//		dojo.widget.Widget.

			// DOM widgets construct themselves from a template
			var ts = dojo.widget._templateCache[this.widgetType];

			// Handle style for this widget here, as even if templatePath
			// is not set, style specified by templateCssString or templateCssPath
			// should be applied. templateCssString has higher priority
			// than templateCssPath
			if(args["templatecsspath"]){
				args["templateCssPath"] = args["templatecsspath"];
			}
			var cpath = args["templateCssPath"] || this.templateCssPath;
			if(cpath && !dojo.widget._cssFiles[cpath.toString()]){
				if((!this.templateCssString)&&(cpath)){
					this.templateCssString = dojo.hostenv.getText(cpath);
					this.templateCssPath = null;
				}
				dojo.widget._cssFiles[cpath.toString()] = true;
			}

			if((this["templateCssString"])&&(!dojo.widget._cssStrings[this.templateCssString])){
				dojo.html.insertCssText(this.templateCssString, null, cpath);
				dojo.widget._cssStrings[this.templateCssString] = true;
			}
			if(
				(!this.preventClobber)&&(
					(this.templatePath)||
					(this.templateNode)||
					(
						(this["templateString"])&&(this.templateString.length)
					)||
					(
						(typeof ts != "undefined")&&( (ts["string"])||(ts["node"]) )
					)
				)
			){
				// if it looks like we can build the thing from a template, do it!
				this.buildFromTemplate(args, frag);
			}else{
				// otherwise, assign the DOM node that was the source of the widget
				// parsing to be the root node
				this.domNode = this.getFragNodeRef(frag);
			}
			this.fillInTemplate(args, frag); 	// this is where individual widgets
												// will handle population of data
												// from properties, remote data
												// sets, etc.
		},

		buildFromTemplate: function(/*Object*/ args, /*Object*/ frag){
			// summary:
			//		Called by buildRendering, creates the actual UI in a DomWidget.

			// var start = new Date();
			// copy template properties if they're already set in the templates object
			// dojo.debug("buildFromTemplate:", this);
			var avoidCache = false;
			if(args["templatepath"]){
//				avoidCache = true;
				args["templatePath"] = args["templatepath"];
			}
			dojo.widget.fillFromTemplateCache(	this,
												args["templatePath"],
												null,
												avoidCache);
			var ts = dojo.widget._templateCache[this.templatePath?this.templatePath.toString():this.widgetType];
			if((ts)&&(!avoidCache)){
				if(!this.templateString.length){
					this.templateString = ts["string"];
				}
				if(!this.templateNode){
					this.templateNode = ts["node"];
				}
			}
			var matches = false;
			var node = null;
			// var tstr = new String(this.templateString);
			var tstr = this.templateString;
			// attempt to clone a template node, if there is one
			if((!this.templateNode)&&(this.templateString)){
				matches = this.templateString.match(/\$\{([^\}]+)\}/g);
				if(matches) {
					// if we do property replacement, don't create a templateNode
					// to clone from.
					var hash = this.strings || {};
					// FIXME: should this hash of default replacements be cached in
					// templateString?
					for(var key in dojo.widget.defaultStrings) {
						if(dojo.lang.isUndefined(hash[key])) {
							hash[key] = dojo.widget.defaultStrings[key];
						}
					}
					// FIXME: this is a lot of string munging. Can we make it faster?
					for(var i = 0; i < matches.length; i++) {
						var key = matches[i];
						key = key.substring(2, key.length-1);
						var kval = (key.substring(0, 5) == "this.") ? dojo.lang.getObjPathValue(key.substring(5), this) : hash[key];
						var value;
						if((kval)||(dojo.lang.isString(kval))){
							value = new String((dojo.lang.isFunction(kval)) ? kval.call(this, key, this.templateString) : kval);
							// Safer substitution, see heading "Attribute values" in
							// http://www.w3.org/TR/REC-html40/appendix/notes.html#h-B.3.2
							while (value.indexOf("\"") > -1) {
								value=value.replace("\"","&quot;");
							}
							tstr = tstr.replace(matches[i], value);
						}
					}
				}else{
					// otherwise, we are required to instantiate a copy of the template
					// string if one is provided.

					// FIXME: need to be able to distinguish here what should be done
					// or provide a generic interface across all DOM implementations
					// FIMXE: this breaks if the template has whitespace as its first
					// characters
					// node = this.createNodesFromText(this.templateString, true);
					// this.templateNode = node[0].cloneNode(true); // we're optimistic here
					this.templateNode = this.createNodesFromText(this.templateString, true)[0];
					if(!avoidCache){
						ts.node = this.templateNode;
					}
				}
			}
			if((!this.templateNode)&&(!matches)){
				dojo.debug("DomWidget.buildFromTemplate: could not create template");
				return false;
			}else if(!matches){
				node = this.templateNode.cloneNode(true);
				if(!node){ return false; }
			}else{
				node = this.createNodesFromText(tstr, true)[0];
			}

			// recurse through the node, looking for, and attaching to, our
			// attachment points which should be defined on the template node.

			this.domNode = node;
			// dojo.profile.start("attachTemplateNodes");
			this.attachTemplateNodes();
			// dojo.profile.end("attachTemplateNodes");

			// relocate source contents to templated container node
			// this.containerNode must be able to receive children, or exceptions will be thrown
			if (this.isContainer && this.containerNode){
				var src = this.getFragNodeRef(frag);
				if (src){
					dojo.dom.moveChildren(src, this.containerNode);
				}
			}
		},

		attachTemplateNodes: function(baseNode, targetObj){
			// summary:
			//		hooks up event handlers and property/node linkages. Calls
			//		dojo.widget.attachTemplateNodes to do all the hard work.
			// baseNode: DomNode
			//		defaults to "this.domNode"
			// targetObj: Widget
			//		defaults to "this"
			if(!baseNode){ baseNode = this.domNode; }
			if(!targetObj){ targetObj = this; }
			return dojo.widget.attachTemplateNodes(baseNode, targetObj,
						dojo.widget.getDojoEventsFromStr(this.templateString));
		},

		fillInTemplate: function(){
			// summary:
			//		stub function! sub-classes may use as a default UI
			//		initializer function. The UI rendering will be available by
			//		the time this is called from buildRendering. If
			//		buildRendering is over-ridden, this function may not be
			//		fired!

			// dojo.unimplemented("dojo.widget.DomWidget.fillInTemplate");
		},

		// method over-ride
		destroyRendering: function(){
			// summary: UI destructor.  Destroy the dom nodes associated w/this widget.
			try{
				dojo.dom.destroyNode(this.domNode);
				delete this.domNode;
			}catch(e){ /* squelch! */ }
			if(this._sourceNodeRef){
				try{
					dojo.dom.destroyNode(this._sourceNodeRef);
				}catch(e){ /* squelch! */ }
			}
		},

		createNodesFromText: function(){
			// summary
			//	Attempts to create a set of nodes based on the structure of the passed text.
			//	Implemented in HtmlWidget and SvgWidget.
			dojo.unimplemented("dojo.widget.DomWidget.createNodesFromText");
		}
	}
);

dojo.provide("dojo.html.display");

dojo.html._toggle = function(node, tester, setter){
	node = dojo.byId(node);
	setter(node, !tester(node));
	return tester(node);
}

dojo.html.show = function(/* HTMLElement */node){
	//	summary
	//	Show the passed element by reverting display property set by dojo.html.hide
	node = dojo.byId(node);
	if(dojo.html.getStyleProperty(node, 'display')=='none'){
		dojo.html.setStyle(node, 'display', (node.dojoDisplayCache||''));
		node.dojoDisplayCache = undefined;	// cannot use delete on a node in IE6
	}
}

dojo.html.hide = function(/* HTMLElement */node){
	//	summary
	//	Hide the passed element by setting display:none
	node = dojo.byId(node);
	if(typeof node["dojoDisplayCache"] == "undefined"){ // it could == '', so we cannot say !node.dojoDisplayCount
		var d = dojo.html.getStyleProperty(node, 'display')
		if(d!='none'){
			node.dojoDisplayCache = d;
		}
	}
	dojo.html.setStyle(node, 'display', 'none');
}

dojo.html.setShowing = function(/* HTMLElement */node, /* boolean? */showing){
	//	summary
	// Calls show() if showing is true, hide() otherwise
	dojo.html[(showing ? 'show' : 'hide')](node);
}

dojo.html.isShowing = function(/* HTMLElement */node){
	//	summary
	//	Returns whether the element is displayed or not.
	// FIXME: returns true if node is bad, isHidden would be easier to make correct
	return (dojo.html.getStyleProperty(node, 'display') != 'none');	//	boolean
}

dojo.html.toggleShowing = function(/* HTMLElement */node){
	//	summary
	// Call setShowing() on node with the complement of isShowing(), then return the new value of isShowing()
	return dojo.html._toggle(node, dojo.html.isShowing, dojo.html.setShowing);	//	boolean
}

// Simple mapping of tag names to display values
// FIXME: simplistic
dojo.html.displayMap = { tr: '', td: '', th: '', img: 'inline', span: 'inline', input: 'inline', button: 'inline' };

dojo.html.suggestDisplayByTagName = function(/* HTMLElement */node){
	//	summary
	// Suggest a value for the display property that will show 'node' based on it's tag
	node = dojo.byId(node);
	if(node && node.tagName){
		var tag = node.tagName.toLowerCase();
		return (tag in dojo.html.displayMap ? dojo.html.displayMap[tag] : 'block');	//	string
	}
}

dojo.html.setDisplay = function(/* HTMLElement */node, /* string */display){
	//	summary
	// 	Sets the value of style.display to value of 'display' parameter if it is a string.
	// 	Otherwise, if 'display' is false, set style.display to 'none'.
	// 	Finally, set 'display' to a suggested display value based on the node's tag
	dojo.html.setStyle(node, 'display', ((display instanceof String || typeof display == "string") ? display : (display ? dojo.html.suggestDisplayByTagName(node) : 'none')));
}

dojo.html.isDisplayed = function(/* HTMLElement */node){
	//	summary
	// 	Is true if the the computed display style for node is not 'none'
	// 	FIXME: returns true if node is bad, isNotDisplayed would be easier to make correct
	return (dojo.html.getComputedStyle(node, 'display') != 'none');	//	boolean
}

dojo.html.toggleDisplay = function(/* HTMLElement */node){
	//	summary
	// 	Call setDisplay() on node with the complement of isDisplayed(), then
	// 	return the new value of isDisplayed()
	return dojo.html._toggle(node, dojo.html.isDisplayed, dojo.html.setDisplay);	//	boolean
}

dojo.html.setVisibility = function(/* HTMLElement */node, /* string */visibility){
	//	summary
	// 	Sets the value of style.visibility to value of 'visibility' parameter if it is a string.
	// 	Otherwise, if 'visibility' is false, set style.visibility to 'hidden'. Finally, set style.visibility to 'visible'.
	dojo.html.setStyle(node, 'visibility', ((visibility instanceof String || typeof visibility == "string") ? visibility : (visibility ? 'visible' : 'hidden')));
}

dojo.html.isVisible = function(/* HTMLElement */node){
	//	summary
	// 	Returns true if the the computed visibility style for node is not 'hidden'
	// 	FIXME: returns true if node is bad, isInvisible would be easier to make correct
	return (dojo.html.getComputedStyle(node, 'visibility') != 'hidden');	//	boolean
}

dojo.html.toggleVisibility = function(node){
	//	summary
	// Call setVisibility() on node with the complement of isVisible(), then return the new value of isVisible()
	return dojo.html._toggle(node, dojo.html.isVisible, dojo.html.setVisibility);	//	boolean
}

dojo.html.setOpacity = function(/* HTMLElement */node, /* float */opacity, /* boolean? */dontFixOpacity){
	//	summary
	//	Sets the opacity of node in a cross-browser way.
	//	float between 0.0 (transparent) and 1.0 (opaque)
	node = dojo.byId(node);
	var h = dojo.render.html;
	if(!dontFixOpacity){
		if( opacity >= 1.0){
			if(h.ie){
				dojo.html.clearOpacity(node);
				return;
			}else{
				opacity = 0.999999;
			}
		}else if( opacity < 0.0){ opacity = 0; }
	}
	if(h.ie){
		if(node.nodeName.toLowerCase() == "tr"){
			// FIXME: is this too naive? will we get more than we want?
			var tds = node.getElementsByTagName("td");
			for(var x=0; x<tds.length; x++){
				tds[x].style.filter = "Alpha(Opacity="+opacity*100+")";
			}
		}
		node.style.filter = "Alpha(Opacity="+opacity*100+")";
	}else if(h.moz){
		node.style.opacity = opacity; // ffox 1.0 directly supports "opacity"
		node.style.MozOpacity = opacity;
	}else if(h.safari){
		node.style.opacity = opacity; // 1.3 directly supports "opacity"
		node.style.KhtmlOpacity = opacity;
	}else{
		node.style.opacity = opacity;
	}
}

dojo.html.clearOpacity = function(/* HTMLElement */node){
	//	summary
	//	Clears any opacity setting on the passed element.
	node = dojo.byId(node);
	var ns = node.style;
	var h = dojo.render.html;
	if(h.ie){
		try {
			if( node.filters && node.filters.alpha ){
				ns.filter = ""; // FIXME: may get rid of other filter effects
			}
		} catch(e) {
			/*
			 * IE7 gives error if node.filters not set;
			 * don't know why or how to workaround (other than this)
			 */
		}
	}else if(h.moz){
		ns.opacity = 1;
		ns.MozOpacity = 1;
	}else if(h.safari){
		ns.opacity = 1;
		ns.KhtmlOpacity = 1;
	}else{
		ns.opacity = 1;
	}
}

dojo.html.getOpacity = function(/* HTMLElement */node){
	//	summary
	//	Returns the opacity of the passed element
	node = dojo.byId(node);
	var h = dojo.render.html;
	if(h.ie){
		var opac = (node.filters && node.filters.alpha &&
			typeof node.filters.alpha.opacity == "number"
			? node.filters.alpha.opacity : 100) / 100;
	}else{
		var opac = node.style.opacity || node.style.MozOpacity ||
			node.style.KhtmlOpacity || 1;
	}
	return opac >= 0.999999 ? 1.0 : Number(opac);	//	float
}

dojo.provide("dojo.html.layout");


dojo.html.sumAncestorProperties = function(/* HTMLElement */node, /* string */prop){
	//	summary
	//	Returns the sum of the passed property on all ancestors of node.
	node = dojo.byId(node);
	if(!node){ return 0; } // FIXME: throw an error?

	var retVal = 0;
	while(node){
		if(dojo.html.getComputedStyle(node, 'position') == 'fixed'){
			return 0;
		}
		var val = node[prop];
		if(val){
			retVal += val - 0;
			if(node==dojo.body()){ break; }// opera and khtml #body & #html has the same values, we only need one value
		}
		node = node.parentNode;
	}
	return retVal;	//	integer
}

dojo.html.setStyleAttributes = function(/* HTMLElement */node, /* string */attributes) {
	//	summary
	//	allows a dev to pass a string similar to what you'd pass in style="", and apply it to a node.
	node = dojo.byId(node);
	var splittedAttribs=attributes.replace(/(;)?\s*$/, "").split(";");
	for(var i=0; i<splittedAttribs.length; i++){
		var nameValue=splittedAttribs[i].split(":");
		var name=nameValue[0].replace(/\s*$/, "").replace(/^\s*/, "").toLowerCase();
		var value=nameValue[1].replace(/\s*$/, "").replace(/^\s*/, "");
		switch(name){
			case "opacity":
				dojo.html.setOpacity(node, value);
				break;
			case "content-height":
				dojo.html.setContentBox(node, {height: value});
				break;
			case "content-width":
				dojo.html.setContentBox(node, {width: value});
				break;
			case "outer-height":
				dojo.html.setMarginBox(node, {height: value});
				break;
			case "outer-width":
				dojo.html.setMarginBox(node, {width: value});
				break;
			default:
				node.style[dojo.html.toCamelCase(name)]=value;
		}
	}
}

dojo.html.boxSizing = {
	MARGIN_BOX: "margin-box",
	BORDER_BOX: "border-box",
	PADDING_BOX: "padding-box",
	CONTENT_BOX: "content-box"
};

dojo.html.getAbsolutePosition = dojo.html.abs = function(/* HTMLElement */node, /* boolean? */includeScroll, /* string? */boxType){
	//	summary
	//	Gets the absolute position of the passed element based on the document itself.
	node = dojo.byId(node, node.ownerDocument);
	var ret = {
		x: 0,
		y: 0
	};

	var bs = dojo.html.boxSizing;
	if(!boxType) { boxType = bs.CONTENT_BOX; }
	var nativeBoxType = 2; //BORDER box
	var targetBoxType;
	switch(boxType){
		case bs.MARGIN_BOX:
			targetBoxType = 3;
			break;
		case bs.BORDER_BOX:
			targetBoxType = 2;
			break;
		case bs.PADDING_BOX:
		default:
			targetBoxType = 1;
			break;
		case bs.CONTENT_BOX:
			targetBoxType = 0;
			break;
	}

	var h = dojo.render.html;
	var db = document["body"]||document["documentElement"];

	if(h.ie){
		with(node.getBoundingClientRect()){
			ret.x = left-2;
			ret.y = top-2;
		}
	}else if(document.getBoxObjectFor){
		// mozilla
		nativeBoxType = 1; //getBoxObjectFor return padding box coordinate
		try{
			var bo = document.getBoxObjectFor(node);
			ret.x = bo.x - dojo.html.sumAncestorProperties(node, "scrollLeft");
			ret.y = bo.y - dojo.html.sumAncestorProperties(node, "scrollTop");
		}catch(e){
			// squelch
		}
	}else{
		if(node["offsetParent"]){
			var endNode;
			// in Safari, if the node is an absolutely positioned child of
			// the body and the body has a margin the offset of the child
			// and the body contain the body's margins, so we need to end
			// at the body
			if(	(h.safari)&&
				(node.style.getPropertyValue("position") == "absolute")&&
				(node.parentNode == db)){
				endNode = db;
			}else{
				endNode = db.parentNode;
			}

			//TODO: set correct nativeBoxType for safari/konqueror

			if(node.parentNode != db){
				var nd = node;
				if(dojo.render.html.opera){ nd = db; }
				ret.x -= dojo.html.sumAncestorProperties(nd, "scrollLeft");
				ret.y -= dojo.html.sumAncestorProperties(nd, "scrollTop");
			}
			var curnode = node;
			do{
				var n = curnode["offsetLeft"];
				//FIXME: ugly hack to workaround the submenu in
				//popupmenu2 does not shown up correctly in opera.
				//Someone have a better workaround?
				if(!h.opera || n>0){
					ret.x += isNaN(n) ? 0 : n;
				}
				var m = curnode["offsetTop"];
				ret.y += isNaN(m) ? 0 : m;
				curnode = curnode.offsetParent;
			}while((curnode != endNode)&&(curnode != null));
		}else if(node["x"]&&node["y"]){
			ret.x += isNaN(node.x) ? 0 : node.x;
			ret.y += isNaN(node.y) ? 0 : node.y;
		}
	}

	// account for document scrolling!
	if(includeScroll){
		var scroll = dojo.html.getScroll();
		ret.y += scroll.top;
		ret.x += scroll.left;
	}

	var extentFuncArray=[dojo.html.getPaddingExtent, dojo.html.getBorderExtent, dojo.html.getMarginExtent];
	if(nativeBoxType > targetBoxType){
		for(var i=targetBoxType;i<nativeBoxType;++i){
			ret.y += extentFuncArray[i](node, 'top');
			ret.x += extentFuncArray[i](node, 'left');
		}
	}else if(nativeBoxType < targetBoxType){
		for(var i=targetBoxType;i>nativeBoxType;--i){
			ret.y -= extentFuncArray[i-1](node, 'top');
			ret.x -= extentFuncArray[i-1](node, 'left');
		}
	}
	ret.top = ret.y;
	ret.left = ret.x;
	return ret;	//	object
}

dojo.html.isPositionAbsolute = function(/* HTMLElement */node){
	//	summary
	//	Returns true if the element is absolutely positioned.
	return (dojo.html.getComputedStyle(node, 'position') == 'absolute');	//	boolean
}

dojo.html._sumPixelValues = function(/* HTMLElement */node, selectors, autoIsZero){
	var total = 0;
	for(var x=0; x<selectors.length; x++){
		total += dojo.html.getPixelValue(node, selectors[x], autoIsZero);
	}
	return total;
}

dojo.html.getMargin = function(/* HTMLElement */node){
	//	summary
	//	Returns the width and height of the passed node's margin
	return {
		width: dojo.html._sumPixelValues(node, ["margin-left", "margin-right"], (dojo.html.getComputedStyle(node, 'position') == 'absolute')),
		height: dojo.html._sumPixelValues(node, ["margin-top", "margin-bottom"], (dojo.html.getComputedStyle(node, 'position') == 'absolute'))
	};	//	object
}

dojo.html.getBorder = function(/* HTMLElement */node){
	//	summary
	//	Returns the width and height of the passed node's border
	return {
		width: dojo.html.getBorderExtent(node, 'left') + dojo.html.getBorderExtent(node, 'right'),
		height: dojo.html.getBorderExtent(node, 'top') + dojo.html.getBorderExtent(node, 'bottom')
	};	//	object
}

dojo.html.getBorderExtent = function(/* HTMLElement */node, /* string */side){
	//	summary
	//	returns the width of the requested border
	return (dojo.html.getStyle(node, 'border-' + side + '-style') == 'none' ? 0 : dojo.html.getPixelValue(node, 'border-' + side + '-width'));	// integer
}

dojo.html.getMarginExtent = function(/* HTMLElement */node, /* string */side){
	//	summary
	//	returns the width of the requested margin
	return dojo.html._sumPixelValues(node, ["margin-" + side], dojo.html.isPositionAbsolute(node));	//	integer
}

dojo.html.getPaddingExtent = function(/* HTMLElement */node, /* string */side){
	//	summary
	//	Returns the width of the requested padding
	return dojo.html._sumPixelValues(node, ["padding-" + side], true);	//	integer
}

dojo.html.getPadding = function(/* HTMLElement */node){
	//	summary
	//	Returns the width and height of the passed node's padding
	return {
		width: dojo.html._sumPixelValues(node, ["padding-left", "padding-right"], true),
		height: dojo.html._sumPixelValues(node, ["padding-top", "padding-bottom"], true)
	};	//	object
}

dojo.html.getPadBorder = function(/* HTMLElement */node){
	//	summary
	//	Returns the width and height of the passed node's padding and border
	var pad = dojo.html.getPadding(node);
	var border = dojo.html.getBorder(node);
	return { width: pad.width + border.width, height: pad.height + border.height };	//	object
}

dojo.html.getBoxSizing = function(/* HTMLElement */node){
	//	summary
	//	Returns which box model the passed element is working with
	var h = dojo.render.html;
	var bs = dojo.html.boxSizing;
	if(((h.ie)||(h.opera)) && node.nodeName!="IMG"){
		var cm = document["compatMode"];
		if((cm == "BackCompat")||(cm == "QuirksMode")){
			return bs.BORDER_BOX; 	//	string
		}else{
			return bs.CONTENT_BOX; 	//	string
		}
	}else{
		if(arguments.length == 0){ node = document.documentElement; }
		var sizing = dojo.html.getStyle(node, "-moz-box-sizing");
		if(!sizing){ sizing = dojo.html.getStyle(node, "box-sizing"); }
		return (sizing ? sizing : bs.CONTENT_BOX);	//	string
	}
}

dojo.html.isBorderBox = function(/* HTMLElement */node){
	//	summary
	//	returns whether the passed element is using border box sizing or not.
	return (dojo.html.getBoxSizing(node) == dojo.html.boxSizing.BORDER_BOX);	//	boolean
}

dojo.html.getBorderBox = function(/* HTMLElement */node){
	//	summary
	//	Returns the dimensions of the passed element based on border-box sizing.
	node = dojo.byId(node);
	return { width: node.offsetWidth, height: node.offsetHeight };	//	object
}

dojo.html.getPaddingBox = function(/* HTMLElement */node){
	//	summary
	//	Returns the dimensions of the padding box (see http://www.w3.org/TR/CSS21/box.html)
	var box = dojo.html.getBorderBox(node);
	var border = dojo.html.getBorder(node);
	return {
		width: box.width - border.width,
		height:box.height - border.height
	};	//	object
}

dojo.html.getContentBox = function(/* HTMLElement */node){
	//	summary
	//	Returns the dimensions of the content box (see http://www.w3.org/TR/CSS21/box.html)
	node = dojo.byId(node);
	var padborder = dojo.html.getPadBorder(node);
	return {
		width: node.offsetWidth - padborder.width,
		height: node.offsetHeight - padborder.height
	};	//	object
}

dojo.html.setContentBox = function(/* HTMLElement */node, /* object */args){
	//	summary
	//	Sets the dimensions of the passed node according to content sizing.
	node = dojo.byId(node);
	var width = 0; var height = 0;
	var isbb = dojo.html.isBorderBox(node);
	var padborder = (isbb ? dojo.html.getPadBorder(node) : { width: 0, height: 0});
	var ret = {};
	if(typeof args.width != "undefined"){
		width = args.width + padborder.width;
		ret.width = dojo.html.setPositivePixelValue(node, "width", width);
	}
	if(typeof args.height != "undefined"){
		height = args.height + padborder.height;
		ret.height = dojo.html.setPositivePixelValue(node, "height", height);
	}
	return ret;	//	object
}

dojo.html.getMarginBox = function(/* HTMLElement */node){
	//	summary
	//	returns the dimensions of the passed node including any margins.
	var borderbox = dojo.html.getBorderBox(node);
	var margin = dojo.html.getMargin(node);
	return { width: borderbox.width + margin.width, height: borderbox.height + margin.height };	//	object
}

dojo.html.setMarginBox = function(/* HTMLElement */node, /* object */args){
	//	summary
	//	Sets the dimensions of the passed node using margin box calcs.
	node = dojo.byId(node);
	var width = 0; var height = 0;
	var isbb = dojo.html.isBorderBox(node);
	var padborder = (!isbb ? dojo.html.getPadBorder(node) : { width: 0, height: 0 });
	var margin = dojo.html.getMargin(node);
	var ret = {};
	if(typeof args.width != "undefined"){
		width = args.width - padborder.width;
		width -= margin.width;
		ret.width = dojo.html.setPositivePixelValue(node, "width", width);
	}
	if(typeof args.height != "undefined"){
		height = args.height - padborder.height;
		height -= margin.height;
		ret.height = dojo.html.setPositivePixelValue(node, "height", height);
	}
	return ret;	//	object
}

dojo.html.getElementBox = function(/* HTMLElement */node, /* string */type){
	//	summary
	//	return dimesions of a node based on the passed box model type.
	var bs = dojo.html.boxSizing;
	switch(type){
		case bs.MARGIN_BOX:
			return dojo.html.getMarginBox(node);	//	object
		case bs.BORDER_BOX:
			return dojo.html.getBorderBox(node);	//	object
		case bs.PADDING_BOX:
			return dojo.html.getPaddingBox(node);	//	object
		case bs.CONTENT_BOX:
		default:
			return dojo.html.getContentBox(node);	//	object
	}
}
// in: coordinate array [x,y,w,h] or dom node
// return: coordinate object
dojo.html.toCoordinateObject = dojo.html.toCoordinateArray = function(/* array */coords, /* boolean? */includeScroll, /* string? */boxtype) {
	//	summary
	//	Converts an array of coordinates into an object of named arguments.
	if(coords instanceof Array || typeof coords == "array"){
		dojo.deprecated("dojo.html.toCoordinateArray", "use dojo.html.toCoordinateObject({left: , top: , width: , height: }) instead", "0.5");
		// coords is already an array (of format [x,y,w,h]), just return it
		while ( coords.length < 4 ) { coords.push(0); }
		while ( coords.length > 4 ) { coords.pop(); }
		var ret = {
			left: coords[0],
			top: coords[1],
			width: coords[2],
			height: coords[3]
		};
	}else if(!coords.nodeType && !(coords instanceof String || typeof coords == "string") &&
			 ('width' in coords || 'height' in coords || 'left' in coords ||
			  'x' in coords || 'top' in coords || 'y' in coords)){
		// coords is a coordinate object or at least part of one
		var ret = {
			left: coords.left||coords.x||0,
			top: coords.top||coords.y||0,
			width: coords.width||0,
			height: coords.height||0
		};
	}else{
		// coords is an dom object (or dom object id); return it's coordinates
		var node = dojo.byId(coords);
		var pos = dojo.html.abs(node, includeScroll, boxtype);
		var marginbox = dojo.html.getMarginBox(node);
		var ret = {
			left: pos.left,
			top: pos.top,
			width: marginbox.width,
			height: marginbox.height
		};
	}
	ret.x = ret.left;
	ret.y = ret.top;
	return ret;	//	object
}

dojo.html.setMarginBoxWidth = dojo.html.setOuterWidth = function(node, width){
	return dojo.html._callDeprecated("setMarginBoxWidth", "setMarginBox", arguments, "width");
}
dojo.html.setMarginBoxHeight = dojo.html.setOuterHeight = function(){
	return dojo.html._callDeprecated("setMarginBoxHeight", "setMarginBox", arguments, "height");
}
dojo.html.getMarginBoxWidth = dojo.html.getOuterWidth = function(){
	return dojo.html._callDeprecated("getMarginBoxWidth", "getMarginBox", arguments, null, "width");
}
dojo.html.getMarginBoxHeight = dojo.html.getOuterHeight = function(){
	return dojo.html._callDeprecated("getMarginBoxHeight", "getMarginBox", arguments, null, "height");
}
dojo.html.getTotalOffset = function(node, type, includeScroll){
	return dojo.html._callDeprecated("getTotalOffset", "getAbsolutePosition", arguments, null, type);
}
dojo.html.getAbsoluteX = function(node, includeScroll){
	return dojo.html._callDeprecated("getAbsoluteX", "getAbsolutePosition", arguments, null, "x");
}
dojo.html.getAbsoluteY = function(node, includeScroll){
	return dojo.html._callDeprecated("getAbsoluteY", "getAbsolutePosition", arguments, null, "y");
}
dojo.html.totalOffsetLeft = function(node, includeScroll){
	return dojo.html._callDeprecated("totalOffsetLeft", "getAbsolutePosition", arguments, null, "left");
}
dojo.html.totalOffsetTop = function(node, includeScroll){
	return dojo.html._callDeprecated("totalOffsetTop", "getAbsolutePosition", arguments, null, "top");
}
dojo.html.getMarginWidth = function(node){
	return dojo.html._callDeprecated("getMarginWidth", "getMargin", arguments, null, "width");
}
dojo.html.getMarginHeight = function(node){
	return dojo.html._callDeprecated("getMarginHeight", "getMargin", arguments, null, "height");
}
dojo.html.getBorderWidth = function(node){
	return dojo.html._callDeprecated("getBorderWidth", "getBorder", arguments, null, "width");
}
dojo.html.getBorderHeight = function(node){
	return dojo.html._callDeprecated("getBorderHeight", "getBorder", arguments, null, "height");
}
dojo.html.getPaddingWidth = function(node){
	return dojo.html._callDeprecated("getPaddingWidth", "getPadding", arguments, null, "width");
}
dojo.html.getPaddingHeight = function(node){
	return dojo.html._callDeprecated("getPaddingHeight", "getPadding", arguments, null, "height");
}
dojo.html.getPadBorderWidth = function(node){
	return dojo.html._callDeprecated("getPadBorderWidth", "getPadBorder", arguments, null, "width");
}
dojo.html.getPadBorderHeight = function(node){
	return dojo.html._callDeprecated("getPadBorderHeight", "getPadBorder", arguments, null, "height");
}
dojo.html.getBorderBoxWidth = dojo.html.getInnerWidth = function(){
	return dojo.html._callDeprecated("getBorderBoxWidth", "getBorderBox", arguments, null, "width");
}
dojo.html.getBorderBoxHeight = dojo.html.getInnerHeight = function(){
	return dojo.html._callDeprecated("getBorderBoxHeight", "getBorderBox", arguments, null, "height");
}
dojo.html.getContentBoxWidth = dojo.html.getContentWidth = function(){
	return dojo.html._callDeprecated("getContentBoxWidth", "getContentBox", arguments, null, "width");
}
dojo.html.getContentBoxHeight = dojo.html.getContentHeight = function(){
	return dojo.html._callDeprecated("getContentBoxHeight", "getContentBox", arguments, null, "height");
}
dojo.html.setContentBoxWidth = dojo.html.setContentWidth = function(node, width){
	return dojo.html._callDeprecated("setContentBoxWidth", "setContentBox", arguments, "width");
}
dojo.html.setContentBoxHeight = dojo.html.setContentHeight = function(node, height){
	return dojo.html._callDeprecated("setContentBoxHeight", "setContentBox", arguments, "height");
}

dojo.provide("dojo.html.util");

dojo.html.getElementWindow = function(/* HTMLElement */element){
	//	summary
	// 	Get the window object where the element is placed in.
	return dojo.html.getDocumentWindow( element.ownerDocument );	//	Window
}

dojo.html.getDocumentWindow = function(doc){
	//	summary
	// 	Get window object associated with document doc

	// With Safari, there is not wa to retrieve the window from the document, so we must fix it.
	if(dojo.render.html.safari && !doc._parentWindow){
		/*
			This is a Safari specific function that fix the reference to the parent
			window from the document object.
		*/

		var fix=function(win){
			win.document._parentWindow=win;
			for(var i=0; i<win.frames.length; i++){
				fix(win.frames[i]);
			}
		}
		fix(window.top);
	}

	//In some IE versions (at least 6.0), document.parentWindow does not return a
	//reference to the real window object (maybe a copy), so we must fix it as well
	//We use IE specific execScript to attach the real window reference to
	//document._parentWindow for later use
	if(dojo.render.html.ie && window !== document.parentWindow && !doc._parentWindow){
		/*
		In IE 6, only the variable "window" can be used to connect events (others
		may be only copies).
		*/
		doc.parentWindow.execScript("document._parentWindow = window;", "Javascript");
		//to prevent memory leak, unset it after use
		//another possibility is to add an onUnload handler which seems overkill to me (liucougar)
		var win = doc._parentWindow;
		doc._parentWindow = null;
		return win;	//	Window
	}

	return doc._parentWindow || doc.parentWindow || doc.defaultView;	//	Window
}

dojo.html.gravity = function(/* HTMLElement */node, /* DOMEvent */e){
	//	summary
	//	Calculates the mouse's direction of gravity relative to the centre
	//	of the given node.
	//	<p>
	//	If you wanted to insert a node into a DOM tree based on the mouse
	//	position you might use the following code:
	//	<pre>
	//	if (gravity(node, e) & gravity.NORTH) { [insert before]; }
	//	else { [insert after]; }
	//	</pre>
	//
	//	@param node The node
	//	@param e		The event containing the mouse coordinates
	//	@return		 The directions, NORTH or SOUTH and EAST or WEST. These
	//						 are properties of the function.
	node = dojo.byId(node);
	var mouse = dojo.html.getCursorPosition(e);

	with (dojo.html) {
		var absolute = getAbsolutePosition(node, true);
		var bb = getBorderBox(node);
		var nodecenterx = absolute.x + (bb.width / 2);
		var nodecentery = absolute.y + (bb.height / 2);
	}

	with (dojo.html.gravity) {
		return ((mouse.x < nodecenterx ? WEST : EAST) | (mouse.y < nodecentery ? NORTH : SOUTH));	//	integer
	}
}

dojo.html.gravity.NORTH = 1;
dojo.html.gravity.SOUTH = 1 << 1;
dojo.html.gravity.EAST = 1 << 2;
dojo.html.gravity.WEST = 1 << 3;

dojo.html.overElement = function(/* HTMLElement */element, /* DOMEvent */e){
	//	summary
	//	Returns whether the mouse is over the passed element.
	//	Element must be display:block (ie, not a <span>)
	element = dojo.byId(element);
	var mouse = dojo.html.getCursorPosition(e);
	var bb = dojo.html.getBorderBox(element);
	var absolute = dojo.html.getAbsolutePosition(element, true, dojo.html.boxSizing.BORDER_BOX);
	var top = absolute.y;
	var bottom = top + bb.height;
	var left = absolute.x;
	var right = left + bb.width;

	return (mouse.x >= left
		&& mouse.x <= right
		&& mouse.y >= top
		&& mouse.y <= bottom
	);	//	boolean
}

dojo.html.renderedTextContent = function(/* HTMLElement */node){
	//	summary
	//	Attempts to return the text as it would be rendered, with the line breaks
	//	sorted out nicely. Unfinished.
	node = dojo.byId(node);
	var result = "";
	if (node == null) { return result; }
	for (var i = 0; i < node.childNodes.length; i++) {
		switch (node.childNodes[i].nodeType) {
			case 1: // ELEMENT_NODE
			case 5: // ENTITY_REFERENCE_NODE
				var display = "unknown";
				try {
					display = dojo.html.getStyle(node.childNodes[i], "display");
				} catch(E) {}
				switch (display) {
					case "block": case "list-item": case "run-in":
					case "table": case "table-row-group": case "table-header-group":
					case "table-footer-group": case "table-row": case "table-column-group":
					case "table-column": case "table-cell": case "table-caption":
						// TODO: this shouldn't insert double spaces on aligning blocks
						result += "\n";
						result += dojo.html.renderedTextContent(node.childNodes[i]);
						result += "\n";
						break;

					case "none": break;

					default:
						if(node.childNodes[i].tagName && node.childNodes[i].tagName.toLowerCase() == "br") {
							result += "\n";
						} else {
							result += dojo.html.renderedTextContent(node.childNodes[i]);
						}
						break;
				}
				break;
			case 3: // TEXT_NODE
			case 2: // ATTRIBUTE_NODE
			case 4: // CDATA_SECTION_NODE
				var text = node.childNodes[i].nodeValue;
				var textTransform = "unknown";
				try {
					textTransform = dojo.html.getStyle(node, "text-transform");
				} catch(E) {}
				switch (textTransform){
					case "capitalize":
						var words = text.split(' ');
						for(var i=0; i<words.length; i++){
							words[i] = words[i].charAt(0).toUpperCase() + words[i].substring(1);
						}
						text = words.join(" ");
						break;
					case "uppercase": text = text.toUpperCase(); break;
					case "lowercase": text = text.toLowerCase(); break;
					default: break; // leave as is
				}
				// TODO: implement
				switch (textTransform){
					case "nowrap": break;
					case "pre-wrap": break;
					case "pre-line": break;
					case "pre": break; // leave as is
					default:
						// remove whitespace and collapse first space
						text = text.replace(/\s+/, " ");
						if (/\s$/.test(result)) { text.replace(/^\s/, ""); }
						break;
				}
				result += text;
				break;
			default:
				break;
		}
	}
	return result;	//	string
}

dojo.html.createNodesFromText = function(/* string */txt, /* boolean? */trim){
	//	summary
	//	Attempts to create a set of nodes based on the structure of the passed text.
	if(trim) { txt = txt.replace(/^\s+|\s+$/g, ""); }

	var tn = dojo.doc().createElement("div");
	// tn.style.display = "none";
	tn.style.visibility= "hidden";
	dojo.body().appendChild(tn);
	var tableType = "none";
	if((/^<t[dh][\s\r\n>]/i).test(txt.replace(/^\s+/))) {
		txt = "<table><tbody><tr>" + txt + "</tr></tbody></table>";
		tableType = "cell";
	} else if((/^<tr[\s\r\n>]/i).test(txt.replace(/^\s+/))) {
		txt = "<table><tbody>" + txt + "</tbody></table>";
		tableType = "row";
	} else if((/^<(thead|tbody|tfoot)[\s\r\n>]/i).test(txt.replace(/^\s+/))) {
		txt = "<table>" + txt + "</table>";
		tableType = "section";
	}
	tn.innerHTML = txt;
	if(tn["normalize"]){
		tn.normalize();
	}

	var parent = null;
	switch(tableType) {
		case "cell":
			parent = tn.getElementsByTagName("tr")[0];
			break;
		case "row":
			parent = tn.getElementsByTagName("tbody")[0];
			break;
		case "section":
			parent = tn.getElementsByTagName("table")[0];
			break;
		default:
			parent = tn;
			break;
	}

	/* this doesn't make much sense, I'm assuming it just meant trim() so wrap was replaced with trim
	if(wrap){
		var ret = [];
		// start hack
		var fc = tn.firstChild;
		ret[0] = ((fc.nodeValue == " ")||(fc.nodeValue == "\t")) ? fc.nextSibling : fc;
		// end hack
		// tn.style.display = "none";
		dojo.body().removeChild(tn);
		return ret;
	}
	*/
	var nodes = [];
	for(var x=0; x<parent.childNodes.length; x++){
		nodes.push(parent.childNodes[x].cloneNode(true));
	}
	tn.style.display = "none"; // FIXME: why do we do this?
	dojo.html.destroyNode(tn);
	return nodes;	//	array
}

dojo.html.placeOnScreen = function(
	/* HTMLElement */node,
	/* integer */desiredX,
	/* integer */desiredY,
	/* integer */padding,
	/* boolean? */hasScroll,
	/* string? */corners,
	/* boolean? */tryOnly
){
	//	summary
	//	Keeps 'node' in the visible area of the screen while trying to
	//	place closest to desiredX, desiredY. The input coordinates are
	//	expected to be the desired screen position, not accounting for
	//	scrolling. If you already accounted for scrolling, set 'hasScroll'
	//	to true. Set padding to either a number or array for [paddingX, paddingY]
	//	to put some buffer around the element you want to position.
	//	Set which corner(s) you want to bind to, such as
	//
	//	placeOnScreen(node, desiredX, desiredY, padding, hasScroll, "TR")
	//	placeOnScreen(node, [desiredX, desiredY], padding, hasScroll, ["TR", "BL"])
	//
	//	The desiredX/desiredY will be treated as the topleft(TL)/topright(TR) or
	//	BottomLeft(BL)/BottomRight(BR) corner of the node. Each corner is tested
	//	and if a perfect match is found, it will be used. Otherwise, it goes through
	//	all of the specified corners, and choose the most appropriate one.
	//	By default, corner = ['TL'].
	//	If tryOnly is set to true, the node will not be moved to the place.
	//
	//	NOTE: node is assumed to be absolutely or relatively positioned.
	//
	//	Alternate call sig:
	//	 placeOnScreen(node, [x, y], padding, hasScroll)
	//
	//	Examples:
	//	 placeOnScreen(node, 100, 200)
	//	 placeOnScreen("myId", [800, 623], 5)
	//	 placeOnScreen(node, 234, 3284, [2, 5], true)

	// TODO: make this function have variable call sigs
	//	kes(node, ptArray, cornerArray, padding, hasScroll)
	//	kes(node, ptX, ptY, cornerA, cornerB, cornerC, paddingArray, hasScroll)
	if(desiredX instanceof Array || typeof desiredX == "array") {
		tryOnly = corners;
		corners = hasScroll;
		hasScroll = padding;
		padding = desiredY;
		desiredY = desiredX[1];
		desiredX = desiredX[0];
	}

	if(corners instanceof String || typeof corners == "string"){
		corners = corners.split(",");
	}

	if(!isNaN(padding)) {
		padding = [Number(padding), Number(padding)];
	} else if(!(padding instanceof Array || typeof padding == "array")) {
		padding = [0, 0];
	}

	var scroll = dojo.html.getScroll().offset;
	var view = dojo.html.getViewport();

	node = dojo.byId(node);
	var oldDisplay = node.style.display;
	node.style.display="";
	var bb = dojo.html.getBorderBox(node);
	var w = bb.width;
	var h = bb.height;
	node.style.display=oldDisplay;

	if(!(corners instanceof Array || typeof corners == "array")){
		corners = ['TL'];
	}

	var bestx, besty, bestDistance = Infinity, bestCorner;

	for(var cidex=0; cidex<corners.length; ++cidex){
		var corner = corners[cidex];
		var match = true;
		var tryX = desiredX - (corner.charAt(1)=='L' ? 0 : w) + padding[0]*(corner.charAt(1)=='L' ? 1 : -1);
		var tryY = desiredY - (corner.charAt(0)=='T' ? 0 : h) + padding[1]*(corner.charAt(0)=='T' ? 1 : -1);
		if(hasScroll) {
			tryX -= scroll.x;
			tryY -= scroll.y;
		}

		if(tryX < 0){
			tryX = 0;
			match = false;
		}

		if(tryY < 0){
			tryY = 0;
			match = false;
		}

		var x = tryX + w;
		if(x > view.width) {
			x = view.width - w;
			match = false;
		} else {
			x = tryX;
		}
		x = Math.max(padding[0], x) + scroll.x;

		var y = tryY + h;
		if(y > view.height) {
			y = view.height - h;
			match = false;
		} else {
			y = tryY;
		}
		y = Math.max(padding[1], y) + scroll.y;

		if(match){ //perfect match, return now
			bestx = x;
			besty = y;
			bestDistance = 0;
			bestCorner = corner;
			break;
		}else{
			//not perfect, find out whether it is better than the saved one
			var dist = Math.pow(x-tryX-scroll.x,2)+Math.pow(y-tryY-scroll.y,2);
			if(bestDistance > dist){
				bestDistance = dist;
				bestx = x;
				besty = y;
				bestCorner = corner;
			}
		}
	}

	if(!tryOnly){
		node.style.left = bestx + "px";
		node.style.top = besty + "px";
	}

	return { left: bestx, top: besty, x: bestx, y: besty, dist: bestDistance, corner:  bestCorner};	//	object
}

dojo.html.placeOnScreenPoint = function(node, desiredX, desiredY, padding, hasScroll) {
	dojo.deprecated("dojo.html.placeOnScreenPoint", "use dojo.html.placeOnScreen() instead", "0.5");
	return dojo.html.placeOnScreen(node, desiredX, desiredY, padding, hasScroll, ['TL', 'TR', 'BL', 'BR']);
}

dojo.html.placeOnScreenAroundElement = function(
	/* HTMLElement */node,
	/* HTMLElement */aroundNode,
	/* integer */padding,
	/* string? */aroundType,
	/* string? */aroundCorners,
	/* boolean? */tryOnly
){
	//	summary
	//	Like placeOnScreen, except it accepts aroundNode instead of x,y
	//	and attempts to place node around it. aroundType (see
	//	dojo.html.boxSizing in html/layout.js) determines which box of the
	//	aroundNode should be used to calculate the outer box.
	//	aroundCorners specify Which corner of aroundNode should be
	//	used to place the node => which corner(s) of node to use (see the
	//	corners parameter in dojo.html.placeOnScreen)
	//	aroundCorners: {'TL': 'BL', 'BL': 'TL'}

	var best, bestDistance=Infinity;
	aroundNode = dojo.byId(aroundNode);
	var oldDisplay = aroundNode.style.display;
	aroundNode.style.display="";
	var mb = dojo.html.getElementBox(aroundNode, aroundType);
	var aroundNodeW = mb.width;
	var aroundNodeH = mb.height;
	var aroundNodePos = dojo.html.getAbsolutePosition(aroundNode, true, aroundType);
	aroundNode.style.display=oldDisplay;

	for(var nodeCorner in aroundCorners){
		var pos, desiredX, desiredY;
		var corners = aroundCorners[nodeCorner];

		desiredX = aroundNodePos.x + (nodeCorner.charAt(1)=='L' ? 0 : aroundNodeW);
		desiredY = aroundNodePos.y + (nodeCorner.charAt(0)=='T' ? 0 : aroundNodeH);

		pos = dojo.html.placeOnScreen(node, desiredX, desiredY, padding, true, corners, true);
		if(pos.dist == 0){
			best = pos;
			break;
		}else{
			//not perfect, find out whether it is better than the saved one
			if(bestDistance > pos.dist){
				bestDistance = pos.dist;
				best = pos;
			}
		}
	}

	if(!tryOnly){
		node.style.left = best.left + "px";
		node.style.top = best.top + "px";
	}
	return best;	//	object
}

dojo.html.scrollIntoView = function(/* HTMLElement */node){
	//	summary
	//	Scroll the passed node into view, if it is not.
	if(!node){ return; }

	// don't rely on that node.scrollIntoView works just because the function is there
	// it doesnt work in Konqueror or Opera even though the function is there and probably
	// not safari either
	// dont like browser sniffs implementations but sometimes you have to use it
	if(dojo.render.html.ie){
		//only call scrollIntoView if there is a scrollbar for this menu,
		//otherwise, scrollIntoView will scroll the window scrollbar
		if(dojo.html.getBorderBox(node.parentNode).height <= node.parentNode.scrollHeight){
			node.scrollIntoView(false);
		}
	}else if(dojo.render.html.mozilla){
		// IE, mozilla
		node.scrollIntoView(false);
	}else{
		var parent = node.parentNode;
		var parentBottom = parent.scrollTop + dojo.html.getBorderBox(parent).height;
		var nodeBottom = node.offsetTop + dojo.html.getMarginBox(node).height;
		if(parentBottom < nodeBottom){
			parent.scrollTop += (nodeBottom - parentBottom);
		}else if(parent.scrollTop > node.offsetTop){
			parent.scrollTop -= (parent.scrollTop - node.offsetTop);
		}
	}
}

dojo.provide("dojo.gfx.color");

// TODO: rewrite the "x2y" methods to take advantage of the parsing
//       abilities of the Color object. Also, beef up the Color
//       object (as possible) to parse most common formats

// takes an r, g, b, a(lpha) value, [r, g, b, a] array, "rgb(...)" string, hex string (#aaa, #aaaaaa, aaaaaaa)
dojo.gfx.color.Color = function(r, g, b, a) {
	// dojo.debug("r:", r[0], "g:", r[1], "b:", r[2]);
	if(dojo.lang.isArray(r)){
		this.r = r[0];
		this.g = r[1];
		this.b = r[2];
		this.a = r[3]||1.0;
	}else if(dojo.lang.isString(r)){
		var rgb = dojo.gfx.color.extractRGB(r);
		this.r = rgb[0];
		this.g = rgb[1];
		this.b = rgb[2];
		this.a = g||1.0;
	}else if(r instanceof dojo.gfx.color.Color){
		// why does this create a new instance if we were passed one?
		this.r = r.r;
		this.b = r.b;
		this.g = r.g;
		this.a = r.a;
	}else{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
}

dojo.gfx.color.Color.fromArray = function(arr) {
	return new dojo.gfx.color.Color(arr[0], arr[1], arr[2], arr[3]);
}

dojo.extend(dojo.gfx.color.Color, {
	toRgb: function(includeAlpha) {
		if(includeAlpha) {
			return this.toRgba();
		} else {
			return [this.r, this.g, this.b];
		}
	},
	toRgba: function() {
		return [this.r, this.g, this.b, this.a];
	},
	toHex: function() {
		return dojo.gfx.color.rgb2hex(this.toRgb());
	},
	toCss: function() {
		return "rgb(" + this.toRgb().join() + ")";
	},
	toString: function() {
		return this.toHex(); // decent default?
	},
	blend: function(color, weight){
		var rgb = null;
		if(dojo.lang.isArray(color)){
			rgb = color;
		}else if(color instanceof dojo.gfx.color.Color){
			rgb = color.toRgb();
		}else{
			rgb = new dojo.gfx.color.Color(color).toRgb();
		}
		return dojo.gfx.color.blend(this.toRgb(), rgb, weight);
	}
});

dojo.gfx.color.named = {
	white:      [255,255,255],
	black:      [0,0,0],
	red:        [255,0,0],
	green:	    [0,255,0],
	lime:	    [0,255,0],
	blue:       [0,0,255],
	navy:       [0,0,128],
	gray:       [128,128,128],
	silver:     [192,192,192]
};

dojo.gfx.color.blend = function(a, b, weight){
	// summary:
	//		blend colors a and b (both as RGB array or hex strings) with weight
	//		from -1 to +1, 0 being a 50/50 blend
	if(typeof a == "string"){
		return dojo.gfx.color.blendHex(a, b, weight);
	}
	if(!weight){
		weight = 0;
	}
	weight = Math.min(Math.max(-1, weight), 1);

	// alex: this interface blows.
	// map -1 to 1 to the range 0 to 1
	weight = ((weight + 1)/2);

	var c = [];

	// var stop = (1000*weight);
	for(var x = 0; x < 3; x++){
		c[x] = parseInt( b[x] + ( (a[x] - b[x]) * weight) );
	}
	return c;
}

// very convenient blend that takes and returns hex values
// (will get called automatically by blend when blend gets strings)
dojo.gfx.color.blendHex = function(a, b, weight) {
	return dojo.gfx.color.rgb2hex(dojo.gfx.color.blend(dojo.gfx.color.hex2rgb(a), dojo.gfx.color.hex2rgb(b), weight));
}

// get RGB array from css-style color declarations
dojo.gfx.color.extractRGB = function(color) {
	var hex = "0123456789abcdef";
	color = color.toLowerCase();
	if( color.indexOf("rgb") == 0 ) {
		var matches = color.match(/rgba*\((\d+), *(\d+), *(\d+)/i);
		var ret = matches.splice(1, 3);
		return ret;
	} else {
		var colors = dojo.gfx.color.hex2rgb(color);
		if(colors) {
			return colors;
		} else {
			// named color (how many do we support?)
			return dojo.gfx.color.named[color] || [255, 255, 255];
		}
	}
}

dojo.gfx.color.hex2rgb = function(hex) {
	var hexNum = "0123456789ABCDEF";
	var rgb = new Array(3);
	if( hex.indexOf("#") == 0 ) { hex = hex.substring(1); }
	hex = hex.toUpperCase();
	if(hex.replace(new RegExp("["+hexNum+"]", "g"), "") != "") {
		return null;
	}
	if( hex.length == 3 ) {
		rgb[0] = hex.charAt(0) + hex.charAt(0)
		rgb[1] = hex.charAt(1) + hex.charAt(1)
		rgb[2] = hex.charAt(2) + hex.charAt(2);
	} else {
		rgb[0] = hex.substring(0, 2);
		rgb[1] = hex.substring(2, 4);
		rgb[2] = hex.substring(4);
	}
	for(var i = 0; i < rgb.length; i++) {
		rgb[i] = hexNum.indexOf(rgb[i].charAt(0)) * 16 + hexNum.indexOf(rgb[i].charAt(1));
	}
	return rgb;
}

dojo.gfx.color.rgb2hex = function(r, g, b) {
	if(dojo.lang.isArray(r)) {
		g = r[1] || 0;
		b = r[2] || 0;
		r = r[0] || 0;
	}
	var ret = dojo.lang.map([r, g, b], function(x) {
		x = new Number(x);
		var s = x.toString(16);
		while(s.length < 2) { s = "0" + s; }
		return s;
	});
	ret.unshift("#");
	return ret.join("");
}

dojo.provide("dojo.lfx.Animation");


/*
	Animation package based on Dan Pupius' work: http://pupius.co.uk/js/Toolkit.Drawing.js
*/
dojo.lfx.Line = function(/*int*/ start, /*int*/ end){
	// summary: dojo.lfx.Line is the object used to generate values
	//			from a start value to an end value
	this.start = start;
	this.end = end;
	if(dojo.lang.isArray(start)){
		/* start: Array
		   end: Array
		   pId: a */
		var diff = [];
		dojo.lang.forEach(this.start, function(s,i){
			diff[i] = this.end[i] - s;
		}, this);

		this.getValue = function(/*float*/ n){
			var res = [];
			dojo.lang.forEach(this.start, function(s, i){
				res[i] = (diff[i] * n) + s;
			}, this);
			return res; // Array
		}
	}else{
		var diff = end - start;

		this.getValue = function(/*float*/ n){
			//	summary: returns the point on the line
			//	n: a floating point number greater than 0 and less than 1
			return (diff * n) + this.start; // Decimal
		}
	}
}

dojo.lfx.easeDefault = function(/*Decimal?*/ n){
	//	summary: Returns the point for point n on a sin wave.
	if(dojo.render.html.khtml){
		// the cool kids are obviously not using konqueror...
		// found a very wierd bug in floats constants, 1.5 evals as 1
		// seems somebody mixed up ints and floats in 3.5.4 ??
		// FIXME: investigate more and post a KDE bug (Fredrik)
		return (parseFloat("0.5")+((Math.sin( (n+parseFloat("1.5")) * Math.PI))/2));
	}else{
		return (0.5+((Math.sin( (n+1.5) * Math.PI))/2));
	}
}

dojo.lfx.easeIn = function(/*Decimal?*/ n){
	//	summary: returns the point on an easing curve
	//	n: a floating point number greater than 0 and less than 1
	return Math.pow(n, 3);
}

dojo.lfx.easeOut = function(/*Decimal?*/ n){
	//	summary: returns the point on the line
	//	n: a floating point number greater than 0 and less than 1
	return ( 1 - Math.pow(1 - n, 3) );
}

dojo.lfx.easeInOut = function(/*Decimal?*/ n){
	//	summary: returns the point on the line
	//	n: a floating point number greater than 0 and less than 1
	return ( (3 * Math.pow(n, 2)) - (2 * Math.pow(n, 3)) );
}

dojo.lfx.IAnimation = function(){
	// summary: dojo.lfx.IAnimation is an interface that implements
	//			commonly used functions of animation objects
}
dojo.lang.extend(dojo.lfx.IAnimation, {
	// public properties
	curve: null,
	duration: 1000,
	easing: null,
	repeatCount: 0,
	rate: 25,

	// events
	handler: null,
	beforeBegin: null,
	onBegin: null,
	onAnimate: null,
	onEnd: null,
	onPlay: null,
	onPause: null,
	onStop: null,

	// public methods
	play: null,
	pause: null,
	stop: null,

	connect: function(/*Event*/ evt, /*Object*/ scope, /*Function*/ newFunc){
		// summary: Convenience function.  Quickly connect to an event
		//			of this object and save the old functions connected to it.
		// evt: The name of the event to connect to.
		// scope: the scope in which to run newFunc.
		// newFunc: the function to run when evt is fired.
		if(!newFunc){
			/* scope: Function
			   newFunc: null
			   pId: f */
			newFunc = scope;
			scope = this;
		}
		newFunc = dojo.lang.hitch(scope, newFunc);
		var oldFunc = this[evt]||function(){};
		this[evt] = function(){
			var ret = oldFunc.apply(this, arguments);
			newFunc.apply(this, arguments);
			return ret;
		}
		return this; // dojo.lfx.IAnimation
	},

	fire: function(/*Event*/ evt, /*Array*/ args){
		// summary: Convenience function.  Fire event "evt" and pass it
		//			the arguments specified in "args".
		// evt: The event to fire.
		// args: The arguments to pass to the event.
		if(this[evt]){
			this[evt].apply(this, (args||[]));
		}
		return this; // dojo.lfx.IAnimation
	},

	repeat: function(/*int*/ count){
		// summary: Set the repeat count of this object.
		// count: How many times to repeat the animation.
		this.repeatCount = count;
		return this; // dojo.lfx.IAnimation
	},

	// private properties
	_active: false,
	_paused: false
});

dojo.lfx.Animation = function(	/*Object*/ handlers,
								/*int*/ duration,
								/*dojo.lfx.Line*/ curve,
								/*function*/ easing,
								/*int*/ repeatCount,
								/*int*/ rate){
	//	summary
	//		a generic animation object that fires callbacks into it's handlers
	//		object at various states
	//	handlers: { handler: Function?, onstart: Function?, onstop: Function?, onanimate: Function? }
	dojo.lfx.IAnimation.call(this);
	if(dojo.lang.isNumber(handlers)||(!handlers && duration.getValue)){
		// no handlers argument:
		rate = repeatCount;
		repeatCount = easing;
		easing = curve;
		curve = duration;
		duration = handlers;
		handlers = null;
	}else if(handlers.getValue||dojo.lang.isArray(handlers)){
		// no handlers or duration:
		rate = easing;
		repeatCount = curve;
		easing = duration;
		curve = handlers;
		duration = null;
		handlers = null;
	}
	if(dojo.lang.isArray(curve)){
		/* curve: Array
		   pId: a */
		this.curve = new dojo.lfx.Line(curve[0], curve[1]);
	}else{
		this.curve = curve;
	}
	if(duration != null && duration > 0){ this.duration = duration; }
	if(repeatCount){ this.repeatCount = repeatCount; }
	if(rate){ this.rate = rate; }
	if(handlers){
		dojo.lang.forEach([
				"handler", "beforeBegin", "onBegin",
				"onEnd", "onPlay", "onStop", "onAnimate"
			], function(item){
				if(handlers[item]){
					this.connect(item, handlers[item]);
				}
			}, this);
	}
	if(easing && dojo.lang.isFunction(easing)){
		this.easing=easing;
	}
}
dojo.inherits(dojo.lfx.Animation, dojo.lfx.IAnimation);
dojo.lang.extend(dojo.lfx.Animation, {
	// "private" properties
	_startTime: null,
	_endTime: null,
	_timer: null,
	_percent: 0,
	_startRepeatCount: 0,

	// public methods
	play: function(/*int?*/ delay, /*bool?*/ gotoStart){
		// summary: Start the animation.
		// delay: How many milliseconds to delay before starting.
		// gotoStart: If true, starts the animation from the beginning; otherwise,
		//            starts it from its current position.
		if(gotoStart){
			clearTimeout(this._timer);
			this._active = false;
			this._paused = false;
			this._percent = 0;
		}else if(this._active && !this._paused){
			return this; // dojo.lfx.Animation
		}

		this.fire("handler", ["beforeBegin"]);
		this.fire("beforeBegin");

		if(delay > 0){
			setTimeout(dojo.lang.hitch(this, function(){ this.play(null, gotoStart); }), delay);
			return this; // dojo.lfx.Animation
		}

		this._startTime = new Date().valueOf();
		if(this._paused){
			this._startTime -= (this.duration * this._percent / 100);
		}
		this._endTime = this._startTime + this.duration;

		this._active = true;
		this._paused = false;

		var step = this._percent / 100;
		var value = this.curve.getValue(step);
		if(this._percent == 0 ){
			if(!this._startRepeatCount){
				this._startRepeatCount = this.repeatCount;
			}
			this.fire("handler", ["begin", value]);
			this.fire("onBegin", [value]);
		}

		this.fire("handler", ["play", value]);
		this.fire("onPlay", [value]);

		this._cycle();
		return this; // dojo.lfx.Animation
	},

	pause: function(){
		// summary: Pauses a running animation.
		clearTimeout(this._timer);
		if(!this._active){ return this; /*dojo.lfx.Animation*/}
		this._paused = true;
		var value = this.curve.getValue(this._percent / 100);
		this.fire("handler", ["pause", value]);
		this.fire("onPause", [value]);
		return this; // dojo.lfx.Animation
	},

	gotoPercent: function(/*Decimal*/ pct, /*bool?*/ andPlay){
		// summary: Sets the progress of the animation.
		// pct: A percentage in decimal notation (between and including 0.0 and 1.0).
		// andPlay: If true, play the animation after setting the progress.
		clearTimeout(this._timer);
		this._active = true;
		this._paused = true;
		this._percent = pct;
		if(andPlay){ this.play(); }
		return this; // dojo.lfx.Animation
	},

	stop: function(/*bool?*/ gotoEnd){
		// summary: Stops a running animation.
		// gotoEnd: If true, the animation will end.
		clearTimeout(this._timer);
		var step = this._percent / 100;
		if(gotoEnd){
			step = 1;
		}
		var value = this.curve.getValue(step);
		this.fire("handler", ["stop", value]);
		this.fire("onStop", [value]);
		this._active = false;
		this._paused = false;
		return this; // dojo.lfx.Animation
	},

	status: function(){
		// summary: Returns a string representation of the status of
		//			the animation.
		if(this._active){
			return this._paused ? "paused" : "playing"; // String
		}else{
			return "stopped"; // String
		}
		return this;
	},

	// "private" methods
	_cycle: function(){
		clearTimeout(this._timer);
		if(this._active){
			var curr = new Date().valueOf();
			var step = (curr - this._startTime) / (this._endTime - this._startTime);

			if(step >= 1){
				step = 1;
				this._percent = 100;
			}else{
				this._percent = step * 100;
			}

			// Perform easing
			if((this.easing)&&(dojo.lang.isFunction(this.easing))){
				step = this.easing(step);
			}

			var value = this.curve.getValue(step);
			this.fire("handler", ["animate", value]);
			this.fire("onAnimate", [value]);

			if( step < 1 ){
				this._timer = setTimeout(dojo.lang.hitch(this, "_cycle"), this.rate);
			}else{
				this._active = false;
				this.fire("handler", ["end"]);
				this.fire("onEnd");

				if(this.repeatCount > 0){
					this.repeatCount--;
					this.play(null, true);
				}else if(this.repeatCount == -1){
					this.play(null, true);
				}else{
					if(this._startRepeatCount){
						this.repeatCount = this._startRepeatCount;
						this._startRepeatCount = 0;
					}
				}
			}
		}
		return this; // dojo.lfx.Animation
	}
});

dojo.lfx.Combine = function(/*dojo.lfx.IAnimation...*/ animations){
	// summary: An animation object to play animations passed to it at the same time.
	dojo.lfx.IAnimation.call(this);
	this._anims = [];
	this._animsEnded = 0;

	var anims = arguments;
	if(anims.length == 1 && (dojo.lang.isArray(anims[0]) || dojo.lang.isArrayLike(anims[0]))){
		/* animations: dojo.lfx.IAnimation[]
		   pId: a */
		anims = anims[0];
	}

	dojo.lang.forEach(anims, function(anim){
		this._anims.push(anim);
		anim.connect("onEnd", dojo.lang.hitch(this, "_onAnimsEnded"));
	}, this);
}
dojo.inherits(dojo.lfx.Combine, dojo.lfx.IAnimation);
dojo.lang.extend(dojo.lfx.Combine, {
	// private members
	_animsEnded: 0,

	// public methods
	play: function(/*int?*/ delay, /*bool?*/ gotoStart){
		// summary: Start the animations.
		// delay: How many milliseconds to delay before starting.
		// gotoStart: If true, starts the animations from the beginning; otherwise,
		//            starts them from their current position.
		if( !this._anims.length ){ return this; /*dojo.lfx.Combine*/}

		this.fire("beforeBegin");

		if(delay > 0){
			setTimeout(dojo.lang.hitch(this, function(){ this.play(null, gotoStart); }), delay);
			return this; // dojo.lfx.Combine
		}

		if(gotoStart || this._anims[0].percent == 0){
			this.fire("onBegin");
		}
		this.fire("onPlay");
		this._animsCall("play", null, gotoStart);
		return this; // dojo.lfx.Combine
	},

	pause: function(){
		// summary: Pauses the running animations.
		this.fire("onPause");
		this._animsCall("pause");
		return this; // dojo.lfx.Combine
	},

	stop: function(/*bool?*/ gotoEnd){
		// summary: Stops the running animations.
		// gotoEnd: If true, the animations will end.
		this.fire("onStop");
		this._animsCall("stop", gotoEnd);
		return this; // dojo.lfx.Combine
	},

	// private methods
	_onAnimsEnded: function(){
		this._animsEnded++;
		if(this._animsEnded >= this._anims.length){
			this.fire("onEnd");
		}
		return this; // dojo.lfx.Combine
	},

	_animsCall: function(/*String*/ funcName){
		var args = [];
		if(arguments.length > 1){
			for(var i = 1 ; i < arguments.length ; i++){
				args.push(arguments[i]);
			}
		}
		var _this = this;
		dojo.lang.forEach(this._anims, function(anim){
			anim[funcName](args);
		}, _this);
		return this; // dojo.lfx.Combine
	}
});

dojo.lfx.Chain = function(/*dojo.lfx.IAnimation...*/ animations) {
	// summary: An animation object to play animations passed to it
	//			one after another.
	dojo.lfx.IAnimation.call(this);
	this._anims = [];
	this._currAnim = -1;

	var anims = arguments;
	if(anims.length == 1 && (dojo.lang.isArray(anims[0]) || dojo.lang.isArrayLike(anims[0]))){
		/* animations: dojo.lfx.IAnimation[]
		   pId: a */
		anims = anims[0];
	}

	var _this = this;
	dojo.lang.forEach(anims, function(anim, i, anims_arr){
		this._anims.push(anim);
		if(i < anims_arr.length - 1){
			anim.connect("onEnd", dojo.lang.hitch(this, "_playNext") );
		}else{
			anim.connect("onEnd", dojo.lang.hitch(this, function(){ this.fire("onEnd"); }) );
		}
	}, this);
}
dojo.inherits(dojo.lfx.Chain, dojo.lfx.IAnimation);
dojo.lang.extend(dojo.lfx.Chain, {
	// private members
	_currAnim: -1,

	// public methods
	play: function(/*int?*/ delay, /*bool?*/ gotoStart){
		// summary: Start the animation sequence.
		// delay: How many milliseconds to delay before starting.
		// gotoStart: If true, starts the sequence from the beginning; otherwise,
		//            starts it from its current position.
		if( !this._anims.length ) { return this; /*dojo.lfx.Chain*/}
		if( gotoStart || !this._anims[this._currAnim] ) {
			this._currAnim = 0;
		}

		var currentAnimation = this._anims[this._currAnim];

		this.fire("beforeBegin");
		if(delay > 0){
			setTimeout(dojo.lang.hitch(this, function(){ this.play(null, gotoStart); }), delay);
			return this; // dojo.lfx.Chain
		}

		if(currentAnimation){
			if(this._currAnim == 0){
				this.fire("handler", ["begin", this._currAnim]);
				this.fire("onBegin", [this._currAnim]);
			}
			this.fire("onPlay", [this._currAnim]);
			currentAnimation.play(null, gotoStart);
		}
		return this; // dojo.lfx.Chain
	},

	pause: function(){
		// summary: Pauses the running animation sequence.
		if( this._anims[this._currAnim] ) {
			this._anims[this._currAnim].pause();
			this.fire("onPause", [this._currAnim]);
		}
		return this; // dojo.lfx.Chain
	},

	playPause: function(){
		// summary: If the animation sequence is playing, pause it; otherwise,
		//			play it.
		if(this._anims.length == 0){ return this; }
		if(this._currAnim == -1){ this._currAnim = 0; }
		var currAnim = this._anims[this._currAnim];
		if( currAnim ) {
			if( !currAnim._active || currAnim._paused ) {
				this.play();
			} else {
				this.pause();
			}
		}
		return this; // dojo.lfx.Chain
	},

	stop: function(){
		// summary: Stops the running animations.
		var currAnim = this._anims[this._currAnim];
		if(currAnim){
			currAnim.stop();
			this.fire("onStop", [this._currAnim]);
		}
		return currAnim; // dojo.lfx.IAnimation
	},

	// private methods
	_playNext: function(){
		if( this._currAnim == -1 || this._anims.length == 0 ) { return this; }
		this._currAnim++;
		if( this._anims[this._currAnim] ){
			this._anims[this._currAnim].play(null, true);
		}
		return this; // dojo.lfx.Chain
	}
});

dojo.lfx.combine = function(/*dojo.lfx.IAnimation...*/ animations){
	// summary: Convenience function.  Returns a dojo.lfx.Combine created
	//			using the animations passed in.
	var anims = arguments;
	if(dojo.lang.isArray(arguments[0])){
		/* animations: dojo.lfx.IAnimation[]
		   pId: a */
		anims = arguments[0];
	}
	if(anims.length == 1){ return anims[0]; }
	return new dojo.lfx.Combine(anims); // dojo.lfx.Combine
}

dojo.lfx.chain = function(/*dojo.lfx.IAnimation...*/ animations){
	// summary: Convenience function.  Returns a dojo.lfx.Chain created
	//			using the animations passed in.
	var anims = arguments;
	if(dojo.lang.isArray(arguments[0])){
		/* animations: dojo.lfx.IAnimation[]
		   pId: a */
		anims = arguments[0];
	}
	if(anims.length == 1){ return anims[0]; }
	return new dojo.lfx.Chain(anims); // dojo.lfx.Combine
}

dojo.provide("dojo.html.color");


dojo.html.getBackgroundColor = function(/* HTMLElement */node){
	//	summary
	//	returns the background color of the passed node as a 32-bit color (RGBA)
	node = dojo.byId(node);
	var color;
	do{
		color = dojo.html.getStyle(node, "background-color");
		// Safari doesn't say "transparent"
		if(color.toLowerCase() == "rgba(0, 0, 0, 0)") { color = "transparent"; }
		if(node == document.getElementsByTagName("body")[0]) { node = null; break; }
		node = node.parentNode;
	}while(node && dojo.lang.inArray(["transparent", ""], color));
	if(color == "transparent"){
		color = [255, 255, 255, 0];
	}else{
		color = dojo.gfx.color.extractRGB(color);
	}
	return color;	//	array
}

dojo.provide("dojo.lfx.html");


dojo.lfx.html._byId = function(nodes){
	if(!nodes){ return []; }
	if(dojo.lang.isArrayLike(nodes)){
		if(!nodes.alreadyChecked){
			var n = [];
			dojo.lang.forEach(nodes, function(node){
				n.push(dojo.byId(node));
			});
			n.alreadyChecked = true;
			return n;
		}else{
			return nodes;
		}
	}else{
		var n = [];
		n.push(dojo.byId(nodes));
		n.alreadyChecked = true;
		return n;
	}
}

dojo.lfx.html.propertyAnimation = function(	/*DOMNode[]*/ nodes,
											/*Object[]*/ propertyMap,
											/*int*/ duration,
											/*function*/ easing,
											/*Object*/ handlers){
	// summary: Returns an animation that will transition the properties of "nodes"
	//			depending how they are defined in "propertyMap".
	// nodes: An array of DOMNodes or one DOMNode.
	// propertyMap: { property: String, start: Decimal?, end: Decimal?, units: String? }
	//				An array of objects defining properties to change.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// handlers: { handler: Function?, onstart: Function?, onstop: Function?, onanimate: Function? }
	nodes = dojo.lfx.html._byId(nodes);

	var targs = {
		"propertyMap": propertyMap,
		"nodes": nodes,
		"duration": duration,
		"easing": easing||dojo.lfx.easeDefault
	};

	var setEmUp = function(args){
		if(args.nodes.length==1){
			// FIXME: we're only supporting start-value filling when one node is
			// passed

			var pm = args.propertyMap;
			if(!dojo.lang.isArray(args.propertyMap)){
				// it's stupid to have to pack an array with a set of objects
				// when you can just pass in an object list
				var parr = [];
				for(var pname in pm){
					pm[pname].property = pname;
					parr.push(pm[pname]);
				}
				pm = args.propertyMap = parr;
			}
			dojo.lang.forEach(pm, function(prop){
				if(dj_undef("start", prop)){
					if(prop.property != "opacity"){
						prop.start = parseInt(dojo.html.getComputedStyle(args.nodes[0], prop.property));
					}else{
						prop.start = dojo.html.getOpacity(args.nodes[0]);
					}
				}
			});
		}
	}

	var coordsAsInts = function(coords){
		var cints = [];
		dojo.lang.forEach(coords, function(c){
			cints.push(Math.round(c));
		});
		return cints;
	}

	var setStyle = function(n, style){
		n = dojo.byId(n);
		if(!n || !n.style){ return; }
		for(var s in style){
			try{
				if(s == "opacity"){
					dojo.html.setOpacity(n, style[s]);
				}else{
						n.style[s] = style[s];
				}
			}catch(e){ dojo.debug(e); }
		}
	}

	var propLine = function(properties){
		this._properties = properties;
		this.diffs = new Array(properties.length);
		dojo.lang.forEach(properties, function(prop, i){
			// calculate the end - start to optimize a bit
			if(dojo.lang.isFunction(prop.start)){
				prop.start = prop.start(prop, i);
			}
			if(dojo.lang.isFunction(prop.end)){
				prop.end = prop.end(prop, i);
			}
			if(dojo.lang.isArray(prop.start)){
				// don't loop through the arrays
				this.diffs[i] = null;
			}else if(prop.start instanceof dojo.gfx.color.Color){
				// save these so we don't have to call toRgb() every getValue() call
				prop.startRgb = prop.start.toRgb();
				prop.endRgb = prop.end.toRgb();
			}else{
				this.diffs[i] = prop.end - prop.start;
			}
		}, this);

		this.getValue = function(n){
			var ret = {};
			dojo.lang.forEach(this._properties, function(prop, i){
				var value = null;
				if(dojo.lang.isArray(prop.start)){
					// FIXME: what to do here?
				}else if(prop.start instanceof dojo.gfx.color.Color){
					value = (prop.units||"rgb") + "(";
					for(var j = 0 ; j < prop.startRgb.length ; j++){
						value += Math.round(((prop.endRgb[j] - prop.startRgb[j]) * n) + prop.startRgb[j]) + (j < prop.startRgb.length - 1 ? "," : "");
					}
					value += ")";
				}else{
					value = ((this.diffs[i]) * n) + prop.start + (prop.property != "opacity" ? prop.units||"px" : "");
				}
				ret[dojo.html.toCamelCase(prop.property)] = value;
			}, this);
			return ret;
		}
	}

	var anim = new dojo.lfx.Animation({
			beforeBegin: function(){
				setEmUp(targs);
				anim.curve = new propLine(targs.propertyMap);
			},
			onAnimate: function(propValues){
				dojo.lang.forEach(targs.nodes, function(node){
					setStyle(node, propValues);
				});
			}
		},
		targs.duration,
		null,
		targs.easing
	);
	if(handlers){
		for(var x in handlers){
			if(dojo.lang.isFunction(handlers[x])){
				anim.connect(x, anim, handlers[x]);
			}
		}
	}

	return anim; // dojo.lfx.Animation
}

dojo.lfx.html._makeFadeable = function(nodes){
	var makeFade = function(node){
		if(dojo.render.html.ie){
			// only set the zoom if the "tickle" value would be the same as the
			// default
			if( (node.style.zoom.length == 0) &&
				(dojo.html.getStyle(node, "zoom") == "normal") ){
				// make sure the node "hasLayout"
				// NOTE: this has been tested with larger and smaller user-set text
				// sizes and works fine
				node.style.zoom = "1";
				// node.style.zoom = "normal";
			}
			// don't set the width to auto if it didn't already cascade that way.
			// We don't want to f anyones designs
			if(	(node.style.width.length == 0) &&
				(dojo.html.getStyle(node, "width") == "auto") ){
				node.style.width = "auto";
			}
		}
	}
	if(dojo.lang.isArrayLike(nodes)){
		dojo.lang.forEach(nodes, makeFade);
	}else{
		makeFade(nodes);
	}
}

dojo.lfx.html.fade = function(/*DOMNode[]*/ nodes,
							  /*Object*/values,
							  /*int?*/ duration,
							  /*Function?*/ easing,
							  /*Function?*/ callback){
	// summary:Returns an animation that will fade the "nodes" from the start to end values passed.
	// nodes: An array of DOMNodes or one DOMNode.
	// values: { start: Decimal?, end: Decimal? }
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes = dojo.lfx.html._byId(nodes);
	var props = { property: "opacity" };
	if(!dj_undef("start", values)){
		props.start = values.start;
	}else{
		props.start = function(){ return dojo.html.getOpacity(nodes[0]); };
	}

	if(!dj_undef("end", values)){
		props.end = values.end;
	}else{
		dojo.raise("dojo.lfx.html.fade needs an end value");
	}

	var anim = dojo.lfx.propertyAnimation(nodes, [ props ], duration, easing);
	anim.connect("beforeBegin", function(){
		dojo.lfx.html._makeFadeable(nodes);
	});
	if(callback){
		anim.connect("onEnd", function(){ callback(nodes, anim); });
	}

	return anim; // dojo.lfx.Animation
}

dojo.lfx.html.fadeIn = function(/*DOMNode[]*/ nodes, /*int?*/ duration, /*Function?*/ easing, /*Function?*/ callback){
	// summary: Returns an animation that will fade "nodes" from its current opacity to fully opaque.
	// nodes: An array of DOMNodes or one DOMNode.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	return dojo.lfx.html.fade(nodes, { end: 1 }, duration, easing, callback); // dojo.lfx.Animation
}

dojo.lfx.html.fadeOut = function(/*DOMNode[]*/ nodes, /*int?*/ duration, /*Function?*/ easing, /*Function?*/ callback){
	// summary: Returns an animation that will fade "nodes" from its current opacity to fully transparent.
	// nodes: An array of DOMNodes or one DOMNode.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	return dojo.lfx.html.fade(nodes, { end: 0 }, duration, easing, callback); // dojo.lfx.Animation
}

dojo.lfx.html.fadeShow = function(/*DOMNode[]*/ nodes, /*int?*/ duration, /*Function?*/ easing, /*Function?*/ callback){
	// summary: Returns an animation that will fade "nodes" from transparent to opaque and shows
	//			"nodes" at the end if it is hidden.
	// nodes: An array of DOMNodes or one DOMNode.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes=dojo.lfx.html._byId(nodes);
	dojo.lang.forEach(nodes, function(node){
		dojo.html.setOpacity(node, 0.0);
	});

	var anim = dojo.lfx.html.fadeIn(nodes, duration, easing, callback);
	anim.connect("beforeBegin", function(){
		if(dojo.lang.isArrayLike(nodes)){
			dojo.lang.forEach(nodes, dojo.html.show);
		}else{
			dojo.html.show(nodes);
		}
	});

	return anim; // dojo.lfx.Animation
}

dojo.lfx.html.fadeHide = function(/*DOMNode[]*/ nodes, /*int?*/ duration, /*Function?*/ easing, /*Function?*/ callback){
	// summary: Returns an animation that will fade "nodes" from its current opacity to opaque and hides
	//			"nodes" at the end.
	// nodes: An array of DOMNodes or one DOMNode.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	var anim = dojo.lfx.html.fadeOut(nodes, duration, easing, function(){
		if(dojo.lang.isArrayLike(nodes)){
			dojo.lang.forEach(nodes, dojo.html.hide);
		}else{
			dojo.html.hide(nodes);
		}
		if(callback){ callback(nodes, anim); }
	});

	return anim; // dojo.lfx.Animation
}

dojo.lfx.html.wipeIn = function(/*DOMNode[]*/ nodes, /*int?*/ duration, /*Function?*/ easing, /*Function?*/ callback){
	// summary: Returns an animation that will show and wipe in "nodes".
	// nodes: An array of DOMNodes or one DOMNode.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes = dojo.lfx.html._byId(nodes);
	var anims = [];

	dojo.lang.forEach(nodes, function(node){
		var oprop = {  };	// old properties of node (before we mucked w/them)

		// get node height, either it's natural height or it's height specified via style or class attributes
		// (for FF, the node has to be (temporarily) rendered to measure height)
		// TODO: should this offscreen code be part of dojo.html, so that getBorderBox() works on hidden nodes?
		var origTop, origLeft, origPosition;
		with(node.style){
			origTop=top; origLeft=left; origPosition=position;
			top="-9999px"; left="-9999px"; position="absolute";
			display="";
		}
		var height = dojo.html.getBorderBox(node).height;
		with(node.style){
			top=origTop; left=origLeft; position=origPosition;
			display="none";
		}

		var anim = dojo.lfx.propertyAnimation(node,
			{	"height": {
					start: 1, // 0 causes IE to display the whole panel
					end: function(){ return height; }
				}
			},
			duration,
			easing);

		anim.connect("beforeBegin", function(){
			oprop.overflow = node.style.overflow;
			oprop.height = node.style.height;
			with(node.style){
				overflow = "hidden";
				height = "1px"; // 0 causes IE to display the whole panel
			}
			dojo.html.show(node);
		});

		anim.connect("onEnd", function(){
			with(node.style){
				overflow = oprop.overflow;
				height = oprop.height;
			}
			if(callback){ callback(node, anim); }
		});
		anims.push(anim);
	});

	return dojo.lfx.combine(anims); // dojo.lfx.Combine
}

dojo.lfx.html.wipeOut = function(/*DOMNode[]*/ nodes, /*int?*/ duration, /*Function?*/ easing, /*Function?*/ callback){
	// summary: Returns an animation that will wipe out and hide "nodes".
	// nodes: An array of DOMNodes or one DOMNode.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes = dojo.lfx.html._byId(nodes);
	var anims = [];

	dojo.lang.forEach(nodes, function(node){
		var oprop = {  };	// old properties of node (before we mucked w/them)
		var anim = dojo.lfx.propertyAnimation(node,
			{	"height": {
					start: function(){ return dojo.html.getContentBox(node).height; },
					end: 1 // 0 causes IE to display the whole panel
				}
			},
			duration,
			easing,
			{
				"beforeBegin": function(){
					oprop.overflow = node.style.overflow;
					oprop.height = node.style.height;
					with(node.style){
						overflow = "hidden";
					}
					dojo.html.show(node);
				},

				"onEnd": function(){
					dojo.html.hide(node);
					with(node.style){
						overflow = oprop.overflow;
						height = oprop.height;
					}
					if(callback){ callback(node, anim); }
				}
			}
		);
		anims.push(anim);
	});

	return dojo.lfx.combine(anims); // dojo.lfx.Combine
}

dojo.lfx.html.slideTo = function(/*DOMNode*/ nodes,
								 /*Object*/ coords,
								 /*int?*/ duration,
								 /*Function?*/ easing,
								 /*Function?*/ callback){
	// summary: Returns an animation that will slide "nodes" from its current position to
	//			the position defined in "coords".
	// nodes: An array of DOMNodes or one DOMNode.
	// coords: { top: Decimal?, left: Decimal? }
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes = dojo.lfx.html._byId(nodes);
	var anims = [];
	var compute = dojo.html.getComputedStyle;

	if(dojo.lang.isArray(coords)){
		/* coords: Array
		   pId: a */
		dojo.deprecated('dojo.lfx.html.slideTo(node, array)', 'use dojo.lfx.html.slideTo(node, {top: value, left: value});', '0.5');
		coords = { top: coords[0], left: coords[1] };
	}
	dojo.lang.forEach(nodes, function(node){
		var top = null;
		var left = null;

		var init = (function(){
			var innerNode = node;
			return function(){
				var pos = compute(innerNode, 'position');
				top = (pos == 'absolute' ? node.offsetTop : parseInt(compute(node, 'top')) || 0);
				left = (pos == 'absolute' ? node.offsetLeft : parseInt(compute(node, 'left')) || 0);

				if (!dojo.lang.inArray(['absolute', 'relative'], pos)) {
					var ret = dojo.html.abs(innerNode, true);
					dojo.html.setStyleAttributes(innerNode, "position:absolute;top:"+ret.y+"px;left:"+ret.x+"px;");
					top = ret.y;
					left = ret.x;
				}
			}
		})();
		init();

		var anim = dojo.lfx.propertyAnimation(node,
			{	"top": { start: top, end: (coords.top||0) },
				"left": { start: left, end: (coords.left||0)  }
			},
			duration,
			easing,
			{ "beforeBegin": init }
		);

		if(callback){
			anim.connect("onEnd", function(){ callback(nodes, anim); });
		}

		anims.push(anim);
	});

	return dojo.lfx.combine(anims); // dojo.lfx.Combine
}

dojo.lfx.html.slideBy = function(/*DOMNode*/ nodes, /*Object*/ coords, /*int?*/ duration, /*Function?*/ easing, /*Function?*/ callback){
	// summary: Returns an animation that will slide "nodes" from its current position
	//			to its current position plus the numbers defined in "coords".
	// nodes: An array of DOMNodes or one DOMNode.
	// coords: { top: Decimal?, left: Decimal? }
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes = dojo.lfx.html._byId(nodes);
	var anims = [];
	var compute = dojo.html.getComputedStyle;

	if(dojo.lang.isArray(coords)){
		/* coords: Array
		   pId: a */
		dojo.deprecated('dojo.lfx.html.slideBy(node, array)', 'use dojo.lfx.html.slideBy(node, {top: value, left: value});', '0.5');
		coords = { top: coords[0], left: coords[1] };
	}

	dojo.lang.forEach(nodes, function(node){
		var top = null;
		var left = null;

		var init = (function(){
			var innerNode = node;
			return function(){
				var pos = compute(innerNode, 'position');
				top = (pos == 'absolute' ? node.offsetTop : parseInt(compute(node, 'top')) || 0);
				left = (pos == 'absolute' ? node.offsetLeft : parseInt(compute(node, 'left')) || 0);

				if (!dojo.lang.inArray(['absolute', 'relative'], pos)) {
					var ret = dojo.html.abs(innerNode, true);
					dojo.html.setStyleAttributes(innerNode, "position:absolute;top:"+ret.y+"px;left:"+ret.x+"px;");
					top = ret.y;
					left = ret.x;
				}
			}
		})();
		init();

		var anim = dojo.lfx.propertyAnimation(node,
			{
				"top": { start: top, end: top+(coords.top||0) },
				"left": { start: left, end: left+(coords.left||0) }
			},
			duration,
			easing).connect("beforeBegin", init);

		if(callback){
			anim.connect("onEnd", function(){ callback(nodes, anim); });
		}

		anims.push(anim);
	});

	return dojo.lfx.combine(anims); // dojo.lfx.Combine
}

dojo.lfx.html.explode = function(/*DOMNode*/ start,
								 /*DOMNode*/ endNode,
								 /*int?*/ duration,
								 /*Function?*/ easing,
								 /*Function?*/ callback){
	// summary: Returns an animation that will
	// start:
	// endNode:
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	var h = dojo.html;
	start = dojo.byId(start);
	endNode = dojo.byId(endNode);
	var startCoords = h.toCoordinateObject(start, true);
	var outline = document.createElement("div");
	h.copyStyle(outline, endNode);
	if(endNode.explodeClassName){ outline.className = endNode.explodeClassName; }
	with(outline.style){
		position = "absolute";
		display = "none";
		// border = "1px solid black";
		var backgroundStyle = h.getStyle(start, "background-color");
		backgroundColor = backgroundStyle ? backgroundStyle.toLowerCase() : "transparent";
		backgroundColor = (backgroundColor == "transparent") ? "rgb(221, 221, 221)" : backgroundColor;
	}
	dojo.body().appendChild(outline);

	with(endNode.style){
		visibility = "hidden";
		display = "block";
	}
	var endCoords = h.toCoordinateObject(endNode, true);
	with(endNode.style){
		display = "none";
		visibility = "visible";
	}

	var props = { opacity: { start: 0.5, end: 1.0 } };
	dojo.lang.forEach(["height", "width", "top", "left"], function(type){
		props[type] = { start: startCoords[type], end: endCoords[type] }
	});

	var anim = new dojo.lfx.propertyAnimation(outline,
		props,
		duration,
		easing,
		{
			"beforeBegin": function(){
				h.setDisplay(outline, "block");
			},
			"onEnd": function(){
				h.setDisplay(endNode, "block");
				outline.parentNode.removeChild(outline);
			}
		}
	);

	if(callback){
		anim.connect("onEnd", function(){ callback(endNode, anim); });
	}
	return anim; // dojo.lfx.Animation
}

dojo.lfx.html.implode = function(/*DOMNode*/ startNode,
								 /*DOMNode*/ end,
								 /*int?*/ duration,
								 /*Function?*/ easing,
								 /*Function?*/ callback){
	// summary: Returns an animation that will
	// startNode:
	// end:
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	var h = dojo.html;
	startNode = dojo.byId(startNode);
	end = dojo.byId(end);
	var startCoords = dojo.html.toCoordinateObject(startNode, true);
	var endCoords = dojo.html.toCoordinateObject(end, true);

	var outline = document.createElement("div");
	dojo.html.copyStyle(outline, startNode);
	if (startNode.explodeClassName) { outline.className = startNode.explodeClassName; }
	dojo.html.setOpacity(outline, 0.3);
	with(outline.style){
		position = "absolute";
		display = "none";
		backgroundColor = h.getStyle(startNode, "background-color").toLowerCase();
	}
	dojo.body().appendChild(outline);

	var props = { opacity: { start: 1.0, end: 0.5 } };
	dojo.lang.forEach(["height", "width", "top", "left"], function(type){
		props[type] = { start: startCoords[type], end: endCoords[type] }
	});

	var anim = new dojo.lfx.propertyAnimation(outline,
		props,
		duration,
		easing,
		{
			"beforeBegin": function(){
				dojo.html.hide(startNode);
				dojo.html.show(outline);
			},
			"onEnd": function(){
				outline.parentNode.removeChild(outline);
			}
		}
	);

	if(callback){
		anim.connect("onEnd", function(){ callback(startNode, anim); });
	}
	return anim; // dojo.lfx.Animation
}

dojo.lfx.html.highlight = function(/*DOMNode[]*/ nodes,
								   /*dojo.gfx.color.Color*/ startColor,
								   /*int?*/ duration,
								   /*Function?*/ easing,
								   /*Function?*/ callback){
	// summary: Returns an animation that will set the background color
	//			of "nodes" to startColor and transition it to "nodes"
	//			original color.
	// startColor: Color to transition from.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes = dojo.lfx.html._byId(nodes);
	var anims = [];

	dojo.lang.forEach(nodes, function(node){
		var color = dojo.html.getBackgroundColor(node);
		var bg = dojo.html.getStyle(node, "background-color").toLowerCase();
		var bgImage = dojo.html.getStyle(node, "background-image");
		var wasTransparent = (bg == "transparent" || bg == "rgba(0, 0, 0, 0)");
		while(color.length > 3) { color.pop(); }

		var rgb = new dojo.gfx.color.Color(startColor);
		var endRgb = new dojo.gfx.color.Color(color);

		var anim = dojo.lfx.propertyAnimation(node,
			{ "background-color": { start: rgb, end: endRgb } },
			duration,
			easing,
			{
				"beforeBegin": function(){
					if(bgImage){
						node.style.backgroundImage = "none";
					}
					node.style.backgroundColor = "rgb(" + rgb.toRgb().join(",") + ")";
				},
				"onEnd": function(){
					if(bgImage){
						node.style.backgroundImage = bgImage;
					}
					if(wasTransparent){
						node.style.backgroundColor = "transparent";
					}
					if(callback){
						callback(node, anim);
					}
				}
			}
		);

		anims.push(anim);
	});
	return dojo.lfx.combine(anims); // dojo.lfx.Combine
}

dojo.lfx.html.unhighlight = function(/*DOMNode[]*/ nodes,
									 /*dojo.gfx.color.Color*/ endColor,
									 /*int?*/ duration,
									 /*Function?*/ easing,
									 /*Function?*/ callback){
	// summary: Returns an animation that will transition "nodes" background color
	//			from its current color to "endColor".
	// endColor: Color to transition to.
	// duration: Duration of the animation in milliseconds.
	// easing: An easing function.
	// callback: Function to run at the end of the animation.
	nodes = dojo.lfx.html._byId(nodes);
	var anims = [];

	dojo.lang.forEach(nodes, function(node){
		var color = new dojo.gfx.color.Color(dojo.html.getBackgroundColor(node));
		var rgb = new dojo.gfx.color.Color(endColor);

		var bgImage = dojo.html.getStyle(node, "background-image");

		var anim = dojo.lfx.propertyAnimation(node,
			{ "background-color": { start: color, end: rgb } },
			duration,
			easing,
			{
				"beforeBegin": function(){
					if(bgImage){
						node.style.backgroundImage = "none";
					}
					node.style.backgroundColor = "rgb(" + color.toRgb().join(",") + ")";
				},
				"onEnd": function(){
					if(callback){
						callback(node, anim);
					}
				}
			}
		);
		anims.push(anim);
	});
	return dojo.lfx.combine(anims); // dojo.lfx.Combine
}

dojo.lang.mixin(dojo.lfx, dojo.lfx.html);

dojo.provide("dojo.lfx.*");
dojo.provide("dojo.lfx.toggle");

dojo.lfx.toggle.plain = {
	show: function(node, duration, easing, callback){
		dojo.html.show(node);
		if(dojo.lang.isFunction(callback)){ callback(); }
	},

	hide: function(node, duration, easing, callback){
		dojo.html.hide(node);
		if(dojo.lang.isFunction(callback)){ callback(); }
	}
}

dojo.lfx.toggle.fade = {
	show: function(node, duration, easing, callback){
		dojo.lfx.fadeShow(node, duration, easing, callback).play();
	},

	hide: function(node, duration, easing, callback){
		dojo.lfx.fadeHide(node, duration, easing, callback).play();
	}
}

dojo.lfx.toggle.wipe = {
	show: function(node, duration, easing, callback){
		dojo.lfx.wipeIn(node, duration, easing, callback).play();
	},

	hide: function(node, duration, easing, callback){
		dojo.lfx.wipeOut(node, duration, easing, callback).play();
	}
}

dojo.lfx.toggle.explode = {
	show: function(node, duration, easing, callback, explodeSrc){
		dojo.lfx.explode(explodeSrc||{x:0,y:0,width:0,height:0}, node, duration, easing, callback).play();
	},

	hide: function(node, duration, easing, callback, explodeSrc){
		dojo.lfx.implode(node, explodeSrc||{x:0,y:0,width:0,height:0}, duration, easing, callback).play();
	}
}

dojo.provide("dojo.widget.HtmlWidget");

dojo.declare("dojo.widget.HtmlWidget", dojo.widget.DomWidget, {
	// summary
	//	Base class for all browser based widgets, or at least "html" widgets.
	//	The meaning of "html" has become unclear; in practice, all widgets derive from this class.

	// templateCssPath: String
	//	Path to CSS file for this widget
	templateCssPath: null,

	// templatePath: String
	//	Path to template (HTML file) for this widget
	templatePath: null,

	// lang: String
	//	Language to display this widget in (like en-us).
	//	Defaults to brower's specified preferred language (typically the language of the OS)
	lang: "",

	// toggle: String
	//	Controls animation effect for when show() and hide() (or toggle()) are called.
	//	Possible values: "plain", "wipe", "fade", "explode"
	toggle: "plain",

	// toggleDuration: Integer
	//	Number of milliseconds for toggle animation effect to complete
	toggleDuration: 150,

	initialize: function(args, frag){
		// summary: called after the widget is rendered; most subclasses won't override or call this function
	},

	postMixInProperties: function(args, frag){
		if(this.lang === ""){this.lang = null;}
		// now that we know the setting for toggle, get toggle object
		// (default to plain toggler if user specified toggler not present)
		this.toggleObj =
			dojo.lfx.toggle[this.toggle.toLowerCase()] || dojo.lfx.toggle.plain;
	},

	createNodesFromText: function(txt, wrap){
		return dojo.html.createNodesFromText(txt, wrap);
	},

	destroyRendering: function(finalize){
		try{
			if(this.bgIframe){
				this.bgIframe.remove();
				delete this.bgIframe;
			}
			if(!finalize && this.domNode){
				dojo.event.browser.clean(this.domNode);
			}
			dojo.widget.HtmlWidget.superclass.destroyRendering.call(this);
		}catch(e){ /* squelch! */ }
	},

	/////////////////////////////////////////////////////////
	// Displaying/hiding the widget
	/////////////////////////////////////////////////////////
	isShowing: function(){
		// summary
		//	Tests whether widget is set to show-mode or hide-mode (see show() and
		//	hide() methods)
		//
		//	This function is poorly named.  Even if widget is in show-mode,
		//	if it's inside a container that's hidden
		//	(either a container widget, or just a domnode with display:none),
		//	then it won't be displayed
		return dojo.html.isShowing(this.domNode);	// Boolean
	},

	toggleShowing: function(){
		// summary: show or hide the widget, to switch it's state
		if(this.isShowing()){
			this.hide();
		}else{
			this.show();
		}
	},

	show: function(){
		// summary: show the widget
		if(this.isShowing()){ return; }
		this.animationInProgress=true;
		this.toggleObj.show(this.domNode, this.toggleDuration, null,
			dojo.lang.hitch(this, this.onShow), this.explodeSrc);
	},

	onShow: function(){
		// summary: called after the show() animation has completed
		this.animationInProgress=false;
		this.checkSize();
	},

	hide: function(){
		// summary: hide the widget (ending up with display:none)
		if(!this.isShowing()){ return; }
		this.animationInProgress = true;
		this.toggleObj.hide(this.domNode, this.toggleDuration, null,
			dojo.lang.hitch(this, this.onHide), this.explodeSrc);
	},

	onHide: function(){
		// summary: called after the hide() animation has completed
		this.animationInProgress=false;
	},

	//////////////////////////////////////////////////////////////////////////////
	// Sizing related methods
	//  If the parent changes size then for each child it should call either
	//   - resizeTo(): size the child explicitly
	//   - or checkSize(): notify the child the the parent has changed size
	//////////////////////////////////////////////////////////////////////////////

	_isResized: function(w, h){
		// summary
		//	Test if my size has changed.
		//	If width & height are specified then that's my new size; otherwise,
		//	query outerWidth/outerHeight of my domNode

		// If I'm not being displayed then disregard (show() must
		// check if the size has changed)
		if(!this.isShowing()){ return false; }

		// If my parent has been resized and I have style="height: 100%"
		// or something similar then my size has changed too.
		var wh = dojo.html.getMarginBox(this.domNode);
		var width=w||wh.width;
		var height=h||wh.height;
		if(this.width == width && this.height == height){ return false; }

		this.width=width;
		this.height=height;
		return true;
	},

	checkSize: function(){
		// summary
		//	Called when my parent has changed size, but my parent won't call resizeTo().
		//	This is useful if my size is height:100% or something similar.
		//	Also called whenever I am shown, because the first time I am shown I may need
		//	to do size calculations.
		if(!this._isResized()){ return; }
		this.onResized();
	},

	resizeTo: function(w, h){
		// summary: explicitly set this widget's size (in pixels).
		dojo.html.setMarginBox(this.domNode, { width: w, height: h });

		// can't do sizing if widget is hidden because referencing node.offsetWidth/node.offsetHeight returns 0.
		// do sizing on show() instead.
		if(this.isShowing()){
			this.onResized();
		}
	},

	resizeSoon: function(){
		// summary
		//	schedule onResized() to be called soon, after browser has had
		//	a little more time to calculate the sizes
		if(this.isShowing()){
			dojo.lang.setTimeout(this, this.onResized, 0);
		}
	},

	onResized: function(){
		// summary
		//	Called when my size has changed.
		//	Must notify children if their size has (possibly) changed.
		dojo.lang.forEach(this.children, function(child){ if(child.checkSize){child.checkSize();} });
	}
});

dojo.provide("dojo.widget.*");

dojo.provide("dojo.string.common");

dojo.string.trim = function(/* string */str, /* integer? */wh){
	//	summary
	//	Trim whitespace from str.  If wh > 0, trim from start, if wh < 0, trim from end, else both
	if(!str.replace){ return str; }
	if(!str.length){ return str; }
	var re = (wh > 0) ? (/^\s+/) : (wh < 0) ? (/\s+$/) : (/^\s+|\s+$/g);
	return str.replace(re, "");	//	string
}

dojo.string.trimStart = function(/* string */str) {
	//	summary
	//	Trim whitespace at the beginning of 'str'
	return dojo.string.trim(str, 1);	//	string
}

dojo.string.trimEnd = function(/* string */str) {
	//	summary
	//	Trim whitespace at the end of 'str'
	return dojo.string.trim(str, -1);
}

dojo.string.repeat = function(/* string */str, /* integer */count, /* string? */separator) {
	//	summary
	//	Return 'str' repeated 'count' times, optionally placing 'separator' between each rep
	var out = "";
	for(var i = 0; i < count; i++) {
		out += str;
		if(separator && i < count - 1) {
			out += separator;
		}
	}
	return out;	//	string
}

dojo.string.pad = function(/* string */str, /* integer */len/*=2*/, /* string */ c/*='0'*/, /* integer */dir/*=1*/) {
	//	summary
	//	Pad 'str' to guarantee that it is at least 'len' length with the character 'c' at either the
	//	start (dir=1) or end (dir=-1) of the string
	var out = String(str);
	if(!c) {
		c = '0';
	}
	if(!dir) {
		dir = 1;
	}
	while(out.length < len) {
		if(dir > 0) {
			out = c + out;
		} else {
			out += c;
		}
	}
	return out;	//	string
}

dojo.string.padLeft = function(/* string */str, /* integer */len, /* string */c) {
	//	summary
	//	same as dojo.string.pad(str, len, c, 1)
	return dojo.string.pad(str, len, c, 1);	//	string
}

dojo.string.padRight = function(/* string */str, /* integer */len, /* string */c) {
	//	summary
	//	same as dojo.string.pad(str, len, c, -1)
	return dojo.string.pad(str, len, c, -1);	//	string
}

dojo.provide("dojo.string");

dojo.provide("dojo.io.common");

/******************************************************************************
 *	Notes about dojo.io design:
 *
 *	The dojo.io.* package has the unenviable task of making a lot of different
 *	types of I/O feel natural, despite a universal lack of good (or even
 *	reasonable!) I/O capability in the host environment. So lets pin this down
 *	a little bit further.
 *
 *	Rhino:
 *		perhaps the best situation anywhere. Access to Java classes allows you
 *		to do anything one might want in terms of I/O, both synchronously and
 *		async. Can open TCP sockets and perform low-latency client/server
 *		interactions. HTTP transport is available through Java HTTP client and
 *		server classes. Wish it were always this easy.
 *
 *	xpcshell:
 *		XPCOM for I/O.
 *
 *	spidermonkey:
 *		S.O.L.
 *
 *	Browsers:
 *		Browsers generally do not provide any useable filesystem access. We are
 *		therefore limited to HTTP for moving information to and from Dojo
 *		instances living in a browser.
 *
 *		XMLHTTP:
 *			Sync or async, allows reading of arbitrary text files (including
 *			JS, which can then be eval()'d), writing requires server
 *			cooperation and is limited to HTTP mechanisms (POST and GET).
 *
 *		<iframe> hacks:
 *			iframe document hacks allow browsers to communicate asynchronously
 *			with a server via HTTP POST and GET operations. With significant
 *			effort and server cooperation, low-latency data transit between
 *			client and server can be acheived via iframe mechanisms (repubsub).
 *
 *		SVG:
 *			Adobe's SVG viewer implements helpful primitives for XML-based
 *			requests, but receipt of arbitrary text data seems unlikely w/o
 *			<![CDATA[]]> sections.
 *
 *
 *	A discussion between Dylan, Mark, Tom, and Alex helped to lay down a lot
 *	the IO API interface. A transcript of it can be found at:
 *		http://dojotoolkit.org/viewcvs/viewcvs.py/documents/irc/irc_io_api_log.txt?rev=307&view=auto
 *
 *	Also referenced in the design of the API was the DOM 3 L&S spec:
 *		http://www.w3.org/TR/2004/REC-DOM-Level-3-LS-20040407/load-save.html
 ******************************************************************************/

// a map of the available transport options. Transports should add themselves
// by calling add(name)
dojo.io.transports = [];
dojo.io.hdlrFuncNames = [ "load", "error", "timeout" ]; // we're omitting a progress() event for now

dojo.io.Request = function(/*String*/ url, /*String*/ mimetype, /*String*/ transport, /*String or Boolean*/ changeUrl){
// summary:
//		Constructs a Request object that is used by dojo.io.bind().
// description:
//		dojo.io.bind() will create one of these for you if
//		you call dojo.io.bind() with an plain object containing the bind parameters.
//		This method can either take the arguments specified, or an Object containing all of the parameters that you
//		want to use to create the dojo.io.Request (similar to how dojo.io.bind() is called.
//		The named parameters to this constructor represent the minimum set of parameters need
	if((arguments.length == 1)&&(arguments[0].constructor == Object)){
		this.fromKwArgs(arguments[0]);
	}else{
		this.url = url;
		if(mimetype){ this.mimetype = mimetype; }
		if(transport){ this.transport = transport; }
		if(arguments.length >= 4){ this.changeUrl = changeUrl; }
	}
}

dojo.lang.extend(dojo.io.Request, {

	/** The URL to hit */
	url: "",

	/** The mime type used to interrpret the response body */
	mimetype: "text/plain",

	/** The HTTP method to use */
	method: "GET",

	/** An Object containing key-value pairs to be included with the request */
	content: undefined, // Object

	/** The transport medium to use */
	transport: undefined, // String

	/** If defined the URL of the page is physically changed */
	changeUrl: undefined, // String

	/** A form node to use in the request */
	formNode: undefined, // HTMLFormElement

	/** Whether the request should be made synchronously */
	sync: false,

	bindSuccess: false,

	/** Cache/look for the request in the cache before attempting to request?
	 *  NOTE: this isn't a browser cache, this is internal and would only cache in-page
	 */
	useCache: false,

	/** Prevent the browser from caching this by adding a query string argument to the URL */
	preventCache: false,

	// events stuff
	load: function(/*String*/type, /*Object*/data, /*Object*/transportImplementation, /*Object*/kwArgs){
		// summary:
		//		Called on successful completion of a bind.
		//		type: String
		//				A string with value "load"
		//		data: Object
		//				The object representing the result of the bind. The actual structure
		//				of the data object will depend on the mimetype that was given to bind
		//				in the bind arguments.
		//		transportImplementation: Object
		//				The object that implements a particular transport. Structure is depedent
		//				on the transport. For XMLHTTPTransport (dojo.io.BrowserIO), it will be the
		//				XMLHttpRequest object from the browser.
		//		kwArgs: Object
		//				Object that contains the request parameters that were given to the
		//				bind call. Useful for storing and retrieving state from when bind
		//				was called.
	},
	error: function(/*String*/type, /*Object*/error, /*Object*/transportImplementation, /*Object*/kwArgs){
		// summary:
		//		Called when there is an error with a bind.
		//		type: String
		//				A string with value "error"
		//		error: Object
		//				The error object. Should be a dojo.io.Error object, but not guaranteed.
		//		transportImplementation: Object
		//				The object that implements a particular transport. Structure is depedent
		//				on the transport. For XMLHTTPTransport (dojo.io.BrowserIO), it will be the
		//				XMLHttpRequest object from the browser.
		//		kwArgs: Object
		//				Object that contains the request parameters that were given to the
		//				bind call. Useful for storing and retrieving state from when bind
		//				was called.
	},
	timeout: function(/*String*/type, /*Object*/empty, /*Object*/transportImplementation, /*Object*/kwArgs){
		// summary:
		//		Called when there is an error with a bind. Only implemented in certain transports at this time.
		//		type: String
		//				A string with value "timeout"
		//		empty: Object
		//				Should be null. Just a spacer argument so that load, error, timeout and handle have the
		//				same signatures.
		//		transportImplementation: Object
		//				The object that implements a particular transport. Structure is depedent
		//				on the transport. For XMLHTTPTransport (dojo.io.BrowserIO), it will be the
		//				XMLHttpRequest object from the browser. May be null for the timeout case for
		//				some transports.
		//		kwArgs: Object
		//				Object that contains the request parameters that were given to the
		//				bind call. Useful for storing and retrieving state from when bind
		//				was called.
	},
	handle: function(/*String*/type, /*Object*/data, /*Object*/transportImplementation, /*Object*/kwArgs){
		// summary:
		//		The handle method can be defined instead of defining separate load, error and timeout
		//		callbacks.
		//		type: String
		//				A string with the type of callback: "load", "error", or "timeout".
		//		data: Object
		//				See the above callbacks for what this parameter could be.
		//		transportImplementation: Object
		//				The object that implements a particular transport. Structure is depedent
		//				on the transport. For XMLHTTPTransport (dojo.io.BrowserIO), it will be the
		//				XMLHttpRequest object from the browser.
		//		kwArgs: Object
		//				Object that contains the request parameters that were given to the
		//				bind call. Useful for storing and retrieving state from when bind
		//				was called.
	},

	//FIXME: change IframeIO.js to use timeouts?
	// The number of seconds to wait until firing a timeout callback.
	// If it is zero, that means, don't do a timeout check.
	timeoutSeconds: 0,

	// the abort method needs to be filled in by the transport that accepts the
	// bind() request
	abort: function(){ },

	// backButton: function(){ },
	// forwardButton: function(){ },

	fromKwArgs: function(/*Object*/ kwArgs){
		// summary:
		//		Creates a dojo.io.Request from a simple object (kwArgs object).

		// normalize args
		if(kwArgs["url"]){ kwArgs.url = kwArgs.url.toString(); }
		if(kwArgs["formNode"]) { kwArgs.formNode = dojo.byId(kwArgs.formNode); }
		if(!kwArgs["method"] && kwArgs["formNode"] && kwArgs["formNode"].method) {
			kwArgs.method = kwArgs["formNode"].method;
		}

		// backwards compatibility
		if(!kwArgs["handle"] && kwArgs["handler"]){ kwArgs.handle = kwArgs.handler; }
		if(!kwArgs["load"] && kwArgs["loaded"]){ kwArgs.load = kwArgs.loaded; }
		if(!kwArgs["changeUrl"] && kwArgs["changeURL"]) { kwArgs.changeUrl = kwArgs.changeURL; }

		// encoding fun!
		kwArgs.encoding = dojo.lang.firstValued(kwArgs["encoding"], djConfig["bindEncoding"], "");

		kwArgs.sendTransport = dojo.lang.firstValued(kwArgs["sendTransport"], djConfig["ioSendTransport"], false);

		var isFunction = dojo.lang.isFunction;
		for(var x=0; x<dojo.io.hdlrFuncNames.length; x++){
			var fn = dojo.io.hdlrFuncNames[x];
			if(kwArgs[fn] && isFunction(kwArgs[fn])){ continue; }
			if(kwArgs["handle"] && isFunction(kwArgs["handle"])){
				kwArgs[fn] = kwArgs.handle;
			}
			// handler is aliased above, shouldn't need this check
			/* else if(dojo.lang.isObject(kwArgs.handler)){
				if(isFunction(kwArgs.handler[fn])){
					kwArgs[fn] = kwArgs.handler[fn]||kwArgs.handler["handle"]||function(){};
				}
			}*/
		}
		dojo.lang.mixin(this, kwArgs);
	}

});

dojo.io.Error = function(/*String*/ msg, /*String*/ type, /*Number*/num){
	// summary:
	//		Constructs an object representing a bind error.
	this.message = msg;
	this.type =  type || "unknown"; // must be one of "io", "parse", "unknown"
	this.number = num || 0; // per-substrate error number, not normalized
}

dojo.io.transports.addTransport = function(/*String*/name){
	// summary:
	//		Used to register transports that can support bind calls.
	this.push(name);
	// FIXME: do we need to handle things that aren't direct children of the
	// dojo.io module? (say, dojo.io.foo.fooTransport?)
	this[name] = dojo.io[name];
}

// binding interface, the various implementations register their capabilities
// and the bind() method dispatches
dojo.io.bind = function(/*dojo.io.Request or Object*/request){
	// summary:
	//		Binding interface for IO. Loading different IO transports, like
	//		dojo.io.BrowserIO or dojo.io.IframeIO, will register with bind
	//		to handle particular types of bind calls.
	//		request: Object
	//				Object containing bind arguments. This object is converted to
	//				a dojo.io.Request object, and that request object is the return
	//				value for this method.
	if(!(request instanceof dojo.io.Request)){
		try{
			request = new dojo.io.Request(request);
		}catch(e){ dojo.debug(e); }
	}

	// if the request asks for a particular implementation, use it
	var tsName = "";
	if(request["transport"]){
		tsName = request["transport"];
		if(!this[tsName]){
			dojo.io.sendBindError(request, "No dojo.io.bind() transport with name '"
				+ request["transport"] + "'.");
			return request; //dojo.io.Request
		}
		if(!this[tsName].canHandle(request)){
			dojo.io.sendBindError(request, "dojo.io.bind() transport with name '"
				+ request["transport"] + "' cannot handle this type of request.");
			return request;	//dojo.io.Request
		}
	}else{
		// otherwise we do our best to auto-detect what available transports
		// will handle
		for(var x=0; x<dojo.io.transports.length; x++){
			var tmp = dojo.io.transports[x];
			if((this[tmp])&&(this[tmp].canHandle(request))){
				tsName = tmp;
				break;
			}
		}
		if(tsName == ""){
			dojo.io.sendBindError(request, "None of the loaded transports for dojo.io.bind()"
				+ " can handle the request.");
			return request; //dojo.io.Request
		}
	}
	this[tsName].bind(request);
	request.bindSuccess = true;
	return request; //dojo.io.Request
}

dojo.io.sendBindError = function(/* Object */request, /* String */message){
	// summary:
	//		Used internally by dojo.io.bind() to return/raise a bind error.

	//Need to be careful since not all hostenvs support setTimeout.
	if((typeof request.error == "function" || typeof request.handle == "function")
		&& (typeof setTimeout == "function" || typeof setTimeout == "object")){
		var errorObject = new dojo.io.Error(message);
		setTimeout(function(){
			request[(typeof request.error == "function") ? "error" : "handle"]("error", errorObject, null, request);
		}, 50);
	}else{
		dojo.raise(message);
	}
}

dojo.io.queueBind = function(/*dojo.io.Request or Object*/request){
	// summary:
	//		queueBind will use dojo.io.bind() but guarantee that only one bind
	//		call is handled at a time.
	// description:
	//		If queueBind is called while a bind call
	//		is in process, it will queue up the other calls to bind and call them
	//		in order as bind calls complete.
	//		request: Object
	//			Same sort of request object as used for dojo.io.bind().
	if(!(request instanceof dojo.io.Request)){
		try{
			request = new dojo.io.Request(request);
		}catch(e){ dojo.debug(e); }
	}

	// make sure we get called if/when we get a response
	var oldLoad = request.load;
	request.load = function(){
		dojo.io._queueBindInFlight = false;
		var ret = oldLoad.apply(this, arguments);
		dojo.io._dispatchNextQueueBind();
		return ret;
	}

	var oldErr = request.error;
	request.error = function(){
		dojo.io._queueBindInFlight = false;
		var ret = oldErr.apply(this, arguments);
		dojo.io._dispatchNextQueueBind();
		return ret;
	}

	dojo.io._bindQueue.push(request);
	dojo.io._dispatchNextQueueBind();
	return request; //dojo.io.Request
}

dojo.io._dispatchNextQueueBind = function(){
	// summary:
	//	Private method used by dojo.io.queueBind().
	if(!dojo.io._queueBindInFlight){
		dojo.io._queueBindInFlight = true;
		if(dojo.io._bindQueue.length > 0){
			dojo.io.bind(dojo.io._bindQueue.shift());
		}else{
			dojo.io._queueBindInFlight = false;
		}
	}
}
dojo.io._bindQueue = [];
dojo.io._queueBindInFlight = false;

dojo.io.argsFromMap = function(/*Object*/map, /*String?*/encoding, /*String?*/last){
	// summary:
	//		Converts name/values pairs in the map object to an URL-encoded string
	//		with format of name1=value1&name2=value2...
	//		map: Object
	//			Object that has the contains the names and values.
	//		encoding: String?
	//			String to specify how to encode the name and value. If the encoding string
	//			contains "utf" (case-insensitive), then encodeURIComponent is used. Otherwise
	//			dojo.string.encodeAscii is used.
	//		last: String?
	//			The last parameter in the list. Helps with final string formatting?
	var enc = /utf/i.test(encoding||"") ? encodeURIComponent : dojo.string.encodeAscii;
	var mapped = [];
	var control = new Object();
	for(var name in map){
		var domap = function(elt){
			var val = enc(name)+"="+enc(elt);
			mapped[(last == name) ? "push" : "unshift"](val);
		}
		if(!control[name]){
			var value = map[name];
			// FIXME: should be isArrayLike?
			if (dojo.lang.isArray(value)){
				dojo.lang.forEach(value, domap);
			}else{
				domap(value);
			}
		}
	}
	return mapped.join("&"); //String
}

dojo.io.setIFrameSrc = function(/*DOMNode*/ iframe, /*String*/ src, /*Boolean*/ replace){
	//summary:
	//		Sets the URL that is loaded in an IFrame. The replace parameter indicates whether
	//		location.replace() should be used when changing the location of the iframe.
	try{
		var r = dojo.render.html;
		// dojo.debug(iframe);
		if(!replace){
			if(r.safari){
				iframe.location = src;
			}else{
				frames[iframe.name].location = src;
			}
		}else{
			// Fun with DOM 0 incompatibilities!
			var idoc;
			if(r.ie){
				idoc = iframe.contentWindow.document;
			}else if(r.safari){
				idoc = iframe.document;
			}else{ //  if(r.moz){
				idoc = iframe.contentWindow;
			}

			//For Safari (at least 2.0.3) and Opera, if the iframe
			//has just been created but it doesn't have content
			//yet, then iframe.document may be null. In that case,
			//use iframe.location and return.
			if(!idoc){
				iframe.location = src;
				return;
			}else{
				idoc.location.replace(src);
			}
		}
	}catch(e){
		dojo.debug(e);
		dojo.debug("setIFrameSrc: "+e);
	}
}

/*
dojo.io.sampleTranport = new function(){
	this.canHandle = function(kwArgs){
		// canHandle just tells dojo.io.bind() if this is a good transport to
		// use for the particular type of request.
		if(
			(
				(kwArgs["mimetype"] == "text/plain") ||
				(kwArgs["mimetype"] == "text/html") ||
				(kwArgs["mimetype"] == "text/javascript")
			)&&(
				(kwArgs["method"] == "get") ||
				( (kwArgs["method"] == "post") && (!kwArgs["formNode"]) )
			)
		){
			return true;
		}

		return false;
	}

	this.bind = function(kwArgs){
		var hdlrObj = {};

		// set up a handler object
		for(var x=0; x<dojo.io.hdlrFuncNames.length; x++){
			var fn = dojo.io.hdlrFuncNames[x];
			if(typeof kwArgs.handler == "object"){
				if(typeof kwArgs.handler[fn] == "function"){
					hdlrObj[fn] = kwArgs.handler[fn]||kwArgs.handler["handle"];
				}
			}else if(typeof kwArgs[fn] == "function"){
				hdlrObj[fn] = kwArgs[fn];
			}else{
				hdlrObj[fn] = kwArgs["handle"]||function(){};
			}
		}

		// build a handler function that calls back to the handler obj
		var hdlrFunc = function(evt){
			if(evt.type == "onload"){
				hdlrObj.load("load", evt.data, evt);
			}else if(evt.type == "onerr"){
				var errObj = new dojo.io.Error("sampleTransport Error: "+evt.msg);
				hdlrObj.error("error", errObj);
			}
		}

		// the sample transport would attach the hdlrFunc() when sending the
		// request down the pipe at this point
		var tgtURL = kwArgs.url+"?"+dojo.io.argsFromMap(kwArgs.content);
		// sampleTransport.sendRequest(tgtURL, hdlrFunc);
	}

	dojo.io.transports.addTransport("sampleTranport");
}
*/

dojo.provide("dojo.string.extras");


//TODO: should we use ${} substitution syntax instead, like widgets do?
dojo.string.substituteParams = function(/*string*/template, /* object - optional or ... */hash){
// summary:
//	Performs parameterized substitutions on a string. Throws an exception if any parameter is unmatched.
//
// description:
//	For example,
//		dojo.string.substituteParams("File '%{0}' is not found in directory '%{1}'.","foo.html","/temp");
//	returns
//		"File 'foo.html' is not found in directory '/temp'."
//
// template: the original string template with %{values} to be replaced
// hash: name/value pairs (type object) to provide substitutions.  Alternatively, substitutions may be
//	included as arguments 1..n to this function, corresponding to template parameters 0..n-1

	var map = (typeof hash == 'object') ? hash : dojo.lang.toArray(arguments, 1);

	return template.replace(/\%\{(\w+)\}/g, function(match, key){
		if(typeof(map[key]) != "undefined" && map[key] != null){
			return map[key];
		}
		dojo.raise("Substitution not found: " + key);
	}); // string
};

dojo.string.capitalize = function(/*string*/str){
// summary:
//	Uppercases the first letter of each word

	if(!dojo.lang.isString(str)){ return ""; }
	if(arguments.length == 0){ str = this; }

	var words = str.split(' ');
	for(var i=0; i<words.length; i++){
		words[i] = words[i].charAt(0).toUpperCase() + words[i].substring(1);
	}
	return words.join(" "); // string
}

dojo.string.isBlank = function(/*string*/str){
// summary:
//	Return true if the entire string is whitespace characters

	if(!dojo.lang.isString(str)){ return true; }
	return (dojo.string.trim(str).length == 0); // boolean
}

//FIXME: not sure exactly what encodeAscii is trying to do, or if it's working right
dojo.string.encodeAscii = function(/*string*/str){
	if(!dojo.lang.isString(str)){ return str; } // unknown
	var ret = "";
	var value = escape(str);
	var match, re = /%u([0-9A-F]{4})/i;
	while((match = value.match(re))){
		var num = Number("0x"+match[1]);
		var newVal = escape("&#" + num + ";");
		ret += value.substring(0, match.index) + newVal;
		value = value.substring(match.index+match[0].length);
	}
	ret += value.replace(/\+/g, "%2B");
	return ret; // string
}

dojo.string.escape = function(/*string*/type, /*string*/str){
// summary:
//	Adds escape sequences for special characters according to the convention of 'type'
//
// type: one of xml|html|xhtml|sql|regexp|regex|javascript|jscript|js|ascii
// str: the string to be escaped

	var args = dojo.lang.toArray(arguments, 1);
	switch(type.toLowerCase()){
		case "xml":
		case "html":
		case "xhtml":
			return dojo.string.escapeXml.apply(this, args); // string
		case "sql":
			return dojo.string.escapeSql.apply(this, args); // string
		case "regexp":
		case "regex":
			return dojo.string.escapeRegExp.apply(this, args); // string
		case "javascript":
		case "jscript":
		case "js":
			return dojo.string.escapeJavaScript.apply(this, args); // string
		case "ascii":
			// so it's encode, but it seems useful
			return dojo.string.encodeAscii.apply(this, args); // string
		default:
			return str; // string
	}
}

dojo.string.escapeXml = function(/*string*/str, /*boolean*/noSingleQuotes){
//summary:
//	Adds escape sequences for special characters in XML: &<>"'
//  Optionally skips escapes for single quotes

	str = str.replace(/&/gm, "&amp;").replace(/</gm, "&lt;")
		.replace(/>/gm, "&gt;").replace(/"/gm, "&quot;");
	if(!noSingleQuotes){ str = str.replace(/'/gm, "&#39;"); }
	return str; // string
}

dojo.string.escapeSql = function(/*string*/str){
//summary:
//	Adds escape sequences for single quotes in SQL expressions

	return str.replace(/'/gm, "''"); //string
}

dojo.string.escapeRegExp = function(/*string*/str){
//summary:
//	Adds escape sequences for special characters in regular expressions

	return str.replace(/\\/gm, "\\\\").replace(/([\f\b\n\t\r[\^$|?*+(){}])/gm, "\\$1"); // string
}

//FIXME: should this one also escape backslash?
dojo.string.escapeJavaScript = function(/*string*/str){
//summary:
//	Adds escape sequences for single and double quotes as well
//	as non-visible characters in JavaScript string literal expressions

	return str.replace(/(["'\f\b\n\t\r])/gm, "\\$1"); // string
}

//FIXME: looks a lot like escapeJavaScript, just adds quotes? deprecate one?
dojo.string.escapeString = function(/*string*/str){
//summary:
//	Adds escape sequences for non-visual characters, double quote and backslash
//	and surrounds with double quotes to form a valid string literal.
	return ('"' + str.replace(/(["\\])/g, '\\$1') + '"'
		).replace(/[\f]/g, "\\f"
		).replace(/[\b]/g, "\\b"
		).replace(/[\n]/g, "\\n"
		).replace(/[\t]/g, "\\t"
		).replace(/[\r]/g, "\\r"); // string
}

// TODO: make an HTML version
dojo.string.summary = function(/*string*/str, /*number*/len){
// summary:
//	Truncates 'str' after 'len' characters and appends periods as necessary so that it ends with "..."

	if(!len || str.length <= len){
		return str; // string
	}

	return str.substring(0, len).replace(/\.+$/, "") + "..."; // string
}

dojo.string.endsWith = function(/*string*/str, /*string*/end, /*boolean*/ignoreCase){
// summary:
//	Returns true if 'str' ends with 'end'

	if(ignoreCase){
		str = str.toLowerCase();
		end = end.toLowerCase();
	}
	if((str.length - end.length) < 0){
		return false; // boolean
	}
	return str.lastIndexOf(end) == str.length - end.length; // boolean
}

dojo.string.endsWithAny = function(/*string*/str /* , ... */){
// summary:
//	Returns true if 'str' ends with any of the arguments[2 -> n]

	for(var i = 1; i < arguments.length; i++) {
		if(dojo.string.endsWith(str, arguments[i])) {
			return true; // boolean
		}
	}
	return false; // boolean
}

dojo.string.startsWith = function(/*string*/str, /*string*/start, /*boolean*/ignoreCase){
// summary:
//	Returns true if 'str' starts with 'start'

	if(ignoreCase) {
		str = str.toLowerCase();
		start = start.toLowerCase();
	}
	return str.indexOf(start) == 0; // boolean
}

dojo.string.startsWithAny = function(/*string*/str /* , ... */){
// summary:
//	Returns true if 'str' starts with any of the arguments[2 -> n]

	for(var i = 1; i < arguments.length; i++) {
		if(dojo.string.startsWith(str, arguments[i])) {
			return true; // boolean
		}
	}
	return false; // boolean
}

dojo.string.has = function(/*string*/str /* , ... */) {
// summary:
//	Returns true if 'str' contains any of the arguments 2 -> n

	for(var i = 1; i < arguments.length; i++) {
		if(str.indexOf(arguments[i]) > -1){
			return true; // boolean
		}
	}
	return false; // boolean
}

dojo.string.normalizeNewlines = function(/*string*/text, /*string? (\n or \r)*/newlineChar){
// summary:
//	Changes occurences of CR and LF in text to CRLF, or if newlineChar is provided as '\n' or '\r',
//	substitutes newlineChar for occurrences of CR/LF and CRLF

	if (newlineChar == "\n"){
		text = text.replace(/\r\n/g, "\n");
		text = text.replace(/\r/g, "\n");
	} else if (newlineChar == "\r"){
		text = text.replace(/\r\n/g, "\r");
		text = text.replace(/\n/g, "\r");
	}else{
		text = text.replace(/([^\r])\n/g, "$1\r\n").replace(/\r([^\n])/g, "\r\n$1");
	}
	return text; // string
}

dojo.string.splitEscaped = function(/*string*/str, /*string of length=1*/charac){
// summary:
//	Splits 'str' into an array separated by 'charac', but skips characters escaped with a backslash

	var components = [];
	for (var i = 0, prevcomma = 0; i < str.length; i++){
		if (str.charAt(i) == '\\'){ i++; continue; }
		if (str.charAt(i) == charac){
			components.push(str.substring(prevcomma, i));
			prevcomma = i + 1;
		}
	}
	components.push(str.substr(prevcomma));
	return components; // array
}

dojo.provide("dojo.undo.browser");

try{
	if((!djConfig["preventBackButtonFix"])&&(!dojo.hostenv.post_load_)){
		document.write("<iframe style='border: 0px; width: 1px; height: 1px; position: absolute; bottom: 0px; right: 0px; visibility: visible;' name='djhistory' id='djhistory' src='"+(dojo.hostenv.getBaseScriptUri()+'iframe_history.html')+"'></iframe>");
	}
}catch(e){/* squelch */}

if(dojo.render.html.opera){
	dojo.debug("Opera is not supported with dojo.undo.browser, so back/forward detection will not work.");
}

dojo.undo.browser = {
	initialHref: (!dj_undef("window")) ? window.location.href : "",
	initialHash: (!dj_undef("window")) ? window.location.hash : "",

	moveForward: false,
	historyStack: [],
	forwardStack: [],
	historyIframe: null,
	bookmarkAnchor: null,
	locationTimer: null,

	/**
	 *
	 */
	setInitialState: function(/*Object*/args){
		//summary: Sets the state object and back callback for the very first page that is loaded.
		//description: It is recommended that you call this method as part of an event listener that is registered via
		//dojo.addOnLoad().
		//args: Object
		//		See the addToHistory() function for the list of valid args properties.
		this.initialState = this._createState(this.initialHref, args, this.initialHash);
	},

	//FIXME: Would like to support arbitrary back/forward jumps. Have to rework iframeLoaded among other things.
	//FIXME: is there a slight race condition in moz using change URL with the timer check and when
	//       the hash gets set? I think I have seen a back/forward call in quick succession, but not consistent.
	addToHistory: function(args){
		//summary: adds a state object (args) to the history list. You must set
		//djConfig.preventBackButtonFix = false to use dojo.undo.browser.

		//args: Object
		//		args can have the following properties:
		//		To support getting back button notifications, the object argument should implement a
		//		function called either "back", "backButton", or "handle". The string "back" will be
		//		passed as the first and only argument to this callback.
		//		- To support getting forward button notifications, the object argument should implement a
		//		function called either "forward", "forwardButton", or "handle". The string "forward" will be
		//		passed as the first and only argument to this callback.
		//		- If you want the browser location string to change, define "changeUrl" on the object. If the
		//		value of "changeUrl" is true, then a unique number will be appended to the URL as a fragment
		//		identifier (http://some.domain.com/path#uniquenumber). If it is any other value that does
		//		not evaluate to false, that value will be used as the fragment identifier. For example,
		//		if changeUrl: 'page1', then the URL will look like: http://some.domain.com/path#page1
	 	//		Full example:
		//		dojo.undo.browser.addToHistory({
		//		  back: function() { alert('back pressed'); },
		//		  forward: function() { alert('forward pressed'); },
		//		  changeUrl: true
		//		});
		//
		//	BROWSER NOTES:
		//  Safari 1.2:
		//	back button "works" fine, however it's not possible to actually
		//	DETECT that you've moved backwards by inspecting window.location.
		//	Unless there is some other means of locating.
		//	FIXME: perhaps we can poll on history.length?
		//	Safari 2.0.3+ (and probably 1.3.2+):
		//	works fine, except when changeUrl is used. When changeUrl is used,
		//	Safari jumps all the way back to whatever page was shown before
		//	the page that uses dojo.undo.browser support.
		//	IE 5.5 SP2:
		//	back button behavior is macro. It does not move back to the
		//	previous hash value, but to the last full page load. This suggests
		//	that the iframe is the correct way to capture the back button in
		//	these cases.
		//	Don't test this page using local disk for MSIE. MSIE will not create
		//	a history list for iframe_history.html if served from a file: URL.
		//	The XML served back from the XHR tests will also not be properly
		//	created if served from local disk. Serve the test pages from a web
		//	server to test in that browser.
		//	IE 6.0:
		//	same behavior as IE 5.5 SP2
		//	Firefox 1.0+:
		//	the back button will return us to the previous hash on the same
		//	page, thereby not requiring an iframe hack, although we do then
		//	need to run a timer to detect inter-page movement.

		//If addToHistory is called, then that means we prune the
		//forward stack -- the user went back, then wanted to
		//start a new forward path.
		this.forwardStack = [];

		var hash = null;
		var url = null;
		if(!this.historyIframe){
			this.historyIframe = window.frames["djhistory"];
		}
		if(!this.bookmarkAnchor){
			this.bookmarkAnchor = document.createElement("a");
			dojo.body().appendChild(this.bookmarkAnchor);
			this.bookmarkAnchor.style.display = "none";
		}
		if(args["changeUrl"]){
			hash = "#"+ ((args["changeUrl"]!==true) ? args["changeUrl"] : (new Date()).getTime());

			//If the current hash matches the new one, just replace the history object with
			//this new one. It doesn't make sense to track different state objects for the same
			//logical URL. This matches the browser behavior of only putting in one history
			//item no matter how many times you click on the same #hash link, at least in Firefox
			//and Safari, and there is no reliable way in those browsers to know if a #hash link
			//has been clicked on multiple times. So making this the standard behavior in all browsers
			//so that dojo.undo.browser's behavior is the same in all browsers.
			if(this.historyStack.length == 0 && this.initialState.urlHash == hash){
				this.initialState = this._createState(url, args, hash);
				return;
			}else if(this.historyStack.length > 0 && this.historyStack[this.historyStack.length - 1].urlHash == hash){
				this.historyStack[this.historyStack.length - 1] = this._createState(url, args, hash);
				return;
			}

			this.changingUrl = true;
			setTimeout("window.location.href = '"+hash+"'; dojo.undo.browser.changingUrl = false;", 1);
			this.bookmarkAnchor.href = hash;

			if(dojo.render.html.ie){
				url = this._loadIframeHistory();

				var oldCB = args["back"]||args["backButton"]||args["handle"];

				//The function takes handleName as a parameter, in case the
				//callback we are overriding was "handle". In that case,
				//we will need to pass the handle name to handle.
				var tcb = function(handleName){
					if(window.location.hash != ""){
						setTimeout("window.location.href = '"+hash+"';", 1);
					}
					//Use apply to set "this" to args, and to try to avoid memory leaks.
					oldCB.apply(this, [handleName]);
				}

				//Set interceptor function in the right place.
				if(args["back"]){
					args.back = tcb;
				}else if(args["backButton"]){
					args.backButton = tcb;
				}else if(args["handle"]){
					args.handle = tcb;
				}

				var oldFW = args["forward"]||args["forwardButton"]||args["handle"];

				//The function takes handleName as a parameter, in case the
				//callback we are overriding was "handle". In that case,
				//we will need to pass the handle name to handle.
				var tfw = function(handleName){
					if(window.location.hash != ""){
						window.location.href = hash;
					}
					if(oldFW){ // we might not actually have one
						//Use apply to set "this" to args, and to try to avoid memory leaks.
						oldFW.apply(this, [handleName]);
					}
				}

				//Set interceptor function in the right place.
				if(args["forward"]){
					args.forward = tfw;
				}else if(args["forwardButton"]){
					args.forwardButton = tfw;
				}else if(args["handle"]){
					args.handle = tfw;
				}

			}else if(dojo.render.html.moz){
				// start the timer
				if(!this.locationTimer){
					this.locationTimer = setInterval("dojo.undo.browser.checkLocation();", 200);
				}
			}
		}else{
			url = this._loadIframeHistory();
		}

		this.historyStack.push(this._createState(url, args, hash));
	},

	checkLocation: function(){
		//summary: private method. Do not call this directly.
		if (!this.changingUrl){
			var hsl = this.historyStack.length;

			if((window.location.hash == this.initialHash||window.location.href == this.initialHref)&&(hsl == 1)){
				// FIXME: could this ever be a forward button?
				// we can't clear it because we still need to check for forwards. Ugg.
				// clearInterval(this.locationTimer);
				this.handleBackButton();
				return;
			}

			// first check to see if we could have gone forward. We always halt on
			// a no-hash item.
			if(this.forwardStack.length > 0){
				if(this.forwardStack[this.forwardStack.length-1].urlHash == window.location.hash){
					this.handleForwardButton();
					return;
				}
			}

			// ok, that didn't work, try someplace back in the history stack
			if((hsl >= 2)&&(this.historyStack[hsl-2])){
				if(this.historyStack[hsl-2].urlHash==window.location.hash){
					this.handleBackButton();
					return;
				}
			}
		}
	},

	iframeLoaded: function(evt, ifrLoc){
		//summary: private method. Do not call this directly.
		if(!dojo.render.html.opera){
			var query = this._getUrlQuery(ifrLoc.href);
			if(query == null){
				// alert("iframeLoaded");
				// we hit the end of the history, so we should go back
				if(this.historyStack.length == 1){
					this.handleBackButton();
				}
				return;
			}
			if(this.moveForward){
				// we were expecting it, so it's not either a forward or backward movement
				this.moveForward = false;
				return;
			}

			//Check the back stack first, since it is more likely.
			//Note that only one step back or forward is supported.
			if(this.historyStack.length >= 2 && query == this._getUrlQuery(this.historyStack[this.historyStack.length-2].url)){
				this.handleBackButton();
			}
			else if(this.forwardStack.length > 0 && query == this._getUrlQuery(this.forwardStack[this.forwardStack.length-1].url)){
				this.handleForwardButton();
			}
		}
	},

	handleBackButton: function(){
		//summary: private method. Do not call this directly.

		//The "current" page is always at the top of the history stack.
		var current = this.historyStack.pop();
		if(!current){ return; }
		var last = this.historyStack[this.historyStack.length-1];
		if(!last && this.historyStack.length == 0){
			last = this.initialState;
		}
		if (last){
			if(last.kwArgs["back"]){
				last.kwArgs["back"]();
			}else if(last.kwArgs["backButton"]){
				last.kwArgs["backButton"]();
			}else if(last.kwArgs["handle"]){
				last.kwArgs.handle("back");
			}
		}
		this.forwardStack.push(current);
	},

	handleForwardButton: function(){
		//summary: private method. Do not call this directly.

		var last = this.forwardStack.pop();
		if(!last){ return; }
		if(last.kwArgs["forward"]){
			last.kwArgs.forward();
		}else if(last.kwArgs["forwardButton"]){
			last.kwArgs.forwardButton();
		}else if(last.kwArgs["handle"]){
			last.kwArgs.handle("forward");
		}
		this.historyStack.push(last);
	},

	_createState: function(url, args, hash){
		//summary: private method. Do not call this directly.

		return {"url": url, "kwArgs": args, "urlHash": hash};	//Object
	},

	_getUrlQuery: function(url){
		//summary: private method. Do not call this directly.
		var segments = url.split("?");
		if (segments.length < 2){
			return null; //null
		}
		else{
			return segments[1]; //String
		}
	},

	_loadIframeHistory: function(){
		//summary: private method. Do not call this directly.
		var url = dojo.hostenv.getBaseScriptUri()+"iframe_history.html?"+(new Date()).getTime();
		this.moveForward = true;
		dojo.io.setIFrameSrc(this.historyIframe, url, false);
		return url; //String
	}
}

dojo.provide("dojo.io.BrowserIO");


if(!dj_undef("window")) {

dojo.io.checkChildrenForFile = function(/*DOMNode*/node){
	//summary: Checks any child nodes of node for an input type="file" element.
	var hasFile = false;
	var inputs = node.getElementsByTagName("input");
	dojo.lang.forEach(inputs, function(input){
		if(hasFile){ return; }
		if(input.getAttribute("type")=="file"){
			hasFile = true;
		}
	});
	return hasFile; //boolean
}

dojo.io.formHasFile = function(/*DOMNode*/formNode){
	//summary: Just calls dojo.io.checkChildrenForFile().
	return dojo.io.checkChildrenForFile(formNode); //boolean
}

dojo.io.updateNode = function(/*DOMNode*/node, /*String or Object*/urlOrArgs){
	//summary: Updates a DOMnode with the result of a dojo.io.bind() call.
	//node: DOMNode
	//urlOrArgs: String or Object
	//		Either a String that has an URL, or an object containing dojo.io.bind()
	//		arguments.
	node = dojo.byId(node);
	var args = urlOrArgs;
	if(dojo.lang.isString(urlOrArgs)){
		args = { url: urlOrArgs };
	}
	args.mimetype = "text/html";
	args.load = function(t, d, e){
		while(node.firstChild){
			dojo.dom.destroyNode(node.firstChild);
		}
		node.innerHTML = d;
	};
	dojo.io.bind(args);
}

dojo.io.formFilter = function(/*DOMNode*/node) {
	//summary: Returns true if the node is an input element that is enabled, has
	//a name, and whose type is one of the following values: ["file", "submit", "image", "reset", "button"]
	var type = (node.type||"").toLowerCase();
	return !node.disabled && node.name
		&& !dojo.lang.inArray(["file", "submit", "image", "reset", "button"], type); //boolean
}

// TODO: Move to htmlUtils
dojo.io.encodeForm = function(/*DOMNode*/formNode, /*String?*/encoding, /*Function?*/formFilter){
	//summary: Converts the names and values of form elements into an URL-encoded
	//string (name=value&name=value...).
	//formNode: DOMNode
	//encoding: String?
	//		The encoding to use for the values. Specify a string that starts with
	//		"utf" (for instance, "utf8"), to use encodeURIComponent() as the encoding
	//		function. Otherwise, dojo.string.encodeAscii will be used.
	//formFilter: Function?
	//	A function used to filter out form elements. The element node will be passed
	//	to the formFilter function, and a boolean result is expected (true indicating
	//	indicating that the element should have its name/value included in the output).
	//	If no formFilter is specified, then dojo.io.formFilter() will be used.
	if((!formNode)||(!formNode.tagName)||(!formNode.tagName.toLowerCase() == "form")){
		dojo.raise("Attempted to encode a non-form element.");
	}
	if(!formFilter) { formFilter = dojo.io.formFilter; }
	var enc = /utf/i.test(encoding||"") ? encodeURIComponent : dojo.string.encodeAscii;
	var values = [];

	for(var i = 0; i < formNode.elements.length; i++){
		var elm = formNode.elements[i];
		if(!elm || elm.tagName.toLowerCase() == "fieldset" || !formFilter(elm)) { continue; }
		var name = enc(elm.name);
		var type = elm.type.toLowerCase();

		if(type == "select-multiple"){
			for(var j = 0; j < elm.options.length; j++){
				if(elm.options[j].selected) {
					values.push(name + "=" + enc(elm.options[j].value));
				}
			}
		}else if(dojo.lang.inArray(["radio", "checkbox"], type)){
			if(elm.checked){
				values.push(name + "=" + enc(elm.value));
			}
		}else{
			values.push(name + "=" + enc(elm.value));
		}
	}

	// now collect input type="image", which doesn't show up in the elements array
	var inputs = formNode.getElementsByTagName("input");
	for(var i = 0; i < inputs.length; i++) {
		var input = inputs[i];
		if(input.type.toLowerCase() == "image" && input.form == formNode
			&& formFilter(input)) {
			var name = enc(input.name);
			values.push(name + "=" + enc(input.value));
			values.push(name + ".x=0");
			values.push(name + ".y=0");
		}
	}
	return values.join("&") + "&"; //String
}

dojo.io.FormBind = function(/*DOMNode or Object*/args) {
	//summary: constructor for a dojo.io.FormBind object. See the Dojo Book for
	//some information on usage: http://manual.dojotoolkit.org/WikiHome/DojoDotBook/Book23
	//args: DOMNode or Object
	//		args can either be the DOMNode for a form element, or an object containing
	//		dojo.io.bind() arguments, one of which should be formNode with the value of
	//		a form element DOMNode.
	this.bindArgs = {};

	if(args && args.formNode) {
		this.init(args);
	} else if(args) {
		this.init({formNode: args});
	}
}
dojo.lang.extend(dojo.io.FormBind, {
	form: null,

	bindArgs: null,

	clickedButton: null,

	init: function(/*DOMNode or Object*/args) {
		//summary: Internal function called by the dojo.io.FormBind() constructor
		//do not call this method directly.
		var form = dojo.byId(args.formNode);

		if(!form || !form.tagName || form.tagName.toLowerCase() != "form") {
			throw new Error("FormBind: Couldn't apply, invalid form");
		} else if(this.form == form) {
			return;
		} else if(this.form) {
			throw new Error("FormBind: Already applied to a form");
		}

		dojo.lang.mixin(this.bindArgs, args);
		this.form = form;

		this.connect(form, "onsubmit", "submit");

		for(var i = 0; i < form.elements.length; i++) {
			var node = form.elements[i];
			if(node && node.type && dojo.lang.inArray(["submit", "button"], node.type.toLowerCase())) {
				this.connect(node, "onclick", "click");
			}
		}

		var inputs = form.getElementsByTagName("input");
		for(var i = 0; i < inputs.length; i++) {
			var input = inputs[i];
			if(input.type.toLowerCase() == "image" && input.form == form) {
				this.connect(input, "onclick", "click");
			}
		}
	},

	onSubmit: function(/*DOMNode*/form) {
		//summary: Function used to verify that the form is OK to submit.
		//Override this function if you want specific form validation done.
		return true; //boolean
	},

	submit: function(/*Event*/e) {
		//summary: internal function that is connected as a listener to the
		//form's onsubmit event.
		e.preventDefault();
		if(this.onSubmit(this.form)) {
			dojo.io.bind(dojo.lang.mixin(this.bindArgs, {
				formFilter: dojo.lang.hitch(this, "formFilter")
			}));
		}
	},

	click: function(/*Event*/e) {
		//summary: internal method that is connected as a listener to the
		//form's elements whose click event can submit a form.
		var node = e.currentTarget;
		if(node.disabled) { return; }
		this.clickedButton = node;
	},

	formFilter: function(/*DOMNode*/node) {
		//summary: internal function used to know which form element values to include
		//		in the dojo.io.bind() request.
		var type = (node.type||"").toLowerCase();
		var accept = false;
		if(node.disabled || !node.name) {
			accept = false;
		} else if(dojo.lang.inArray(["submit", "button", "image"], type)) {
			if(!this.clickedButton) { this.clickedButton = node; }
			accept = node == this.clickedButton;
		} else {
			accept = !dojo.lang.inArray(["file", "submit", "reset", "button"], type);
		}
		return accept; //boolean
	},

	// in case you don't have dojo.event.* pulled in
	connect: function(/*Object*/srcObj, /*Function*/srcFcn, /*Function*/targetFcn) {
		//summary: internal function used to connect event listeners to form elements
		//that trigger events. Used in case dojo.event is not loaded.
		if(dojo.evalObjPath("dojo.event.connect")) {
			dojo.event.connect(srcObj, srcFcn, this, targetFcn);
		} else {
			var fcn = dojo.lang.hitch(this, targetFcn);
			srcObj[srcFcn] = function(e) {
				if(!e) { e = window.event; }
				if(!e.currentTarget) { e.currentTarget = e.srcElement; }
				if(!e.preventDefault) { e.preventDefault = function() { window.event.returnValue = false; } }
				fcn(e);
			}
		}
	}
});

dojo.io.XMLHTTPTransport = new function(){
	//summary: The object that implements the dojo.io.bind transport for XMLHttpRequest.
	var _this = this;

	var _cache = {}; // FIXME: make this public? do we even need to?
	this.useCache = false; // if this is true, we'll cache unless kwArgs.useCache = false
	this.preventCache = false; // if this is true, we'll always force GET requests to cache

	// FIXME: Should this even be a function? or do we just hard code it in the next 2 functions?
	function getCacheKey(url, query, method) {
		return url + "|" + query + "|" + method.toLowerCase();
	}

	function addToCache(url, query, method, http) {
		_cache[getCacheKey(url, query, method)] = http;
	}

	function getFromCache(url, query, method) {
		return _cache[getCacheKey(url, query, method)];
	}

	this.clearCache = function() {
		_cache = {};
	}

	// moved successful load stuff here
	function doLoad(kwArgs, http, url, query, useCache) {
		if(	((http.status>=200)&&(http.status<300))|| 	// allow any 2XX response code
			(http.status==304)|| 						// get it out of the cache
			(location.protocol=="file:" && (http.status==0 || http.status==undefined))||
			(location.protocol=="chrome:" && (http.status==0 || http.status==undefined))
		){
			var ret;
			if(kwArgs.method.toLowerCase() == "head"){
				var headers = http.getAllResponseHeaders();
				ret = {};
				ret.toString = function(){ return headers; }
				var values = headers.split(/[\r\n]+/g);
				for(var i = 0; i < values.length; i++) {
					var pair = values[i].match(/^([^:]+)\s*:\s*(.+)$/i);
					if(pair) {
						ret[pair[1]] = pair[2];
					}
				}
			}else if(kwArgs.mimetype == "text/javascript"){
				try{
					ret = dj_eval(http.responseText);
				}catch(e){
					dojo.debug(e);
					dojo.debug(http.responseText);
					ret = null;
				}
			}else if(kwArgs.mimetype == "text/json" || kwArgs.mimetype == "application/json"){
				try{
					ret = dj_eval("("+http.responseText+")");
				}catch(e){
					dojo.debug(e);
					dojo.debug(http.responseText);
					ret = false;
				}
			}else if((kwArgs.mimetype == "application/xml")||
						(kwArgs.mimetype == "text/xml")){
				ret = http.responseXML;
				if(!ret || typeof ret == "string" || !http.getResponseHeader("Content-Type")) {
					ret = dojo.dom.createDocumentFromText(http.responseText);
				}
			}else{
				ret = http.responseText;
			}

			if(useCache){ // only cache successful responses
				addToCache(url, query, kwArgs.method, http);
			}
			kwArgs[(typeof kwArgs.load == "function") ? "load" : "handle"]("load", ret, http, kwArgs);
		}else{
			var errObj = new dojo.io.Error("XMLHttpTransport Error: "+http.status+" "+http.statusText);
			kwArgs[(typeof kwArgs.error == "function") ? "error" : "handle"]("error", errObj, http, kwArgs);
		}
	}

	// set headers (note: Content-Type will get overriden if kwArgs.contentType is set)
	function setHeaders(http, kwArgs){
		if(kwArgs["headers"]) {
			for(var header in kwArgs["headers"]) {
				if(header.toLowerCase() == "content-type" && !kwArgs["contentType"]) {
					kwArgs["contentType"] = kwArgs["headers"][header];
				} else {
					http.setRequestHeader(header, kwArgs["headers"][header]);
				}
			}
		}
	}

	this.inFlight = [];
	this.inFlightTimer = null;

	this.startWatchingInFlight = function(){
		//summary: internal method used to trigger a timer to watch all inflight
		//XMLHttpRequests.
		if(!this.inFlightTimer){
			// setInterval broken in mozilla x86_64 in some circumstances, see
			// https://bugzilla.mozilla.org/show_bug.cgi?id=344439
			// using setTimeout instead
			this.inFlightTimer = setTimeout("dojo.io.XMLHTTPTransport.watchInFlight();", 10);
		}
	}

	this.watchInFlight = function(){
		//summary: internal method that checks each inflight XMLHttpRequest to see
		//if it has completed or if the timeout situation applies.
		var now = null;
		// make sure sync calls stay thread safe, if this callback is called during a sync call
		// and this results in another sync call before the first sync call ends the browser hangs
		if(!dojo.hostenv._blockAsync && !_this._blockAsync){
			for(var x=this.inFlight.length-1; x>=0; x--){
				try{
					var tif = this.inFlight[x];
					if(!tif || tif.http._aborted || !tif.http.readyState){
						this.inFlight.splice(x, 1); continue;
					}
					if(4==tif.http.readyState){
						// remove it so we can clean refs
						this.inFlight.splice(x, 1);
						doLoad(tif.req, tif.http, tif.url, tif.query, tif.useCache);
					}else if (tif.startTime){
						//See if this is a timeout case.
						if(!now){
							now = (new Date()).getTime();
						}
						if(tif.startTime + (tif.req.timeoutSeconds * 1000) < now){
							//Stop the request.
							if(typeof tif.http.abort == "function"){
								tif.http.abort();
							}

							// remove it so we can clean refs
							this.inFlight.splice(x, 1);
							tif.req[(typeof tif.req.timeout == "function") ? "timeout" : "handle"]("timeout", null, tif.http, tif.req);
						}
					}
				}catch(e){
					try{
						var errObj = new dojo.io.Error("XMLHttpTransport.watchInFlight Error: " + e);
						tif.req[(typeof tif.req.error == "function") ? "error" : "handle"]("error", errObj, tif.http, tif.req);
					}catch(e2){
						dojo.debug("XMLHttpTransport error callback failed: " + e2);
					}
				}
			}
		}

		clearTimeout(this.inFlightTimer);
		if(this.inFlight.length == 0){
			this.inFlightTimer = null;
			return;
		}
		this.inFlightTimer = setTimeout("dojo.io.XMLHTTPTransport.watchInFlight();", 10);
	}

	var hasXmlHttp = dojo.hostenv.getXmlhttpObject() ? true : false;
	this.canHandle = function(/*dojo.io.Request*/kwArgs){
		//summary: Tells dojo.io.bind() if this is a good transport to
		//use for the particular type of request. This type of transport cannot
		//handle forms that have an input type="file" element.

		// FIXME: we need to determine when form values need to be
		// multi-part mime encoded and avoid using this transport for those
		// requests.
		return hasXmlHttp
			&& dojo.lang.inArray(["text/plain", "text/html", "application/xml", "text/xml", "text/javascript", "text/json", "application/json"], (kwArgs["mimetype"].toLowerCase()||""))
			&& !( kwArgs["formNode"] && dojo.io.formHasFile(kwArgs["formNode"]) ); //boolean
	}

	this.multipartBoundary = "45309FFF-BD65-4d50-99C9-36986896A96F";	// unique guid as a boundary value for multipart posts

	this.bind = function(/*dojo.io.Request*/kwArgs){
		//summary: function that sends the request to the server.

		//This function will attach an abort() function to the kwArgs dojo.io.Request object,
		//so if you need to abort the request, you can call that method on the request object.
		//The following are acceptable properties in kwArgs (in addition to the
		//normal dojo.io.Request object properties).
		//url: String: URL the server URL to use for the request.
		//method: String: the HTTP method to use (GET, POST, etc...).
		//mimetype: Specifies what format the result data should be given to the load/handle callback. Valid values are:
		//		text/javascript, text/json, application/json, application/xml, text/xml. Any other mimetype will give back a text
		//		string.
		//transport: String: specify "XMLHTTPTransport" to force the use of this XMLHttpRequest transport.
		//headers: Object: The object property names and values will be sent as HTTP request header
		//		names and values.
		//sendTransport: boolean: If true, then dojo.transport=xmlhttp will be added to the request.
		//encoding: String: The type of encoding to use when dealing with the content kwArgs property.
		//content: Object: The content object is converted into a name=value&name=value string, by
		//		using dojo.io.argsFromMap(). The encoding kwArgs property is passed to dojo.io.argsFromMap()
		//		for use in encoding the names and values. The resulting string is added to the request.
		//formNode: DOMNode: a form element node. This should not normally be used. Use new dojo.io.FormBind() instead.
		//		If formNode is used, then the names and values of the form elements will be converted
		//		to a name=value&name=value string and added to the request. The encoding kwArgs property is used
		//		to encode the names and values.
		//postContent: String: Raw name=value&name=value string to be included as part of the request.
		//back or backButton: Function: A function to be called if the back button is pressed. If this kwArgs property
		//		is used, then back button support via dojo.undo.browser will be used. See notes for dojo.undo.browser on usage.
		//		You need to set djConfig.preventBackButtonFix = false to enable back button support.
		//changeUrl: boolean or String: Used as part of back button support. See notes for dojo.undo.browser on usage.
		//user: String: The user name. Used in conjuction with password. Passed to XMLHttpRequest.open().
		//password: String: The user's password. Used in conjuction with user. Passed to XMLHttpRequest.open().
		//file: Object or Array of Objects: an object simulating a file to be uploaded. file objects should have the following properties:
		//		name or fileName: the name of the file
		//		contentType: the MIME content type for the file.
		//		content: the actual content of the file.
		//multipart: boolean: indicates whether this should be a multipart mime request. If kwArgs.file exists, then this
		//		property is set to true automatically.
		//sync: boolean: if true, then a synchronous XMLHttpRequest call is done,
		//		if false (the default), then an asynchronous call is used.
		//preventCache: boolean: If true, then a cache busting parameter is added to the request URL.
		//		default value is false.
		//useCache: boolean: If true, then XMLHttpTransport will keep an internal cache of the server
		//		response and use that response if a similar request is done again.
		//		A similar request is one that has the same URL, query string and HTTP method value.
		//		default is false.
		if(!kwArgs["url"]){
			// are we performing a history action?
			if( !kwArgs["formNode"]
				&& (kwArgs["backButton"] || kwArgs["back"] || kwArgs["changeUrl"] || kwArgs["watchForURL"])
				&& (!djConfig.preventBackButtonFix)) {
        dojo.deprecated("Using dojo.io.XMLHTTPTransport.bind() to add to browser history without doing an IO request",
        				"Use dojo.undo.browser.addToHistory() instead.", "0.4");
				dojo.undo.browser.addToHistory(kwArgs);
				return true;
			}
		}

		// build this first for cache purposes
		var url = kwArgs.url;
		var query = "";
		if(kwArgs["formNode"]){
			var ta = kwArgs.formNode.getAttribute("action");
			if((ta)&&(!kwArgs["url"])){ url = ta; }
			var tp = kwArgs.formNode.getAttribute("method");
			if((tp)&&(!kwArgs["method"])){ kwArgs.method = tp; }
			query += dojo.io.encodeForm(kwArgs.formNode, kwArgs.encoding, kwArgs["formFilter"]);
		}

		if(url.indexOf("#") > -1) {
			dojo.debug("Warning: dojo.io.bind: stripping hash values from url:", url);
			url = url.split("#")[0];
		}

		if(kwArgs["file"]){
			// force post for file transfer
			kwArgs.method = "post";
		}

		if(!kwArgs["method"]){
			kwArgs.method = "get";
		}

		// guess the multipart value
		if(kwArgs.method.toLowerCase() == "get"){
			// GET cannot use multipart
			kwArgs.multipart = false;
		}else{
			if(kwArgs["file"]){
				// enforce multipart when sending files
				kwArgs.multipart = true;
			}else if(!kwArgs["multipart"]){
				// default
				kwArgs.multipart = false;
			}
		}

		if(kwArgs["backButton"] || kwArgs["back"] || kwArgs["changeUrl"]){
			dojo.undo.browser.addToHistory(kwArgs);
		}

		var content = kwArgs["content"] || {};

		if(kwArgs.sendTransport) {
			content["dojo.transport"] = "xmlhttp";
		}

		do { // break-block
			if(kwArgs.postContent){
				query = kwArgs.postContent;
				break;
			}

			if(content) {
				query += dojo.io.argsFromMap(content, kwArgs.encoding);
			}

			if(kwArgs.method.toLowerCase() == "get" || !kwArgs.multipart){
				break;
			}

			var	t = [];
			if(query.length){
				var q = query.split("&");
				for(var i = 0; i < q.length; ++i){
					if(q[i].length){
						var p = q[i].split("=");
						t.push(	"--" + this.multipartBoundary,
								"Content-Disposition: form-data; name=\"" + p[0] + "\"",
								"",
								p[1]);
					}
				}
			}

			if(kwArgs.file){
				if(dojo.lang.isArray(kwArgs.file)){
					for(var i = 0; i < kwArgs.file.length; ++i){
						var o = kwArgs.file[i];
						t.push(	"--" + this.multipartBoundary,
								"Content-Disposition: form-data; name=\"" + o.name + "\"; filename=\"" + ("fileName" in o ? o.fileName : o.name) + "\"",
								"Content-Type: " + ("contentType" in o ? o.contentType : "application/octet-stream"),
								"",
								o.content);
					}
				}else{
					var o = kwArgs.file;
					t.push(	"--" + this.multipartBoundary,
							"Content-Disposition: form-data; name=\"" + o.name + "\"; filename=\"" + ("fileName" in o ? o.fileName : o.name) + "\"",
							"Content-Type: " + ("contentType" in o ? o.contentType : "application/octet-stream"),
							"",
							o.content);
				}
			}

			if(t.length){
				t.push("--"+this.multipartBoundary+"--", "");
				query = t.join("\r\n");
			}
		}while(false);

		// kwArgs.Connection = "close";

		var async = kwArgs["sync"] ? false : true;

		var preventCache = kwArgs["preventCache"] ||
			(this.preventCache == true && kwArgs["preventCache"] != false);
		var useCache = kwArgs["useCache"] == true ||
			(this.useCache == true && kwArgs["useCache"] != false );

		// preventCache is browser-level (add query string junk), useCache
		// is for the local cache. If we say preventCache, then don't attempt
		// to look in the cache, but if useCache is true, we still want to cache
		// the response
		if(!preventCache && useCache){
			var cachedHttp = getFromCache(url, query, kwArgs.method);
			if(cachedHttp){
				doLoad(kwArgs, cachedHttp, url, query, false);
				return;
			}
		}

		// much of this is from getText, but reproduced here because we need
		// more flexibility
		var http = dojo.hostenv.getXmlhttpObject(kwArgs);
		var received = false;

		// build a handler function that calls back to the handler obj
		if(async){
			var startTime =
			// FIXME: setting up this callback handler leaks on IE!!!
			this.inFlight.push({
				"req":		kwArgs,
				"http":		http,
				"url":	 	url,
				"query":	query,
				"useCache":	useCache,
				"startTime": kwArgs.timeoutSeconds ? (new Date()).getTime() : 0
			});
			this.startWatchingInFlight();
		}else{
			// block async callbacks until sync is in, needed in khtml, others?
			_this._blockAsync = true;
		}

		if(kwArgs.method.toLowerCase() == "post"){
			// FIXME: need to hack in more flexible Content-Type setting here!
			if (!kwArgs.user) {
				http.open("POST", url, async);
			}else{
        http.open("POST", url, async, kwArgs.user, kwArgs.password);
			}
			setHeaders(http, kwArgs);
			http.setRequestHeader("Content-Type", kwArgs.multipart ? ("multipart/form-data; boundary=" + this.multipartBoundary) :
				(kwArgs.contentType || "application/x-www-form-urlencoded"));
			try{
				http.send(query);
			}catch(e){
				if(typeof http.abort == "function"){
					http.abort();
				}
				doLoad(kwArgs, {status: 404}, url, query, useCache);
			}
		}else{
			var tmpUrl = url;
			if(query != "") {
				tmpUrl += (tmpUrl.indexOf("?") > -1 ? "&" : "?") + query;
			}
			if(preventCache) {
				tmpUrl += (dojo.string.endsWithAny(tmpUrl, "?", "&")
					? "" : (tmpUrl.indexOf("?") > -1 ? "&" : "?")) + "dojo.preventCache=" + new Date().valueOf();
			}
			if (!kwArgs.user) {
				http.open(kwArgs.method.toUpperCase(), tmpUrl, async);
			}else{
				http.open(kwArgs.method.toUpperCase(), tmpUrl, async, kwArgs.user, kwArgs.password);
			}
			setHeaders(http, kwArgs);
			try {
				http.send(null);
			}catch(e)	{
				if(typeof http.abort == "function"){
					http.abort();
				}
				doLoad(kwArgs, {status: 404}, url, query, useCache);
			}
		}

		if( !async ) {
			doLoad(kwArgs, http, url, query, useCache);
			_this._blockAsync = false;
		}

		kwArgs.abort = function(){
			try{// khtml doesent reset readyState on abort, need this workaround
				http._aborted = true;
			}catch(e){/*squelsh*/}
			return http.abort();
		}

		return;
	}
	dojo.io.transports.addTransport("XMLHTTPTransport");
}

}
dojo.provide("dojo.io.cookie");

dojo.io.cookie.setCookie = function(/*String*/name, /*String*/value,
									/*Number?*/days, /*String?*/path,
									/*String?*/domain, /*boolean?*/secure){
	//summary: sets a cookie.
	var expires = -1;
	if((typeof days == "number")&&(days >= 0)){
		var d = new Date();
		d.setTime(d.getTime()+(days*24*60*60*1000));
		expires = d.toGMTString();
	}
	value = escape(value);
	document.cookie = name + "=" + value + ";"
		+ (expires != -1 ? " expires=" + expires + ";" : "")
		+ (path ? "path=" + path : "")
		+ (domain ? "; domain=" + domain : "")
		+ (secure ? "; secure" : "");
}

dojo.io.cookie.set = dojo.io.cookie.setCookie;

dojo.io.cookie.getCookie = function(/*String*/name){
	//summary: Gets a cookie with the given name.

	// FIXME: Which cookie should we return?
	//        If there are cookies set for different sub domains in the current
	//        scope there could be more than one cookie with the same name.
	//        I think taking the last one in the list takes the one from the
	//        deepest subdomain, which is what we're doing here.
	var idx = document.cookie.lastIndexOf(name+'=');
	if(idx == -1) { return null; }
	var value = document.cookie.substring(idx+name.length+1);
	var end = value.indexOf(';');
	if(end == -1) { end = value.length; }
	value = value.substring(0, end);
	value = unescape(value);
	return value; //String
}

dojo.io.cookie.get = dojo.io.cookie.getCookie;

dojo.io.cookie.deleteCookie = function(/*String*/name){
	//summary: Deletes a cookie with the given name.
	dojo.io.cookie.setCookie(name, "-", 0);
}

dojo.io.cookie.setObjectCookie = function(	/*String*/name, /*Object*/obj,
											/*Number?*/days, /*String?*/path,
											/*String?*/domain, /*boolean?*/secure,
											/*boolean?*/clearCurrent){
	//summary: Takes an object, serializes it to a cookie value, and either
	//sets a cookie with the serialized value.
	//description: If clearCurrent is true, then any current cookie value
	//for this object will be replaced with the the new serialized object value.
	//If clearCurrent is false, then the existing cookie value will be modified
	//with any changes from the new object value.
	//Objects must be simple name/value pairs where the value is either a string
	//or a number. Any other value will be ignored.
	if(arguments.length == 5){ // for backwards compat
		clearCurrent = domain;
		domain = null;
		secure = null;
	}
	var pairs = [], cookie, value = "";
	if(!clearCurrent){
		cookie = dojo.io.cookie.getObjectCookie(name);
	}
	if(days >= 0){
		if(!cookie){ cookie = {}; }
		for(var prop in obj){
			if(obj[prop] == null){
				delete cookie[prop];
			}else if((typeof obj[prop] == "string")||(typeof obj[prop] == "number")){
				cookie[prop] = obj[prop];
			}
		}
		prop = null;
		for(var prop in cookie){
			pairs.push(escape(prop) + "=" + escape(cookie[prop]));
		}
		value = pairs.join("&");
	}
	dojo.io.cookie.setCookie(name, value, days, path, domain, secure);
}

dojo.io.cookie.getObjectCookie = function(/*String*/name){
	//summary: Gets an object value for the given cookie name. The complement of
	//dojo.io.cookie.setObjectCookie().
	var values = null, cookie = dojo.io.cookie.getCookie(name);
	if(cookie){
		values = {};
		var pairs = cookie.split("&");
		for(var i = 0; i < pairs.length; i++){
			var pair = pairs[i].split("=");
			var value = pair[1];
			if( isNaN(value) ){ value = unescape(pair[1]); }
			values[ unescape(pair[0]) ] = value;
		}
	}
	return values;
}

dojo.io.cookie.isSupported = function(){
	//summary: Tests the browser to see if cookies are enabled.
	if(typeof navigator.cookieEnabled != "boolean"){
		dojo.io.cookie.setCookie("__TestingYourBrowserForCookieSupport__",
			"CookiesAllowed", 90, null);
		var cookieVal = dojo.io.cookie.getCookie("__TestingYourBrowserForCookieSupport__");
		navigator.cookieEnabled = (cookieVal == "CookiesAllowed");
		if(navigator.cookieEnabled){
			// FIXME: should we leave this around?
			this.deleteCookie("__TestingYourBrowserForCookieSupport__");
		}
	}
	return navigator.cookieEnabled; //boolean
}

// need to leave this in for backwards-compat from 0.1 for when it gets pulled in by dojo.io.*
if(!dojo.io.cookies){ dojo.io.cookies = dojo.io.cookie; }

dojo.provide("dojo.io.*");

dojo.provide("dojo.widget.Toolbar");


/* ToolbarContainer
 *******************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarContainer",
	dojo.widget.HtmlWidget,
{
	isContainer: true,

	templateString: '<div class="toolbarContainer" dojoAttachPoint="containerNode"></div>',
	templateCssPath: dojo.uri.dojoUri("src/widget/templates/Toolbar.css"),

	getItem: function(name) {
		if(name instanceof dojo.widget.ToolbarItem) { return name; }
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				var item = child.getItem(name);
				if(item) { return item; }
			}
		}
		return null;
	},

	getItems: function() {
		var items = [];
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				items = items.concat(child.getItems());
			}
		}
		return items;
	},

	enable: function() {
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				child.enable.apply(child, arguments);
			}
		}
	},

	disable: function() {
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				child.disable.apply(child, arguments);
			}
		}
	},

	select: function(name) {
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				child.select(arguments);
			}
		}
	},

	deselect: function(name) {
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				child.deselect(arguments);
			}
		}
	},

	getItemsState: function() {
		var values = {};
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				dojo.lang.mixin(values, child.getItemsState());
			}
		}
		return values;
	},

	getItemsActiveState: function() {
		var values = {};
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				dojo.lang.mixin(values, child.getItemsActiveState());
			}
		}
		return values;
	},

	getItemsSelectedState: function() {
		var values = {};
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.Toolbar) {
				dojo.lang.mixin(values, child.getItemsSelectedState());
			}
		}
		return values;
	}
});

/* Toolbar
 **********/

dojo.widget.defineWidget(
	"dojo.widget.Toolbar",
	dojo.widget.HtmlWidget,
{
	isContainer: true,

	templateString: '<div class="toolbar" dojoAttachPoint="containerNode" unselectable="on" dojoOnMouseover="_onmouseover" dojoOnMouseout="_onmouseout" dojoOnClick="_onclick" dojoOnMousedown="_onmousedown" dojoOnMouseup="_onmouseup"></div>',

	// given a node, tries to find it's toolbar item
	_getItem: function(node) {
		var start = new Date();
		var widget = null;
		while(node && node != this.domNode) {
			if(dojo.html.hasClass(node, "toolbarItem")) {
				var widgets = dojo.widget.manager.getWidgetsByFilter(function(w) { return w.domNode == node; });
				if(widgets.length == 1) {
					widget = widgets[0];
					break;
				} else if(widgets.length > 1) {
					dojo.raise("Toolbar._getItem: More than one widget matches the node");
				}
			}
			node = node.parentNode;
		}
		return widget;
	},

	_onmouseover: function(e) {
		var widget = this._getItem(e.target);
		if(widget && widget._onmouseover) { widget._onmouseover(e); }
	},

	_onmouseout: function(e) {
		var widget = this._getItem(e.target);
		if(widget && widget._onmouseout) { widget._onmouseout(e); }
	},

	_onclick: function(e) {
		var widget = this._getItem(e.target);
		if(widget && widget._onclick){
			widget._onclick(e);
		}
	},

	_onmousedown: function(e) {
		var widget = this._getItem(e.target);
		if(widget && widget._onmousedown) { widget._onmousedown(e); }
	},

	_onmouseup: function(e) {
		var widget = this._getItem(e.target);
		if(widget && widget._onmouseup) { widget._onmouseup(e); }
	},

	addChild: function(item, pos, props) {
		var widget = dojo.widget.ToolbarItem.make(item, null, props);
		var ret = dojo.widget.Toolbar.superclass.addChild.call(this, widget, null, pos, null);
		return ret;
	},

	push: function() {
		for(var i = 0; i < arguments.length; i++) {
			this.addChild(arguments[i]);
		}
	},

	getItem: function(name) {
		if(name instanceof dojo.widget.ToolbarItem) { return name; }
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem
				&& child._name == name) { return child; }
		}
		return null;
	},

	getItems: function() {
		var items = [];
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem) {
				items.push(child);
			}
		}
		return items;
	},

	getItemsState: function() {
		var values = {};
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem) {
				values[child._name] = {
					selected: child._selected,
					enabled: !child.disabled
				};
			}
		}
		return values;
	},

	getItemsActiveState: function() {
		var values = this.getItemsState();
		for(var item in values) {
			values[item] = values[item].enabled;
		}
		return values;
	},

	getItemsSelectedState: function() {
		var values = this.getItemsState();
		for(var item in values) {
			values[item] = values[item].selected;
		}
		return values;
	},

	enable: function() {
		var items = arguments.length ? arguments : this.children;
		for(var i = 0; i < items.length; i++) {
			var child = this.getItem(items[i]);
			if(child instanceof dojo.widget.ToolbarItem) {
				child.enable(false, true);
			}
		}
	},

	disable: function() {
		var items = arguments.length ? arguments : this.children;
		for(var i = 0; i < items.length; i++) {
			var child = this.getItem(items[i]);
			if(child instanceof dojo.widget.ToolbarItem) {
				child.disable();
			}
		}
	},

	select: function() {
		for(var i = 0; i < arguments.length; i++) {
			var name = arguments[i];
			var item = this.getItem(name);
			if(item) { item.select(); }
		}
	},

	deselect: function() {
		for(var i = 0; i < arguments.length; i++) {
			var name = arguments[i];
			var item = this.getItem(name);
			if(item) { item.disable(); }
		}
	},

	setValue: function() {
		for(var i = 0; i < arguments.length; i += 2) {
			var name = arguments[i], value = arguments[i+1];
			var item = this.getItem(name);
			if(item) {
				if(item instanceof dojo.widget.ToolbarItem) {
					item.setValue(value);
				}
			}
		}
	}
});

/* ToolbarItem hierarchy:
	- ToolbarItem
		- ToolbarButton
		- ToolbarDialog
			- ToolbarMenu
		- ToolbarSeparator
			- ToolbarSpace
				- ToolbarFlexibleSpace
*/


/* ToolbarItem
 **************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarItem",
	dojo.widget.HtmlWidget,
{
	templateString: '<span unselectable="on" class="toolbarItem"></span>',

	_name: null,
	getName: function() { return this._name; },
	setName: function(value) { return (this._name = value); },
	getValue: function() { return this.getName(); },
	setValue: function(value) { return this.setName(value); },

	_selected: false,
	isSelected: function() { return this._selected; },
	setSelected: function(is, force, preventEvent) {
		if(!this._toggleItem && !force) { return; }
		is = Boolean(is);
		if(force || !this.disabled && this._selected != is) {
			this._selected = is;
			this.update();
			if(!preventEvent) {
				this._fireEvent(is ? "onSelect" : "onDeselect");
				this._fireEvent("onChangeSelect");
			}
		}
	},
	select: function(force, preventEvent) {
		return this.setSelected(true, force, preventEvent);
	},
	deselect: function(force, preventEvent) {
		return this.setSelected(false, force, preventEvent);
	},

	_toggleItem: false,
	isToggleItem: function() { return this._toggleItem; },
	setToggleItem: function(value) { this._toggleItem = Boolean(value); },

	toggleSelected: function(force) {
		return this.setSelected(!this._selected, force);
	},

	isEnabled: function() { return !this.disabled; },
	setEnabled: function(is, force, preventEvent) {
		is = Boolean(is);
		if(force || this.disabled == is) {
			this.disabled = !is;
			this.update();
			if(!preventEvent) {
				this._fireEvent(this.disabled ? "onDisable" : "onEnable");
				this._fireEvent("onChangeEnabled");
			}
		}
		return !this.disabled;
	},
	enable: function(force, preventEvent) {
		return this.setEnabled(true, force, preventEvent);
	},
	disable: function(force, preventEvent) {
		return this.setEnabled(false, force, preventEvent);
	},
	toggleEnabled: function(force, preventEvent) {
		return this.setEnabled(this.disabled, force, preventEvent);
	},

	_icon: null,
	getIcon: function() { return this._icon; },
	setIcon: function(value) {
		var icon = dojo.widget.Icon.make(value);
		if(this._icon) {
			this._icon.setIcon(icon);
		} else {
			this._icon = icon;
		}
		var iconNode = this._icon.getNode();
		if(iconNode.parentNode != this.domNode) {
			if(this.domNode.hasChildNodes()) {
				this.domNode.insertBefore(iconNode, this.domNode.firstChild);
			} else {
				this.domNode.appendChild(iconNode);
			}
		}
		return this._icon;
	},

	// TODO: update the label node (this.labelNode?)
	_label: "",
	getLabel: function() { return this._label; },
	setLabel: function(value) {
		var ret = (this._label = value);
		if(!this.labelNode) {
			this.labelNode = document.createElement("span");
			this.domNode.appendChild(this.labelNode);
		}
		this.labelNode.innerHTML = "";
		this.labelNode.appendChild(document.createTextNode(this._label));
		this.update();
		return ret;
	},

	// fired from: setSelected, setEnabled, setLabel
	update: function() {
		if(this.disabled) {
			this._selected = false;
			dojo.html.addClass(this.domNode, "disabled");
			dojo.html.removeClass(this.domNode, "down");
			dojo.html.removeClass(this.domNode, "hover");
		} else {
			dojo.html.removeClass(this.domNode, "disabled");
			if(this._selected) {
				dojo.html.addClass(this.domNode, "selected");
			} else {
				dojo.html.removeClass(this.domNode, "selected");
			}
		}
		this._updateIcon();
	},

	_updateIcon: function() {
		if(this._icon) {
			if(this.disabled) {
				this._icon.disable();
			} else {
				if(this._cssHover) {
					this._icon.hover();
				} else if(this._selected) {
					this._icon.select();
				} else {
					this._icon.enable();
				}
			}
		}
	},

	_fireEvent: function(evt) {
		if(typeof this[evt] == "function") {
			var args = [this];
			for(var i = 1; i < arguments.length; i++) {
				args.push(arguments[i]);
			}
			this[evt].apply(this, args);
		}
	},

	_onmouseover: function(e) {
		if(this.disabled) { return; }
		dojo.html.addClass(this.domNode, "hover");
		this._fireEvent("onMouseOver");
	},

	_onmouseout: function(e) {
		dojo.html.removeClass(this.domNode, "hover");
		dojo.html.removeClass(this.domNode, "down");
		if(!this._selected) {
			dojo.html.removeClass(this.domNode, "selected");
		}
		this._fireEvent("onMouseOut");
	},

	_onclick: function(e) {
		// dojo.debug("widget:", this.widgetType, ":", this.getName(), ", disabled:", this.disabled);
		if(!this.disabled && !this._toggleItem) {
			this._fireEvent("onClick");
		}
	},

	_onmousedown: function(e) {
		if(e.preventDefault) { e.preventDefault(); }
		if(this.disabled) { return; }
		dojo.html.addClass(this.domNode, "down");
		if(this._toggleItem) {
			if(this.parent.preventDeselect && this._selected) {
				return;
			}
			this.toggleSelected();
		}
		this._fireEvent("onMouseDown");
	},

	_onmouseup: function(e) {
		dojo.html.removeClass(this.domNode, "down");
		this._fireEvent("onMouseUp");
	},

	onClick: function() { },
	onMouseOver: function() { },
	onMouseOut: function() { },
	onMouseDown: function() { },
	onMouseUp: function() { },

	fillInTemplate: function(args, frag) {
		if(args.name) { this._name = args.name; }
		if(args.selected) { this.select(); }
		if(args.disabled) { this.disable(); }
		if(args.label) { this.setLabel(args.label); }
		if(args.icon) { this.setIcon(args.icon); }
		if(args.toggleitem||args.toggleItem) { this.setToggleItem(true); }
	}
});

dojo.widget.ToolbarItem.make = function(wh, whIsType, props) {
	var item = null;

	if(wh instanceof Array) {
		item = dojo.widget.createWidget("ToolbarButtonGroup", props);
		item.setName(wh[0]);
		for(var i = 1; i < wh.length; i++) {
			item.addChild(wh[i]);
		}
	} else if(wh instanceof dojo.widget.ToolbarItem) {
		item = wh;
	} else if(wh instanceof dojo.uri.Uri) {
		item = dojo.widget.createWidget("ToolbarButton",
			dojo.lang.mixin(props||{}, {icon: new dojo.widget.Icon(wh.toString())}));
	} else if(whIsType) {
		item = dojo.widget.createWidget(wh, props);
	} else if(typeof wh == "string" || wh instanceof String) {
		switch(wh.charAt(0)) {
			case "|":
			case "-":
			case "/":
				item = dojo.widget.createWidget("ToolbarSeparator", props);
				break;
			case " ":
				if(wh.length == 1) {
					item = dojo.widget.createWidget("ToolbarSpace", props);
				} else {
					item = dojo.widget.createWidget("ToolbarFlexibleSpace", props);
				}
				break;
			default:
				if(/\.(gif|jpg|jpeg|png)$/i.test(wh)) {
					item = dojo.widget.createWidget("ToolbarButton",
						dojo.lang.mixin(props||{}, {icon: new dojo.widget.Icon(wh.toString())}));
				} else {
					item = dojo.widget.createWidget("ToolbarButton",
						dojo.lang.mixin(props||{}, {label: wh.toString()}));
				}
		}
	} else if(wh && wh.tagName && /^img$/i.test(wh.tagName)) {
		item = dojo.widget.createWidget("ToolbarButton",
			dojo.lang.mixin(props||{}, {icon: wh}));
	} else {
		item = dojo.widget.createWidget("ToolbarButton",
			dojo.lang.mixin(props||{}, {label: wh.toString()}));
	}
	return item;
}

/* ToolbarButtonGroup
 *********************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarButtonGroup",
	dojo.widget.ToolbarItem,
{
	isContainer: true,

	templateString: '<span unselectable="on" class="toolbarButtonGroup" dojoAttachPoint="containerNode"></span>',

	// if a button has the same name, it will be selected
	// if this is set to a number, the button at that index will be selected
	defaultButton: "",

    postCreate: function() {
        for (var i = 0; i < this.children.length; i++) {
            this._injectChild(this.children[i]);
        }
    },

	addChild: function(item, pos, props) {
		var widget = dojo.widget.ToolbarItem.make(item, null, dojo.lang.mixin(props||{}, {toggleItem:true}));
		var ret = dojo.widget.ToolbarButtonGroup.superclass.addChild.call(this, widget, null, pos, null);
        this._injectChild(widget);
        return ret;
    },

    _injectChild: function(widget) {
        dojo.event.connect(widget, "onSelect", this, "onChildSelected");
        dojo.event.connect(widget, "onDeselect", this, "onChildDeSelected");
        if(widget._name == this.defaultButton
			|| (typeof this.defaultButton == "number"
			&& this.children.length-1 == this.defaultButton)) {
			widget.select(false, true);
		}
	},

	getItem: function(name) {
		if(name instanceof dojo.widget.ToolbarItem) { return name; }
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem
				&& child._name == name) { return child; }
		}
		return null;
	},

	getItems: function() {
		var items = [];
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem) {
				items.push(child);
			}
		}
		return items;
	},

	onChildSelected: function(e) {
		this.select(e._name);
	},

	onChildDeSelected: function(e) {
		this._fireEvent("onChangeSelect", this._value);
	},

	enable: function(force, preventEvent) {
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem) {
				child.enable(force, preventEvent);
				if(child._name == this._value) {
					child.select(force, preventEvent);
				}
			}
		}
	},

	disable: function(force, preventEvent) {
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem) {
				child.disable(force, preventEvent);
			}
		}
	},

	_value: "",
	getValue: function() { return this._value; },

	select: function(name, force, preventEvent) {
		for(var i = 0; i < this.children.length; i++) {
			var child = this.children[i];
			if(child instanceof dojo.widget.ToolbarItem) {
				if(child._name == name) {
					child.select(force, preventEvent);
					this._value = name;
				} else {
					child.deselect(true, true);
				}
			}
		}
		if(!preventEvent) {
			this._fireEvent("onSelect", this._value);
			this._fireEvent("onChangeSelect", this._value);
		}
	},
	setValue: this.select,

	preventDeselect: false // if true, once you select one, you can't have none selected
});

/* ToolbarButton
 ***********************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarButton",
	dojo.widget.ToolbarItem,
{
	fillInTemplate: function(args, frag) {
		dojo.widget.ToolbarButton.superclass.fillInTemplate.call(this, args, frag);
		dojo.html.addClass(this.domNode, "toolbarButton");
		if(this._icon) {
			this.setIcon(this._icon);
		}
		if(this._label) {
			this.setLabel(this._label);
		}

		if(!this._name) {
			if(this._label) {
				this.setName(this._label);
			} else if(this._icon) {
				var src = this._icon.getSrc("enabled").match(/[\/^]([^\.\/]+)\.(gif|jpg|jpeg|png)$/i);
				if(src) { this.setName(src[1]); }
			} else {
				this._name = this._widgetId;
			}
		}
	}
});

/* ToolbarDialog
 **********************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarDialog",
	dojo.widget.ToolbarButton,
{
	fillInTemplate: function (args, frag) {
		dojo.widget.ToolbarDialog.superclass.fillInTemplate.call(this, args, frag);
		dojo.event.connect(this, "onSelect", this, "showDialog");
		dojo.event.connect(this, "onDeselect", this, "hideDialog");
	},

	showDialog: function (e) {
		dojo.lang.setTimeout(dojo.event.connect, 1, document, "onmousedown", this, "deselect");
	},

	hideDialog: function (e) {
		dojo.event.disconnect(document, "onmousedown", this, "deselect");
	}

});

/* ToolbarMenu
 **********************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarMenu",
	dojo.widget.ToolbarDialog,
	{}
);

/* ToolbarMenuItem
 ******************/
dojo.widget.ToolbarMenuItem = function() {
}

/* ToolbarSeparator
 **********************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarSeparator",
	dojo.widget.ToolbarItem,
{
	templateString: '<span unselectable="on" class="toolbarItem toolbarSeparator"></span>',

	defaultIconPath: new dojo.uri.dojoUri("src/widget/templates/buttons/sep.gif"),

	fillInTemplate: function(args, frag, skip) {
		dojo.widget.ToolbarSeparator.superclass.fillInTemplate.call(this, args, frag);
		this._name = this.widgetId;
		if(!skip) {
			if(!this._icon) {
				this.setIcon(this.defaultIconPath);
			}
			this.domNode.appendChild(this._icon.getNode());
		}
	},

	// don't want events!
	_onmouseover: null,
    _onmouseout: null,
    _onclick: null,
    _onmousedown: null,
    _onmouseup: null
});

/* ToolbarSpace
 **********************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarSpace",
	dojo.widget.ToolbarSeparator,
{
	fillInTemplate: function(args, frag, skip) {
		dojo.widget.ToolbarSpace.superclass.fillInTemplate.call(this, args, frag, true);
		if(!skip) {
			dojo.html.addClass(this.domNode, "toolbarSpace");
		}
	}
});

/* ToolbarSelect
 ******************/

dojo.widget.defineWidget(
	"dojo.widget.ToolbarSelect",
	dojo.widget.ToolbarItem,
{
	templateString: '<span class="toolbarItem toolbarSelect" unselectable="on"><select dojoAttachPoint="selectBox" dojoOnChange="changed"></select></span>',

	fillInTemplate: function(args, frag) {
		dojo.widget.ToolbarSelect.superclass.fillInTemplate.call(this, args, frag, true);
		var keys = args.values;
		var i = 0;
		for(var val in keys) {
			var opt = document.createElement("option");
			opt.setAttribute("value", keys[val]);
			opt.innerHTML = val;
			this.selectBox.appendChild(opt);
		}
	},

	changed: function(e) {
		this._fireEvent("onSetValue", this.selectBox.value);
	},

	setEnabled: function(is, force, preventEvent) {
		var ret = dojo.widget.ToolbarSelect.superclass.setEnabled.call(this, is, force, preventEvent);
		this.selectBox.disabled = this.disabled;
		return ret;
	},

	// don't want events!
	_onmouseover: null,
    _onmouseout: null,
    _onclick: null,
    _onmousedown: null,
    _onmouseup: null
});

/* Icon
 *********/
// arguments can be IMG nodes, Image() instances or URLs -- enabled is the only one required
dojo.widget.Icon = function(enabled, disabled, hovered, selected){
	if(!arguments.length){
		// FIXME: should this be dojo.raise?
		throw new Error("Icon must have at least an enabled state");
	}
	var states = ["enabled", "disabled", "hovered", "selected"];
	var currentState = "enabled";
	var domNode = document.createElement("img");

	this.getState = function(){ return currentState; }
	this.setState = function(value){
		if(dojo.lang.inArray(states, value)){
			if(this[value]){
				currentState = value;
				var img = this[currentState];
				if ((dojo.render.html.ie55 || dojo.render.html.ie60) && img.src && img.src.match(/[.]png$/i) ) {
					domNode.width = img.width||img.offsetWidth;
					domNode.height = img.height||img.offsetHeight;
					domNode.setAttribute("src", dojo.uri.dojoUri("src/widget/templates/images/blank.gif").uri);
					domNode.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+img.src+"',sizingMethod='image')";
				} else {
					domNode.setAttribute("src", img.src);
				}
			}
		}else{
			throw new Error("Invalid state set on Icon (state: " + value + ")");
		}
	}

	this.setSrc = function(state, value){
		if(/^img$/i.test(value.tagName)){
			this[state] = value;
		}else if(typeof value == "string" || value instanceof String
			|| value instanceof dojo.uri.Uri){
			this[state] = new Image();
			this[state].src = value.toString();
		}
		return this[state];
	}

	this.setIcon = function(icon){
		for(var i = 0; i < states.length; i++){
			if(icon[states[i]]){
				this.setSrc(states[i], icon[states[i]]);
			}
		}
		this.update();
	}

	this.enable = function(){ this.setState("enabled"); }
	this.disable = function(){ this.setState("disabled"); }
	this.hover = function(){ this.setState("hovered"); }
	this.select = function(){ this.setState("selected"); }

	this.getSize = function(){
		return {
			width: domNode.width||domNode.offsetWidth,
			height: domNode.height||domNode.offsetHeight
		};
	}

	this.setSize = function(w, h){
		domNode.width = w;
		domNode.height = h;
		return { width: w, height: h };
	}

	this.getNode = function(){
		return domNode;
	}

	this.getSrc = function(state){
		if(state){ return this[state].src; }
		return domNode.src||"";
	}

	this.update = function(){
		this.setState(currentState);
	}

	for(var i = 0; i < states.length; i++){
		var arg = arguments[i];
		var state = states[i];
		this[state] = null;
		if(!arg){ continue; }
		this.setSrc(state, arg);
	}

	this.enable();
}

dojo.widget.Icon.make = function(a,b,c,d){
	for(var i = 0; i < arguments.length; i++){
		if(arguments[i] instanceof dojo.widget.Icon){
			return arguments[i];
		}
	}

	return new dojo.widget.Icon(a,b,c,d);
}

/* ToolbarColorDialog
 ******************/
dojo.widget.defineWidget(
	"dojo.widget.ToolbarColorDialog",
	dojo.widget.ToolbarDialog,
{
 	palette: "7x10",

	fillInTemplate: function (args, frag) {
		dojo.widget.ToolbarColorDialog.superclass.fillInTemplate.call(this, args, frag);
		this.dialog = dojo.widget.createWidget("ColorPalette", {palette: this.palette});
		this.dialog.domNode.style.position = "absolute";

		dojo.event.connect(this.dialog, "onColorSelect", this, "_setValue");
	},

	_setValue: function(color) {
		this._value = color;
		this._fireEvent("onSetValue", color);
	},

	showDialog: function (e) {
		dojo.widget.ToolbarColorDialog.superclass.showDialog.call(this, e);
		var abs = dojo.html.getAbsolutePosition(this.domNode, true);
		var y = abs.y + dojo.html.getBorderBox(this.domNode).height;
		this.dialog.showAt(abs.x, y);
	},

	hideDialog: function (e) {
		dojo.widget.ToolbarColorDialog.superclass.hideDialog.call(this, e);
		this.dialog.hide();
	}
});
dojo.provide("dojo.html.*");

dojo.provide("dojo.html.selection");


/**
 * type of selection
**/
dojo.html.selectionType = {
	NONE : 0, //selection is empty
	TEXT : 1, //selection contains text (may also contains CONTROL objects)
	CONTROL : 2 //only one element is selected (such as img, table etc)
};

dojo.html.clearSelection = function(){
	// summary: deselect the current selection to make it empty
	var _window = dojo.global();
	var _document = dojo.doc();
	try{
		if(_window["getSelection"]){
			if(dojo.render.html.safari){
				// pulled from WebCore/ecma/kjs_window.cpp, line 2536
				_window.getSelection().collapse();
			}else{
				_window.getSelection().removeAllRanges();
			}
		}else if(_document.selection){
			if(_document.selection.empty){
				_document.selection.empty();
			}else if(_document.selection.clear){
				_document.selection.clear();
			}
		}
		return true;
	}catch(e){
		dojo.debug(e);
		return false;
	}
}

dojo.html.disableSelection = function(/*DomNode*/element){
	// summary: disable selection on a node
	element = dojo.byId(element)||dojo.body();
	var h = dojo.render.html;

	if(h.mozilla){
		element.style.MozUserSelect = "none";
	}else if(h.safari){
		element.style.KhtmlUserSelect = "none";
	}else if(h.ie){
		element.unselectable = "on";
	}else{
		return false;
	}
	return true;
}

dojo.html.enableSelection = function(/*DomNode*/element){
	// summary: enable selection on a node
	element = dojo.byId(element)||dojo.body();

	var h = dojo.render.html;
	if(h.mozilla){
		element.style.MozUserSelect = "";
	}else if(h.safari){
		element.style.KhtmlUserSelect = "";
	}else if(h.ie){
		element.unselectable = "off";
	}else{
		return false;
	}
	return true;
}

dojo.html.selectElement = function(/*DomNode*/element){
	dojo.deprecated("dojo.html.selectElement", "replaced by dojo.html.selection.selectElementChildren", 0.5);
}

dojo.html.selectInputText = function(/*DomNode*/element){
	// summary: select all the text in an input element
	var _window = dojo.global();
	var _document = dojo.doc();
	element = dojo.byId(element);
	if(_document["selection"] && dojo.body()["createTextRange"]){ // IE
		var range = element.createTextRange();
		range.moveStart("character", 0);
		range.moveEnd("character", element.value.length);
		range.select();
	}else if(_window["getSelection"]){
		var selection = _window.getSelection();
		// FIXME: does this work on Safari?
		element.setSelectionRange(0, element.value.length);
	}
	element.focus();
}


dojo.html.isSelectionCollapsed = function(){
	dojo.deprecated("dojo.html.isSelectionCollapsed", "replaced by dojo.html.selection.isCollapsed", 0.5);
	return dojo.html.selection.isCollapsed();
}

dojo.lang.mixin(dojo.html.selection, {
	getType: function() {
		// summary: Get the selection type (like document.select.type in IE).
		if(dojo.doc()["selection"]){ //IE
			return dojo.html.selectionType[dojo.doc().selection.type.toUpperCase()];
		}else{
			var stype = dojo.html.selectionType.TEXT;

			// Check if the actual selection is a CONTROL (IMG, TABLE, HR, etc...).
			var oSel;
			try {oSel = dojo.global().getSelection();}
			catch (e) {}

			if(oSel && oSel.rangeCount==1){
				var oRange = oSel.getRangeAt(0);
				if (oRange.startContainer == oRange.endContainer && (oRange.endOffset - oRange.startOffset) == 1
					&& oRange.startContainer.nodeType != dojo.dom.TEXT_NODE) {
					stype = dojo.html.selectionType.CONTROL;
				}
			}
			return stype;
		}
	},
	isCollapsed: function() {
		// summary: return whether the current selection is empty
		var _window = dojo.global();
		var _document = dojo.doc();
		if(_document["selection"]){ // IE
			return _document.selection.createRange().text == "";
		}else if(_window["getSelection"]){
			var selection = _window.getSelection();
			if(dojo.lang.isString(selection)){ // Safari
				return selection == "";
			}else{ // Mozilla/W3
				return selection.isCollapsed || selection.toString() == "";
			}
		}
	},
	getSelectedElement: function() {
		// summary:
		//		Retrieves the selected element (if any), just in the case that a single
		//		element (object like and image or a table) is selected.
		if ( dojo.html.selection.getType() == dojo.html.selectionType.CONTROL ){
			if(dojo.doc()["selection"]){ //IE
				var range = dojo.doc().selection.createRange();

				if ( range && range.item ){
					return dojo.doc().selection.createRange().item(0);
				}
			}else{
				var selection = dojo.global().getSelection();
				return selection.anchorNode.childNodes[ selection.anchorOffset ];
			}
		}
	},
	getParentElement: function() {
		// summary:
		//		Get the parent element of the current selection
		if(dojo.html.selection.getType() == dojo.html.selectionType.CONTROL){
			var p = dojo.html.selection.getSelectedElement();
			if(p){ return p.parentNode; }
		}else{
			if(dojo.doc()["selection"]){ //IE
				return dojo.doc().selection.createRange().parentElement();
			}else{
				var selection = dojo.global().getSelection();
				if(selection){
					var node = selection.anchorNode;

					while ( node && node.nodeType != dojo.dom.ELEMENT_NODE ){
						node = node.parentNode;
					}

					return node;
				}
			}
		}
	},
	getSelectedText: function(){
		// summary:
		//		Return the text (no html tags) included in the current selection or null if no text is selected
		if(dojo.doc()["selection"]){ //IE
			if(dojo.html.selection.getType() == dojo.html.selectionType.CONTROL){
				return null;
			}
			return dojo.doc().selection.createRange().text;
		}else{
			var selection = dojo.global().getSelection();
			if(selection){
				return selection.toString();
			}
		}
	},
	getSelectedHtml: function(){
		// summary:
		//		Return the html of the current selection or null if unavailable
		if(dojo.doc()["selection"]){ //IE
			if(dojo.html.selection.getType() == dojo.html.selectionType.CONTROL){
				return null;
			}
			return dojo.doc().selection.createRange().htmlText;
		}else{
			var selection = dojo.global().getSelection();
			if(selection && selection.rangeCount){
				var frag = selection.getRangeAt(0).cloneContents();
				var div = document.createElement("div");
				div.appendChild(frag);
				return div.innerHTML;
			}
			return null;
		}
	},
	hasAncestorElement: function(/*String*/tagName /* ... */){
		// summary:
		// 		Check whether current selection has a  parent element which is of type tagName (or one of the other specified tagName)
		return (dojo.html.selection.getAncestorElement.apply(this, arguments) != null);
	},
	getAncestorElement: function(/*String*/tagName /* ... */){
		// summary:
		//		Return the parent element of the current selection which is of type tagName (or one of the other specified tagName)
		var node = dojo.html.selection.getSelectedElement() || dojo.html.selection.getParentElement();
		while(node /*&& node.tagName.toLowerCase() != 'body'*/){
			if(dojo.html.selection.isTag(node, arguments).length>0){
				return node;
			}
			node = node.parentNode;
		}
		return null;
	},
	//modified from dojo.html.isTag to take an array as second parameter
	isTag: function(/*DomNode*/node, /*Array*/tags) {
		if(node && node.tagName) {
			for (var i=0; i<tags.length; i++){
				if (node.tagName.toLowerCase()==String(tags[i]).toLowerCase()){
					return String(tags[i]).toLowerCase();
				}
			}
		}
		return "";
	},
	selectElement: function(/*DomNode*/element) {
		// summary: clear previous selection and select element (including all its children)
		var _window = dojo.global();
		var _document = dojo.doc();
		element = dojo.byId(element);
		if(_document.selection && dojo.body().createTextRange){ // IE
			try{
				var range = dojo.body().createControlRange();
				range.addElement(element);
				range.select();
			}catch(e){
				dojo.html.selection.selectElementChildren(element);
			}
		}else if(_window["getSelection"]){
			var selection = _window.getSelection();
			// FIXME: does this work on Safari?
			if(selection["removeAllRanges"]){ // Mozilla
				var range = _document.createRange() ;
				range.selectNode(element) ;
				selection.removeAllRanges() ;
				selection.addRange(range) ;
			}
		}
	},
	selectElementChildren: function(/*DomNode*/element){
		// summary: clear previous selection and select the content of the node (excluding the node itself)
		var _window = dojo.global();
		var _document = dojo.doc();
		element = dojo.byId(element);
		if(_document.selection && dojo.body().createTextRange){ // IE
			var range = dojo.body().createTextRange();
			range.moveToElementText(element);
			range.select();
		}else if(_window["getSelection"]){
			var selection = _window.getSelection();
			if(selection["setBaseAndExtent"]){ // Safari
				selection.setBaseAndExtent(element, 0, element, element.innerText.length - 1);
			} else if(selection["selectAllChildren"]){ // Mozilla
				selection.selectAllChildren(element);
			}
		}
	},
	getBookmark: function(){
		// summary: Retrieves a bookmark that can be used with moveToBookmark to return to the same range
		var bookmark;
		var _document = dojo.doc();
		if(_document["selection"]){ // IE
			var range = _document.selection.createRange();
			bookmark = range.getBookmark();
		}else{
			var selection;
			try {selection = dojo.global().getSelection();}
			catch (e) {}
			if(selection){
				var range = selection.getRangeAt(0);
				bookmark = range.cloneRange();
			}else{
				dojo.debug("No idea how to store the current selection for this browser!");
			}
		}
		return bookmark;
	},
	moveToBookmark: function(/*Object*/bookmark){
		// summary: Moves current selection to a bookmark
		// bookmark: this should be a returned object from dojo.html.selection.getBookmark()
		var _document = dojo.doc();
		if(_document["selection"]){ // IE
			var range = _document.selection.createRange();
			 range.moveToBookmark(bookmark);
			 range.select();
		}else{ //Moz/W3C
			var selection;
			try {selection = dojo.global().getSelection();}
			catch (e) {}
			if(selection && selection['removeAllRanges']){
				selection.removeAllRanges() ;
				selection.addRange(bookmark) ;
			}else{
				dojo.debug("No idea how to restore selection for this browser!");
			}
		}
	},
	collapse: function(/*Boolean*/beginning) {
		// summary: clear current selection
		if(dojo.global()['getSelection']){
			var selection = dojo.global().getSelection();
			if(selection.removeAllRanges){ // Mozilla
				if(beginning){
					selection.collapseToStart();
				}else{
					selection.collapseToEnd();
				}
			}else{ // Safari
				// pulled from WebCore/ecma/kjs_window.cpp, line 2536
				 dojo.global().getSelection().collapse(beginning);
			}
		}else if(dojo.doc().selection){ // IE
			var range = dojo.doc().selection.createRange();
			range.collapse(beginning);
			range.select();
		}
	},
	remove: function() {
		// summary: delete current selection
		if(dojo.doc().selection) { //IE
			var selection = dojo.doc().selection;

			if ( selection.type.toUpperCase() != "NONE" ){
				selection.clear();
			}

			return selection;
		}else{
			var selection = dojo.global().getSelection();

			for ( var i = 0; i < selection.rangeCount; i++ ){
				selection.getRangeAt(i).deleteContents();
			}

			return selection;
		}
	}
});

dojo.provide("dojo.Deferred");

dojo.Deferred = function(/*Function?*/ canceller){
	/*
	NOTE: this namespace and documentation are imported wholesale
		from MochiKit

	Encapsulates a sequence of callbacks in response to a value that
	may not yet be available.  This is modeled after the Deferred class
	from Twisted <http://twistedmatrix.com>.

	Why do we want this?  JavaScript has no threads, and even if it did,
	threads are hard.  Deferreds are a way of abstracting non-blocking
	events, such as the final response to an XMLHttpRequest.

	The sequence of callbacks is internally represented as a list
	of 2-tuples containing the callback/errback pair.  For example,
	the following call sequence::

		var d = new Deferred();
		d.addCallback(myCallback);
		d.addErrback(myErrback);
		d.addBoth(myBoth);
		d.addCallbacks(myCallback, myErrback);

	is translated into a Deferred with the following internal
	representation::

		[
			[myCallback, null],
			[null, myErrback],
			[myBoth, myBoth],
			[myCallback, myErrback]
		]

	The Deferred also keeps track of its current status (fired).
	Its status may be one of three things:

		-1: no value yet (initial condition)
		0: success
		1: error

	A Deferred will be in the error state if one of the following
	three conditions are met:

		1. The result given to callback or errback is "instanceof" Error
		2. The previous callback or errback raised an exception while
		   executing
		3. The previous callback or errback returned a value "instanceof"
			Error

	Otherwise, the Deferred will be in the success state.  The state of
	the Deferred determines the next element in the callback sequence to
	run.

	When a callback or errback occurs with the example deferred chain,
	something equivalent to the following will happen (imagine that
	exceptions are caught and returned)::

		// d.callback(result) or d.errback(result)
		if(!(result instanceof Error)){
			result = myCallback(result);
		}
		if(result instanceof Error){
			result = myErrback(result);
		}
		result = myBoth(result);
		if(result instanceof Error){
			result = myErrback(result);
		}else{
			result = myCallback(result);
		}

	The result is then stored away in case another step is added to the
	callback sequence.	Since the Deferred already has a value available,
	any new callbacks added will be called immediately.

	There are two other "advanced" details about this implementation that
	are useful:

	Callbacks are allowed to return Deferred instances themselves, so you
	can build complicated sequences of events with ease.

	The creator of the Deferred may specify a canceller.  The canceller
	is a function that will be called if Deferred.cancel is called before
	the Deferred fires.	 You can use this to implement clean aborting of
	an XMLHttpRequest, etc.	 Note that cancel will fire the deferred with
	a CancelledError (unless your canceller returns another kind of
	error), so the errbacks should be prepared to handle that error for
	cancellable Deferreds.

	*/

	this.chain = [];
	this.id = this._nextId();
	this.fired = -1;
	this.paused = 0;
	this.results = [null, null];
	this.canceller = canceller;
	this.silentlyCancelled = false;
};

dojo.lang.extend(dojo.Deferred, {
	getFunctionFromArgs: function(){
		var a = arguments;
		if((a[0])&&(!a[1])){
			if(dojo.lang.isFunction(a[0])){
				return a[0];
			}else if(dojo.lang.isString(a[0])){
				return dj_global[a[0]];
			}
		}else if((a[0])&&(a[1])){
			return dojo.lang.hitch(a[0], a[1]);
		}
		return null;
	},

	makeCalled: function() {
		var deferred = new dojo.Deferred();
		deferred.callback();
		return deferred;
	},

	repr: function(){
		var state;
		if(this.fired == -1){
			state = 'unfired';
		}else if(this.fired == 0){
			state = 'success';
		} else {
			state = 'error';
		}
		return 'Deferred(' + this.id + ', ' + state + ')';
	},

	toString: dojo.lang.forward("repr"),

	_nextId: (function(){
		var n = 1;
		return function(){ return n++; };
	})(),

	cancel: function(){
		// summary:	Cancels a Deferred that has not yet received a value, or is
		//		waiting on another Deferred as its value.
		// description:
		//		If a canceller is defined, the canceller is called. If the
		//		canceller did not return an error, or there was no canceller,
		//		then the errback chain is started with CancelledError.
		if(this.fired == -1){
			if (this.canceller){
				this.canceller(this);
			}else{
				this.silentlyCancelled = true;
			}
			if(this.fired == -1){
				this.errback(new Error(this.repr()));
			}
		}else if(	(this.fired == 0)&&
					(this.results[0] instanceof dojo.Deferred)){
			this.results[0].cancel();
		}
	},


	_pause: function(){
		// summary: Used internally to signal that it's waiting on another Deferred
		this.paused++;
	},

	_unpause: function(){
		// summary: Used internally to signal that it's no longer waiting on
		// another Deferred.
		this.paused--;
		if ((this.paused == 0) && (this.fired >= 0)) {
			this._fire();
		}
	},

	_continue: function(res){
		// summary: Used internally when a dependent deferred fires.
		this._resback(res);
		this._unpause();
	},

	_resback: function(res){
		// The primitive that means either callback or errback
		this.fired = ((res instanceof Error) ? 1 : 0);
		this.results[this.fired] = res;
		this._fire();
	},

	_check: function(){
		if(this.fired != -1){
			if(!this.silentlyCancelled){
				dojo.raise("already called!");
			}
			this.silentlyCancelled = false;
			return;
		}
	},

	callback: function(res){
		// summary:	Begin the callback sequence with a non-error value.

		/*
		callback or errback should only be called once on a given
		Deferred.
		*/
		this._check();
		this._resback(res);
	},

	errback: function(res){
		// summary: Begin the callback sequence with an error result.
		this._check();
		if(!(res instanceof Error)){
			res = new Error(res);
		}
		this._resback(res);
	},

	addBoth: function(cb, cbfn){
		/* summary
		Add the same function as both a callback and an errback as the
		next element on the callback sequence.	This is useful for code
		that you want to guarantee to run, e.g. a finalizer.
		*/
		var enclosed = this.getFunctionFromArgs(cb, cbfn);
		if(arguments.length > 2){
			enclosed = dojo.lang.curryArguments(null, enclosed, arguments, 2);
		}
		return this.addCallbacks(enclosed, enclosed);
	},

	addCallback: function(cb, cbfn){
		// summary: Add a single callback to the end of the callback sequence.
		var enclosed = this.getFunctionFromArgs(cb, cbfn);
		if(arguments.length > 2){
			enclosed = dojo.lang.curryArguments(null, enclosed, arguments, 2);
		}
		return this.addCallbacks(enclosed, null);
	},

	addErrback: function(cb, cbfn){
		// summary: Add a single callback to the end of the callback sequence.
		var enclosed = this.getFunctionFromArgs(cb, cbfn);
		if(arguments.length > 2){
			enclosed = dojo.lang.curryArguments(null, enclosed, arguments, 2);
		}
		return this.addCallbacks(null, enclosed);
		return this.addCallbacks(null, cbfn);
	},

	addCallbacks: function (cb, eb) {
		// summary: Add separate callback and errback to the end of the callback
		// sequence.
		this.chain.push([cb, eb])
		if (this.fired >= 0) {
			this._fire();
		}
		return this;
	},

	_fire: function(){
		// summary: Used internally to exhaust the callback sequence when a result
		// is available.
		var chain = this.chain;
		var fired = this.fired;
		var res = this.results[fired];
		var self = this;
		var cb = null;
		while (chain.length > 0 && this.paused == 0) {
			// Array
			var pair = chain.shift();
			var f = pair[fired];
			if (f == null) {
				continue;
			}
			try {
				res = f(res);
				fired = ((res instanceof Error) ? 1 : 0);
				if(res instanceof dojo.Deferred) {
					cb = function(res){
						self._continue(res);
					}
					this._pause();
				}
			}catch(err){
				fired = 1;
				res = err;
			}
		}
		this.fired = fired;
		this.results[fired] = res;
		if((cb)&&(this.paused)){
			// this is for "tail recursion" in case the dependent
			// deferred is already fired
			res.addBoth(cb);
		}
	}
});

 /* -*- tab-width: 4 -*- */
dojo.provide("dojo.widget.RichText");


// used to save content
if(dojo.hostenv.post_load_){
	(function(){
		var savetextarea = dojo.doc().createElement('textarea');
		savetextarea.id = "dojo.widget.RichText.savedContent";
		savetextarea.style = "display:none;position:absolute;top:-100px;left:-100px;height:3px;width:3px;overflow:hidden;";
		dojo.body().appendChild(savetextarea);
	})();
}else{
	//dojo.body() is not available before onLoad is fired
	try {
		dojo.doc().write('<textarea id="dojo.widget.RichText.savedContent" ' +
			'style="display:none;position:absolute;top:-100px;left:-100px;height:3px;width:3px;overflow:hidden;"></textarea>');
	}catch(e){ }
}

dojo.widget.defineWidget(
	"dojo.widget.RichText",
	dojo.widget.HtmlWidget,
	function(){
		// summary:
		//		dojo.widget.RichText is the core of the WYSIWYG editor in dojo, which
		//		provides the basic editing features. It also encapsulates the differences
		//		of different js engines for various browsers

		// contentPreFilters: Array
		//		pre content filter function register array
		this.contentPreFilters = [];

		// contentPostFilters: Array
		//		post content filter function register array
		this.contentPostFilters = [];

		// contentDomPreFilters: Array
		//		pre content dom filter function register array
		this.contentDomPreFilters = [];

		// contentDomPostFilters: Array
		//		post content dom filter function register array
		this.contentDomPostFilters = [];

		// editingAreaStyleSheets: Array
		//		array to store all the stylesheets applied to the editing area
		this.editingAreaStyleSheets=[];

		if(dojo.render.html.moz){
			this.contentPreFilters.push(this._fixContentForMoz);
		}

		this._keyHandlers = {};

		if(dojo.Deferred){
			this.onLoadDeferred = new dojo.Deferred();
		}
	},
	{
		// inheritWidth: Boolean
		//		whether to inherit the parent's width or simply use 100%
		inheritWidth: false,

		// focusOnLoad: Boolean
		//		whether focusing into this instance of richtext when page onload
		focusOnLoad: false,

		// saveName: String
		//		If a save name is specified the content is saved and restored when the user
		//		leave this page can come back, or if the editor is not properly closed after
		//		editing has started.
		saveName: "",

		// styleSheets: String
		//		semicolon (";") separated list of css files for the editing area
		styleSheets: "",

		// _content: String
		//		temporary content storage
		_content: "",

		// height: String
		//		set height to fix the editor at a specific height, with scrolling
		height: "",

		// minHeight: String
		//		The minimum height that the editor should have
		minHeight: "1em",

		// isClosed: Boolean
		isClosed: true,

		// isLoaded: Boolean
		isLoaded: false,

		// useActiveX: Boolean
		//		whether to use the active-x object in IE
		useActiveX: false,

		// relativeImageUrls: Boolean
		//		whether to use relative URLs for images - if this is enabled
		//		images will be given absolute URLs when inside the editor but
		//		will be changed to use relative URLs (to the current page) on save
		relativeImageUrls: false,

		// _SEPARATOR: String
		//		used to concat contents from multiple textareas into a single string
		_SEPARATOR: "@@**%%__RICHTEXTBOUNDRY__%%**@@",

		// onLoadDeferred: dojo.Deferred
		//		deferred that can be used to connect to the onLoad function. This
		//		will only be set if dojo.Deferred is required
		onLoadDeferred: null,

	/* Init
	 *******/

		fillInTemplate: function(){
			// summary: see dojo.widget.DomWidget
			dojo.event.topic.publish("dojo.widget.RichText::init", this);
			this.open();


			// backwards compatibility, needs to be removed
			dojo.event.connect(this, "onKeyPressed", this, "afterKeyPress");
			dojo.event.connect(this, "onKeyPress", this, "keyPress");
			dojo.event.connect(this, "onKeyDown", this, "keyDown");
			dojo.event.connect(this, "onKeyUp", this, "keyUp");

			this.setupDefaultShortcuts();
		},

		setupDefaultShortcuts: function(){
			// summary: add some default key handlers
			// description:
			// 		Overwrite this to setup your own handlers. The default
			// 		implementation does not use Editor2 commands, but directly
			//		executes the builtin commands within the underlying browser
			//		support.
			var ctrl = this.KEY_CTRL;
			var exec = function (cmd, arg) {
				return arguments.length == 1 ? function () { this.execCommand(cmd); } :
					function () { this.execCommand(cmd, arg); }
			}
			this.addKeyHandler("b", ctrl, exec("bold"));
			this.addKeyHandler("i", ctrl, exec("italic"));
			this.addKeyHandler("u", ctrl, exec("underline"));
			this.addKeyHandler("a", ctrl, exec("selectall"));
			this.addKeyHandler("s", ctrl, function () { this.save(true); });

			this.addKeyHandler("1", ctrl, exec("formatblock", "h1"));
			this.addKeyHandler("2", ctrl, exec("formatblock", "h2"));
			this.addKeyHandler("3", ctrl, exec("formatblock", "h3"));
			this.addKeyHandler("4", ctrl, exec("formatblock", "h4"));

			this.addKeyHandler("\\", ctrl, exec("insertunorderedlist"));
			if(!dojo.render.html.ie){
				this.addKeyHandler("Z", ctrl, exec("redo"));
			}
		},

		// events: Array
		//		 events which should be connected to the underlying editing area
		events: ["onBlur", "onFocus", "onKeyPress", "onKeyDown", "onKeyUp", "onClick"],

		/**
		 * Transforms the node referenced in this.domNode into a rich text editing
		 * node. This can result in the creation and replacement with an <iframe> if
		 * designMode is used, an <object> and active-x component if inside of IE or
		 * a reguler element if contentEditable is available.
		 */
		open: function (/*DomNode, optional*/element) {
			// summary:
			//		Transforms the node referenced in this.domNode into a rich text editing
			//		node. This can result in the creation and replacement with an <iframe> if
			//		designMode is used, an <object> and active-x component if inside of IE or
			//		a reguler element if contentEditable is available.

			if(this.onLoadDeferred.fired >= 0){
				this.onLoadDeferred = new dojo.Deferred();
			}

			var h = dojo.render.html;
			if (!this.isClosed) { this.close(); }
			dojo.event.topic.publish("dojo.widget.RichText::open", this);

			this._content = "";
			if((arguments.length == 1)&&(element["nodeName"])){ this.domNode = element; } // else unchanged

			if(	(this.domNode["nodeName"])&&
				(this.domNode.nodeName.toLowerCase() == "textarea")){
				this.textarea = this.domNode;
				var html = dojo.string.trim(this.textarea.value);
				this.domNode = dojo.doc().createElement("div");
				dojo.html.copyStyle(this.domNode, this.textarea);
				var tmpFunc = dojo.lang.hitch(this, function(){
					//some browsers refuse to submit display=none textarea, so
					//move the textarea out of screen instead
					with(this.textarea.style){
						display = "block";
						position = "absolute";
						left = top = "-1000px";

						if(h.ie){ //nasty IE bug: abnormal formatting if overflow is not hidden
							this.__overflow = overflow;
							overflow = "hidden";
						}
					}
				});
				if(h.ie){
					setTimeout(tmpFunc, 10);
				}else{
					tmpFunc();
				}
				if(!h.safari){
					// FIXME: VERY STRANGE safari 2.0.4 behavior here caused by
					// moving the textarea. Often crashed the browser!!! Seems
					// fixed on webkit nightlies.
					dojo.html.insertBefore(this.domNode, this.textarea);
				}
				// this.domNode.innerHTML = html;

				if(this.textarea.form){
					dojo.event.connect('before', this.textarea.form, "onsubmit",
						// FIXME: should we be calling close() here instead?
						dojo.lang.hitch(this, function(){
							this.textarea.value = this.getEditorContent();
						})
					);
				}

				// dojo plucks our original domNode from the document so we need
				// to go back and put ourselves back in
				var editor = this;
				dojo.event.connect(this, "postCreate", function (){
					dojo.html.insertAfter(editor.textarea, editor.domNode);
				});
			}else{
				var html = this._preFilterContent(dojo.string.trim(this.domNode.innerHTML));
			}
			if(html == ""){ html = "&nbsp;"; }
			var content = dojo.html.getContentBox(this.domNode);
			this._oldHeight = content.height;
			this._oldWidth = content.width;

			this._firstChildContributingMargin = this._getContributingMargin(this.domNode, "top");
			this._lastChildContributingMargin = this._getContributingMargin(this.domNode, "bottom");

			this.savedContent = this.domNode.innerHTML;
			this.domNode.innerHTML = '';

			this.editingArea = dojo.doc().createElement("div");
			this.domNode.appendChild(this.editingArea);

			// If we're a list item we have to put in a blank line to force the
			// bullet to nicely align at the top of text
			if(	(this.domNode["nodeName"])&&
				(this.domNode.nodeName == "LI")){
				this.domNode.innerHTML = " <br>";
			}

			if(this.saveName != ""){
				var saveTextarea = dojo.doc().getElementById("dojo.widget.RichText.savedContent");
				if (saveTextarea.value != "") {
					var datas = saveTextarea.value.split(this._SEPARATOR);
					for (var i = 0; i < datas.length; i++) {
						var data = datas[i].split(":");
						if (data[0] == this.saveName) {
							html = data[1];
							datas.splice(i, 1);
							break;
						}
					}
				}
				dojo.event.connect("before", window, "onunload", this, "_saveContent");
				// dojo.event.connect(window, "onunload", this, "_saveContent");
			}

			if(h.ie70 && this.useActiveX){
				dojo.debug("activeX in ie70 is not currently supported, useActiveX is ignored for now.");
				this.useActiveX = false;
			}
			// Safari's selections go all out of whack if we do it inline,
			// so for now IE is our only hero
			//if (typeof document.body.contentEditable != "undefined") {
			if(this.useActiveX && h.ie){ // active-x
				var self = this;
				//if call _drawObject directly here, textarea replacement
				//won't work: no content is shown. However, add a delay
				//can workaround this. No clue why.
				setTimeout(function(){self._drawObject(html);}, 0);
			}else if(h.ie || this._safariIsLeopard() || h.opera){ // contentEditable, easy
				this.iframe = dojo.doc().createElement( 'iframe' ) ;
				this.iframe.src = 'javascript:void(0)';
				this.editorObject = this.iframe;
				with(this.iframe.style){
					border = '0';
					width = "100%";
				}
				this.iframe.frameBorder = 0;
				this.editingArea.appendChild(this.iframe)
				this.window = this.iframe.contentWindow;
				this.document = this.window.document;
				this.document.open();
				this.document.write("<html><head><style>body{margin:0;padding:0;border:0;overflow:hidden;}</style></head><body><div></div></body></html>");
				this.document.close();
				this.editNode = this.document.body.firstChild;//document.createElement("div");
				this.editNode.contentEditable = true;
				with (this.iframe.style) {
					if(h.ie70){
						if(this.height){
							height = this.height;
						}
						if(this.minHeight){
							minHeight = this.minHeight;
						}
					}else{
						height = this.height ? this.height : this.minHeight;
					}
				}

				// FIXME: setting contentEditable on switches this element to
				// IE's hasLayout mode, triggering weird margin collapsing
				// behavior. It's particularly bad if the element you're editing
				// contains childnodes that don't have margin: defined in local
				// css rules. It would be nice if it was possible to hack around
				// this. Sadly _firstChildContributingMargin and
				// _lastChildContributingMargin don't work on IE unless all
				// elements have margins set in CSS :-(

				//if the normal way fails, we try the hard way to get the list
				//do not use _cacheLocalBlockFormatNames here, as it will trigger security warning in IE7
				//in the array below, ul can not come directly after ol, otherwise the queryCommandValue returns Normal for it
				var formats = ['p', 'pre', 'address', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'ol', 'div', 'ul'];
				var localhtml = "";
				for(var i in formats){
					if(formats[i].charAt(1) != 'l'){
						localhtml += "<"+formats[i]+"><span>content</span></"+formats[i]+">";
					}else{
						localhtml += "<"+formats[i]+"><li>content</li></"+formats[i]+">";
					}
				}
				//queryCommandValue returns empty if we hide editNode, so move it out of screen temporary
				with(this.editNode.style){
					position = "absolute";
					left = "-2000px";
					top = "-2000px";
				}
				this.editNode.innerHTML = localhtml;
				var node = this.editNode.firstChild;
				while(node){
					dojo.withGlobal(this.window, "selectElement", dojo.html.selection, [node.firstChild]);
					var nativename = node.tagName.toLowerCase();
					this._local2NativeFormatNames[nativename] = this.queryCommandValue("formatblock");
//						dojo.debug([nativename,this._local2NativeFormatNames[nativename]]);
					this._native2LocalFormatNames[this._local2NativeFormatNames[nativename]] = nativename;
					node = node.nextSibling;
				}
				with(this.editNode.style){
					position = "";
					left = "";
					top = "";
				}

				this.editNode.innerHTML = html;
				if(this.height){ this.document.body.style.overflowY="scroll"; }

				dojo.lang.forEach(this.events, function(e){
					dojo.event.connect(this.editNode, e.toLowerCase(), this, e);
				}, this);

				this.onLoad();
			} else { // designMode in iframe
				this._drawIframe(html);
				this.editorObject = this.iframe;
			}

			// TODO: this is a guess at the default line-height, kinda works
			if (this.domNode.nodeName == "LI") { this.domNode.lastChild.style.marginTop = "-1.2em"; }
			dojo.html.addClass(this.domNode, "RichTextEditable");

			this.isClosed = false;
		},

		_hasCollapseableMargin: function(/*DomNode*/element, /*String*/side) {
			// summary:
			//		check if an element has padding or borders on the given side
			//		which would prevent it from collapsing margins
			if (dojo.html.getPixelValue(element,
										 'border-'+side+'-width',
										 false)) {
				return false;
			} else if (dojo.html.getPixelValue(element,
												'padding-'+side,
												false)) {
				return false;
			} else {
				return true;
			}
		},

		_getContributingMargin:	function(/*DomNode*/element, /*String*/topOrBottom) {
			// summary:
			//		calculate how much margin this element and its first or last
			//		child are contributing to the total margin between this element
			//		and the adjacent node. CSS border collapsing makes this
			//		necessary.

			if (topOrBottom == "top") {
				var siblingAttr = "previousSibling";
				var childSiblingAttr = "nextSibling";
				var childAttr = "firstChild";
				var marginProp = "margin-top";
				var siblingMarginProp = "margin-bottom";
			} else {
				var siblingAttr = "nextSibling";
				var childSiblingAttr = "previousSibling";
				var childAttr = "lastChild";
				var marginProp = "margin-bottom";
				var siblingMarginProp = "margin-top";
			}

			var elementMargin = dojo.html.getPixelValue(element, marginProp, false);

			function isSignificantNode(element) {
				// see if an node is significant in the current context
				// for calulating margins
				return !(element.nodeType==3 && dojo.string.isBlank(element.data))
					&& dojo.html.getStyle(element, "display") != "none"
					&& !dojo.html.isPositionAbsolute(element);
			}

			// walk throuh first/last children to find total collapsed margin size
			var childMargin = 0;
			var child = element[childAttr];
			while (child) {
				// skip over insignificant elements (whitespace, etc)
				while ((!isSignificantNode(child)) && child[childSiblingAttr]) {
					child = child[childSiblingAttr];
				}

				childMargin = Math.max(childMargin, dojo.html.getPixelValue(child, marginProp, false));
				// stop if we hit a bordered/padded element
				if (!this._hasCollapseableMargin(child, topOrBottom)) break;
				child = child[childAttr];
			}

			// if this element has a border, return full child margin immediately
			// as there won't be any margin collapsing
			if (!this._hasCollapseableMargin(element, topOrBottom)){ return parseInt(childMargin); }

			// find margin supplied by nearest sibling
			var contextMargin = 0;
			var sibling = element[siblingAttr];
			while (sibling) {
				if (isSignificantNode(sibling)) {
					contextMargin = dojo.html.getPixelValue(sibling,
															 siblingMarginProp,
															 false);
					break;
				}
				sibling = sibling[siblingAttr];
			}
			if (!sibling) { // no sibling, look at parent's margin instead
				contextMargin = dojo.html.getPixelValue(element.parentNode,
												marginProp, false);
			}

			if (childMargin > elementMargin) {
				return parseInt(Math.max((childMargin-elementMargin)-contextMargin, 0));
			} else {
				return 0;
			}

		},

		_drawIframe: function (/*String*/html){
			// summary:
			//		Draws an iFrame using the existing one if one exists.
			//		Used by Mozilla, Safari, and Opera

			// detect firefox < 1.5, which has some iframe loading issues
			var oldMoz = Boolean(dojo.render.html.moz && (
									typeof window.XML == 'undefined'))

			if(!this.iframe){
				var currentDomain = (new dojo.uri.Uri(dojo.doc().location)).host;
				this.iframe = dojo.doc().createElement("iframe");
				// dojo.body().appendChild(this.iframe);
				with(this.iframe){
					style.border = "none";
					style.lineHeight = "0"; // squash line height
					style.verticalAlign = "bottom";
					scrolling = this.height ? "auto" : "no";
				}
			}
			// opera likes this to be outside the with block
			this.iframe.src = dojo.uri.dojoUri("src/widget/templates/richtextframe.html") + ((dojo.doc().domain != currentDomain) ? ("#"+dojo.doc().domain) : "");
			this.iframe.width = this.inheritWidth ? this._oldWidth : "100%";
			if(this.height){
				this.iframe.style.height = this.height;
			}else{
				var height = this._oldHeight;
				if(this._hasCollapseableMargin(this.domNode, 'top')){
					height += this._firstChildContributingMargin;
				}
				if(this._hasCollapseableMargin(this.domNode, 'bottom')){
					height += this._lastChildContributingMargin;
				}
				this.iframe.height = height;
			}

			var tmpContent = dojo.doc().createElement('div');
			tmpContent.innerHTML = html;
			//append tmpContent to under the current domNode so that the margin
			//calculation below is correct
			this.editingArea.appendChild(tmpContent);

			// make relative image urls absolute
			if(this.relativeImageUrls){
				var imgs = tmpContent.getElementsByTagName('img');
				for(var i=0; i<imgs.length; i++){
					imgs[i].src = (new dojo.uri.Uri(dojo.global().location, imgs[i].src)).toString();
				}
				html = tmpContent.innerHTML;
			}

			// fix margins on tmpContent
			var firstChild = dojo.html.firstElement(tmpContent);
			var lastChild = dojo.html.lastElement(tmpContent);
			if(firstChild){
				firstChild.style.marginTop = this._firstChildContributingMargin+"px";
			}
			if(lastChild){
				lastChild.style.marginBottom = this._lastChildContributingMargin+"px";
			}
			//do we want to show the content before the editing area finish loading here?
			//if external style sheets are used for the editing area, the appearance now
			//and after loading of the editing area won't be the same (and padding/margin
			//calculation above may not be accurate)
//			tmpContent.style.display = "none";
			this.editingArea.appendChild(this.iframe);
			if(dojo.render.html.safari){
				this.iframe.src = this.iframe.src;
			}

			var _iframeInitialized = false;

			// now we wait for onload. Janky hack!
			var ifrFunc = dojo.lang.hitch(this, function(){
				if(!_iframeInitialized){
					_iframeInitialized = true;
				}else{ return; }
				if(!this.editNode){
					if(this.iframe.contentWindow){
						this.window = this.iframe.contentWindow;
						this.document = this.iframe.contentWindow.document
					}else if(this.iframe.contentDocument){
						// for opera
						this.window = this.iframe.contentDocument.window;
						this.document = this.iframe.contentDocument;
					}

					// curry the getStyle function
					var getStyle = (function (domNode) { return function (style) {
						return dojo.html.getStyle(domNode, style);
					}; })(this.domNode);

					var font =
						getStyle('font-weight') + " " +
						getStyle('font-size') + " " +
						getStyle('font-family');

					// line height is tricky - applying a units value will mess things up.
					// if we can't get a non-units value, bail out.
					var lineHeight = "1.0";
					var lineHeightStyle = dojo.html.getUnitValue(this.domNode, 'line-height');
					if (lineHeightStyle.value && lineHeightStyle.units=="") {
						lineHeight = lineHeightStyle.value;
					}

					dojo.html.insertCssText(
						'body,html{background:transparent;padding:0;margin:0;}' +
						// TODO: left positioning will case contents to disappear out of view
						//       if it gets too wide for the visible area
						'body{top:0;left:0;right:0;' +
						(((this.height)||(dojo.render.html.opera)) ? '' : 'position:fixed;') +
						'font:' + font + ';' +
						'min-height:' + this.minHeight + ';' +
						'line-height:' + lineHeight + '}' +
						'p{margin: 1em 0 !important;}' +
						'body > *:first-child{padding-top:0 !important;margin-top:' + this._firstChildContributingMargin + 'px !important;}' + // FIXME: test firstChild nodeType
						'body > *:last-child{padding-bottom:0 !important;margin-bottom:' + this._lastChildContributingMargin + 'px !important;}' +
						'li > ul:-moz-first-node, li > ol:-moz-first-node{padding-top:1.2em;}\n' +
						'li{min-height:1.2em;}' +
						//'    p,ul,li { padding-top: 0; padding-bottom: 0; margin-top:0; margin-bottom: 0; }\n' +
						'', this.document);

					dojo.html.removeNode(tmpContent);
					this.document.body.innerHTML = html;
					if(oldMoz||dojo.render.html.safari){
						this.document.designMode = "on";
					}
					this.onLoad();
				}else{
					dojo.html.removeNode(tmpContent);
					this.editNode.innerHTML = html;
					this.onDisplayChanged();
				}
			});

			if(this.editNode){
				ifrFunc(); // iframe already exists, just set content
			}else if(dojo.render.html.moz){
				// FIXME: if we put this on a delay, we get a height of 20px.
				// Otherwise we get the correctly specified minHeight value.
				this.iframe.onload = function(){
					setTimeout(ifrFunc, 250);
				}
			}else{ // new mozillas, opera, safari
				this.iframe.onload = ifrFunc;
			}
		},

		_applyEditingAreaStyleSheets: function(){
			// summary:
			//		apply the specified css files in styleSheets
			var files = [];
			if(this.styleSheets){
				files = this.styleSheets.split(';');
				this.styleSheets = '';
			}

			//empty this.editingAreaStyleSheets here, as it will be filled in addStyleSheet
			files = files.concat(this.editingAreaStyleSheets);
			this.editingAreaStyleSheets = [];

			if(files.length>0){
				for(var i=0;i<files.length;i++){
					var url = files[i];
					if(url){
						this.addStyleSheet(dojo.uri.dojoUri(url));
	 				}
	 			}
			}
		},

		addStyleSheet: function(/*dojo.uri.Uri*/uri) {
			// summary:
			//		add an external stylesheet for the editing area
			// uri:	a dojo.uri.Uri pointing to the url of the external css file
			var url=uri.toString();
			if(dojo.lang.find(this.editingAreaStyleSheets, url) > -1){
				dojo.debug("dojo.widget.RichText.addStyleSheet: Style sheet "+url+" is already applied to the editing area!");
				return;
			}

			//if uri is relative, then convert it to absolute so that it can be resolved correctly in iframe
			if(url.charAt(0) == '.' || (url.charAt(0) != '/' && !uri.host)){
				url = (new dojo.uri.Uri(dojo.global().location, url)).toString();
			}

			this.editingAreaStyleSheets.push(url);
			if(this.document.createStyleSheet){ //IE
				this.document.createStyleSheet(url);
			}else{ //other browser
				var head = this.document.getElementsByTagName("head")[0];
				var stylesheet = this.document.createElement("link");
				with(stylesheet){
					rel="stylesheet";
					type="text/css";
					href=url;
				}
				head.appendChild(stylesheet);
			}
		},

		removeStyleSheet: function (/*dojo.uri.Uri*/uri) {
			// summary:
			//		remove an external stylesheet for the editing area
			var url=uri.toString();
			//if uri is relative, then convert it to absolute so that it can be resolved correctly in iframe
			if(url.charAt(0) == '.' || (url.charAt(0) != '/' && !uri.host)){
				url = (new dojo.uri.Uri(dojo.global().location, url)).toString();
			}
			var index = dojo.lang.find(this.editingAreaStyleSheets, url);
			if(index == -1){
				dojo.debug("dojo.widget.RichText.removeStyleSheet: Style sheet "+url+" is not applied to the editing area so it can not be removed!");
				return;
			}
			delete this.editingAreaStyleSheets[index];

			var links = this.document.getElementsByTagName("link");
			for(var i=0;i<links.length;i++){
				if(links[i].href == url){
					if(dojo.render.html.ie){//we need to empty the href first, to get IE to remove the rendered styles
						links[i].href="";
					}
					dojo.html.removeNode(links[i]);
					break;
				}
			}
		},

		_drawObject: function (/*String*/html) {
			// summary:
			//		Draws an active x object, used by IE
			this.object = dojo.html.createExternalElement(dojo.doc(), "object");

			with (this.object) {
				classid = "clsid:2D360201-FFF5-11D1-8D03-00A0C959BC0A";
				width = this.inheritWidth ? this._oldWidth : "100%";
				style.height = this.height ? this.height : (this._oldHeight+"px");
				Scrollbars = this.height ? true : false;
				Appearance = this._activeX.appearance.flat;
			}
			this.editorObject = this.object;
			this.editingArea.appendChild(this.object);

			this.object.attachEvent("DocumentComplete", dojo.lang.hitch(this, "onLoad"));
			//DisplayChanged is fired too often even no change is made, so we ignore it
			//and call onDisplayChanged manually in execCommand instead
//			this.object.attachEvent("DisplayChanged", dojo.lang.hitch(this, "onDisplayChanged"));

			dojo.lang.forEach(this.events, function(e){
				this.object.attachEvent(e.toLowerCase(), dojo.lang.hitch(this, e));
			}, this);

			this.object.DocumentHTML = '<!doctype HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">' +
				'<html><title></title>' +
				'<style type="text/css">' +
				'    body,html { padding: 0; margin: 0; }' + //font: ' + font + '; }' +
				(this.height ? '' : '    body,  { overflow: hidden; }') +
				'</style>' +
				//'<base href="' + dojo.global().location + '">' +
				'<body><div>' + html + '<div></body></html>';

			this._cacheLocalBlockFormatNames();
		},

		//static cache variables shared among all instance of this class
		_local2NativeFormatNames: {},
		_native2LocalFormatNames: {},
		//in IE, names for blockformat is locale dependent, so we cache the values here
		//we use activeX to obtain the list, if success or the names are already cached,
		//return true
		_cacheLocalBlockFormatNames: function(){
			// summary:
			//		in IE, names for blockformat is locale dependent, so we cache the values here
			//		we use activeX to obtain the list, if success or the names are already cached,
			//		return true
			if(!this._native2LocalFormatNames['p']){
				var obj = this.object;
				var error = false;
				if(!obj){
					//create obj temporarily
					try{
						obj = dojo.html.createExternalElement(dojo.doc(), "object");
						obj.classid = "clsid:2D360201-FFF5-11D1-8D03-00A0C959BC0A";
						dojo.body().appendChild(obj);
						obj.DocumentHTML = "<html><head></head><body></body></html>";
					}catch(e){ error = true; }
				}
				try{
					var oNamesParm = new ActiveXObject("DEGetBlockFmtNamesParam.DEGetBlockFmtNamesParam");
					obj.ExecCommand(this._activeX.command['getblockformatnames'], 0, oNamesParm);
					var vbNamesArray = new VBArray(oNamesParm.Names);
					var localFormats = vbNamesArray.toArray();
					var nativeFormats = ['p', 'pre', 'address', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'ol', 'ul', '', '', '','','div'];
					for(var i=0;i<nativeFormats.length;++i){
						if(nativeFormats[i].length>0){
							this._local2NativeFormatNames[localFormats[i]] = nativeFormats[i];
							this._native2LocalFormatNames[nativeFormats[i]] = localFormats[i];
						}
					}
				}catch(e){ error = true; }
				if(obj && !this.object){
					//delete the temporary obj
					dojo.body().removeChild(obj);
				}
			}
			return !error;
		},
	/* Event handlers
	 *****************/

		_isResized: function(){ return false; },

		onLoad: function(e){
			// summary: handler after the content of the document finishes loading
			this.isLoaded = true;
			if (this.object){
				this.document = this.object.DOM;
				this.window = this.document.parentWindow;
				this.editNode = this.document.body.firstChild;
				this.editingArea.style.height = this.height ? this.height : this.minHeight;
				if(!this.height){
					this.connect(this, "onDisplayChanged", "_updateHeight");
				}
				//pretend the object as an iframe, so that the context menu for the
				//editor can be placed correctly when shown
				this.window._frameElement = this.object;
			}else if (this.iframe && !dojo.render.html.ie){
				this.editNode = this.document.body;
				if(!this.height){
					this.connect(this, "onDisplayChanged", "_updateHeight");
				}

				try { // sanity check for Mozilla
					this.document.execCommand("useCSS", false, true); // old moz call
					this.document.execCommand("styleWithCSS", false, false); // new moz call
					//this.document.execCommand("insertBrOnReturn", false, false); // new moz call
				}catch(e2){ }

				if (dojo.render.html.safari) {
					/*
					this.iframe.style.visiblity = "visible";
					this.iframe.style.border = "1px solid black";
					this.editNode.style.visiblity = "visible";
					this.editNode.style.border = "1px solid black";
					*/
					// this.onDisplayChanged();
					this.connect(this.editNode, "onblur", "onBlur");
					this.connect(this.editNode, "onfocus", "onFocus");
					this.connect(this.editNode, "onclick", "onFocus");

					this.interval = setInterval(dojo.lang.hitch(this, "onDisplayChanged"), 750);
					// dojo.raise("onload");
					// dojo.debug(this.editNode.parentNode.parentNode.parentNode.nodeName);
				} else if (dojo.render.html.mozilla || dojo.render.html.opera) {
					var doc = this.document;
					var addListener = dojo.event.browser.addListener;
					var self = this;
					dojo.lang.forEach(this.events, function(e){
						var l = addListener(self.document, e.substr(2).toLowerCase(), dojo.lang.hitch(self, e));
						if(e=="onBlur"){
							// We need to unhook the blur event listener on close as we
							// can encounter a garunteed crash in FF if another event is
							// also fired
							var unBlur = { unBlur: function(e){
									dojo.event.browser.removeListener(doc, "blur", l);
							} };
							dojo.event.connect("before", self, "close", unBlur, "unBlur");
						}
					});
				}
				// FIXME: when scrollbars appear/disappear this needs to be fired
			}else if(dojo.render.html.ie){
				// IE contentEditable
				if(!this.height){
					this.connect(this, "onDisplayChanged", "_updateHeight");
				}
				this.editNode.style.zoom = 1.0;
			}

			this._applyEditingAreaStyleSheets();

			if(this.focusOnLoad){
				this.focus();
			}
			this.onDisplayChanged(e);
			if(this.onLoadDeferred){
				this.onLoadDeferred.callback(true);
			}
		},

		onKeyDown: function(e){
			// summary: Fired on keydown
			if((!e)&&(this.object)){
				e = dojo.event.browser.fixEvent(this.window.event);
			}
			// dojo.debug("onkeydown:", e.keyCode);
			// we need this event at the moment to get the events from control keys
			// such as the backspace. It might be possible to add this to Dojo, so that
			// keyPress events can be emulated by the keyDown and keyUp detection.
			if((dojo.render.html.ie)&&(e.keyCode == e.KEY_TAB)){
				e.preventDefault();
				e.stopPropagation();
				// FIXME: this is a poor-man's indent/outdent. It would be
				// better if it added 4 "&nbsp;" chars in an undoable way.
				// Unfortuantly pasteHTML does not prove to be undoable
				this.execCommand((e.shiftKey ? "outdent" : "indent"));
			}else if(dojo.render.html.ie){
				if((65 <= e.keyCode)&&(e.keyCode <= 90)){
					e.charCode = e.keyCode;
					this.onKeyPress(e);
				}
				// dojo.debug(e.ctrlKey);
				// dojo.debug(e.keyCode);
				// dojo.debug(e.charCode);
				// this.onKeyPress(e);
			}
		},

		onKeyUp: function(e){
			// summary: Fired on keyup
			return;
		},

		KEY_CTRL: 1,

		onKeyPress: function(e){
			// summary: Fired on keypress
			if((!e)&&(this.object)){
				e = dojo.event.browser.fixEvent(this.window.event);
			}
			// handle the various key events

			var modifiers = e.ctrlKey ? this.KEY_CTRL : 0;

			if (this._keyHandlers[e.key]) {
				// dojo.debug("char:", e.key);
				var handlers = this._keyHandlers[e.key], i = 0, handler;
				while (handler = handlers[i++]) {
					if (modifiers == handler.modifiers) {
						e.preventDefault();
						handler.handler.call(this);
						break;
					}
				}
			}

			// function call after the character has been inserted
			dojo.lang.setTimeout(this, this.onKeyPressed, 1, e);
		},

		addKeyHandler: function (/*String*/key, /*Int*/modifiers, /*Function*/handler) {
			// summary: add a handler for a keyboard shortcut
			if (!(this._keyHandlers[key] instanceof Array)) { this._keyHandlers[key] = []; }
			this._keyHandlers[key].push({
				modifiers: modifiers || 0,
				handler: handler
			});
		},

		onKeyPressed: function (e) {
			// summary:
			//		Fired after a keypress event has occured and it's action taken. This
		 	//		is useful if action needs to be taken after text operations have finished

			// Mozilla adds a single <p> with an embedded <br> when you hit enter once:
			//   <p><br>\n</p>
			// when you hit enter again it adds another <br> inside your enter
			//   <p><br>\n<br>\n</p>
			// and if you hit enter again it splits the <br>s over 2 <p>s
			//   <p><br>\n</p>\n<p><br>\n</p>
			// now this assumes that <p>s have double the line-height of <br>s to work
			// and so we need to remove the <p>s to ensure the position of the cursor
			// changes from the users perspective when they hit enter, as the second two
			// html snippets render the same when margins are set to 0.

			// TODO: doesn't really work; is this really needed?
			//if (dojo.render.html.moz) {
			//	for (var i = 0; i < this.document.getElementsByTagName("p").length; i++) {
			//		var p = this.document.getElementsByTagName("p")[i];
			//		if (p.innerHTML.match(/^<br>\s$/m)) {
			//			while (p.hasChildNodes()) { p.parentNode.insertBefore(p.firstChild, p); }
			//			p.parentNode.removeChild(p);
			//		}
			//	}
			//}
			this.onDisplayChanged(/*e*/); // can't pass in e
		},

		onClick: function(e){ this.onDisplayChanged(e); },
		onBlur: function(e){ },
		_initialFocus: true,
		onFocus: function(e){
			// summary: Fired on focus
			if( (dojo.render.html.mozilla)&&(this._initialFocus) ){
				this._initialFocus = false;
				if(dojo.string.trim(this.editNode.innerHTML) == "&nbsp;"){
					this.placeCursorAtStart();
//					this.execCommand("selectall");
//					this.window.getSelection().collapseToStart();
				}
			}
			/*
			// FIXME: attempted (but ultimately unworkable) solution for #2066
			if(dojo.render.html.safari){
				var sel = this.window.getSelection();
				dojo.debug(dojo.dom.isDescendantOf(sel.focusNode, this.document.body));

				var isLastNode = (sel.focusNode == this.document.body.lastChild);
				if(!isLastNode){ return; }
				var isText = (sel.focusNode.nodeType == 3);
				if(!isText){ return; }
				var isLastChar = (sel.focusOffset == sel.focusNode.nodeValue.length);
				if(!isLastChar){ return; }
				dojo.debug(sel.focusOffset, sel.focusNode.nodeValue.length);
				if(isLastNode && isText && isLastChar){
					this.document.body.appendChild(this.document.createTextNode(" "));
					this.document.body.appendChild(this.document.createElement("br"));
					this.document.body.appendChild(this.document.createElement("p"));
					this.document.body.appendChild(this.document.createElement("p"));
					sel.collapse(sel.focusNode, sel.focusOffset-1);
					sel.collapse(sel.focusNode, sel.focusOffset+1);
				}
			}
			*/
		},

		blur: function () {
			// summary: remove focus from this instance
			if(this.iframe) { this.window.blur(); }
			else if(this.object) { this.document.body.blur(); }
			else if(this.editNode) { this.editNode.blur(); }
		},

		focus: function () {
			// summary: move focus to this instance
			if(this.iframe && !dojo.render.html.ie) { this.window.focus(); }
			else if(this.object) { this.document.focus(); }
			// editNode may be hidden in display:none div, lets just punt in this case
			else if(this.editNode && this.editNode.focus) { this.editNode.focus(); }
			else{
				dojo.debug("Have no idea how to focus into the editor!");
			}
		},

		/** this event will be fired everytime the display context changes and the
		 result needs to be reflected in the UI */
		onDisplayChanged: function (e){ },


	/* Formatting commands
	 **********************/

		// Object: IE's Active X codes: see http://www.computerbytesman.com/js/activex/dhtmledit.htm
		_activeX: {
			command: {
				bold: 5000,
				italic: 5023,
				underline: 5048,

				justifycenter: 5024,
				justifyleft: 5025,
				justifyright: 5026,

				cut: 5003,
				copy: 5002,
				paste: 5032,
				"delete": 5004,

				undo: 5049,
				redo: 5033,

				removeformat: 5034,
				selectall: 5035,
				unlink: 5050,

				indent: 5018,
				outdent: 5031,

				insertorderedlist: 5030,
				insertunorderedlist: 5051,

				// table commands
				inserttable: 5022,
				insertcell: 5019,
				insertcol: 5020,
				insertrow: 5021,
				deletecells: 5005,
				deletecols: 5006,
				deleterows: 5007,
				mergecells: 5029,
				splitcell: 5047,

				// the command need mapping, they don't translate directly
				// to the contentEditable commands
				setblockformat: 5043,
				getblockformat: 5011,
				getblockformatnames: 5012,
				setfontname: 5044,
				getfontname: 5013,
				setfontsize: 5045,
				getfontsize: 5014,
				setbackcolor: 5042,
				getbackcolor: 5010,
				setforecolor: 5046,
				getforecolor: 5015,

				findtext: 5008,
				font: 5009,
				hyperlink: 5016,
				image: 5017,

				lockelement: 5027,
				makeabsolute: 5028,
				sendbackward: 5036,
				bringforward: 5037,
				sendbelowtext: 5038,
				bringabovetext: 5039,
				sendtoback: 5040,
				bringtofront: 5041,

				properties: 5052
			},

			ui: {
				"default": 0,
				prompt: 1,
				noprompt: 2
			},

			status: {
				notsupported: 0,
				disabled: 1,
				enabled: 3,
				latched: 7,
				ninched: 11
			},

			appearance: {
				flat: 0,
				inset: 1
			},

			state: {
				unchecked: 0,
				checked: 1,
				gray: 2
			}
		},

		_normalizeCommand: function (/*String*/cmd){
			// summary:
			//		Used as the advice function by dojo.event.connect to map our
		 	//		normalized set of commands to those supported by the target
		 	//		browser
			var drh = dojo.render.html;

			var command = cmd.toLowerCase();
			if(command == "formatblock"){
				if(drh.safari){ command = "heading"; }
			}else if(this.object){
				switch(command){
					case "createlink":
						command = "hyperlink";
						break;
					case "insertimage":
						command = "image";
						break;
				}
			}else if(command == "hilitecolor" && !drh.mozilla){
				command = "backcolor";
			}

			return command;
		},

		_safariIsLeopard: function(){
			var gt420 = false;
			if(dojo.render.html.safari){
				var tmp = dojo.render.html.UA.split("AppleWebKit/")[1];
				var ver = parseFloat(tmp.split(" ")[0]);
				if(ver >= 420){ gt420 = true; }
			}
			return gt420;
		},

		queryCommandAvailable: function (/*String*/command) {
			// summary:
			//		Tests whether a command is supported by the host. Clients SHOULD check
			//		whether a command is supported before attempting to use it, behaviour
			//		for unsupported commands is undefined.
			// command: The command to test for
			var ie = 1;
			var mozilla = 1 << 1;
			var safari = 1 << 2;
			var opera = 1 << 3;
			var safari420 = 1 << 4;

			var gt420 = this._safariIsLeopard();

			function isSupportedBy (browsers) {
				return {
					ie: Boolean(browsers & ie),
					mozilla: Boolean(browsers & mozilla),
					safari: Boolean(browsers & safari),
					safari420: Boolean(browsers & safari420),
					opera: Boolean(browsers & opera)
				}
			}

			var supportedBy = null;

			switch (command.toLowerCase()) {
				case "bold": case "italic": case "underline":
				case "subscript": case "superscript":
				case "fontname": case "fontsize":
				case "forecolor": case "hilitecolor":
				case "justifycenter": case "justifyfull": case "justifyleft":
				case "justifyright": case "delete": case "selectall":
					supportedBy = isSupportedBy(mozilla | ie | safari | opera);
					break;

				case "createlink": case "unlink": case "removeformat":
				case "inserthorizontalrule": case "insertimage":
				case "insertorderedlist": case "insertunorderedlist":
				case "indent": case "outdent": case "formatblock":
				case "inserthtml": case "undo": case "redo": case "strikethrough":
					supportedBy = isSupportedBy(mozilla | ie | opera | safari420);
					break;

				case "blockdirltr": case "blockdirrtl":
				case "dirltr": case "dirrtl":
				case "inlinedirltr": case "inlinedirrtl":
					supportedBy = isSupportedBy(ie);
					break;
				case "cut": case "copy": case "paste":
					supportedBy = isSupportedBy( ie | mozilla | safari420);
					break;

				case "inserttable":
					supportedBy = isSupportedBy(mozilla | (this.object ? ie : 0));
					break;

				case "insertcell": case "insertcol": case "insertrow":
				case "deletecells": case "deletecols": case "deleterows":
				case "mergecells": case "splitcell":
					supportedBy = isSupportedBy(this.object ? ie : 0);
					break;

				default: return false;
			}

			return (dojo.render.html.ie && supportedBy.ie) ||
				(dojo.render.html.mozilla && supportedBy.mozilla) ||
				(dojo.render.html.safari && supportedBy.safari) ||
				(gt420 && supportedBy.safari420) ||
				(dojo.render.html.opera && supportedBy.opera);  // Boolean return true if the command is supported, false otherwise
		},

		execCommand: function (/*String*/command, argument){
			// summary: Executes a command in the Rich Text area
			// command: The command to execute
			// argument: An optional argument to the command
			var returnValue;

			//focus() is required for IE (none-activeX mode) to work
			//In addition, focus() makes sure after the execution of
			//the command, the editor receives the focus as expected
			this.focus();

			command = this._normalizeCommand(command);
			if (argument != undefined) {
				if(command == "heading") { throw new Error("unimplemented"); }
				else if(command == "formatblock"){
					if(this.object){ //IE activeX mode
						argument = this._native2LocalFormatNames[argument];
					}
					else if(dojo.render.html.ie){ argument = '<'+argument+'>'; }
				}
			}
			if(this.object){
				switch (command) {
					case "hilitecolor":
						command = "setbackcolor";
						break;
					case "forecolor":
					case "backcolor":
					case "fontsize":
					case "fontname":
						command = "set" + command;
						break;
					case "formatblock":
						command = "setblockformat";
				}

				if(command == "strikethrough"){
					command = "inserthtml";
					var range = this.document.selection.createRange();
					if(!range.htmlText){
						return;
					}
					argument=range.htmlText.strike();
				}else if(command == "inserthorizontalrule"){
					command = "inserthtml";
					argument="<hr>";
				}

				if(command == "inserthtml"){
					var range = this.document.selection.createRange();
					if(this.document.selection.type.toUpperCase() == "CONTROL"){
						//if selection is controlrange, no pasteHTML is available,
						//we replace the outerHTML directly
						for(var i=0;i<range.length;i++){
							range.item(i).outerHTML = argument;
						}
					}else{
						// on IE, we can use the pasteHTML method of the textRange object
						// to get an undo-able innerHTML modification
						range.pasteHTML(argument);
						range.select();
					}
					returnValue = true;
				}else if(arguments.length == 1){
					returnValue = this.object.ExecCommand(this._activeX.command[command],
						this._activeX.ui.noprompt);
				}else{
					returnValue = this.object.ExecCommand(this._activeX.command[command],
						this._activeX.ui.noprompt, argument);
				}
			}else if(command == "inserthtml"){
				if(dojo.render.html.ie){
					//dojo.debug("inserthtml breaks the undo stack when not using the ActiveX version of the control!");
					var insertRange = this.document.selection.createRange();
					insertRange.pasteHTML(argument);
					insertRange.select();
					//insertRange.collapse(true);
					return true;
				}else{
					return this.document.execCommand(command, false, argument);
				}
			/* */
			// fix up unlink in Mozilla to unlink the link and not just the selection
			}else if((command == "unlink")&&
				(this.queryCommandEnabled("unlink"))&&
				(dojo.render.html.mozilla)){
				// grab selection
				// Mozilla gets upset if we just store the range so we have to
				// get the basic properties and recreate to save the selection
				var selection = this.window.getSelection();
				var selectionRange = selection.getRangeAt(0);
				var selectionStartContainer = selectionRange.startContainer;
				var selectionStartOffset = selectionRange.startOffset;
				var selectionEndContainer = selectionRange.endContainer;
				var selectionEndOffset = selectionRange.endOffset;

				// select our link and unlink
				var a = dojo.withGlobal(this.window, "getAncestorElement", dojo.html.selection, ['a']);
				dojo.withGlobal(this.window, "selectElement", dojo.html.selection, [a]);

				returnValue = this.document.execCommand("unlink", false, null);

				// restore original selection
				var selectionRange = this.document.createRange();
				selectionRange.setStart(selectionStartContainer, selectionStartOffset);
				selectionRange.setEnd(selectionEndContainer, selectionEndOffset);
				selection.removeAllRanges();
				selection.addRange(selectionRange);

				return returnValue;
			}else if((command == "hilitecolor")&&(dojo.render.html.mozilla)){
				// mozilla doesn't support hilitecolor properly when useCSS is
				// set to false (bugzilla #279330)

				this.document.execCommand("useCSS", false, false);
				returnValue = this.document.execCommand(command, false, argument);
				this.document.execCommand("useCSS", false, true);

			}else if((dojo.render.html.ie)&&( (command == "backcolor")||(command == "forecolor") )){
				// Tested under IE 6 XP2, no problem here, comment out
				// IE weirdly collapses ranges when we exec these commands, so prevent it
//				var tr = this.document.selection.createRange();
				argument = arguments.length > 1 ? argument : null;
				returnValue = this.document.execCommand(command, false, argument);

				// timeout is workaround for weird IE behavior were the text
				// selection gets correctly re-created, but subsequent input
				// apparently isn't bound to it
//				setTimeout(function(){tr.select();}, 1);
			}else{
				// dojo.debug("command:", command, "arg:", argument);

				argument = arguments.length > 1 ? argument : null;
//				if(dojo.render.html.moz){
//					this.document = this.iframe.contentWindow.document
//				}

				if(argument || command!="createlink") {
					returnValue = this.document.execCommand(command, false, argument);
				}
			}

			this.onDisplayChanged();
			return returnValue;
		},

		queryCommandEnabled: function(/*String*/command){
			// summary: check whether a command is enabled or not
			command = this._normalizeCommand(command);
			if(this.object){
				switch (command) {
					case "hilitecolor":
						command = "setbackcolor";
						break;
					case "forecolor":
					case "backcolor":
					case "fontsize":
					case "fontname":
						command = "set" + command;
						break;
					case "formatblock":
						command = "setblockformat";
						break;
					//below are not natively supported commands, we fake them
					case "strikethrough":
						command = "bold"; //whenever bold is enabled, strikethrough should be so as well
						break;
					case "inserthorizontalrule":
						return true;
				}

				if(typeof this._activeX.command[command] == "undefined"){ return false; }
				var status = this.object.QueryStatus(this._activeX.command[command]);
				return ((status != this._activeX.status.notsupported)&&
					(status != this._activeX.status.disabled));
			}else{
				if(dojo.render.html.mozilla){
					if(command == "unlink"){ // mozilla returns true always
						return dojo.withGlobal(this.window, "hasAncestorElement", dojo.html.selection, ['a']);
					} else if (command == "inserttable") {
						return true;
					}
				}

				// return this.document.queryCommandEnabled(command);
				var elem = (dojo.render.html.ie) ? this.document.selection.createRange() : this.document;
				return elem.queryCommandEnabled(command);
			}
		},

		queryCommandState: function(command){
			// summary: check the state of a given command
			command = this._normalizeCommand(command);
			if(this.object){
				if(command == "forecolor"){
					command = "setforecolor";
				}else if(command == "backcolor"){
					command = "setbackcolor";
				}else if(command == "strikethrough"){
					//check whether we are under a <strike>
					return dojo.withGlobal(this.window, "hasAncestorElement", dojo.html.selection, ['strike']);
				}else if(command == "inserthorizontalrule"){
					return false;
				}

				if(typeof this._activeX.command[command] == "undefined"){ return null; }
				var status = this.object.QueryStatus(this._activeX.command[command]);
				return ((status == this._activeX.status.latched)||
					(status == this._activeX.status.ninched));
			}else{
				return this.document.queryCommandState(command);
			}
		},

		queryCommandValue: function (command) {
			// summary: check the value of a given command
			command = this._normalizeCommand(command);
			if (this.object) {
				switch (command) {
					case "forecolor":
					case "backcolor":
					case "fontsize":
					case "fontname":
						command = "get" + command;
						return this.object.execCommand(
							this._activeX.command[command],
							this._activeX.ui.noprompt);
					case "formatblock":
						var retvalue = this.object.execCommand(
							this._activeX.command["getblockformat"],
							this._activeX.ui.noprompt);
						if(retvalue){
							return this._local2NativeFormatNames[retvalue];
						}
				}
			} else {
				if(dojo.render.html.ie && command == "formatblock"){
					return this._local2NativeFormatNames[this.document.queryCommandValue(command)] || this.document.queryCommandValue(command);
				}
				return this.document.queryCommandValue(command);
			}
		},


	/* Misc.
	 ********/

		placeCursorAtStart: function(){
			// summary:
			//		place the cursor at the start of the editing area
			this.focus();
			//see comments in placeCursorAtEnd
			if(dojo.render.html.moz && this.editNode.firstChild &&
				this.editNode.firstChild.nodeType != dojo.dom.TEXT_NODE){
				dojo.withGlobal(this.window, "selectElementChildren", dojo.html.selection, [this.editNode.firstChild]);
			}else{
				dojo.withGlobal(this.window, "selectElementChildren", dojo.html.selection, [this.editNode]);
			}
			dojo.withGlobal(this.window, "collapse", dojo.html.selection, [true]);
		},

		placeCursorAtEnd: function(){
			// summary:
			//		place the cursor at the end of the editing area
			this.focus();
			//In mozilla, if last child is not a text node, we have to use selectElementChildren on this.editNode.lastChild
			//otherwise the cursor would be placed at the end of the closing tag of this.editNode.lastChild
			if(dojo.render.html.moz && this.editNode.lastChild &&
				this.editNode.lastChild.nodeType != dojo.dom.TEXT_NODE){
				dojo.withGlobal(this.window, "selectElementChildren", dojo.html.selection, [this.editNode.lastChild]);
			}else{
				dojo.withGlobal(this.window, "selectElementChildren", dojo.html.selection, [this.editNode]);
			}
			dojo.withGlobal(this.window, "collapse", dojo.html.selection, [false]);
		},

		replaceEditorContent: function(/*String*/html){
			// summary:
			//		this function set the content while trying to maintain the undo stack
			html = this._preFilterContent(html);
			if(this.isClosed){
				this.domNode.innerHTML = html;
			}else if(this.window && this.window.getSelection && !dojo.render.html.moz){ // Safari
				// look ma! it's a totally f'd browser!
				this.editNode.innerHTML = html;
			}else if((this.window && this.window.getSelection) || (this.document && this.document.selection)){ // Moz/IE
				this.execCommand("selectall");
				if(dojo.render.html.moz && !html){ html = "&nbsp;" }
				this.execCommand("inserthtml", html);
			}
		},

		_preFilterContent: function(/*String*/html){
			// summary:
			//		filter the input before setting the content of the editing area
			var ec = html;
			dojo.lang.forEach(this.contentPreFilters, function(ef){
				ec = ef(ec);
			});
			if(this.contentDomPreFilters.length>0){
				var dom = dojo.doc().createElement('div');
				dom.style.display = "none";
				dojo.body().appendChild(dom);
				dom.innerHTML = ec;
				dojo.lang.forEach(this.contentDomPreFilters, function(ef){
					dom = ef(dom);
				});
				ec = dom.innerHTML;
				dojo.body().removeChild(dom);
			}
			return ec;
		},
		_postFilterContent: function(/*String*/html){
			// summary:
			//		filter the output after getting the content of the editing area
			var ec = html;
			if(this.contentDomPostFilters.length>0){
				var dom = this.document.createElement('div');
				dom.innerHTML = ec;
				dojo.lang.forEach(this.contentDomPostFilters, function(ef){
					dom = ef(dom);
				});
				ec = dom.innerHTML;
			}
			dojo.lang.forEach(this.contentPostFilters, function(ef){
				ec = ef(ec);
			});
			return ec;
		},

		//Int: stored last time height
		_lastHeight: 0,

		_updateHeight: function(){
			// summary:
			//		Updates the height of the editor area to fit the contents.
			if(!this.isLoaded){ return; }
			if(this.height){ return; }

			var height = dojo.html.getBorderBox(this.editNode).height;
			//height maybe zero in some cases even though the content is not empty,
			//we try the height of body instead
			if(!height){
				height = dojo.html.getBorderBox(this.document.body).height;
			}
			if(height == 0){
				dojo.debug("Can not figure out the height of the editing area!");
				return; //prevent setting height to 0
			}
			this._lastHeight = height;
			this.editorObject.style.height = this._lastHeight + "px";
			this.window.scrollTo(0, 0);
		},

		_saveContent: function(e){
			// summary:
			//		Saves the content in an onunload event if the editor has not been closed
			var saveTextarea = dojo.doc().getElementById("dojo.widget.RichText.savedContent");
			saveTextarea.value += this._SEPARATOR + this.saveName + ":" + this.getEditorContent();
		},

		getEditorContent: function(){
			// summary:
			//		return the current content of the editing area (post filters are applied)
			var ec = "";
			try{
				ec = (this._content.length > 0) ? this._content : this.editNode.innerHTML;
				if(dojo.string.trim(ec) == "&nbsp;"){ ec = ""; }
			}catch(e){ /* squelch */ }

			if(dojo.render.html.ie && !this.object){
				//removing appended <P>&nbsp;</P> for IE in none-activeX mode
				var re = new RegExp("(?:<p>&nbsp;</p>[\n\r]*)+$", "i");
				ec = ec.replace(re,"");
			}

			ec = this._postFilterContent(ec);

			if (this.relativeImageUrls) {
				// why use a regexp instead of dom? because IE is stupid
				// and won't let us set img.src to a relative URL
				// this comes after contentPostFilters because once content
				// gets innerHTML'd img urls will be fully qualified
				var siteBase = dojo.global().location.protocol + "//" + dojo.global().location.host;
				var pathBase = dojo.global().location.pathname;
				if (pathBase.match(/\/$/)) {
					// ends with slash, match full path
				} else {
					// match parent path to find siblings
					var pathParts = pathBase.split("/");
					if (pathParts.length) {
						pathParts.pop();
					}
					pathBase = pathParts.join("/") + "/";

				}

				var sameSite = new RegExp("(<img[^>]*\ src=[\"'])("+siteBase+"("+pathBase+")?)", "ig");
				ec = ec.replace(sameSite, "$1");
			}
			return ec;
		},

		close: function(/*Boolean*/save, /*Boolean*/force){
			// summary:
			//		Kills the editor and optionally writes back the modified contents to the
			//		element from which it originated.
			// save:
			//		Whether or not to save the changes. If false, the changes are discarded.
			// force:
			if(this.isClosed){return false; }

			if (arguments.length == 0) { save = true; }
			this._content = this._postFilterContent(this.editNode.innerHTML);
			var changed = (this.savedContent != this._content);

			// line height is squashed for iframes
			// FIXME: why was this here? if (this.iframe){ this.domNode.style.lineHeight = null; }

			if(this.interval){ clearInterval(this.interval); }

			if(dojo.render.html.ie && !this.object){
				dojo.event.browser.clean(this.editNode);
			}

			if (this.iframe) {
				// FIXME: should keep iframe around for later re-use
				delete this.iframe;
			}

			if(this.textarea){
				with(this.textarea.style){
					position = "";
					left = top = "";
					if(dojo.render.html.ie){
						overflow = this.__overflow;
						this.__overflow = null;
					}
				}
				if(save){
					this.textarea.value = this._content;
				}else{
					this.textarea.value = this.savedContent;
				}
				dojo.html.removeNode(this.domNode);
				this.domNode = this.textarea;
			}else{
				if(save){
					if(dojo.render.html.moz){
						var nc = dojo.doc().createElement("span");
						this.domNode.appendChild(nc);
						nc.innerHTML = this.editNode.innerHTML;
					}else{
						this.domNode.innerHTML = this._content;
					}
				}else{
					this.domNode.innerHTML = this.savedContent;
				}
			}

			dojo.html.removeClass(this.domNode, "RichTextEditable");
			this.isClosed = true;
			this.isLoaded = false;
			// FIXME: is this always the right thing to do?
			delete this.editNode;

			if(this.window._frameElement){
				this.window._frameElement = null;
			}

			this.window = null;
			this.document = null;
			this.object = null;
			this.editingArea = null;
			this.editorObject = null;

			return changed; // Boolean: whether the content has been modified
		},

		destroyRendering: function(){}, // stub!

		destroy: function (){
			this.destroyRendering();
			if(!this.isClosed){ this.close(false); }

			dojo.widget.RichText.superclass.destroy.call(this);
		},

		connect: function (targetObj, targetFunc, thisFunc) {
			// summary: convenient method for dojo.event.connect
			dojo.event.connect(targetObj, targetFunc, this, thisFunc);
		},

		disconnect: function (targetObj, targetFunc, thisFunc) {
			// summary: convenient method for dojo.event.disconnect
			dojo.event.disconnect(targetObj, targetFunc, this, thisFunc);
		},

		disconnectAllWithRoot: function (targetObj) {
			dojo.deprecated("disconnectAllWithRoot", "is deprecated. No need to disconnect manually", "0.5");
		},

		_fixContentForMoz: function(html){
			// summary:
			//		Moz can not handle strong/em tags correctly, correct them here
			html = html.replace(/<strong([ \>])/gi, '<b$1' );
			html = html.replace(/<\/strong>/gi, '<\/b>' );
			html = html.replace(/<em([ \>])/gi, '<i$1' );
			html = html.replace(/<\/em>/gi, '<\/i>' );
			return html;
		}
	}
);

dojo.provide("dojo.widget.ColorPalette");

dojo.widget.defineWidget(
	"dojo.widget.ColorPalette",
	dojo.widget.HtmlWidget,
{
	// summary
	//		Grid showing various colors, so the user pick a certain color

	// palette: String
	//		Size of grid, either "7x10" or "3x4".
	palette: "7x10",

	_palettes: {
		"7x10": [["fff", "fcc", "fc9", "ff9", "ffc", "9f9", "9ff", "cff", "ccf", "fcf"],
			["ccc", "f66", "f96", "ff6", "ff3", "6f9", "3ff", "6ff", "99f", "f9f"],
			["c0c0c0", "f00", "f90", "fc6", "ff0", "3f3", "6cc", "3cf", "66c", "c6c"],
			["999", "c00", "f60", "fc3", "fc0", "3c0", "0cc", "36f", "63f", "c3c"],
			["666", "900", "c60", "c93", "990", "090", "399", "33f", "60c", "939"],
			["333", "600", "930", "963", "660", "060", "366", "009", "339", "636"],
			["000", "300", "630", "633", "330", "030", "033", "006", "309", "303"]],

		"3x4": [["ffffff"/*white*/, "00ff00"/*lime*/, "008000"/*green*/, "0000ff"/*blue*/],
			["c0c0c0"/*silver*/, "ffff00"/*yellow*/, "ff00ff"/*fuchsia*/, "000080"/*navy*/],
			["808080"/*gray*/, "ff0000"/*red*/, "800080"/*purple*/, "000000"/*black*/]]
			//["00ffff"/*aqua*/, "808000"/*olive*/, "800000"/*maroon*/, "008080"/*teal*/]];
	},

	buildRendering: function () {
		this.domNode = document.createElement("table");
		dojo.html.disableSelection(this.domNode);
		dojo.event.connect(this.domNode, "onmousedown", function (e) {
			e.preventDefault();
		});
		with (this.domNode) { // set the table's properties
			cellPadding = "0"; cellSpacing = "1"; border = "1";
			style.backgroundColor = "white";
		}
		var colors = this._palettes[this.palette];
		for (var i = 0; i < colors.length; i++) {
			var tr = this.domNode.insertRow(-1);
			for (var j = 0; j < colors[i].length; j++) {
				if (colors[i][j].length == 3) {
					colors[i][j] = colors[i][j].replace(/(.)(.)(.)/, "$1$1$2$2$3$3");
				}

				var td = tr.insertCell(-1);
				with (td.style) {
					backgroundColor = "#" + colors[i][j];
					border = "1px solid gray";
					width = height = "15px";
					fontSize = "1px";
				}

				td.color = "#" + colors[i][j];

				td.onmouseover = function (e) { this.style.borderColor = "white"; }
				td.onmouseout = function (e) { this.style.borderColor = "gray"; }
				dojo.event.connect(td, "onmousedown", this, "onClick");

				td.innerHTML = "&nbsp;";
			}
		}
	},

	onClick: function(/*Event*/ e) {
		this.onColorSelect(e.currentTarget.color);
		e.currentTarget.style.borderColor = "gray";
	},

	onColorSelect: function(color){
		// summary:
		//		Callback when a color is selected.
		// color: String
		//		Hex value corresponding to color.
	}
});

/* TODO:
 * - font selector
 * - test, bug fix, more features :)
*/
dojo.provide("dojo.widget.Editor");
dojo.deprecated("dojo.widget.Editor", "is replaced by dojo.widget.Editor2", "0.5");


dojo.widget.tags.addParseTreeHandler("dojo:Editor");

dojo.widget.Editor = function() {
	dojo.widget.HtmlWidget.call(this);
	this.contentFilters = [];
	this._toolbars = [];
}
dojo.inherits(dojo.widget.Editor, dojo.widget.HtmlWidget);

dojo.widget.Editor.itemGroups = {
	textGroup: ["bold", "italic", "underline", "strikethrough"],
	blockGroup: ["formatBlock", "fontName", "fontSize"],
	justifyGroup: ["justifyleft", "justifycenter", "justifyright"],
	commandGroup: ["save", "cancel"],
	colorGroup: ["forecolor", "hilitecolor"],
	listGroup: ["insertorderedlist", "insertunorderedlist"],
	indentGroup: ["outdent", "indent"],
	linkGroup: ["createlink", "insertimage", "inserthorizontalrule"]
};

dojo.widget.Editor.formatBlockValues = {
	"Normal": "p",
	"Main heading": "h2",
	"Sub heading": "h3",
	"Sub sub heading": "h4",
	"Preformatted": "pre"
};

dojo.widget.Editor.fontNameValues = {
	"Arial": "Arial, Helvetica, sans-serif",
	"Verdana": "Verdana, sans-serif",
	"Times New Roman": "Times New Roman, serif",
	"Courier": "Courier New, monospace"
};

dojo.widget.Editor.fontSizeValues = {
	"1 (8 pt)" : "1",
	"2 (10 pt)": "2",
	"3 (12 pt)": "3",
	"4 (14 pt)": "4",
	"5 (18 pt)": "5",
	"6 (24 pt)": "6",
	"7 (36 pt)": "7"
};

dojo.widget.Editor.defaultItems = [
	"commandGroup", "|", "blockGroup", "|", "textGroup", "|", "colorGroup", "|", "justifyGroup", "|", "listGroup", "indentGroup", "|", "linkGroup"
];

// ones we support by default without asking the RichText component
// NOTE: you shouldn't put buttons like bold, italic, etc in here
dojo.widget.Editor.supportedCommands = ["save", "cancel", "|", "-", "/", " "];

dojo.lang.extend(dojo.widget.Editor, {
	widgetType: "Editor",

	saveUrl: "",
	saveMethod: "post",
	saveArgName: "editorContent",
	closeOnSave: false,
	items: dojo.widget.Editor.defaultItems,
	formatBlockItems: dojo.lang.shallowCopy(dojo.widget.Editor.formatBlockValues),
	fontNameItems: dojo.lang.shallowCopy(dojo.widget.Editor.fontNameValues),
	fontSizeItems: dojo.lang.shallowCopy(dojo.widget.Editor.fontSizeValues),

	// used to get the properties of an item if it is given as a string
	getItemProperties: function(name) {
		var props = {};
		switch(name.toLowerCase()) {
			case "bold":
			case "italic":
			case "underline":
			case "strikethrough":
				props.toggleItem = true;
				break;

			case "justifygroup":
				props.defaultButton = "justifyleft";
				props.preventDeselect = true;
				props.buttonGroup = true;
				break;

			case "listgroup":
				props.buttonGroup = true;
				break;

			case "save":
			case "cancel":
				props.label = dojo.string.capitalize(name);
				break;

			case "forecolor":
			case "hilitecolor":
				props.name = name;
				props.toggleItem = true; // FIXME: they aren't exactly toggle items
				props.icon = this.getCommandImage(name);
				break;

			case "formatblock":
				props.name = "formatBlock";
				props.values = this.formatBlockItems;
				break;

			case "fontname":
				props.name = "fontName";
				props.values = this.fontNameItems;

			case "fontsize":
				props.name = "fontSize";
				props.values = this.fontSizeItems;
		}
		return props;
	},

	validateItems: true, // set to false to add items, regardless of support
	focusOnLoad: true,
	minHeight: "1em",

	_richText: null, // RichText widget
	_richTextType: "RichText",

	_toolbarContainer: null, // ToolbarContainer widget
	_toolbarContainerType: "ToolbarContainer",

	_toolbars: [],
	_toolbarType: "Toolbar",

	_toolbarItemType: "ToolbarItem",

	buildRendering: function(args, frag) {
		// get the node from args/frag
		var node = frag["dojo:"+this.widgetType.toLowerCase()]["nodeRef"];
		var trt = dojo.widget.createWidget(this._richTextType, {
			focusOnLoad: this.focusOnLoad,
			minHeight: this.minHeight
		}, node)
		var _this = this;
		// this appears to fix a weird timing bug on Safari
		setTimeout(function(){
			_this.setRichText(trt);

			_this.initToolbar();

			_this.fillInTemplate(args, frag);
		}, 0);
	},

	setRichText: function(richText) {
		if(this._richText && this._richText == richText) {
			dojo.debug("Already set the richText to this richText!");
			return;
		}

		if(this._richText && !this._richText.isClosed) {
			dojo.debug("You are switching richTexts yet you haven't closed the current one. Losing reference!");
		}
		this._richText = richText;
		dojo.event.connect(this._richText, "close", this, "onClose");
		dojo.event.connect(this._richText, "onLoad", this, "onLoad");
		dojo.event.connect(this._richText, "onDisplayChanged", this, "updateToolbar");
		if(this._toolbarContainer) {
			this._toolbarContainer.enable();
			this.updateToolbar(true);
		}
	},

	initToolbar: function() {
		// var tic = new Date();
		if(this._toolbarContainer) { return; } // only create it once
		this._toolbarContainer = dojo.widget.createWidget(this._toolbarContainerType);
		var tb = this.addToolbar();
		var last = true;
		for(var i = 0; i < this.items.length; i++) {
			if(this.items[i] == "\n") { // new row
				tb = this.addToolbar();
			} else {
				if((this.items[i] == "|")&&(!last)){
					last = true;
				}else{
					last = this.addItem(this.items[i], tb);
				}
			}
		}
		this.insertToolbar(this._toolbarContainer.domNode, this._richText.domNode);
		// alert(new Date - tic);
	},

	// allow people to override this so they can make their own placement logic
	insertToolbar: function(tbNode, richTextNode) {
		dojo.html.insertBefore(tbNode, richTextNode);
		//dojo.html.insertBefore(this._toolbarContainer.domNode, this._richText.domNode);
	},

	addToolbar: function(toolbar) {
		this.initToolbar();
		if(!(toolbar instanceof dojo.widget.Toolbar)) {
			toolbar = dojo.widget.createWidget(this._toolbarType);
		}
		this._toolbarContainer.addChild(toolbar);
		this._toolbars.push(toolbar);
		return toolbar;
	},

	addItem: function(item, tb, dontValidate) {
		if(!tb) { tb = this._toolbars[0]; }
		var cmd = ((item)&&(!dojo.lang.isUndefined(item["getValue"]))) ?  cmd = item["getValue"](): item;

		var groups = dojo.widget.Editor.itemGroups;
		if(item instanceof dojo.widget.ToolbarItem) {
			tb.addChild(item);
		} else if(groups[cmd]) {
			var group = groups[cmd];
			var worked = true;
			if(cmd == "justifyGroup" || cmd == "listGroup") {
				var btnGroup = [cmd];
				for(var i = 0 ; i < group.length; i++) {
					if(dontValidate || this.isSupportedCommand(group[i])) {
						btnGroup.push(this.getCommandImage(group[i]));
					}else{
						worked = false;
					}
				}
				if(btnGroup.length){
					/*
					// the addChild interface is assinine. Work around it.
					var tprops = this.getItemProperties(cmd);
					var tmpGroup = dojo.widget.createWidget("ToolbarButtonGroup", tprops);
					dojo.debug(btnGroup);
					dojo.event.connect(tmpGroup, "onClick", this, "_action");
					dojo.event.connect(tmpGroup, "onChangeSelect", this, "_action");
					*/
					var btn = tb.addChild(btnGroup, null, this.getItemProperties(cmd));
					dojo.event.connect(btn, "onClick", this, "_action");
					dojo.event.connect(btn, "onChangeSelect", this, "_action");
				}
				return worked;
			} else {
				for(var i = 0; i < group.length; i++) {
					if(!this.addItem(group[i], tb)){
						worked = false;
					}
				}
				return worked;
			}
		} else {
			if((!dontValidate)&&(!this.isSupportedCommand(cmd))){
				return false;
			}
			if(dontValidate || this.isSupportedCommand(cmd)) {
				cmd = cmd.toLowerCase();
				if(cmd == "formatblock") {
					var select = dojo.widget.createWidget("ToolbarSelect", {
						name: "formatBlock",
						values: this.formatBlockItems
					});
					tb.addChild(select);
					var _this = this;
					dojo.event.connect(select, "onSetValue", function(item, value) {
						_this.onAction("formatBlock", value);
					});
				} else if(cmd == "fontname") {
					var select = dojo.widget.createWidget("ToolbarSelect", {
						name: "fontName",
						values: this.fontNameItems
					});
					tb.addChild(select);
					dojo.event.connect(select, "onSetValue", dojo.lang.hitch(this, function(item, value) {
						this.onAction("fontName", value);
					}));
				} else if(cmd == "fontsize") {
					var select = dojo.widget.createWidget("ToolbarSelect", {
						name: "fontSize",
						values: this.fontSizeItems
					});
					tb.addChild(select);
					dojo.event.connect(select, "onSetValue", dojo.lang.hitch(this, function(item, value) {
						this.onAction("fontSize", value);
					}));
				} else if(dojo.lang.inArray(cmd, ["forecolor", "hilitecolor"])) {
					var btn = tb.addChild(dojo.widget.createWidget("ToolbarColorDialog", this.getItemProperties(cmd)));
					dojo.event.connect(btn, "onSetValue", this, "_setValue");
				} else {
					var btn = tb.addChild(this.getCommandImage(cmd), null, this.getItemProperties(cmd));
					if(cmd == "save"){
						dojo.event.connect(btn, "onClick", this, "_save");
					}else if(cmd == "cancel"){
						dojo.event.connect(btn, "onClick", this, "_close");
					} else {
						dojo.event.connect(btn, "onClick", this, "_action");
						dojo.event.connect(btn, "onChangeSelect", this, "_action");
					}
				}
			}
		}
		return true;
	},

	enableToolbar: function() {
		if(this._toolbarContainer) {
			this._toolbarContainer.domNode.style.display = "";
			this._toolbarContainer.enable();
		}
	},

	disableToolbar: function(hide){
		if(hide){
			if(this._toolbarContainer){
				this._toolbarContainer.domNode.style.display = "none";
			}
		}else{
			if(this._toolbarContainer){
				this._toolbarContainer.disable();
			}
		}
	},

	_updateToolbarLastRan: null,
	_updateToolbarTimer: null,
	_updateToolbarFrequency: 500,

	updateToolbar: function(force) {
		if(!this._toolbarContainer) { return; }

		// keeps the toolbar from updating too frequently
		// TODO: generalize this functionality?
		var diff = new Date() - this._updateToolbarLastRan;
		if(!force && this._updateToolbarLastRan && (diff < this._updateToolbarFrequency)) {
			clearTimeout(this._updateToolbarTimer);
			var _this = this;
			this._updateToolbarTimer = setTimeout(function() {
				_this.updateToolbar();
			}, this._updateToolbarFrequency/2);
			return;
		} else {
			this._updateToolbarLastRan = new Date();
		}
		// end frequency checker

		var items = this._toolbarContainer.getItems();
		for(var i = 0; i < items.length; i++) {
			var item = items[i];
			if(item instanceof dojo.widget.ToolbarSeparator) { continue; }
			var cmd = item._name;
			if (cmd == "save" || cmd == "cancel") { continue; }
			else if(cmd == "justifyGroup") {
				try {
					if(!this._richText.queryCommandEnabled("justifyleft")) {
						item.disable(false, true);
					} else {
						item.enable(false, true);
						var jitems = item.getItems();
						for(var j = 0; j < jitems.length; j++) {
							var name = jitems[j]._name;
							var value = this._richText.queryCommandValue(name);
							if(typeof value == "boolean" && value) {
								value = name;
								break;
							} else if(typeof value == "string") {
								value = "justify"+value;
							} else {
								value = null;
							}
						}
						if(!value) { value = "justifyleft"; } // TODO: query actual style
						item.setValue(value, false, true);
					}
				} catch(err) {}
			} else if(cmd == "listGroup") {
				var litems = item.getItems();
				for(var j = 0; j < litems.length; j++) {
					this.updateItem(litems[j]);
				}
			} else {
				this.updateItem(item);
			}
		}
	},

	updateItem: function(item) {
		try {
			var cmd = item._name;
			var enabled = this._richText.queryCommandEnabled(cmd);
			item.setEnabled(enabled, false, true);

			var active = this._richText.queryCommandState(cmd);
			if(active && cmd == "underline") {
				// don't activate underlining if we are on a link
				active = !this._richText.queryCommandEnabled("unlink");
			}
			item.setSelected(active, false, true);
			return true;
		} catch(err) {
			return false;
		}
	},

	supportedCommands: dojo.widget.Editor.supportedCommands.concat(),

	isSupportedCommand: function(cmd) {
		// FIXME: how do we check for ActiveX?
		var yes = dojo.lang.inArray(cmd, this.supportedCommands);
		if(!yes) {
			try {
				var richText = this._richText || dojo.widget.HtmlRichText.prototype;
				yes = richText.queryCommandAvailable(cmd);
			} catch(E) {}
		}
		return yes;
	},

	getCommandImage: function(cmd) {
		if(cmd == "|") {
			return cmd;
		} else {
			return dojo.uri.dojoUri("src/widget/templates/buttons/" + cmd + ".gif");
		}
	},

	_action: function(e) {
		this._fire("onAction", e.getValue());
	},

	_setValue: function(a, b) {
		this._fire("onAction", a.getValue(), b);
	},

	_save: function(e){
		// FIXME: how should this behave when there's a larger form in play?
		if(!this._richText.isClosed){
			if(this.saveUrl.length){
				var content = {};
				content[this.saveArgName] = this.getHtml();
				dojo.io.bind({
					method: this.saveMethod,
					url: this.saveUrl,
					content: content
				});
			}else{
				dojo.debug("please set a saveUrl for the editor");
			}
			if(this.closeOnSave){
				this._richText.close(e.getName().toLowerCase() == "save");
			}
		}
	},

	_close: function(e) {
		if(!this._richText.isClosed) {
			this._richText.close(e.getName().toLowerCase() == "save");
		}
	},

	onAction: function(cmd, value) {
		switch(cmd) {
			case "createlink":
				if(!(value = prompt("Please enter the URL of the link:", "http://"))) {
					return;
				}
				break;
			case "insertimage":
				if(!(value = prompt("Please enter the URL of the image:", "http://"))) {
					return;
				}
				break;
		}
		this._richText.execCommand(cmd, value);
	},

	fillInTemplate: function(args, frag) {
		// dojo.event.connect(this, "onResized", this._richText, "onResized");
	},

	_fire: function(eventName) {
		if(dojo.lang.isFunction(this[eventName])) {
			var args = [];
			if(arguments.length == 1) {
				args.push(this);
			} else {
				for(var i = 1; i < arguments.length; i++) {
					args.push(arguments[i]);
				}
			}
			this[eventName].apply(this, args);
		}
	},

	getHtml: function(){
		this._richText.contentFilters = this._richText.contentFilters.concat(this.contentFilters);
		return this._richText.getEditorContent();
	},

	getEditorContent: function(){
		return this.getHtml();
	},

	onClose: function(save, hide){
		this.disableToolbar(hide);
		if(save) {
			this._fire("onSave");
		} else {
			this._fire("onCancel");
		}
	},

	// events baby!
	onLoad: function(){},
	onSave: function(){},
	onCancel: function(){}
});


dojo.provide("dojo.lang.type");

dojo.lang.whatAmI = function(value) {
	dojo.deprecated("dojo.lang.whatAmI", "use dojo.lang.getType instead", "0.5");
	return dojo.lang.getType(value);
}
dojo.lang.whatAmI.custom = {};

dojo.lang.getType = function(/* anything */ value){
	// summary: Attempts to determine what type value is.
	// value: Any literal value or object instance.
	try{
		if(dojo.lang.isArray(value)){
			return "array";	//	string
		}
		if(dojo.lang.isFunction(value)){
			return "function";	//	string
		}
		if(dojo.lang.isString(value)){
			return "string";	//	string
		}
		if(dojo.lang.isNumber(value)){
			return "number";	//	string
		}
		if(dojo.lang.isBoolean(value)){
			return "boolean";	//	string
		}
		if(dojo.lang.isAlien(value)){
			return "alien";	//	string
		}
		if(dojo.lang.isUndefined(value)){
			return "undefined";	//	string
		}
		// FIXME: should this go first?
		for(var name in dojo.lang.whatAmI.custom){
			if(dojo.lang.whatAmI.custom[name](value)){
				return name;	//	string
			}
		}
		if(dojo.lang.isObject(value)){
			return "object";	//	string
		}
	}catch(e){}
	return "unknown";	//	string
}

dojo.lang.isNumeric = function(/* anything */ value){
	// summary:
	//		Returns true if value can be interpreted as a number
	// value: Any literal value or object instance.
	// examples:
	//		dojo.lang.isNumeric(3);                 // returns true
	//		dojo.lang.isNumeric("3");               // returns true
	//		dojo.lang.isNumeric(new Number(3));     // returns true
	//		dojo.lang.isNumeric(new String("3"));   // returns true
	//
	//		dojo.lang.isNumeric(3/0);               // returns false
	//		dojo.lang.isNumeric("foo");             // returns false
	//		dojo.lang.isNumeric(new Number("foo")); // returns false
	//		dojo.lang.isNumeric(false);             // returns false
	//		dojo.lang.isNumeric(true);              // returns false
	return (!isNaN(value)
		&& isFinite(value)
		&& (value != null)
		&& !dojo.lang.isBoolean(value)
		&& !dojo.lang.isArray(value)
		&& !/^\s*$/.test(value)
	);	//	boolean
}

dojo.lang.isBuiltIn = function(/* anything */ value){
	// summary:
	//		Returns true if value is of a type provided by core JavaScript
	// description:
	//		Returns true for any literal, and for any object that is an
	//		instance of a built-in type like String, Number, Boolean, Array,
	//		Function, or Error.
	// value: Any literal value or object instance.

	return (dojo.lang.isArray(value)
		|| dojo.lang.isFunction(value)
		|| dojo.lang.isString(value)
		|| dojo.lang.isNumber(value)
		|| dojo.lang.isBoolean(value)
		|| (value == null)
		|| (value instanceof Error)
		|| (typeof value == "error")
	);	//	boolean
}

dojo.lang.isPureObject = function(/* anything */ value){
	// summary:
	//		Returns true for any value where the value of value.constructor ==
	//		Object
	// description:
	//		Returns true for any literal, and for any object that is an
	//		instance of a built-in type like String, Number, Boolean, Array,
	//		Function, or Error.
	// value:
	//		Any literal value or object instance.
	// examples:
	//		dojo.lang.isPureObject(new Object()); // returns true
	//		dojo.lang.isPureObject({a: 1, b: 2}); // returns true
	//
	//		dojo.lang.isPureObject(new Date());   // returns false
	//		dojo.lang.isPureObject([11, 2, 3]);   // returns false
	return ((value != null)
		&& dojo.lang.isObject(value)
		&& value.constructor == Object
	);	//	boolean
}

dojo.lang.isOfType = function(/* anything */ value, /* function */ type, /* object? */ keywordParameters) {
	/* summary:
	 *	 Returns true if 'value' is of type 'type'
	 * description:
	 *	 Given a value and a datatype, this method returns true if the
	 *	 type of the value matches the datatype. The datatype parameter
	 *	 can be an array of datatypes, in which case the method returns
	 *	 true if the type of the value matches any of the datatypes.
	 * value: Any literal value or object instance.
	 * type: A class of object, or a literal type, or the string name of a type, or an array with a list of types.
	 * keywordParameters: {optional: boolean}
	 */

	/* examples:
	 *   dojo.lang.isOfType("foo", String);                // returns true
	 *   dojo.lang.isOfType(12345, Number);                // returns true
	 *   dojo.lang.isOfType(false, Boolean);               // returns true
	 *   dojo.lang.isOfType([6, 8], Array);                // returns true
	 *   dojo.lang.isOfType(dojo.lang.isOfType, Function); // returns true
	 *   dojo.lang.isOfType({foo: "bar"}, Object);         // returns true
	 *   dojo.lang.isOfType(new Date(), Date);             // returns true
	 *
	 *   dojo.lang.isOfType("foo", "string");                // returns true
	 *   dojo.lang.isOfType(12345, "number");                // returns true
	 *   dojo.lang.isOfType(false, "boolean");               // returns true
	 *   dojo.lang.isOfType([6, 8], "array");                // returns true
	 *   dojo.lang.isOfType(dojo.lang.isOfType, "function"); // returns true
	 *   dojo.lang.isOfType({foo: "bar"}, "object");         // returns true
	 *   dojo.lang.isOfType(xxxxx, "undefined");             // returns true
	 *   dojo.lang.isOfType(null, "null");                   // returns true
	 *
	 *   dojo.lang.isOfType("foo", [Number, String, Boolean]); // returns true
	 *   dojo.lang.isOfType(12345, [Number, String, Boolean]); // returns true
	 *   dojo.lang.isOfType(false, [Number, String, Boolean]); // returns true
	 *
	 *   dojo.lang.isOfType(null, Date, {optional: true} );    // returns true	// description:
	 */
	var optional = false;
	if(keywordParameters){
		optional = keywordParameters["optional"];
	}
	if(optional && ((value === null) || dojo.lang.isUndefined(value))){
		return true;	//	boolean
	}
	if(dojo.lang.isArray(type)){
		var arrayOfTypes = type;
		for(var i in arrayOfTypes){
			var aType = arrayOfTypes[i];
			if(dojo.lang.isOfType(value, aType)){
				return true; 	//	boolean
			}
		}
		return false;	//	boolean
	}else{
		if(dojo.lang.isString(type)){
			type = type.toLowerCase();
		}
		switch (type) {
			case Array:
			case "array":
				return dojo.lang.isArray(value);	//	boolean
			case Function:
			case "function":
				return dojo.lang.isFunction(value);	//	boolean
			case String:
			case "string":
				return dojo.lang.isString(value);	//	boolean
			case Number:
			case "number":
				return dojo.lang.isNumber(value);	//	boolean
			case "numeric":
				return dojo.lang.isNumeric(value);	//	boolean
			case Boolean:
			case "boolean":
				return dojo.lang.isBoolean(value);	//	boolean
			case Object:
			case "object":
				return dojo.lang.isObject(value);	//	boolean
			case "pureobject":
				return dojo.lang.isPureObject(value);	//	boolean
			case "builtin":
				return dojo.lang.isBuiltIn(value);	//	boolean
			case "alien":
				return dojo.lang.isAlien(value);	//	boolean
			case "undefined":
				return dojo.lang.isUndefined(value);	//	boolean
			case null:
			case "null":
				return (value === null);	//	boolean
			case "optional":
				dojo.deprecated('dojo.lang.isOfType(value, [type, "optional"])', 'use dojo.lang.isOfType(value, type, {optional: true} ) instead', "0.5");
				return ((value === null) || dojo.lang.isUndefined(value));	//	boolean
			default:
				if(dojo.lang.isFunction(type)){
					return (value instanceof type);	//	boolean
				}else{
					dojo.raise("dojo.lang.isOfType() was passed an invalid type");
				}
		}
	}
	dojo.raise("If we get here, it means a bug was introduced above.");
}

dojo.lang.getObject=function(/* String */ str){
	// summary:
	//   Will return an object, if it exists, based on the name in the passed string.
	var parts=str.split("."), i=0, obj=dj_global;
	do{
		obj=obj[parts[i++]];
	}while(i<parts.length&&obj);
	return (obj!=dj_global)?obj:null;	//	Object
}

dojo.lang.doesObjectExist=function(/* String */ str){
	// summary:
	//   Check to see if object [str] exists, based on the passed string.
	var parts=str.split("."), i=0, obj=dj_global;
	do{
		obj=obj[parts[i++]];
	}while(i<parts.length&&obj);
	return (obj&&obj!=dj_global);	//	boolean
}

dojo.provide("dojo.lang.assert");


dojo.lang.assert = function(/* boolean */ booleanValue, /* string? */ message){
	/* summary:
	 *   Throws an exception if the assertion fails.
	 * description:
	 *   If the asserted condition is true, this method does nothing. If the
	 *   condition is false, we throw an error with a error message.
	 * booleanValue: Must be true for the assertion to succeed.
	 * message: A string describing the assertion.
	 */

	 // throws: Throws an Error if 'booleanValue' is false.
	 if(!booleanValue){
		var errorMessage = "An assert statement failed.\n" +
			"The method dojo.lang.assert() was called with a 'false' value.\n";
		if(message){
			errorMessage += "Here's the assert message:\n" + message + "\n";
		}
		// Use throw instead of dojo.raise, until bug #264 is fixed:
		// dojo.raise(errorMessage);
		throw new Error(errorMessage);
	}
}

dojo.lang.assertType = function(/* anything */ value, /* misc. */ type, /* object? */ keywordParameters){
	/* summary:
	 *   Throws an exception if 'value' is not of type 'type'
	 * description:
	 *   Given a value and a data type, this method checks the type of the value
	 *   to make sure it matches the data type, and throws an exception if there
	 *   is a mismatch.
	 * value: Any literal value or object instance.
	 * type: A class of object, or a literal type, or the string name of a type, or an array with a list of types.
	 * keywordParameters: {optional: boolean}
	 */

	/* examples:
	 *   dojo.lang.assertType("foo", String);
	 *   dojo.lang.assertType(12345, Number);
	 *   dojo.lang.assertType(false, Boolean);
	 *   dojo.lang.assertType([6, 8], Array);
	 *   dojo.lang.assertType(dojo.lang.assertType, Function);
	 *   dojo.lang.assertType({foo: "bar"}, Object);
	 *   dojo.lang.assertType(new Date(), Date);
	 *   dojo.lang.assertType(null, Array, {optional: true});
	 * throws: Throws an Error if 'value' is not of type 'type'.
	 */
	if (dojo.lang.isString(keywordParameters)) {
		dojo.deprecated('dojo.lang.assertType(value, type, "message")', 'use dojo.lang.assertType(value, type) instead', "0.5");
	}
	if(!dojo.lang.isOfType(value, type, keywordParameters)){
		if(!dojo.lang.assertType._errorMessage){
			dojo.lang.assertType._errorMessage = "Type mismatch: dojo.lang.assertType() failed.";
		}
		dojo.lang.assert(false, dojo.lang.assertType._errorMessage);
	}
}

dojo.lang.assertValidKeywords = function(/* object */ object, /* array */ expectedProperties, /* string? */ message){
	/* summary:
	 *   Throws an exception 'object' has any properties other than the 'expectedProperties'.
	 * description:
	 *   Given an anonymous object and a list of expected property names, this
	 *   method check to make sure the object does not have any properties
	 *   that aren't on the list of expected properties, and throws an Error
	 *   if there are unexpected properties. This is useful for doing error
	 *   checking on keyword arguments, to make sure there aren't typos.
	 * object: An anonymous object.
	 * expectedProperties: An array of strings (or an object with all the expected properties).
	 * message: A message describing the assertion.
	 */

	/* examples:
	 *   dojo.lang.assertValidKeywords({a: 1, b: 2}, ["a", "b"]);
	 *   dojo.lang.assertValidKeywords({a: 1, b: 2}, ["a", "b", "c"]);
	 *   dojo.lang.assertValidKeywords({foo: "iggy"}, ["foo"]);
	 *   dojo.lang.assertValidKeywords({foo: "iggy"}, ["foo", "bar"]);
	 *   dojo.lang.assertValidKeywords({foo: "iggy"}, {foo: null, bar: null});
	 * throws: Throws an Error if 'object' has unexpected properties.
	 */
	var key;
	if(!message){
		if(!dojo.lang.assertValidKeywords._errorMessage){
			dojo.lang.assertValidKeywords._errorMessage = "In dojo.lang.assertValidKeywords(), found invalid keyword:";
		}
		message = dojo.lang.assertValidKeywords._errorMessage;
	}
	if(dojo.lang.isArray(expectedProperties)){
		for(key in object){
			if(!dojo.lang.inArray(expectedProperties, key)){
				dojo.lang.assert(false, message + " " + key);
			}
		}
	}else{
		for(key in object){
			if(!(key in expectedProperties)){
				dojo.lang.assert(false, message + " " + key);
			}
		}
	}
}

dojo.provide("dojo.AdapterRegistry");

dojo.AdapterRegistry = function(/*Boolean?*/ returnWrappers){
	// summary:
	//		A registry to make contextual calling/searching easier.
	// description:
	//		Objects of this class keep list of arrays in the form [name, check,
	//		wrap, directReturn] that are used to determine what the contextual
	//		result of a set of checked arguments is. All check/wrap functions
	//		in this registry should be of the same arity.
	this.pairs = [];
	this.returnWrappers = returnWrappers || false;
}

dojo.lang.extend(dojo.AdapterRegistry, {
	register: function(name, check, /*Function*/ wrap, directReturn, override){
		// summary:
		//		register a check function to determine if the wrap function or
		//		object gets selected
		// name: String
		//		a way to identify this matcher.
		// check: Function
		//		a function that arguments are passed to from the adapter's
		//		match() function.  The check function should return true if the
		//		given arguments are appropriate for the wrap function.
		// directReturn: Boolean?
		//		If directReturn is true, the value passed in for wrap will be
		//		returned instead of being called. Alternately, the
		//		AdapterRegistry can be set globally to "return not call" using
		//		the returnWrappers property. Either way, this behavior allows
		//		the registry to act as a "search" function instead of a
		//		function interception library.
		// override: Boolean?
		//		If override is given and true, the check function will be given
		//		highest priority. Otherwise, it will be the lowest priority
		//		adapter.

		var type = (override) ? "unshift" : "push";
		this.pairs[type]([name, check, wrap, directReturn]);
	},

	match: function(/* ... */){
    // summary:
		//		Find an adapter for the given arguments. If no suitable adapter
		//		is found, throws an exception. match() accepts any number of
		//		arguments, all of which are passed to all matching functions
		//		from the registered pairs.
		for(var i = 0; i < this.pairs.length; i++){
			var pair = this.pairs[i];
			if(pair[1].apply(this, arguments)){
				if((pair[3])||(this.returnWrappers)){
					return pair[2];
				}else{
					return pair[2].apply(this, arguments);
				}
			}
		}
		throw new Error("No match found");
		// dojo.raise("No match found");
	},

	unregister: function(name){
		// summary: Remove a named adapter from the registry

		// FIXME: this is kind of a dumb way to handle this. On a large
		// registry this will be slow-ish and we can use the name as a lookup
		// should we choose to trade memory for speed.
		for(var i = 0; i < this.pairs.length; i++){
			var pair = this.pairs[i];
			if(pair[0] == name){
				this.pairs.splice(i, 1);
				return true;
			}
		}
		return false;
	}
});

dojo.provide("dojo.lang.repr");


dojo.lang.reprRegistry = new dojo.AdapterRegistry();
dojo.lang.registerRepr = function(/*String*/name, /*Function*/check, /*Function*/wrap, /*Boolean?*/override){
	// summary:
	//	Register a repr function.  repr functions should take
	//	one argument and return a string representation of it
	//	suitable for developers, primarily used when debugging.
	//
	//	If override is given, it is used as the highest priority
	//	repr, otherwise it will be used as the lowest.

	dojo.lang.reprRegistry.register(name, check, wrap, override);
};

dojo.lang.repr = function(/*Object*/obj){
	// summary: Return a "programmer representation" for an object
	// description: returns a string representation of an object suitable for developers, primarily used when debugging

	if(typeof(obj) == "undefined"){
		// obj: undefined
		return "undefined"; // String
	}else if(obj === null){
		// obj: null
		return "null"; // String
	}

	try{
		if(typeof(obj["__repr__"]) == 'function'){
			return obj["__repr__"]();
		}else if((typeof(obj["repr"]) == 'function')&&(obj.repr != arguments.callee)){
			return obj["repr"]();
		}
		return dojo.lang.reprRegistry.match(obj);
	}catch(e){
		if(typeof(obj.NAME) == 'string' && (
				obj.toString == Function.prototype.toString ||
				obj.toString == Object.prototype.toString
			)){
			return obj.NAME; // String
		}
	}

	if(typeof(obj) == "function"){
		// obj: Function
		obj = (obj + "").replace(/^\s+/, "");
		var idx = obj.indexOf("{");
		if(idx != -1){
			obj = obj.substr(0, idx) + "{...}";
		}
	}
	return obj + ""; // String
}

dojo.lang.reprArrayLike = function(/*Array*/arr){
	// summary: Maps each element of arr to dojo.lang.repr and provides output in an array-like format
	// description: returns an array-like string representation of the provided array suitable for developers, primarily used when debugging
	try{
		var na = dojo.lang.map(arr, dojo.lang.repr);
		return "[" + na.join(", ") + "]"; // String
	}catch(e){ }
};

(function(){
	var m = dojo.lang;
	m.registerRepr("arrayLike", m.isArrayLike, m.reprArrayLike);
	m.registerRepr("string", m.isString, m.reprString);
	m.registerRepr("numbers", m.isNumber, m.reprNumber);
	m.registerRepr("boolean", m.isBoolean, m.reprNumber);
	// m.registerRepr("numbers", m.typeMatcher("number", "boolean"), m.reprNumber);
})();

dojo.provide("dojo.lang.*");

dojo.provide("dojo.html.iframe");

// thanks burstlib!
dojo.html.iframeContentWindow = function(/* HTMLIFrameElement */iframe_el) {
	//	summary
	//	returns the window reference of the passed iframe
	var win = dojo.html.getDocumentWindow(dojo.html.iframeContentDocument(iframe_el)) ||
		// Moz. TODO: is this available when defaultView isn't?
		dojo.html.iframeContentDocument(iframe_el).__parent__ ||
		(iframe_el.name && document.frames[iframe_el.name]) || null;
	return win;	//	Window
}

dojo.html.iframeContentDocument = function(/* HTMLIFrameElement */iframe_el){
	//	summary
	//	returns a reference to the document object inside iframe_el
	var doc = iframe_el.contentDocument // W3
		|| ((iframe_el.contentWindow)&&(iframe_el.contentWindow.document))	// IE
		|| ((iframe_el.name)&&(document.frames[iframe_el.name])&&(document.frames[iframe_el.name].document))
		|| null;
	return doc;	//	HTMLDocument
}

dojo.html.BackgroundIframe = function(/* HTMLElement */node) {
	//	summary
	//	For IE z-index schenanigans
	//	Two possible uses:
	//	1. new dojo.html.BackgroundIframe(node)
	//		Makes a background iframe as a child of node, that fills area (and position) of node
	//	2. new dojo.html.BackgroundIframe()
	//		Attaches frame to dojo.body().  User must call size() to set size.
	if(dojo.render.html.ie55 || dojo.render.html.ie60) {
		var html="<iframe src='javascript:false'"
			+ " style='position: absolute; left: 0px; top: 0px; width: 100%; height: 100%;"
			+ "z-index: -1; filter:Alpha(Opacity=\"0\");' "
			+ ">";
		this.iframe = dojo.doc().createElement(html);
		this.iframe.tabIndex = -1; // Magic to prevent iframe from getting focus on tab keypress - as style didnt work.
		if(node){
			node.appendChild(this.iframe);
			this.domNode=node;
		}else{
			dojo.body().appendChild(this.iframe);
			this.iframe.style.display="none";
		}
	}
}
dojo.lang.extend(dojo.html.BackgroundIframe, {
	iframe: null,
	onResized: function(){
		//	summary
		//	Resize event handler.
		// TODO: this function shouldn't be necessary but setting width=height=100% doesn't work!
		if(this.iframe && this.domNode && this.domNode.parentNode){ // No parentElement if onResized() timeout event occurs on a removed domnode
			var outer = dojo.html.getMarginBox(this.domNode);
			if (outer.width  == 0 || outer.height == 0 ){
				dojo.lang.setTimeout(this, this.onResized, 100);
				return;
			}
			this.iframe.style.width = outer.width + "px";
			this.iframe.style.height = outer.height + "px";
		}
	},

	size: function(/* HTMLElement */node) {
		// summary:
		//		Call this function if the iframe is connected to dojo.body()
		//		rather than the node being shadowed

		//	(TODO: erase)
		if(!this.iframe){ return; }
		var coords = dojo.html.toCoordinateObject(node, true, dojo.html.boxSizing.BORDER_BOX);
		with(this.iframe.style){
			width = coords.width + "px";
			height = coords.height + "px";
			left = coords.left + "px";
			top = coords.top + "px";
		}
	},

	setZIndex: function(/* HTMLElement */node){
		//	summary
		//	Sets the z-index of the background iframe.
		if(!this.iframe){ return; }
		if(dojo.dom.isNode(node)){
			this.iframe.style.zIndex = dojo.html.getStyle(node, "z-index") - 1;
		}else if(!isNaN(node)){
			this.iframe.style.zIndex = node;
		}
	},

	show: function(){
		//	summary:
		//		show the iframe
		if(this.iframe){
			this.iframe.style.display = "block";
		}
	},

	hide: function(){
		//	summary:
		//		hide the iframe
		if(this.iframe){
			this.iframe.style.display = "none";
		}
	},

	remove: function(){
		//	summary:
		//		remove the iframe
		if(this.iframe){
			dojo.html.removeNode(this.iframe, true);
			delete this.iframe;
			this.iframe=null;
		}
	}
});

dojo.provide("dojo.widget.PopupContainer");


dojo.declare(
	"dojo.widget.PopupContainerBase",
	null,
	function(){
		this.queueOnAnimationFinish = [];
	},
{
	// summary:
	//		PopupContainerBase is the mixin class which provide popup behaviors:
	//		it can open in a given position x,y or around a given node.
	//		In addition, it handles animation and IE bleed through workaround.
	// description:
	//		This class can not be used standalone: it should be mixed-in to a
	//		dojo.widget.HtmlWidget. Use PopupContainer instead if you want a
	//		a standalone popup widget

	isContainer: true,
	templateString: '<div dojoAttachPoint="containerNode" style="display:none;position:absolute;" class="dojoPopupContainer" ></div>',

	// isShowingNow: Boolean: whether this popup is shown
	isShowingNow: false,

	// currentSubpopup: Widget: the shown sub popup if any
	currentSubpopup: null,

	// beginZIndex: Integer: the minimal popup zIndex
	beginZIndex: 1000,

	// parentPopup: Widget: parent popup widget
	parentPopup: null,

	// parent: Widget: the widget that caused me to be displayed; the logical parent.
	parent: null,

	// popupIndex: Integer: level of sub popup
	popupIndex: 0,

	// aroundBox: dojo.html.boxSizing: which bounding box to use for open aroundNode. By default use BORDER box of the aroundNode
	aroundBox: dojo.html.boxSizing.BORDER_BOX,

	// openedForWindow: Object: in which window the open() is triggered
	openedForWindow: null,

	processKey: function(/*Event*/evt){
		// summary: key event handler
		return false;
	},

	applyPopupBasicStyle: function(){
		// summary: apply necessary css rules to the top domNode
		// description:
		//		this function should be called in sub class where a custom
		//		templateString/templateStringPath is used (see Tooltip widget)
		with(this.domNode.style){
			display = 'none';
			position = 'absolute';
		}
	},

	aboutToShow: function() {
		// summary: connect to this stub to modify the content of the popup
	},

	open: function(/*Integer*/x, /*Integer*/y, /*DomNode*/parent, /*Object*/explodeSrc, /*String?*/orient, /*Array?*/padding){
		// summary:
		//		Open the popup at position (x,y), relative to dojo.body()
	 	//		Or open(node, parent, explodeSrc, aroundOrient) to open
	 	//		around node
		if (this.isShowingNow){ return; }

		// if I click right button and menu is opened, then it gets 2 commands: close -> open
		// so close enables animation and next "open" is put to queue to occur at new location
		if(this.animationInProgress){
			this.queueOnAnimationFinish.push(this.open, arguments);
			return;
		}

		this.aboutToShow();

		var around = false, node, aroundOrient;
		if(typeof x == 'object'){
			node = x;
			aroundOrient = explodeSrc;
			explodeSrc = parent;
			parent = y;
			around = true;
		}

		// save this so that the focus can be returned
		this.parent = parent;

		// for unknown reasons even if the domNode is attached to the body in postCreate(),
		// it's not attached here, so have to attach it here.
		dojo.body().appendChild(this.domNode);

		// if explodeSrc isn't specified then explode from my parent widget
		explodeSrc = explodeSrc || parent["domNode"] || [];

		//keep track of parent popup to decided whether this is a top level popup
		var parentPopup = null;
		this.isTopLevel = true;
		while(parent){
			if(parent !== this && (parent.setOpenedSubpopup != undefined && parent.applyPopupBasicStyle != undefined)){
				parentPopup = parent;
				this.isTopLevel = false;
				parentPopup.setOpenedSubpopup(this);
				break;
			}
			parent = parent.parent;
		}

		this.parentPopup = parentPopup;
		this.popupIndex = parentPopup ? parentPopup.popupIndex + 1 : 1;

		if(this.isTopLevel){
			var button = dojo.html.isNode(explodeSrc) ? explodeSrc : null;
			dojo.widget.PopupManager.opened(this, button);
		}

		//Store the current selection and restore it before the action for a menu item
		//is executed. This is required as clicking on an menu item deselects current selection
		if(this.isTopLevel && !dojo.withGlobal(this.openedForWindow||dojo.global(), dojo.html.selection.isCollapsed)){
			this._bookmark = dojo.withGlobal(this.openedForWindow||dojo.global(), dojo.html.selection.getBookmark);
		}else{
			this._bookmark = null;
		}

		//convert explodeSrc from format [x, y] to
		//{left: x, top: y, width: 0, height: 0} which is the new
		//format required by dojo.html.toCoordinateObject
		if(explodeSrc instanceof Array){
			explodeSrc = {left: explodeSrc[0], top: explodeSrc[1], width: 0, height: 0};
		}

		// display temporarily, and move into position, then hide again
		with(this.domNode.style){
			display="";
			zIndex = this.beginZIndex + this.popupIndex;
		}

		if(around){
			this.move(node, padding, aroundOrient);
		}else{
			this.move(x, y, padding, orient);
		}
		this.domNode.style.display="none";

		this.explodeSrc = explodeSrc;

		// then use the user defined method to display it
		this.show();

		this.isShowingNow = true;
	},

	// TODOC: move(node, padding, aroundOrient) how to do this?
	move: function(/*Int*/x, /*Int*/y, /*Integer?*/padding, /*String?*/orient){
		// summary: calculate where to place the popup

		var around = (typeof x == "object");
		if(around){
			var aroundOrient=padding;
			var node=x;
			padding=y;
			if(!aroundOrient){ //By default, attempt to open above the aroundNode, or below
				aroundOrient = {'BL': 'TL', 'TL': 'BL'};
			}
			dojo.html.placeOnScreenAroundElement(this.domNode, node, padding, this.aroundBox, aroundOrient);
		}else{
			if(!orient){ orient = 'TL,TR,BL,BR';}
			dojo.html.placeOnScreen(this.domNode, x, y, padding, true, orient);
		}
	},

	close: function(/*Boolean?*/force){
		// summary: hide the popup
		if(force){
			this.domNode.style.display="none";
		}

		// If we are in the process of opening the menu and we are asked to close it
		if(this.animationInProgress){
			this.queueOnAnimationFinish.push(this.close, []);
			return;
		}

		this.closeSubpopup(force);
		this.hide();
		if(this.bgIframe){
			this.bgIframe.hide();
			this.bgIframe.size({left: 0, top: 0, width: 0, height: 0});
		}
		if(this.isTopLevel){
			dojo.widget.PopupManager.closed(this);
		}
		this.isShowingNow = false;
		// return focus to the widget that opened the menu

		if(this.parent){
			setTimeout(
				dojo.lang.hitch(this,
					function(){
						try{
							if(this.parent['focus']){
								this.parent.focus();
							}else{
								this.parent.domNode.focus();
							}
						}catch(e){dojo.debug("No idea how to focus to parent", e);}
					}
				),
				10
			);
		}


		//do not need to restore if current selection is not empty
		//(use keyboard to select a menu item)
		if(this._bookmark && dojo.withGlobal(this.openedForWindow||dojo.global(), dojo.html.selection.isCollapsed)){
			if(this.openedForWindow){
				this.openedForWindow.focus()
			}
			try{
				dojo.withGlobal(this.openedForWindow||dojo.global(), "moveToBookmark", dojo.html.selection, [this._bookmark]);
			}catch(e){
				/*squelch IE internal error, see http://trac.dojotoolkit.org/ticket/1984 */
			}
		}
		this._bookmark = null;
	},

	closeAll: function(/*Boolean?*/force){
		// summary: hide all popups including sub ones
		if (this.parentPopup){
			this.parentPopup.closeAll(force);
		}else{
			this.close(force);
		}
	},

	setOpenedSubpopup: function(/*Widget*/popup) {
		// summary: used by sub popup to set currentSubpopup in the parent popup
		this.currentSubpopup = popup;
	},

	closeSubpopup: function(/*Boolean?*/force) {
		// summary: close opened sub popup
		if(this.currentSubpopup == null){ return; }

		this.currentSubpopup.close(force);
		this.currentSubpopup = null;
	},

	onShow: function() {
		dojo.widget.PopupContainer.superclass.onShow.apply(this, arguments);
		// With some animation (wipe), after close, the size of the domnode is 0
		// and next time when shown, the open() function can not determine
		// the correct place to popup, so we store the opened size here and
		// set it after close (in function onHide())
		this.openedSize={w: this.domNode.style.width, h: this.domNode.style.height};
		// prevent IE bleed through
		if(dojo.render.html.ie){
			if(!this.bgIframe){
				this.bgIframe = new dojo.html.BackgroundIframe();
				this.bgIframe.setZIndex(this.domNode);
			}

			this.bgIframe.size(this.domNode);
			this.bgIframe.show();
		}
		this.processQueue();
	},

	processQueue: function() {
		// summary: do events from queue
		if (!this.queueOnAnimationFinish.length) return;

		var func = this.queueOnAnimationFinish.shift();
		var args = this.queueOnAnimationFinish.shift();

		func.apply(this, args);
	},

	onHide: function() {
		dojo.widget.HtmlWidget.prototype.onHide.call(this);

		//restore size of the domnode, see comment in
		//function onShow()
		if(this.openedSize){
			with(this.domNode.style){
				width=this.openedSize.w;
				height=this.openedSize.h;
			}
		}

		this.processQueue();
	}
});

dojo.widget.defineWidget(
	"dojo.widget.PopupContainer",
	[dojo.widget.HtmlWidget, dojo.widget.PopupContainerBase], {
		// summary: dojo.widget.PopupContainer is the widget version of dojo.widget.PopupContainerBase
	});


dojo.widget.PopupManager = new function(){
	// summary:
	//		the popup manager makes sure we don't have several popups
	//		open at once. the root popup in an opening sequence calls
	//		opened(). when a root menu closes it calls closed(). then
	//		everything works. lovely.

	this.currentMenu = null;
	this.currentButton = null;		// button that opened current menu (if any)
	this.currentFocusMenu = null;	// the (sub)menu which receives key events
	this.focusNode = null;
	this.registeredWindows = [];

	this.registerWin = function(/*Window*/win){
		// summary: register a window so that when clicks/scroll in it, the popup can be closed automatically
		if(!win.__PopupManagerRegistered)
		{
			dojo.event.connect(win.document, 'onmousedown', this, 'onClick');
			dojo.event.connect(win, "onscroll", this, "onClick");
			dojo.event.connect(win.document, "onkey", this, 'onKey');
			win.__PopupManagerRegistered = true;
			this.registeredWindows.push(win);
		}
	};

	/*

	*/
	this.registerAllWindows = function(/*Window*/targetWindow){
		// summary:
		//		This function register all the iframes and the top window,
		//		so that whereever the user clicks in the page, the popup
		//		menu will be closed
		//		In case you add an iframe after onload event, please call
		//		dojo.widget.PopupManager.registerWin manually

		//starting from window.top, clicking everywhere in this page
		//should close popup menus
		if(!targetWindow) { //see comment below
			targetWindow = dojo.html.getDocumentWindow(window.top && window.top.document || window.document);
		}

		this.registerWin(targetWindow);

		for (var i = 0; i < targetWindow.frames.length; i++){
			try{
				//do not remove  dojo.html.getDocumentWindow, see comment in it
				var win = dojo.html.getDocumentWindow(targetWindow.frames[i].document);
				if(win){
					this.registerAllWindows(win);
				}
			}catch(e){ /* squelch error for cross domain iframes */ }
		}
	};

	this.unRegisterWin = function(/*Window*/win){
		// summary: remove listeners on the registered window
		if(win.__PopupManagerRegistered)
		{
			dojo.event.disconnect(win.document, 'onmousedown', this, 'onClick');
			dojo.event.disconnect(win, "onscroll", this, "onClick");
			dojo.event.disconnect(win.document, "onkey", this, 'onKey');
			win.__PopupManagerRegistered = false;
		}
	};

	this.unRegisterAllWindows = function(){
		// summary: remove listeners on all the registered windows
		for(var i=0;i<this.registeredWindows.length;++i){
			this.unRegisterWin(this.registeredWindows[i]);
		}
		this.registeredWindows = [];
	};

	dojo.addOnLoad(this, "registerAllWindows");
	dojo.addOnUnload(this, "unRegisterAllWindows");

	this.closed = function(/*Widget*/menu){
		// summary: notify the manager that menu is closed
		if (this.currentMenu == menu){
			this.currentMenu = null;
			this.currentButton = null;
			this.currentFocusMenu = null;
		}
	};

	this.opened = function(/*Widget*/menu, /*DomNode*/button){
		// summary: sets the current opened popup
		if (menu == this.currentMenu){ return; }

		if (this.currentMenu){
			this.currentMenu.close();
		}

		this.currentMenu = menu;
		this.currentFocusMenu = menu;
		this.currentButton = button;
	};

	this.setFocusedMenu = function(/*Widget*/menu){
		// summary:
		// 		Set the current focused popup, This is used by popups which supports keyboard navigation
		this.currentFocusMenu = menu;
	};

	this.onKey = function(/*Event*/e){
		if (!e.key) { return; }
		if(!this.currentMenu || !this.currentMenu.isShowingNow){ return; }

		var m = this.currentFocusMenu;
		while (m){
			if(m.processKey(e)){
				e.preventDefault();
				e.stopPropagation();
				break;
			}
			m = m.parentPopup;
		}
	},

	this.onClick = function(/*Event*/e){
		if (!this.currentMenu){ return; }

		var scrolloffset = dojo.html.getScroll().offset;

		// starting from the base menu, perform a hit test
		// and exit when one succeeds

		var m = this.currentMenu;

		while (m){
			if(dojo.html.overElement(m.domNode, e) || dojo.html.isDescendantOf(e.target, m.domNode)){
				return;
			}
			m = m.currentSubpopup;
		}

		// Also, if user clicked the button that opened this menu, then
		// that button will send the menu a close() command, so this code
		// shouldn't try to close the menu.  Closing twice messes up animation.
		if (this.currentButton && dojo.html.overElement(this.currentButton, e)){
			return;
		}

		// the click didn't fall within the open menu tree
		// so close it

		this.currentMenu.close();
	};
}

dojo.provide("dojo.widget.ContentPane");



dojo.widget.defineWidget(
	"dojo.widget.ContentPane",
	dojo.widget.HtmlWidget,
	function(){
		// summary:
		//		A widget that can be used as a standalone widget
		//		or as a baseclass for other widgets
		//		Handles replacement of document fragment using either external uri or javascript/java
		//		generated markup or DomNode content, instanciating widgets within content and runs scripts.
		//		Dont confuse it with an iframe, it only needs document fragments.
		//		It's useful as a child of LayoutContainer, SplitContainer, or TabContainer.
		//		But note that those classes can contain any widget as a child.
		// scriptScope: Function
		//		reference holder to the inline scripts container, if scriptSeparation is true
		// bindArgs: String[]
		//		Send in extra args to the dojo.io.bind call

		// per widgetImpl variables
		this._styleNodes =  [];
		this._onLoadStack = [];
		this._onUnloadStack = [];
		this._callOnUnload = false;
		this._ioBindObj;
		//	Note:
		//		dont change this value externally
		this.scriptScope; // undefined for now

		// loading option
		//	example:
		//		bindArgs="preventCache:false;" overrides cacheContent
		this.bindArgs = {};


	}, {
		isContainer: true,

		// loading options
		// adjustPaths: Boolean
		//		adjust relative paths in markup to fit this page
		adjustPaths: true,

		// href: String
		//		The href of the content that displays now
		//		Set this at construction if you want to load externally,
		//		changing href after creation doesnt have any effect, see setUrl
		href: "",

		// extractContent Boolean: Extract visible content from inside of <body> .... </body>
		extractContent: true,

		// parseContent Boolean: Construct all widgets that is in content
		parseContent:	true,

		// cacheContent Boolean: Cache content retreived externally
		cacheContent:	true,

		// preload: Boolean
		//		Force load of data even if pane is hidden.
		// Note:
		//		In order to delay download you need to initially hide the node it constructs from
		preload: false,

		// refreshOnShow: Boolean
		//		Refresh (re-download) content when pane goes from hidden to shown
		refreshOnShow: false,

		// handler: String||Function
		//		Generate pane content from a java function
		//		The name of the java proxy function
		handler: "",

		// executeScripts: Boolean
		//		Run scripts within content, extractContent has NO effect on this.
		// Note:
		//		if true scripts in content will be evaled after content is innerHTML'ed
		executeScripts: false,

		// scriptSeparation: Boolean
		//		Run scripts in a separate scope, unique for each ContentPane
		scriptSeparation: true,

		// loadingMessage: String
		//		Message that shows while downloading
		loadingMessage: "Loading...",

		// isLoaded: Boolean
		//		Tells loading status
		isLoaded: false,

		postCreate: function(args, frag, parentComp){
			if (this.handler!==""){
				this.setHandler(this.handler);
			}
			if(this.isShowing() || this.preload){
				this.loadContents();
			}
		},

		show: function(){
			// if refreshOnShow is true, reload the contents every time; otherwise, load only the first time
			if(this.refreshOnShow){
				this.refresh();
			}else{
				this.loadContents();
			}
			dojo.widget.ContentPane.superclass.show.call(this);
		},

		refresh: function(){
			// summary:
			//		Force a refresh (re-download) of content, be sure to turn of cache
			this.isLoaded=false;
			this.loadContents();
		},

		loadContents: function() {
			// summary:
			//		Download if isLoaded is false, else ignore
			if ( this.isLoaded ){
				return;
			}
			if ( dojo.lang.isFunction(this.handler)) {
				this._runHandler();
			} else if ( this.href != "" ) {
				this._downloadExternalContent(this.href, this.cacheContent && !this.refreshOnShow);
			}
		},

		setUrl: function(/*String||dojo.uri.Uri*/ url) {
			// summary:
			//		Reset the (external defined) content of this pane and replace with new url

			//	Note:
			//		It delays the download until widget is shown if preload is false
			this.href = url;
			this.isLoaded = false;
			if ( this.preload || this.isShowing() ){
				this.loadContents();
			}
		},

		abort: function(){
			// summary
			//		Aborts a inflight download of content
			var bind = this._ioBindObj;
			if(!bind || !bind.abort){ return; }
			bind.abort();
			delete this._ioBindObj;
		},

		_downloadExternalContent: function(url, useCache) {
			this.abort();
			this._handleDefaults(this.loadingMessage, "onDownloadStart");
			var self = this;
			this._ioBindObj = dojo.io.bind(
				this._cacheSetting({
					url: url,
					mimetype: "text/html",
					handler: function(type, data, xhr){
						delete self._ioBindObj; // makes sure abort doesnt clear cache
						if(type=="load"){
							self.onDownloadEnd.call(self, url, data);
						}else{
							// XHR isnt a normal JS object, IE doesnt have prototype on XHR so we cant extend it or shallowCopy it
							var e = {
								responseText: xhr.responseText,
								status: xhr.status,
								statusText: xhr.statusText,
								responseHeaders: xhr.getAllResponseHeaders(),
								text: "Error loading '" + url + "' (" + xhr.status + " "+  xhr.statusText + ")"
							};
							self._handleDefaults.call(self, e, "onDownloadError");
							self.onLoad();
						}
					}
				}, useCache)
			);
		},

		_cacheSetting: function(bindObj, useCache){
			for(var x in this.bindArgs){
				if(dojo.lang.isUndefined(bindObj[x])){
					bindObj[x] = this.bindArgs[x];
				}
			}

			if(dojo.lang.isUndefined(bindObj.useCache)){ bindObj.useCache = useCache; }
			if(dojo.lang.isUndefined(bindObj.preventCache)){ bindObj.preventCache = !useCache; }
			if(dojo.lang.isUndefined(bindObj.mimetype)){ bindObj.mimetype = "text/html"; }
			return bindObj;
		},

		onLoad: function(e){
			// summary:
			//		Event hook, is called after everything is loaded and widgetified
			this._runStack("_onLoadStack");
			this.isLoaded=true;
		},

		onUnLoad: function(e){
			// summary:
			//		Deprecated, use onUnload (lowercased load)
			dojo.deprecated(this.widgetType+".onUnLoad, use .onUnload (lowercased load)", 0.5);
		},

		onUnload: function(e){
			// summary:
			//		Event hook, is called before old content is cleared
			this._runStack("_onUnloadStack");
			delete this.scriptScope;
			// FIXME: remove for 0.5 along with onUnLoad
			if(this.onUnLoad !== dojo.widget.ContentPane.prototype.onUnLoad){
				this.onUnLoad.apply(this, arguments);
			}
		},

		_runStack: function(stName){
			var st = this[stName]; var err = "";
			var scope = this.scriptScope || window;
			for(var i = 0;i < st.length; i++){
				try{
					st[i].call(scope);
				}catch(e){
					err += "\n"+st[i]+" failed: "+e.description;
				}
			}
			this[stName] = [];

			if(err.length){
				var name = (stName== "_onLoadStack") ? "addOnLoad" : "addOnUnLoad";
				this._handleDefaults(name+" failure\n "+err, "onExecError", "debug");
			}
		},

		addOnLoad: function(obj, func){
			// summary
			//		Stores function refs and calls them one by one in the order they came in
			//		when load event occurs.
			//	obj: Function||Object?
			//		holder object
			//	func: Function
			//		function that will be called
			this._pushOnStack(this._onLoadStack, obj, func);
		},

		addOnUnload: function(obj, func){
			// summary
			//		Stores function refs and calls them one by one in the order they came in
			//		when unload event occurs.
			//	obj: Function||Object
			//		holder object
			//	func: Function
			//		function that will be called
			this._pushOnStack(this._onUnloadStack, obj, func);
		},

		addOnUnLoad: function(){
			// summary:
			//		Deprecated use addOnUnload (lower cased load)
			dojo.deprecated(this.widgetType + ".addOnUnLoad, use addOnUnload instead. (lowercased Load)", 0.5);
			this.addOnUnload.apply(this, arguments);
		},

		_pushOnStack: function(stack, obj, func){
			if(typeof func == 'undefined') {
				stack.push(obj);
			}else{
				stack.push(function(){ obj[func](); });
			}
		},

		destroy: function(){
			// make sure we call onUnload
			this.onUnload();
			dojo.widget.ContentPane.superclass.destroy.call(this);
		},

		onExecError: function(/*Object*/e){
			// summary:
			//		called when content script eval error or Java error occurs, preventDefault-able
			//		default is to debug not alert as in 0.3.1
		},

		onContentError: function(/*Object*/e){
			// summary:
			//		called on DOM faults, require fault etc in content, preventDefault-able
			//		default is to display errormessage inside pane
		},

		onDownloadError: function(/*Object*/e){
			// summary:
			//		called when download error occurs, preventDefault-able
			//		default is to display errormessage inside pane
		},

		onDownloadStart: function(/*Object*/e){
			// summary:
			//		called before download starts, preventDefault-able
			//		default is to display loadingMessage inside pane
			//		by changing e.text in your event handler you can change loading message
		},

		//
		onDownloadEnd: function(url, data){
			// summary:
			//		called when download is finished
			//
			//	url String: url that downloaded data
			//	data String: the markup that was downloaded
			data = this.splitAndFixPaths(data, url);
			this.setContent(data);
		},

		// useful if user wants to prevent default behaviour ie: _setContent("Error...")
		_handleDefaults: function(e, handler, messType){
			if(!handler){ handler = "onContentError"; }

			if(dojo.lang.isString(e)){ e = {text: e}; }

			if(!e.text){ e.text = e.toString(); }

			e.toString = function(){ return this.text; };

			if(typeof e.returnValue != "boolean"){
				e.returnValue = true;
			}
			if(typeof e.preventDefault != "function"){
				e.preventDefault = function(){ this.returnValue = false; };
			}
			// call our handler
			this[handler](e);
			if(e.returnValue){
				switch(messType){
					case true: // fallthrough, old compat
					case "alert":
						alert(e.toString()); break;
					case "debug":
						dojo.debug(e.toString()); break;
					default:
					// makes sure scripts can clean up after themselves, before we setContent
					if(this._callOnUnload){ this.onUnload(); }
					// makes sure we dont try to call onUnLoad again on this event,
					// ie onUnLoad before 'Loading...' but not before clearing 'Loading...'
					this._callOnUnload = false;

					// we might end up in a endless recursion here if domNode cant append content
					if(arguments.callee._loopStop){
						dojo.debug(e.toString());
					}else{
						arguments.callee._loopStop = true;
						this._setContent(e.toString());
					}
				}
			}
			arguments.callee._loopStop = false;
		},

		// pathfixes, require calls, css stuff and neccesary content clean
		splitAndFixPaths: function(s, url){
			// summary:
			// 		adjusts all relative paths in (hopefully) all cases, images, remote scripts, links etc.
			// 		splits up content in different pieces, scripts, title, style, link and whats left becomes .xml
			//	s String:	The markup in string
			//	url (String||dojo.uri.Uri?) url that pulled in markup

			var titles = [], scripts = [],tmp = [];// init vars
			var match = [], requires = [], attr = [], styles = [];
			var str = '', path = '', fix = '', tagFix = '', tag = '', origPath = '';

			if(!url) { url = "./"; } // point to this page if not set

			if(s){ // make sure we dont run regexes on empty content

				/************** <title> ***********/
				// khtml is picky about dom faults, you can't attach a <style> or <title> node as child of body
				// must go into head, so we need to cut out those tags
				var regex = /<title[^>]*>([\s\S]*?)<\/title>/i;
				while(match = regex.exec(s)){
					titles.push(match[1]);
					s = s.substring(0, match.index) + s.substr(match.index + match[0].length);
				};

				/************** adjust paths *****************/
				if(this.adjustPaths){
					// attributepaths one tag can have multiple paths example:
					// <input src="..." style="url(..)"/> or <a style="url(..)" href="..">
					// strip out the tag and run fix on that.
					// this guarantees that we won't run replace on another tag's attribute + it was easier do
					var regexFindTag = /<[a-z][a-z0-9]*[^>]*\s(?:(?:src|href|style)=[^>])+[^>]*>/i;
					var regexFindAttr = /\s(src|href|style)=(['"]?)([\w()\[\]\/.,\\'"-:;#=&?\s@]+?)\2/i;
					// these are the supported protocols, all other is considered relative
					var regexProtocols = /^(?:[#]|(?:(?:https?|ftps?|file|javascript|mailto|news):))/;

					while(tag = regexFindTag.exec(s)){
						str += s.substring(0, tag.index);
						s = s.substring((tag.index + tag[0].length), s.length);
						tag = tag[0];

						// loop through attributes
						tagFix = '';
						while(attr = regexFindAttr.exec(tag)){
							path = ""; origPath = attr[3];
							switch(attr[1].toLowerCase()){
								case "src":// falltrough
								case "href":
									if(regexProtocols.exec(origPath)){
										path = origPath;
									} else {
										path = (new dojo.uri.Uri(url, origPath).toString());
									}
									break;
								case "style":// style
									path = dojo.html.fixPathsInCssText(origPath, url);
									break;
								default:
									path = origPath;
							}
							fix = " " + attr[1] + "=" + attr[2] + path + attr[2];
							// slices up tag before next attribute check
							tagFix += tag.substring(0, attr.index) + fix;
							tag = tag.substring((attr.index + attr[0].length), tag.length);
						}
						str += tagFix + tag; //dojo.debug(tagFix + tag);
					}
					s = str+s;
				}

				/****************  cut out all <style> and <link rel="stylesheet" href=".."> **************/
				regex = /(?:<(style)[^>]*>([\s\S]*?)<\/style>|<link ([^>]*rel=['"]?stylesheet['"]?[^>]*)>)/i;
				while(match = regex.exec(s)){
					if(match[1] && match[1].toLowerCase() == "style"){
						styles.push(dojo.html.fixPathsInCssText(match[2],url));
					}else if(attr = match[3].match(/href=(['"]?)([^'">]*)\1/i)){
						styles.push({path: attr[2]});
					}
					s = s.substring(0, match.index) + s.substr(match.index + match[0].length);
				};

				/***************** cut out all <script> tags, push them into scripts array ***************/
				var regex = /<script([^>]*)>([\s\S]*?)<\/script>/i;
				var regexSrc = /src=(['"]?)([^"']*)\1/i;
				var regexDojoJs = /.*(\bdojo\b\.js(?:\.uncompressed\.js)?)$/;
				var regexInvalid = /(?:var )?\bdjConfig\b(?:[\s]*=[\s]*\{[^}]+\}|\.[\w]*[\s]*=[\s]*[^;\n]*)?;?|dojo\.hostenv\.writeIncludes\(\s*\);?/g;
				var regexRequires = /dojo\.(?:(?:require(?:After)?(?:If)?)|(?:widget\.(?:manager\.)?registerWidgetPackage)|(?:(?:hostenv\.)?setModulePrefix|registerModulePath)|defineNamespace)\((['"]).*?\1\)\s*;?/;

				while(match = regex.exec(s)){
					if(this.executeScripts && match[1]){
						if(attr = regexSrc.exec(match[1])){
							// remove a dojo.js or dojo.js.uncompressed.js from remoteScripts
							// we declare all files named dojo.js as bad, regardless of path
							if(regexDojoJs.exec(attr[2])){
								dojo.debug("Security note! inhibit:"+attr[2]+" from  being loaded again.");
							}else{
								scripts.push({path: attr[2]});
							}
						}
					}
					if(match[2]){
						// remove all invalid variables etc like djConfig and dojo.hostenv.writeIncludes()
						var sc = match[2].replace(regexInvalid, "");
						if(!sc){ continue; }

						// cut out all dojo.require (...) calls, if we have execute
						// scripts false widgets dont get there require calls
						// takes out possible widgetpackage registration as well
						while(tmp = regexRequires.exec(sc)){
							requires.push(tmp[0]);
							sc = sc.substring(0, tmp.index) + sc.substr(tmp.index + tmp[0].length);
						}
						if(this.executeScripts){
							scripts.push(sc);
						}
					}
					s = s.substr(0, match.index) + s.substr(match.index + match[0].length);
				}

				/********* extract content *********/
				if(this.extractContent){
					match = s.match(/<body[^>]*>\s*([\s\S]+)\s*<\/body>/im);
					if(match) { s = match[1]; }
				}

				/*** replace scriptScope prefix in html Event handler
				* working order: find tags with scriptScope in a tag attribute
				* then replace all standalone scriptScope occurencies with reference to to this widget
				* valid onClick="scriptScope.func()" or onClick="scriptScope['func']();scriptScope.i++"
				* not valid onClick="var.scriptScope.ref" nor onClick="var['scriptScope'].ref" */
				if(this.executeScripts && this.scriptSeparation){
					var regex = /(<[a-zA-Z][a-zA-Z0-9]*\s[^>]*?\S=)((['"])[^>]*scriptScope[^>]*>)/;
					var regexAttr = /([\s'";:\(])scriptScope(.*)/; // we rely on that attribute begins ' or "
					str = "";
					while(tag = regex.exec(s)){
						tmp = ((tag[3]=="'") ? '"': "'");fix= "";
						str += s.substring(0, tag.index) + tag[1];
						while(attr = regexAttr.exec(tag[2])){
							tag[2] = tag[2].substring(0, attr.index) + attr[1] + "dojo.widget.byId("+ tmp + this.widgetId + tmp + ").scriptScope" + attr[2];
						}
						str += tag[2];
						s = s.substr(tag.index + tag[0].length);
					}
					s = str + s;
				}
	 		}

			return {"xml": 		s, // Object
				"styles":		styles,
				"titles": 		titles,
				"requires": 	requires,
				"scripts": 		scripts,
				"url": 			url};
		},


		_setContent: function(cont){
			this.destroyChildren();

			// remove old stylenodes from HEAD
			for(var i = 0; i < this._styleNodes.length; i++){
				if(this._styleNodes[i] && this._styleNodes[i].parentNode){
					this._styleNodes[i].parentNode.removeChild(this._styleNodes[i]);
				}
			}
			this._styleNodes = [];

			try{
				var node = this.containerNode || this.domNode;
				while(node.firstChild){
					dojo.html.destroyNode(node.firstChild);
				}
				if(typeof cont != "string"){
					node.appendChild(cont);
				}else{
					node.innerHTML = cont;
				}
			}catch(e){
				e.text = "Couldn't load content:"+e.description;
				this._handleDefaults(e, "onContentError");
			}
		},

		setContent: function(data){
			// summary:
			//		Replaces old content with data content, include style classes from old content
			//	data String||DomNode:	new content, be it Document fragment or a DomNode chain
			//			If data contains style tags, link rel=stylesheet it inserts those styles into DOM
			this.abort();
			if(this._callOnUnload){ this.onUnload(); }// this tells a remote script clean up after itself
			this._callOnUnload = true;

			if(!data || dojo.html.isNode(data)){
				// if we do a clean using setContent(""); or setContent(#node) bypass all parsing, extractContent etc
				this._setContent(data);
				this.onResized();
				this.onLoad();
			}else{
				// need to run splitAndFixPaths? ie. manually setting content
				// adjustPaths is taken care of inside splitAndFixPaths
				if(typeof data.xml != "string"){
					this.href = ""; // so we can refresh safely
					data = this.splitAndFixPaths(data);
				}

				this._setContent(data.xml);

				// insert styles from content (in same order they came in)
				for(var i = 0; i < data.styles.length; i++){
					if(data.styles[i].path){
						this._styleNodes.push(dojo.html.insertCssFile(data.styles[i].path, dojo.doc(), false, true));
					}else{
						this._styleNodes.push(dojo.html.insertCssText(data.styles[i]));
					}
				}

				if(this.parseContent){
					for(var i = 0; i < data.requires.length; i++){
						try{
							eval(data.requires[i]);
						} catch(e){
							e.text = "ContentPane: error in package loading calls, " + (e.description||e);
							this._handleDefaults(e, "onContentError", "debug");
						}
					}
				}
				// need to allow async load, Xdomain uses it
				// is inline function because we cant send args to dojo.addOnLoad
				var _self = this;
				function asyncParse(){
					if(_self.executeScripts){
						_self._executeScripts(data.scripts);
					}

					if(_self.parseContent){
						var node = _self.containerNode || _self.domNode;
						var parser = new dojo.xml.Parse();
						var frag = parser.parseElement(node, null, true);
						// createSubComponents not createComponents because frag has already been created
						dojo.widget.getParser().createSubComponents(frag, _self);
					}

					_self.onResized();
					_self.onLoad();
				}
				// try as long as possible to make setContent sync call
				if(dojo.hostenv.isXDomain && data.requires.length){
					dojo.addOnLoad(asyncParse);
				}else{
					asyncParse();
				}
			}
		},

		setHandler: function(/*Function*/ handler) {
			// summary:
			//		Generate pane content from given java function
			var fcn = dojo.lang.isFunction(handler) ? handler : window[handler];
			if(!dojo.lang.isFunction(fcn)) {
				// FIXME: needs testing! somebody with java knowledge needs to try this
				this._handleDefaults("Unable to set handler, '" + handler + "' not a function.", "onExecError", true);
				return;
			}
			this.handler = function() {
				return fcn.apply(this, arguments);
			}
		},

		_runHandler: function() {
			var ret = true;
			if(dojo.lang.isFunction(this.handler)) {
				this.handler(this, this.domNode);
				ret = false;
			}
			this.onLoad();
			return ret;
		},

		_executeScripts: function(scripts) {
			// loop through the scripts in the order they came in
			var self = this;
			var tmp = "", code = "";
			for(var i = 0; i < scripts.length; i++){
				if(scripts[i].path){ // remotescript
					dojo.io.bind(this._cacheSetting({
						"url": 		scripts[i].path,
						"load":     function(type, scriptStr){
								dojo.lang.hitch(self, tmp = ";"+scriptStr);
						},
						"error":    function(type, error){
								error.text = type + " downloading remote script";
								self._handleDefaults.call(self, error, "onExecError", "debug");
						},
						"mimetype": "text/plain",
						"sync":     true
					}, this.cacheContent));
					code += tmp;
				}else{
					code += scripts[i];
				}
			}


			try{
				if(this.scriptSeparation){
					// initialize a new anonymous container for our script, dont make it part of this widgets scope chain
					// instead send in a variable that points to this widget, useful to connect events to onLoad, onUnload etc..
					delete this.scriptScope;
					this.scriptScope = new (new Function('_container_', code+'; return this;'))(self);
				}else{
					// exec in global, lose the _container_ feature
					var djg = dojo.global();
					if(djg.execScript){
						djg.execScript(code);
					}else{
						var djd = dojo.doc();
						var sc = djd.createElement("script");
						sc.appendChild(djd.createTextNode(code));
						(this.containerNode||this.domNode).appendChild(sc);
					}
				}
			}catch(e){
				e.text = "Error running scripts from content:\n"+e.description;
				this._handleDefaults(e, "onExecError", "debug");
			}
		}
	}
);

dojo.provide("dojo.widget.Editor2Toolbar");


dojo.lang.declare("dojo.widget.HandlerManager", null,
	function(){
		this._registeredHandlers=[];
	},
{
	// summary: internal base class for handler function management
	registerHandler: function(/*Object*/obj, /*String*/func){
		// summary: register a handler
		// obj: object which has the function to call
		// func: the function in the object
		if(arguments.length == 2){
			this._registeredHandlers.push(function(){return obj[func].apply(obj, arguments);});
		}else{
			/* obj: Function
			    func: null
			    pId: f */
			this._registeredHandlers.push(obj);
		}
	},
	removeHandler: function(func){
		// summary: remove a registered handler
		for(var i=0;i<this._registeredHandlers.length;i++){
			if(func === this._registeredHandlers[i]){
				delete this._registeredHandlers[i];
				return;
			}
		}
		dojo.debug("HandlerManager handler "+func+" is not registered, can not remove.");
	},
	destroy: function(){
		for(var i=0;i<this._registeredHandlers.length;i++){
			delete this._registeredHandlers[i];
		}
	}
});

dojo.widget.Editor2ToolbarItemManager = new dojo.widget.HandlerManager;
dojo.lang.mixin(dojo.widget.Editor2ToolbarItemManager,
{
	getToolbarItem: function(/*String*/name){
		// summary: return a toobar item with the given name
		var item;
		name = name.toLowerCase();
		for(var i=0;i<this._registeredHandlers.length;i++){
			item = this._registeredHandlers[i](name);
			if(item){
				return item;
			}
		}

		switch(name){
			//button for builtin functions
			case 'bold':
			case 'copy':
			case 'cut':
			case 'delete':
			case 'indent':
			case 'inserthorizontalrule':
			case 'insertorderedlist':
			case 'insertunorderedlist':
			case 'italic':
			case 'justifycenter':
			case 'justifyfull':
			case 'justifyleft':
			case 'justifyright':
			case 'outdent':
			case 'paste':
			case 'redo':
			case 'removeformat':
			case 'selectall':
			case 'strikethrough':
			case 'subscript':
			case 'superscript':
			case 'underline':
			case 'undo':
			case 'unlink':
			case 'createlink':
			case 'insertimage':
			//extra simple buttons
			case 'htmltoggle':
				item = new dojo.widget.Editor2ToolbarButton(name);
				break;
			case 'forecolor':
			case 'hilitecolor':
				item = new dojo.widget.Editor2ToolbarColorPaletteButton(name);
				break;
			case 'plainformatblock':
				item = new dojo.widget.Editor2ToolbarFormatBlockPlainSelect("formatblock");
				break;
			case 'formatblock':
				item = new dojo.widget.Editor2ToolbarFormatBlockSelect("formatblock");
				break;
			case 'fontsize':
				item = new dojo.widget.Editor2ToolbarFontSizeSelect("fontsize");
				break;
			case 'fontname':
				item = new dojo.widget.Editor2ToolbarFontNameSelect("fontname");
				break;
			case 'inserttable':
			case 'insertcell':
			case 'insertcol':
			case 'insertrow':
			case 'deletecells':
			case 'deletecols':
			case 'deleterows':
			case 'mergecells':
			case 'splitcell':
				dojo.debug(name + " is implemented in dojo.widget.Editor2Plugin.TableOperation, please require it first.");
				break;
			//TODO:
			case 'inserthtml':
			case 'blockdirltr':
			case 'blockdirrtl':
			case 'dirltr':
			case 'dirrtl':
			case 'inlinedirltr':
			case 'inlinedirrtl':
				dojo.debug("Not yet implemented toolbar item: "+name);
				break;
			default:
				dojo.debug("dojo.widget.Editor2ToolbarItemManager.getToolbarItem: Unknown toolbar item: "+name);
		}
		return item;
	}
});

dojo.addOnUnload(dojo.widget.Editor2ToolbarItemManager, "destroy");

dojo.declare("dojo.widget.Editor2ToolbarButton", null,
	function(name){
		this._name = name;
//		this._command = editor.getCommand(name);
	},
{
	// summary:
	//		dojo.widget.Editor2ToolbarButton is the base class for all toolbar item in Editor2Toolbar
	create: function(/*DomNode*/node, /*dojo.widget.Editor2Toolbar*/toolbar, /*Boolean*/nohover){
		// summary: create the item
		// node: the dom node which is the root of this toolbar item
		// toolbar: the Editor2Toolbar widget this toolbar item belonging to
		// nohover: whether this item in charge of highlight this item
		this._domNode = node;
		var cmd = toolbar.parent.getCommand(this._name); //FIXME: maybe an issue if different instance has different language
		if(cmd){
			this._domNode.title = cmd.getText();
		}
		//make this unselectable: different browsers
		//use different properties for this, so use
		//js do it automatically
		this.disableSelection(this._domNode);

		this._parentToolbar = toolbar;
		dojo.event.connect(this._domNode, 'onclick', this, 'onClick');
		if(!nohover){
			dojo.event.connect(this._domNode, 'onmouseover', this, 'onMouseOver');
			dojo.event.connect(this._domNode, 'onmouseout', this, 'onMouseOut');
		}
	},
	disableSelection: function(/*DomNode*/rootnode){
		// summary: disable selection on the passed node and all its children
		dojo.html.disableSelection(rootnode);
		var nodes = rootnode.all || rootnode.getElementsByTagName("*");
		for(var x=0; x<nodes.length; x++){
			dojo.html.disableSelection(nodes[x]);
		}
	},
	onMouseOver: function(){
		var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
		if(curInst){
			var _command = curInst.getCommand(this._name);
			if(_command && _command.getState() != dojo.widget.Editor2Manager.commandState.Disabled){
				this.highlightToolbarItem();
			}
		}
	},
	onMouseOut: function(){
		this.unhighlightToolbarItem();
	},
	destroy: function(){
		// summary: destructor
		this._domNode = null;
//		delete this._command;
		this._parentToolbar = null;
	},
	onClick: function(e){
		if(this._domNode && !this._domNode.disabled && this._parentToolbar.checkAvailability()){
			e.preventDefault();
			e.stopPropagation();
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(curInst){
				var _command = curInst.getCommand(this._name);
				if(_command){
					_command.execute();
				}
			}
		}
	},
	refreshState: function(){
		// summary: update the state of the toolbar item
		var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
		var em = dojo.widget.Editor2Manager;
		if(curInst){
			var _command = curInst.getCommand(this._name);
			if(_command){
				var state = _command.getState();
				if(state != this._lastState){
					switch(state){
						case em.commandState.Latched:
							this.latchToolbarItem();
							break;
						case em.commandState.Enabled:
							this.enableToolbarItem();
							break;
						case em.commandState.Disabled:
						default:
							this.disableToolbarItem();
					}
					this._lastState = state;
				}
			}
		}
		return em.commandState.Enabled;
	},

	latchToolbarItem: function(){
		this._domNode.disabled = false;
		this.removeToolbarItemStyle(this._domNode);
		dojo.html.addClass(this._domNode, this._parentToolbar.ToolbarLatchedItemStyle);
	},

	enableToolbarItem: function(){
		this._domNode.disabled = false;
		this.removeToolbarItemStyle(this._domNode);
		dojo.html.addClass(this._domNode, this._parentToolbar.ToolbarEnabledItemStyle);
	},

	disableToolbarItem: function(){
		this._domNode.disabled = true;
		this.removeToolbarItemStyle(this._domNode);
		dojo.html.addClass(this._domNode, this._parentToolbar.ToolbarDisabledItemStyle);
	},

	highlightToolbarItem: function(){
		dojo.html.addClass(this._domNode, this._parentToolbar.ToolbarHighlightedItemStyle);
	},

	unhighlightToolbarItem: function(){
		dojo.html.removeClass(this._domNode, this._parentToolbar.ToolbarHighlightedItemStyle);
	},

	removeToolbarItemStyle: function(){
		dojo.html.removeClass(this._domNode, this._parentToolbar.ToolbarEnabledItemStyle);
		dojo.html.removeClass(this._domNode, this._parentToolbar.ToolbarLatchedItemStyle);
		dojo.html.removeClass(this._domNode, this._parentToolbar.ToolbarDisabledItemStyle);
		this.unhighlightToolbarItem();
	}
});

dojo.declare("dojo.widget.Editor2ToolbarDropDownButton", dojo.widget.Editor2ToolbarButton, {
	// summary: dojo.widget.Editor2ToolbarDropDownButton extends the basic button with a dropdown list

	onClick: function(){
		if(this._domNode && !this._domNode.disabled && this._parentToolbar.checkAvailability()){
			if(!this._dropdown){
				this._dropdown = dojo.widget.createWidget("PopupContainer", {});
				this._domNode.appendChild(this._dropdown.domNode);
			}
			if(this._dropdown.isShowingNow){
				this._dropdown.close();
			}else{
				this.onDropDownShown();
				this._dropdown.open(this._domNode, null, this._domNode);
			}
		}
	},
	destroy: function(){
		this.onDropDownDestroy();
		if(this._dropdown){
			this._dropdown.destroy();
		}
		dojo.widget.Editor2ToolbarDropDownButton.superclass.destroy.call(this);
	},
	onDropDownShown: function(){},
	onDropDownDestroy: function(){}
});

dojo.declare("dojo.widget.Editor2ToolbarColorPaletteButton", dojo.widget.Editor2ToolbarDropDownButton, {
	// summary: dojo.widget.Editor2ToolbarColorPaletteButton provides a dropdown color palette picker

	onDropDownShown: function(){
		if(!this._colorpalette){
			this._colorpalette = dojo.widget.createWidget("ColorPalette", {});
			this._dropdown.addChild(this._colorpalette);

			this.disableSelection(this._dropdown.domNode);
			this.disableSelection(this._colorpalette.domNode);
			//do we need a destory to delete this._colorpalette manually?
			//I assume as it is added to this._dropdown via addChild, it
			//should be deleted when this._dropdown is destroyed

			dojo.event.connect(this._colorpalette, "onColorSelect", this, 'setColor');
			dojo.event.connect(this._dropdown, "open", this, 'latchToolbarItem');
			dojo.event.connect(this._dropdown, "close", this, 'enableToolbarItem');
		}
	},
	setColor: function(color){
		this._dropdown.close();
		var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
		if(curInst){
			var _command = curInst.getCommand(this._name);
			if(_command){
				_command.execute(color);
			}
		}
	}
});

dojo.declare("dojo.widget.Editor2ToolbarFormatBlockPlainSelect", dojo.widget.Editor2ToolbarButton, {
	// summary: dojo.widget.Editor2ToolbarFormatBlockPlainSelect provides a simple select for setting block format

	create: function(node, toolbar){
//		dojo.widget.Editor2ToolbarFormatBlockPlainSelect.superclass.create.apply(this, arguments);
		this._domNode = node;
		this._parentToolbar = toolbar;
		//TODO: check node is a select
		this._domNode = node;
		this.disableSelection(this._domNode);
		dojo.event.connect(this._domNode, 'onchange', this, 'onChange');
	},

	destroy: function(){
		this._domNode = null;
	},

	onChange: function(){
		if(this._parentToolbar.checkAvailability()){
			var sv = this._domNode.value.toLowerCase();
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(curInst){
				var _command = curInst.getCommand(this._name);
				if(_command){
					_command.execute(sv);
				}
			}
		}
	},

	refreshState: function(){
		if(this._domNode){
			dojo.widget.Editor2ToolbarFormatBlockPlainSelect.superclass.refreshState.call(this);
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(curInst){
				var _command = curInst.getCommand(this._name);
				if(_command){
					var format = _command.getValue();
					if(!format){ format = ""; }
					dojo.lang.forEach(this._domNode.options, function(item){
						if(item.value.toLowerCase() == format.toLowerCase()){
							item.selected = true;
						}
					});
				}
			}
		}
	}
});

dojo.declare("dojo.widget.Editor2ToolbarComboItem", dojo.widget.Editor2ToolbarDropDownButton,{
	// summary: dojo.widget.Editor2ToolbarComboItem provides an external loaded dropdown list

	href: null,
	create: function(node, toolbar){
		dojo.widget.Editor2ToolbarComboItem.superclass.create.apply(this, arguments);
		//do not use lazy initilization, as we need the local names in refreshState()
		if(!this._contentPane){
			dojo.require("dojo.widget.ContentPane");
			this._contentPane = dojo.widget.createWidget("ContentPane", {preload: 'true'});
			this._contentPane.addOnLoad(this, "setup");
			this._contentPane.setUrl(this.href);
		}
	},

	onMouseOver: function(e){
		if(this._lastState != dojo.widget.Editor2Manager.commandState.Disabled){
			dojo.html.addClass(e.currentTarget, this._parentToolbar.ToolbarHighlightedSelectStyle);
		}
	},
	onMouseOut:function(e){
		dojo.html.removeClass(e.currentTarget, this._parentToolbar.ToolbarHighlightedSelectStyle);
	},

	onDropDownShown: function(){
		if(!this._dropdown.__addedContentPage){
			this._dropdown.addChild(this._contentPane);
			this._dropdown.__addedContentPage = true;
		}
	},

	setup: function(){
		// summary: overload this to connect event
	},

	onChange: function(e){
		if(this._parentToolbar.checkAvailability()){
			var name = e.currentTarget.getAttribute("dropDownItemName");
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(curInst){
				var _command = curInst.getCommand(this._name);
				if(_command){
					_command.execute(name);
				}
			}
		}
		this._dropdown.close();
	},

	onMouseOverItem: function(e){
		dojo.html.addClass(e.currentTarget, this._parentToolbar.ToolbarHighlightedSelectItemStyle);
	},

	onMouseOutItem: function(e){
		dojo.html.removeClass(e.currentTarget, this._parentToolbar.ToolbarHighlightedSelectItemStyle);
	}
});

dojo.declare("dojo.widget.Editor2ToolbarFormatBlockSelect", dojo.widget.Editor2ToolbarComboItem, {
	// summary: dojo.widget.Editor2ToolbarFormatBlockSelect is an improved format block setting item

	href: dojo.uri.dojoUri("src/widget/templates/Editor2/EditorToolbar_FormatBlock.html"),

	setup: function(){
		dojo.widget.Editor2ToolbarFormatBlockSelect.superclass.setup.call(this);

		var nodes = this._contentPane.domNode.all || this._contentPane.domNode.getElementsByTagName("*");
		this._blockNames = {};
		this._blockDisplayNames = {};
		for(var x=0; x<nodes.length; x++){
			var node = nodes[x];
			dojo.html.disableSelection(node);
			var name=node.getAttribute("dropDownItemName")
			if(name){
				this._blockNames[name] = node;
				var childrennodes = node.getElementsByTagName(name);
				this._blockDisplayNames[name] = childrennodes[childrennodes.length-1].innerHTML;
			}
		}
		for(var name in this._blockNames){
			dojo.event.connect(this._blockNames[name], "onclick", this, "onChange");
			dojo.event.connect(this._blockNames[name], "onmouseover", this, "onMouseOverItem");
			dojo.event.connect(this._blockNames[name], "onmouseout", this, "onMouseOutItem");
		}
	},

	onDropDownDestroy: function(){
		if(this._blockNames){
			for(var name in this._blockNames){
				delete this._blockNames[name];
				delete this._blockDisplayNames[name];
			}
		}
	},

	refreshState: function(){
		dojo.widget.Editor2ToolbarFormatBlockSelect.superclass.refreshState.call(this);
		if(this._lastState != dojo.widget.Editor2Manager.commandState.Disabled){
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(curInst){
				var _command = curInst.getCommand(this._name);
				if(_command){
					var format = _command.getValue();
					if(format == this._lastSelectedFormat && this._blockDisplayNames){
						return this._lastState;
					}
					this._lastSelectedFormat = format;
					var label = this._domNode.getElementsByTagName("label")[0];
					var isSet = false;
					if(this._blockDisplayNames){
						for(var name in this._blockDisplayNames){
							if(name == format){
								label.innerHTML = 	this._blockDisplayNames[name];
								isSet = true;
								break;
							}
						}
						if(!isSet){
							label.innerHTML = "&nbsp;";
						}
					}
				}
			}
		}

		return this._lastState;
	}
});

dojo.declare("dojo.widget.Editor2ToolbarFontSizeSelect", dojo.widget.Editor2ToolbarComboItem,{
	// summary: dojo.widget.Editor2ToolbarFontSizeSelect provides a dropdown list for setting fontsize

	href: dojo.uri.dojoUri("src/widget/templates/Editor2/EditorToolbar_FontSize.html"),

	setup: function(){
		dojo.widget.Editor2ToolbarFormatBlockSelect.superclass.setup.call(this);

		var nodes = this._contentPane.domNode.all || this._contentPane.domNode.getElementsByTagName("*");
		this._fontsizes = {};
		this._fontSizeDisplayNames = {};
		for(var x=0; x<nodes.length; x++){
			var node = nodes[x];
			dojo.html.disableSelection(node);
			var name=node.getAttribute("dropDownItemName")
			if(name){
				this._fontsizes[name] = node;
				this._fontSizeDisplayNames[name] = node.getElementsByTagName('font')[0].innerHTML;
			}
		}
		for(var name in this._fontsizes){
			dojo.event.connect(this._fontsizes[name], "onclick", this, "onChange");
			dojo.event.connect(this._fontsizes[name], "onmouseover", this, "onMouseOverItem");
			dojo.event.connect(this._fontsizes[name], "onmouseout", this, "onMouseOutItem");
		}
	},

	onDropDownDestroy: function(){
		if(this._fontsizes){
			for(var name in this._fontsizes){
				delete this._fontsizes[name];
				delete this._fontSizeDisplayNames[name];
			}
		}
	},

	refreshState: function(){
		dojo.widget.Editor2ToolbarFormatBlockSelect.superclass.refreshState.call(this);
		if(this._lastState != dojo.widget.Editor2Manager.commandState.Disabled){
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(curInst){
				var _command = curInst.getCommand(this._name);
				if(_command){
					var size = _command.getValue();
					if(size == this._lastSelectedSize && this._fontSizeDisplayNames){
						return this._lastState;
					}
					this._lastSelectedSize = size;
					var label = this._domNode.getElementsByTagName("label")[0];
					var isSet = false;
					if(this._fontSizeDisplayNames){
						for(var name in this._fontSizeDisplayNames){
							if(name == size){
								label.innerHTML = 	this._fontSizeDisplayNames[name];
								isSet = true;
								break;
							}
						}
						if(!isSet){
							label.innerHTML = "&nbsp;";
						}
					}
				}
			}
		}
		return this._lastState;
	}
});

dojo.declare("dojo.widget.Editor2ToolbarFontNameSelect", dojo.widget.Editor2ToolbarFontSizeSelect, {
	// summary: dojo.widget.Editor2ToolbarFontNameSelect provides a dropdown list for setting fontname
	href: dojo.uri.dojoUri("src/widget/templates/Editor2/EditorToolbar_FontName.html")
});

dojo.widget.defineWidget(
	"dojo.widget.Editor2Toolbar",
	dojo.widget.HtmlWidget,
	function(){
		dojo.event.connect(this, "fillInTemplate", dojo.lang.hitch(this, function(){
			if(dojo.render.html.ie){
				this.domNode.style.zoom = 1.0;
			}
		}));
	},
	{
		// summary:
		//		dojo.widget.Editor2Toolbar is the main widget for the toolbar associated with an Editor2

		templatePath: dojo.uri.dojoUri("src/widget/templates/EditorToolbar.html"),
		templateCssPath: dojo.uri.dojoUri("src/widget/templates/EditorToolbar.css"),

		// ToolbarLatchedItemStyle: String: class name for latched toolbar button items
		ToolbarLatchedItemStyle: "ToolbarButtonLatched",

		// ToolbarEnabledItemStyle: String: class name for enabled toolbar button items
		ToolbarEnabledItemStyle: "ToolbarButtonEnabled",

		// ToolbarDisabledItemStyle: String: class name for disabled toolbar button items
		ToolbarDisabledItemStyle: "ToolbarButtonDisabled",

		// ToolbarHighlightedItemStyle: String: class name for highlighted toolbar button items
		ToolbarHighlightedItemStyle: "ToolbarButtonHighlighted",

		// ToolbarHighlightedSelectStyle: String: class name for highlighted toolbar select items
		ToolbarHighlightedSelectStyle: "ToolbarSelectHighlighted",

		// ToolbarHighlightedSelectItemStyle: String: class name for highlighted toolbar select dropdown items
		ToolbarHighlightedSelectItemStyle: "ToolbarSelectHighlightedItem",

//		itemNodeType: 'span', //all the items (with attribute dojoETItemName set) defined in the toolbar should be a of this type

		postCreate: function(){
			var nodes = dojo.html.getElementsByClass("dojoEditorToolbarItem", this.domNode/*, this.itemNodeType*/);

			this.items = {};
			for(var x=0; x<nodes.length; x++){
				var node = nodes[x];
				var itemname = node.getAttribute("dojoETItemName");
				if(itemname){
					var item = dojo.widget.Editor2ToolbarItemManager.getToolbarItem(itemname);
					if(item){
						item.create(node, this);
						this.items[itemname.toLowerCase()] = item;
					}else{
						//hide unsupported toolbar items
						node.style.display = "none";
					}
				}
			}
		},

		update: function(){
			// summary: update all the toolbar items
			for(var cmd in this.items){
				this.items[cmd].refreshState();
			}
		},

		shareGroup: '',
		checkAvailability: function(){
			// summary: returns whether items in this toolbar can be executed
			// description:
			//		For unshared toolbar, when clicking on a toolbar, the corresponding
			//		editor will be focused, and this function always return true. For shared
			//		toolbar, if the current focued editor is not one of the instances sharing
			//		this toolbar, this function return false, otherwise true.
			if(!this.shareGroup){
				this.parent.focus();
				return true;
			}
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(this.shareGroup == curInst.toolbarGroup){
				return true;
			}
			return false;
		},
		destroy: function(){
			for(var it in this.items){
				this.items[it].destroy();
				delete this.items[it];
			}
			dojo.widget.Editor2Toolbar.superclass.destroy.call(this);
		}
	}
);

dojo.provide("dojo.lfx.shadow");


dojo.lfx.shadow = function(/* HTMLElement */node) {
	//	summary
	//	creates a shadow underneath node.
	this.shadowPng = dojo.uri.dojoUri("src/html/images/shadow");
	this.shadowThickness = 8;
	this.shadowOffset = 15;
	this.init(node);
}

dojo.extend(dojo.lfx.shadow, {
	init: function(/* HTMLElement */node){
		//	summary
		//	Initializes the shadow.
		this.node=node;

		// make all the pieces of the shadow, and position/size them as much
		// as possible (but a lot of the coordinates are set in sizeShadow
		this.pieces={};
		var x1 = -1 * this.shadowThickness;
		var y0 = this.shadowOffset;
		var y1 = this.shadowOffset + this.shadowThickness;
		this._makePiece("tl", "top", y0, "left", x1);
		this._makePiece("l", "top", y1, "left", x1, "scale");
		this._makePiece("tr", "top", y0, "left", 0);
		this._makePiece("r", "top", y1, "left", 0, "scale");
		this._makePiece("bl", "top", 0, "left", x1);
		this._makePiece("b", "top", 0, "left", 0, "crop");
		this._makePiece("br", "top", 0, "left", 0);
	},

	_makePiece: function(name, vertAttach, vertCoord, horzAttach, horzCoord, sizing){
		var img;
		var url = this.shadowPng + name.toUpperCase() + ".png";
		if(dojo.render.html.ie55 || dojo.render.html.ie60){
			img=dojo.doc().createElement("div");
			img.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+url+"'"+
			(sizing?", sizingMethod='"+sizing+"'":"") + ")";
		}else{
			img=dojo.doc().createElement("img");
			img.src=url;
		}
		img.style.position="absolute";
		img.style[vertAttach]=vertCoord+"px";
		img.style[horzAttach]=horzCoord+"px";
		img.style.width=this.shadowThickness+"px";
		img.style.height=this.shadowThickness+"px";
		this.pieces[name]=img;
		this.node.appendChild(img);
	},

	size: function(/* int */width, /* int */height){
		//	summary
		//	Resizes the shadow based on width and height.
		var sideHeight = height - (this.shadowOffset+this.shadowThickness+1);
		if (sideHeight < 0) { sideHeight = 0; }
		if (height < 1) { height = 1; }
		if (width < 1) { width = 1; }
		with(this.pieces){
			l.style.height = sideHeight+"px";
			r.style.height = sideHeight+"px";
			b.style.width = (width-1)+"px";
			bl.style.top = (height-1)+"px";
			b.style.top = (height-1)+"px";
			br.style.top = (height-1)+"px";
			tr.style.left = (width-1)+"px";
			r.style.left = (width-1)+"px";
			br.style.left = (width-1)+"px";
		}
	}
});


dojo.provide("dojo.widget.html.layout");


dojo.widget.html.layout = function(/*DomNode*/ container, /*Object[]*/ children, /*String*/ layoutPriority) {
	/**
	 * summary
	 *		Layout a bunch of child dom nodes within a parent dom node
	 * container:
	 *		parent node
	 * layoutPriority:
	 *		"top-bottom" or "left-right"
	 * children:
	 *		an array like [ {domNode: foo, layoutAlign: "bottom" }, {domNode: bar, layoutAlign: "client"} ]
	 */

	dojo.html.addClass(container, "dojoLayoutContainer");

	// Copy children array and remove elements w/out layout.
	// Also record each child's position in the input array, for sorting purposes.
	children = dojo.lang.filter(children, function(child, idx){
		child.idx = idx;
		return dojo.lang.inArray(["top","bottom","left","right","client","flood"], child.layoutAlign)
	});

	// Order the children according to layoutPriority.
	// Multiple children w/the same layoutPriority will be sorted by their position in the input array.
	if(layoutPriority && layoutPriority!="none"){
		var rank = function(child){
			switch(child.layoutAlign){
				case "flood":
					return 1;
				case "left":
				case "right":
					return (layoutPriority=="left-right") ? 2 : 3;
				case "top":
				case "bottom":
					return (layoutPriority=="left-right") ? 3 : 2;
				default:
					return 4;
			}
		};
		children.sort(function(a,b){
			return (rank(a)-rank(b)) || (a.idx - b.idx);
		});
	}

	// remaining space (blank area where nothing has been written)
	var f={
		top: dojo.html.getPixelValue(container, "padding-top", true),
		left: dojo.html.getPixelValue(container, "padding-left", true)
	};
	dojo.lang.mixin(f, dojo.html.getContentBox(container));

	// set positions/sizes
	dojo.lang.forEach(children, function(child){
		var elm=child.domNode;
		var pos=child.layoutAlign;
		// set elem to upper left corner of unused space; may move it later
		with(elm.style){
			left = f.left+"px";
			top = f.top+"px";
			bottom = "auto";
			right = "auto";
		}
		dojo.html.addClass(elm, "dojoAlign" + dojo.string.capitalize(pos));

		// set size && adjust record of remaining space.
		// note that setting the width of a <div> may affect it's height.
		// TODO: same is true for widgets but need to implement API to support that
		if ( (pos=="top")||(pos=="bottom") ) {
			dojo.html.setMarginBox(elm, { width: f.width });
			var h = dojo.html.getMarginBox(elm).height;
			f.height -= h;
			if(pos=="top"){
				f.top += h;
			}else{
				elm.style.top = f.top + f.height + "px";
			}
			// TODO: for widgets I want to call resizeTo(), but I can't because
			// I only want to set the width, and have the height determined
			// dynamically.  (The thinner you make a div, the more height it consumes.)
			if(child.onResized){
				child.onResized();
			}
		}else if(pos=="left" || pos=="right"){
			var w = dojo.html.getMarginBox(elm).width;

			// TODO: I only want to set the height, not the width, but see bug#941 (FF),
			// and also the resizeTo() function demands both height and width arguments
			if(child.resizeTo){
				child.resizeTo(w, f.height);
			}else{
				dojo.html.setMarginBox(elm, { width: w, height: f.height });
			}

			f.width -= w;
			if(pos=="left"){
				f.left += w;
			}else{
				elm.style.left = f.left + f.width + "px";
			}
		} else if(pos=="flood" || pos=="client"){
			if(child.resizeTo){
				child.resizeTo(f.width, f.height);
			}else{
				dojo.html.setMarginBox(elm, { width: f.width, height: f.height });
			}
		}
	});
};

// This is essential CSS to make layout work (it isn't "styling" CSS)
// make sure that the position:absolute in dojoAlign* overrides other classes
dojo.html.insertCssText(
	".dojoLayoutContainer{ position: relative; display: block; overflow: hidden; }\n" +
	"body .dojoAlignTop, body .dojoAlignBottom, body .dojoAlignLeft, body .dojoAlignRight { position: absolute; overflow: hidden; }\n" +
	"body .dojoAlignClient { position: absolute }\n" +
	".dojoAlignClient { overflow: auto; }\n"
);

dojo.provide("dojo.dnd.DragAndDrop");

// summary:
//		Core "interfaces" for the participants in all DnD operations.
//		Subclasses implement all of the actions outlined by these APIs, with
//		most of the ones you probably care about being defined in
//		HtmlDragAndDrop.js, which will be automatically included should you
//		dojo.require("dojo.dnd.*");.
//
//		In addition to the various actor classes, a global manager will be
//		created/installed at dojo.dnd.dragManager. This manager object is of
//		type dojo.dnd.DragManager and will be replaced by environment-specific
//		managers.
//
// 		The 3 object types involved in any Drag and Drop operation are:
//			* DragSource
//				This is the item that can be selected for dragging. Drag
//				sources can have "types" to help mediate whether or not various
//				DropTargets will accept (or reject them). Most dragging actions
//				are handled by the DragObject which the DragSource generates
//				from its onDragStart method.
//			* DragObject
//				This, along with the manger, does most of the hard work of DnD.
//				Implementations may rely on DragObject instances to implement
//				"shadowing", "movement", or other kinds of DnD variations that
//				affect the visual representation of the drag operation.
//			* DropTarget
//				Represents some section of the screen that can accept drag
//				and drop events. DropTargets keep a list of accepted types
//				which is checked agains the types of the respective DragSource
//				objects that pass over it. DropTargets may implement behaviors
//				that respond to drop events to take application-level actions.

dojo.declare("dojo.dnd.DragSource", null, {
	// String:
	//		what kind of drag source are we? Used to determine if we can be
	//		dropped on a given DropTarget
	type: "",

	onDragEnd: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		stub handler that is called when dragging finishes.
	},

	onDragStart: function(/*dojo.dnd.DragEvent*/evt){ // dojo.dnd.DragObject
		// summary:
		//		stub handler that is called when dragging starts. Subclasses
		//		should ensure that onDragStart *always* returns a
		//		dojo.dnd.DragObject instance.
	},

	onSelected: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		This function gets called when the DOM element was selected for
		//		dragging by the HtmlDragAndDropManager.
	},

	unregister: function(){
		// summary: remove this drag source from the manager
		dojo.dnd.dragManager.unregisterDragSource(this);
	},

	reregister: function(){
		// summary: add this drag source to the manager
		dojo.dnd.dragManager.registerDragSource(this);
	}
});

dojo.declare("dojo.dnd.DragObject", null, {
	// String:
	//		what kind of drag object are we? Used to determine if we can be
	//		dropped on a given DropTarget
	type: "",

	register: function(){
		// summary: register this DragObject with the manager
		var dm = dojo.dnd.dragManager;
		if(dm["registerDragObject"]){ // side-effect prevention
			dm.registerDragObject(this);
		}
	},

	onDragStart: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		// 		over-ridden by subclasses. Gets called directly after being
		// 		created by the DragSource default action is to clone self as
		// 		icon
	},

	onDragMove: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		Implemented by subclasses. Should change the UI for the drag
		//		icon i.e., "it moves itself"
	},

	onDragOver: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		stub handler that is called when the DragObject instance is
		//		"over" a DropTarget.
	},

	onDragOut: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		stub handler that is called when the DragObject instance leaves
		//		a DropTarget.
	},

	onDragEnd: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		stub handler that is called when dragging ends, either through
		//		dropping or cancelation.
	},

	// normal aliases
	onDragLeave: dojo.lang.forward("onDragOut"),
	onDragEnter: dojo.lang.forward("onDragOver"),

	// non-camel aliases
	ondragout: dojo.lang.forward("onDragOut"),
	ondragover: dojo.lang.forward("onDragOver")
});

dojo.declare("dojo.dnd.DropTarget", null, {

	acceptsType: function(/*String*/type){
		// summary:
		//		determines whether or not this DropTarget will accept the given
		//		type. The default behavior is to consult this.acceptedTypes and
		//		if "*" is a member, to always accept the type.
		if(!dojo.lang.inArray(this.acceptedTypes, "*")){ // wildcard
			if(!dojo.lang.inArray(this.acceptedTypes, type)) { return false; } // Boolean
		}
		return true; // Boolean
	},

	accepts: function(/*Array*/dragObjects){
		// summary:
		//		determines if we'll accept all members of the passed array of
		//		dojo.dnd.DragObject instances
		if(!dojo.lang.inArray(this.acceptedTypes, "*")){ // wildcard
			for (var i = 0; i < dragObjects.length; i++) {
				if (!dojo.lang.inArray(this.acceptedTypes,
					dragObjects[i].type)) { return false; } // Boolean
			}
		}
		return true; // Boolean
	},

	unregister: function(){
		// summary: remove from the drag manager
		dojo.dnd.dragManager.unregisterDropTarget(this);
	},

	onDragOver: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		stub handler that is called when DragObject instances are
		//		"over" this DropTarget.
	},

	onDragOut: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		stub handler that is called when DragObject instances are
		//		"leave" this DropTarget.
	},

	onDragMove: function(/*dojo.dnd.DragEvent*/evt){
		// summary:
		//		stub handler that is called when DragObject instances are
		//		moved across this DropTarget. May fire many times in the course
		//		of the drag operation but will end after onDragOut
	},

	onDropStart: function(/*dojo.dnd.DragEvent*/evt){ // Boolean
		// summary:
		//		stub handler that is called when DragObject instances are
		//		dropped on this target. If true is returned from onDropStart,
		//		dropping proceeds, otherwise it's cancled.
	},

	onDrop: function(/*dojo.dnd.DragEvent*/evt){
		// summary: we're getting dropped on!
	},

	onDropEnd: function(){
		// summary: dropping is over
	}
}, function(){
	this.acceptedTypes = [];
});

// NOTE: this interface is defined here for the convenience of the DragManager
// implementor. It is expected that in most cases it will be satisfied by
// extending a native event (DOM event in HTML and SVG).
dojo.dnd.DragEvent = function(){
	this.dragSource = null;
	this.dragObject = null;
	this.target = null;
	this.eventStatus = "success";
	//
	// can be one of:
	//	[	"dropSuccess", "dropFailure", "dragMove",
	//		"dragStart", "dragEnter", "dragLeave"]
	//
}
/*
 *	The DragManager handles listening for low-level events and dispatching
 *	them to higher-level primitives like drag sources and drop targets. In
 *	order to do this, it must keep a list of the items.
 */
dojo.declare("dojo.dnd.DragManager", null, {
	// Array: an array of currently selected DragSource objects
	selectedSources: [],
	// Array: all DragObjects we know about
	dragObjects: [],
	// Array: all DragSources we know about
	dragSources: [],
	registerDragSource: function(/*dojo.dnd.DragSource*/ source){
		// summary: called by DragSource class constructor
	},
	// Array: all DropTargets we know about
	dropTargets: [],
	registerDropTarget: function(/*dojo.dnd.DropTarget*/ target){
		// summary: called by DropTarget class constructor
	},
	// dojo.dnd.DropTarget:
	//		what was the last DropTarget instance we left in the drag phase?
	lastDragTarget: null,
	// dojo.dnd.DropTarget:
	//		the DropTarget the mouse is currently over
	currentDragTarget: null,
	onKeyDown: function(){
		// summary: generic handler called by low-level events
	},
	onMouseOut: function(){
		// summary: generic handler called by low-level events
	},
	onMouseMove: function(){
		// summary: generic handler called by low-level events
	},
	onMouseUp: function(){
		// summary: generic handler called by low-level events
	}
});

// NOTE: despite the existance of the DragManager class, there will be a
// singleton drag manager provided by the renderer-specific D&D support code.
// It is therefore sane for us to assign instance variables to the DragManager
// prototype

// The renderer-specific file will define the following object:
// dojo.dnd.dragManager = null;

dojo.provide("dojo.dnd.HtmlDragManager");

// NOTE: there will only ever be a single instance of HTMLDragManager, so it's
// safe to use prototype properties for book-keeping.
dojo.declare("dojo.dnd.HtmlDragManager", dojo.dnd.DragManager, {
	/**
	 * There are several sets of actions that the DnD code cares about in the
	 * HTML context:
	 *	1.) mouse-down ->
	 *			(draggable selection)
	 *			(dragObject generation)
	 *		mouse-move ->
	 *			(draggable movement)
	 *			(droppable detection)
	 *			(inform droppable)
	 *			(inform dragObject)
	 *		mouse-up
	 *			(inform/destroy dragObject)
	 *			(inform draggable)
	 *			(inform droppable)
	 *	2.) mouse-down -> mouse-down
	 *			(click-hold context menu)
	 *	3.) mouse-click ->
	 *			(draggable selection)
	 *		shift-mouse-click ->
	 *			(augment draggable selection)
	 *		mouse-down ->
	 *			(dragObject generation)
	 *		mouse-move ->
	 *			(draggable movement)
	 *			(droppable detection)
	 *			(inform droppable)
	 *			(inform dragObject)
	 *		mouse-up
	 *			(inform draggable)
	 *			(inform droppable)
	 *	4.) mouse-up
	 *			(clobber draggable selection)
	 */
	disabled: false, // to kill all dragging!
	nestedTargets: false,
	mouseDownTimer: null, // used for click-hold operations
	dsCounter: 0,
	dsPrefix: "dojoDragSource",

	// dimension calculation cache for use durring drag
	dropTargetDimensions: [],

	currentDropTarget: null,
	// currentDropTargetPoints: null,
	previousDropTarget: null,
	_dragTriggered: false,

	selectedSources: [],
	dragObjects: [],
	dragSources: [],

	// mouse position properties
	currentX: null,
	currentY: null,
	lastX: null,
	lastY: null,
	mouseDownX: null,
	mouseDownY: null,
	threshold: 7,

	dropAcceptable: false,

	cancelEvent: function(e){ e.stopPropagation(); e.preventDefault();},

	// method over-rides
	registerDragSource: function(ds){
		//dojo.profile.start("register DragSource");

		if(ds["domNode"]){
			// FIXME: dragSource objects SHOULD have some sort of property that
			// references their DOM node, we shouldn't just be passing nodes and
			// expecting it to work.
			//dojo.profile.start("register DragSource 1");
			var dp = this.dsPrefix;
			var dpIdx = dp+"Idx_"+(this.dsCounter++);
			ds.dragSourceId = dpIdx;
			this.dragSources[dpIdx] = ds;
			ds.domNode.setAttribute(dp, dpIdx);
			//dojo.profile.end("register DragSource 1");

			//dojo.profile.start("register DragSource 2");

			// so we can drag links
			if(dojo.render.html.ie){
				//dojo.profile.start("register DragSource IE");

				dojo.event.browser.addListener(ds.domNode, "ondragstart", this.cancelEvent);
				// terribly slow
				//dojo.event.connect(ds.domNode, "ondragstart", this.cancelEvent);
				//dojo.profile.end("register DragSource IE");

			}
			//dojo.profile.end("register DragSource 2");

		}
		//dojo.profile.end("register DragSource");
	},

	unregisterDragSource: function(ds){
		if (ds["domNode"]){
			var dp = this.dsPrefix;
			var dpIdx = ds.dragSourceId;
			delete ds.dragSourceId;
			delete this.dragSources[dpIdx];
			ds.domNode.setAttribute(dp, null);
			if(dojo.render.html.ie){
				dojo.event.browser.removeListener(ds.domNode, "ondragstart", this.cancelEvent);
			}
		}
	},

	registerDropTarget: function(dt){
		this.dropTargets.push(dt);
	},

	unregisterDropTarget: function(dt){
		var index = dojo.lang.find(this.dropTargets, dt, true);
		if (index>=0) {
			this.dropTargets.splice(index, 1);
		}
	},

	/**
	* Get the DOM element that is meant to drag.
	* Loop through the parent nodes of the event target until
	* the element is found that was created as a DragSource and
	* return it.
	*
	* @param event object The event for which to get the drag source.
	*/
	getDragSource: function(e){
		var tn = e.target;
		if(tn === dojo.body()){ return; }
		var ta = dojo.html.getAttribute(tn, this.dsPrefix);
		while((!ta)&&(tn)){
			tn = tn.parentNode;
			if((!tn)||(tn === dojo.body())){ return; }
			ta = dojo.html.getAttribute(tn, this.dsPrefix);
		}
		return this.dragSources[ta];
	},

	onKeyDown: function(e){
	},

	onMouseDown: function(e){
		if(this.disabled) { return; }

		// only begin on left click
		if(dojo.render.html.ie) {
			if(e.button != 1) { return; }
		} else if(e.which != 1) {
			return;
		}

		var target = e.target.nodeType == dojo.html.TEXT_NODE ?
			e.target.parentNode : e.target;

		// do not start drag involvement if the user is interacting with
		// a form element.
		if(dojo.html.isTag(target, "button", "textarea", "input", "select", "option")) {
			return;
		}

		// find a selection object, if one is a parent of the source node
		var ds = this.getDragSource(e);

		// this line is important.  if we aren't selecting anything then
		// we need to return now, so preventDefault() isn't called, and thus
		// the event is propogated to other handling code
		if(!ds){ return; }

		if(!dojo.lang.inArray(this.selectedSources, ds)){
			this.selectedSources.push(ds);
			ds.onSelected();
		}

 		this.mouseDownX = e.pageX;
 		this.mouseDownY = e.pageY;

		// Must stop the mouse down from being propogated, or otherwise can't
		// drag links in firefox.
		// WARNING: preventing the default action on all mousedown events
		// prevents user interaction with the contents.
		e.preventDefault();

		dojo.event.connect(document, "onmousemove", this, "onMouseMove");
	},

	onMouseUp: function(e, cancel){
		// if we aren't dragging then ignore the mouse-up
		// (in particular, don't call preventDefault(), because other
		// code may need to process this event)
		if(this.selectedSources.length==0){
			return;
		}

		this.mouseDownX = null;
		this.mouseDownY = null;
		this._dragTriggered = false;
 		// e.preventDefault();
		e.dragSource = this.dragSource;
		// let ctrl be used for multiselect or another action
		// if I use same key to trigger treeV3 node selection and here,
		// I have bugs with drag'n'drop. why ?? no idea..
		if((!e.shiftKey)&&(!e.ctrlKey)){
		//if(!e.shiftKey){
			if(this.currentDropTarget) {
				this.currentDropTarget.onDropStart();
			}
			dojo.lang.forEach(this.dragObjects, function(tempDragObj){
				var ret = null;
				if(!tempDragObj){ return; }
				if(this.currentDropTarget) {
					e.dragObject = tempDragObj;

					// NOTE: we can't get anything but the current drop target
					// here since the drag shadow blocks mouse-over events.
					// This is probelematic for dropping "in" something
					var ce = this.currentDropTarget.domNode.childNodes;
					if(ce.length > 0){
						e.dropTarget = ce[0];
						while(e.dropTarget == tempDragObj.domNode){
							e.dropTarget = e.dropTarget.nextSibling;
						}
					}else{
						e.dropTarget = this.currentDropTarget.domNode;
					}
					if(this.dropAcceptable){
						ret = this.currentDropTarget.onDrop(e);
					}else{
						 this.currentDropTarget.onDragOut(e);
					}
				}

				e.dragStatus = this.dropAcceptable && ret ? "dropSuccess" : "dropFailure";
				// decouple the calls for onDragEnd, so they don't block the execution here
				// ie. if the onDragEnd would call an alert, the execution here is blocked until the
				// user has confirmed the alert box and then the rest of the dnd code is executed
				// while the mouse doesnt "hold" the dragged object anymore ... and so on
				dojo.lang.delayThese([
					function() {
						// in FF1.5 this throws an exception, see
						// http://dojotoolkit.org/pipermail/dojo-interest/2006-April/006751.html
						try{
							tempDragObj.dragSource.onDragEnd(e)
						} catch(err) {
							// since the problem seems passing e, we just copy all
							// properties and try the copy ...
							var ecopy = {};
							for (var i in e) {
								if (i=="type") { // the type property contains the exception, no idea why...
									ecopy.type = "mouseup";
									continue;
								}
								ecopy[i] = e[i];
							}
							tempDragObj.dragSource.onDragEnd(ecopy);
						}
					}
					, function() {tempDragObj.onDragEnd(e)}]);
			}, this);

			this.selectedSources = [];
			this.dragObjects = [];
			this.dragSource = null;
			if(this.currentDropTarget) {
				this.currentDropTarget.onDropEnd();
			}
		} else {
			//dojo.debug("special click");
		}

		dojo.event.disconnect(document, "onmousemove", this, "onMouseMove");
		this.currentDropTarget = null;
	},

	onScroll: function(){
		//dojo.profile.start("DNDManager updateoffset");
		for(var i = 0; i < this.dragObjects.length; i++) {
			if(this.dragObjects[i].updateDragOffset) {
				this.dragObjects[i].updateDragOffset();
			}
		}
		//dojo.profile.end("DNDManager updateoffset");

		// TODO: do not recalculate, only adjust coordinates
		if (this.dragObjects.length) {
			this.cacheTargetLocations();
		}
	},

	_dragStartDistance: function(x, y){
		if((!this.mouseDownX)||(!this.mouseDownX)){
			return;
		}
		var dx = Math.abs(x-this.mouseDownX);
		var dx2 = dx*dx;
		var dy = Math.abs(y-this.mouseDownY);
		var dy2 = dy*dy;
		return parseInt(Math.sqrt(dx2+dy2), 10);
	},

	cacheTargetLocations: function(){
		dojo.profile.start("cacheTargetLocations");

		this.dropTargetDimensions = [];
		dojo.lang.forEach(this.dropTargets, function(tempTarget){
			var tn = tempTarget.domNode;
			//only cache dropTarget which can accept current dragSource
			if(!tn || !tempTarget.accepts([this.dragSource])){ return; }
			var abs = dojo.html.getAbsolutePosition(tn, true);
			var bb = dojo.html.getBorderBox(tn);
			this.dropTargetDimensions.push([
				[abs.x, abs.y],	// upper-left
				// lower-right
				[ abs.x+bb.width, abs.y+bb.height ],
				tempTarget
			]);
			//dojo.debug("Cached for "+tempTarget)
		}, this);

		dojo.profile.end("cacheTargetLocations");

		//dojo.debug("Cache locations")
	},

	onMouseMove: function(e){
		if((dojo.render.html.ie)&&(e.button != 1)){
			// Oooops - mouse up occurred - e.g. when mouse was not over the
			// window. I don't think we can detect this for FF - but at least
			// we can be nice in IE.
			this.currentDropTarget = null;
			this.onMouseUp(e, true);
			return;
		}

		// if we've got some sources, but no drag objects, we need to send
		// onDragStart to all the right parties and get things lined up for
		// drop target detection

		if(	(this.selectedSources.length)&&
			(!this.dragObjects.length) ){
			var dx;
			var dy;
			if(!this._dragTriggered){
				this._dragTriggered = (this._dragStartDistance(e.pageX, e.pageY) > this.threshold);
				if(!this._dragTriggered){ return; }
				dx = e.pageX - this.mouseDownX;
				dy = e.pageY - this.mouseDownY;
			}

			// the first element is always our dragSource, if there are multiple
			// selectedSources (elements that move along) then the first one is the master
			// and for it the events will be fired etc.
			this.dragSource = this.selectedSources[0];

			dojo.lang.forEach(this.selectedSources, function(tempSource){
				if(!tempSource){ return; }
				var tdo = tempSource.onDragStart(e);
				if(tdo){
					tdo.onDragStart(e);

					// "bump" the drag object to account for the drag threshold
					tdo.dragOffset.y += dy;
					tdo.dragOffset.x += dx;
					tdo.dragSource = tempSource;

					this.dragObjects.push(tdo);
				}
			}, this);

			/* clean previous drop target in dragStart */
			this.previousDropTarget = null;

			this.cacheTargetLocations();
		}

		// FIXME: we need to add dragSources and dragObjects to e
		dojo.lang.forEach(this.dragObjects, function(dragObj){
			if(dragObj){ dragObj.onDragMove(e); }
		});

		// if we have a current drop target, check to see if we're outside of
		// it. If so, do all the actions that need doing.
		if(this.currentDropTarget){
			//dojo.debug(dojo.html.hasParent(this.currentDropTarget.domNode))
			var c = dojo.html.toCoordinateObject(this.currentDropTarget.domNode, true);
			//		var dtp = this.currentDropTargetPoints;
			var dtp = [
				[c.x,c.y], [c.x+c.width, c.y+c.height]
			];
		}

		if((!this.nestedTargets)&&(dtp)&&(this.isInsideBox(e, dtp))){
			if(this.dropAcceptable){
				this.currentDropTarget.onDragMove(e, this.dragObjects);
			}
		}else{
			// FIXME: need to fix the event object!
			// see if we can find a better drop target
			var bestBox = this.findBestTarget(e);

			if(bestBox.target === null){
				if(this.currentDropTarget){
					this.currentDropTarget.onDragOut(e);
					this.previousDropTarget = this.currentDropTarget;
					this.currentDropTarget = null;
					// this.currentDropTargetPoints = null;
				}
				this.dropAcceptable = false;
				return;
			}

			if(this.currentDropTarget !== bestBox.target){
				if(this.currentDropTarget){
					this.previousDropTarget = this.currentDropTarget;
					this.currentDropTarget.onDragOut(e);
				}
				this.currentDropTarget = bestBox.target;
				// this.currentDropTargetPoints = bestBox.points;
				e.dragObjects = this.dragObjects;
				this.dropAcceptable = this.currentDropTarget.onDragOver(e);

			}else{
				if(this.dropAcceptable){
					this.currentDropTarget.onDragMove(e, this.dragObjects);
				}
			}
		}
	},

	findBestTarget: function(e) {
		var _this = this;
		var bestBox = new Object();
		bestBox.target = null;
		bestBox.points = null;
		dojo.lang.every(this.dropTargetDimensions, function(tmpDA) {
			if(!_this.isInsideBox(e, tmpDA)){
				return true;
			}

			bestBox.target = tmpDA[2];
			bestBox.points = tmpDA;
			// continue iterating only if _this.nestedTargets == true
			return Boolean(_this.nestedTargets);
		});

		return bestBox;
	},

	isInsideBox: function(e, coords){
		if(	(e.pageX > coords[0][0])&&
			(e.pageX < coords[1][0])&&
			(e.pageY > coords[0][1])&&
			(e.pageY < coords[1][1]) ){
			return true;
		}
		return false;
	},

	onMouseOver: function(e){
	},

	onMouseOut: function(e){
	}
});

dojo.dnd.dragManager = new dojo.dnd.HtmlDragManager();

// global namespace protection closure
(function(){
	var d = document;
	var dm = dojo.dnd.dragManager;
	//TODO: when focus manager is ready, dragManager should be rewritten to use it
	// set up event handlers on the document (or no?)
	dojo.event.connect(d, "onkeydown", dm, "onKeyDown");
	dojo.event.connect(d, "onmouseover", dm, "onMouseOver");
	dojo.event.connect(d, "onmouseout", dm, "onMouseOut");
	dojo.event.connect(d, "onmousedown", dm, "onMouseDown");
	dojo.event.connect(d, "onmouseup", dm, "onMouseUp");
	// TODO: process scrolling of elements, not only window (focus manager would
	// probably come to rescue here as well)
	dojo.event.connect(window, "onscroll", dm, "onScroll");
})();

dojo.provide("dojo.dnd.HtmlDragAndDrop");



dojo.declare("dojo.dnd.HtmlDragSource", dojo.dnd.DragSource, {
		dragClass: "", // CSS classname(s) applied to node when it is being dragged

		onDragStart: function(){
			var dragObj = new dojo.dnd.HtmlDragObject(this.dragObject, this.type);
			if(this.dragClass){
				dragObj.dragClass = this.dragClass;
			}

			if(this.constrainToContainer){
				dragObj.constrainTo(this.constrainingContainer || this.domNode.parentNode);
			}

			return dragObj;
		},

		setDragHandle: function(node){
			node = dojo.byId(node);
			dojo.dnd.dragManager.unregisterDragSource(this);
			this.domNode = node;
			dojo.dnd.dragManager.registerDragSource(this);
		},

		setDragTarget: function(node){
			this.dragObject = node;
		},

		constrainTo: function(container){
			this.constrainToContainer = true;
			if(container){
				this.constrainingContainer = container;
			}
		},

		/*
		*
		* see dojo.dnd.DragSource.onSelected
		*/
		onSelected: function(){
			for(var i=0; i<this.dragObjects.length; i++){
				dojo.dnd.dragManager.selectedSources.push(new dojo.dnd.HtmlDragSource(this.dragObjects[i]));
			}
		},

		/**
		* Register elements that should be dragged along with
		* the actual DragSource.
		*
		* Example usage:
		* 	var dragSource = new dojo.dnd.HtmlDragSource(...);
		*	// add a single element
		*	dragSource.addDragObjects(dojo.byId('id1'));
		*	// add multiple elements to drag along
		*	dragSource.addDragObjects('id2', dojo.byId('id3'));
		*
		* el A dom node to add to the drag list.
		*/
		addDragObjects: function(/*DOMNode*/ el){
			for(var i=0; i<arguments.length; i++){
				this.dragObjects.push(dojo.byId(arguments[i]));
			}
		}
	},

	function(node, type){
		node = dojo.byId(node);
		this.dragObjects = [];
		this.constrainToContainer = false;
		if(node){
			this.domNode = node;
			this.dragObject = node;
			// set properties that might have been clobbered by the mixin
			this.type = (type)||(this.domNode.nodeName.toLowerCase());
			dojo.dnd.DragSource.prototype.reregister.call(this);
		}
	}
);

dojo.declare("dojo.dnd.HtmlDragObject",
	dojo.dnd.DragObject,
	{
		dragClass: "",
		opacity: 0.5,
		createIframe: true,		// workaround IE6 bug

		// if true, node will not move in X and/or Y direction
		disableX: false,
		disableY: false,

		createDragNode: function() {
			var node = this.domNode.cloneNode(true);
			if(this.dragClass) { dojo.html.addClass(node, this.dragClass); }
			if(this.opacity < 1) { dojo.html.setOpacity(node, this.opacity); }
			var ltn = node.tagName.toLowerCase();
			var isTr = (ltn == "tr");
			if((isTr)||(ltn == "tbody")){
				// dojo.debug("Dragging table row")
				// Create a table for the cloned row
				var doc = this.domNode.ownerDocument;
				var table = doc.createElement("table");
				if(isTr){
					var tbody = doc.createElement("tbody");
					table.appendChild(tbody);
					tbody.appendChild(node);
				}else{
					table.appendChild(node);
				}

				// Set a fixed width to the cloned TDs
				var tmpSrcTr = ((isTr) ? this.domNode : this.domNode.firstChild);
				var tmpDstTr = ((isTr) ? node : node.firstChild);
				var domTds = tmpSrcTr.childNodes;
				var cloneTds = tmpDstTr.childNodes;
				for(var i = 0; i < domTds.length; i++){
					if((cloneTds[i])&&(cloneTds[i].style)){
						cloneTds[i].style.width = dojo.html.getContentBox(domTds[i]).width + "px";
					}
				}
				node = table;
			}

			if((dojo.render.html.ie55||dojo.render.html.ie60) && this.createIframe){
				with(node.style) {
					top="0px";
					left="0px";
				}
				var outer = document.createElement("div");
				outer.appendChild(node);
				this.bgIframe = new dojo.html.BackgroundIframe(outer);
				outer.appendChild(this.bgIframe.iframe);
				node = outer;
			}
			node.style.zIndex = 999;

			return node;
		},

		onDragStart: function(e){
			dojo.html.clearSelection();

			this.scrollOffset = dojo.html.getScroll().offset;
			this.dragStartPosition = dojo.html.getAbsolutePosition(this.domNode, true);

			this.dragOffset = {y: this.dragStartPosition.y - e.pageY,
				x: this.dragStartPosition.x - e.pageX};

			this.dragClone = this.createDragNode();

			this.containingBlockPosition = this.domNode.offsetParent ?
				dojo.html.getAbsolutePosition(this.domNode.offsetParent, true) : {x:0, y:0};

			if(this.constrainToContainer){
				this.constraints = this.getConstraints();
			}

			// set up for dragging
			with(this.dragClone.style){
				position = "absolute";
				top = this.dragOffset.y + e.pageY + "px";
				left = this.dragOffset.x + e.pageX + "px";
			}

			dojo.body().appendChild(this.dragClone);

			dojo.event.topic.publish('dragStart', { source: this } );
		},

		/** Return min/max x/y (relative to document.body) for this object) **/
		getConstraints: function(){
			if(this.constrainingContainer.nodeName.toLowerCase() == 'body'){
				var viewport = dojo.html.getViewport();
				var width = viewport.width;
				var height = viewport.height;
				var scroll = dojo.html.getScroll().offset;
				var x = scroll.x;
				var y = scroll.y;
			}else{
				var content = dojo.html.getContentBox(this.constrainingContainer);
				width = content.width;
				height = content.height;
				x =
					this.containingBlockPosition.x +
					dojo.html.getPixelValue(this.constrainingContainer, "padding-left", true) +
					dojo.html.getBorderExtent(this.constrainingContainer, "left");
				y =
					this.containingBlockPosition.y +
					dojo.html.getPixelValue(this.constrainingContainer, "padding-top", true) +
					dojo.html.getBorderExtent(this.constrainingContainer, "top");
			}

			// TODO: should handle left/top/right/bottom margin separately; left/top should affect minX/minY
			var mb = dojo.html.getMarginBox(this.domNode);
			return {
				minX: x,
				minY: y,
				maxX: x + width - mb.width,
				maxY: y + height - mb.height
			}
		},

		updateDragOffset: function(){
			var scroll = dojo.html.getScroll().offset;
			if(scroll.y != this.scrollOffset.y){
				var diff = scroll.y - this.scrollOffset.y;
				this.dragOffset.y += diff;
				this.scrollOffset.y = scroll.y;
			}
			if(scroll.x != this.scrollOffset.x){
				var diff = scroll.x - this.scrollOffset.x;
				this.dragOffset.x += diff;
				this.scrollOffset.x = scroll.x;
			}
		},

		/** Moves the node to follow the mouse */
		onDragMove: function(e){
			this.updateDragOffset();
			var x = this.dragOffset.x + e.pageX;
			var y = this.dragOffset.y + e.pageY;

			if (this.constrainToContainer) {
				if (x < this.constraints.minX) { x = this.constraints.minX; }
				if (y < this.constraints.minY) { y = this.constraints.minY; }
				if (x > this.constraints.maxX) { x = this.constraints.maxX; }
				if (y > this.constraints.maxY) { y = this.constraints.maxY; }
			}

			this.setAbsolutePosition(x, y);

			dojo.event.topic.publish('dragMove', { source: this } );
		},

		/**
		 * Set the position of the drag clone.  (x,y) is relative to <body>.
		 */
		setAbsolutePosition: function(x, y){
			// The drag clone is attached to document.body so this is trivial
			if(!this.disableY) { this.dragClone.style.top = y + "px"; }
			if(!this.disableX) { this.dragClone.style.left = x + "px"; }
		},


		/**
		 * If the drag operation returned a success we remove the clone of
		 * ourself from the original position. If the drag operation returned
		 * failure we slide back over to where we came from and end the operation
		 * with a little grace.
		 */
		onDragEnd: function(e){
			switch(e.dragStatus){

				case "dropSuccess":
					dojo.html.removeNode(this.dragClone);
					this.dragClone = null;
					break;

				case "dropFailure": // slide back to the start
					var startCoords = dojo.html.getAbsolutePosition(this.dragClone, true);
					// offset the end so the effect can be seen
					var endCoords = { left: this.dragStartPosition.x + 1,
						top: this.dragStartPosition.y + 1};

					// animate
					var anim = dojo.lfx.slideTo(this.dragClone, endCoords, 300);
					var dragObject = this;
					dojo.event.connect(anim, "onEnd", function(e){
						// pause for a second (not literally) and disappear
						// dojo.lang.setTimeout(function() {
								dojo.html.removeNode(dragObject.dragClone);
								// Allow drag clone to be gc'ed
								dragObject.dragClone = null;
						// 	},
						// 	50);
					});
					anim.play();
					break;
			}

			dojo.event.topic.publish('dragEnd', { source: this } );
		},

		constrainTo: function(container){
			this.constrainToContainer=true;
			if(container){
				this.constrainingContainer = container;
			}else{
				this.constrainingContainer = this.domNode.parentNode;
			}
		}
	},
	function(node, type){
		this.domNode = dojo.byId(node);
		this.type = type;
		this.constrainToContainer = false;
		this.dragSource = null;
		// this.register();
		dojo.dnd.DragObject.prototype.register.call(this);
	}
);

dojo.declare("dojo.dnd.HtmlDropTarget",
	dojo.dnd.DropTarget,
	{
		vertical: false,
		onDragOver: function(e){
			if(!this.accepts(e.dragObjects)){ return false; }

			// cache the positions of the child nodes
			this.childBoxes = [];
			for(var i = 0, child; i < this.domNode.childNodes.length; i++){
				child = this.domNode.childNodes[i];
				if(child.nodeType != dojo.html.ELEMENT_NODE){ continue; }
				var pos = dojo.html.getAbsolutePosition(child, true);
				var inner = dojo.html.getBorderBox(child);
				this.childBoxes.push({top: pos.y, bottom: pos.y+inner.height,
					left: pos.x, right: pos.x+inner.width, height: inner.height,
					width: inner.width, node: child});
			}

			// TODO: use dummy node

			return true;
		},

		_getNodeUnderMouse: function(e){
			// find the child
			for(var i = 0, child; i < this.childBoxes.length; i++){
				with(this.childBoxes[i]){
					if (e.pageX >= left && e.pageX <= right &&
						e.pageY >= top && e.pageY <= bottom){ return i; }
				}
			}

			return -1;
		},

		createDropIndicator: function(){
			this.dropIndicator = document.createElement("div");
			with(this.dropIndicator.style){
				position = "absolute";
				zIndex = 999;
				if(this.vertical){
					borderLeftWidth = "1px";
					borderLeftColor = "black";
					borderLeftStyle = "solid";
					height = dojo.html.getBorderBox(this.domNode).height + "px";
					top = dojo.html.getAbsolutePosition(this.domNode, true).y + "px";
				}else{
					borderTopWidth = "1px";
					borderTopColor = "black";
					borderTopStyle = "solid";
					width = dojo.html.getBorderBox(this.domNode).width + "px";
					left = dojo.html.getAbsolutePosition(this.domNode, true).x + "px";
				}
			}
		},

		onDragMove: function(e, dragObjects){
			var i = this._getNodeUnderMouse(e);

			if(!this.dropIndicator){
				this.createDropIndicator();
			}

			var gravity = this.vertical ? dojo.html.gravity.WEST : dojo.html.gravity.NORTH;
			var hide = false;
			if(i < 0){
				if(this.childBoxes.length){
					var before = (dojo.html.gravity(this.childBoxes[0].node, e) & gravity);
					if(before){ hide = true; }
				}else{
					var before = true;
				}
			}else{
				var child = this.childBoxes[i];
				var before = (dojo.html.gravity(child.node, e) & gravity);
				if(child.node === dragObjects[0].dragSource.domNode){
					hide = true;
				}else{
					var currentPosChild = before ?
							(i>0?this.childBoxes[i-1]:child) :
							(i<this.childBoxes.length-1?this.childBoxes[i+1]:child);
					if(currentPosChild.node === dragObjects[0].dragSource.domNode){
						hide = true;
					}
				}
			}

			if(hide){
				this.dropIndicator.style.display="none";
				return;
			}else{
				this.dropIndicator.style.display="";
			}

			this.placeIndicator(e, dragObjects, i, before);

			if(!dojo.html.hasParent(this.dropIndicator)) {
				dojo.body().appendChild(this.dropIndicator);
			}
		},

		/**
		 * Position the horizontal line that indicates "insert between these two items"
		 */
		placeIndicator: function(e, dragObjects, boxIndex, before) {
			var targetProperty = this.vertical ? "left" : "top";
			var child;
			if(boxIndex < 0){
				if(this.childBoxes.length){
					child = before ? this.childBoxes[0]
						: this.childBoxes[this.childBoxes.length - 1];
				}else{
					this.dropIndicator.style[targetProperty] = dojo.html.getAbsolutePosition(this.domNode, true)[this.vertical?"x":"y"] + "px";
				}
			}else{
				child = this.childBoxes[boxIndex];
			}
			if(child){
				this.dropIndicator.style[targetProperty] = (before ? child[targetProperty] : child[this.vertical?"right":"bottom"]) + "px";
				if(this.vertical){
					this.dropIndicator.style.height = child.height + "px";
					this.dropIndicator.style.top = child.top + "px";
				}else{
					this.dropIndicator.style.width = child.width + "px";
					this.dropIndicator.style.left = child.left + "px";
				}
			}
		},

		onDragOut: function(e) {
			if(this.dropIndicator) {
				dojo.html.removeNode(this.dropIndicator);
				delete this.dropIndicator;
			}
		},

		/**
		 * Inserts the DragObject as a child of this node relative to the
		 * position of the mouse.
		 *
		 * @return true if the DragObject was inserted, false otherwise
		 */
		onDrop: function(e){
			this.onDragOut(e);

			var i = this._getNodeUnderMouse(e);

			var gravity = this.vertical ? dojo.html.gravity.WEST : dojo.html.gravity.NORTH;
			if(i < 0){
				if(this.childBoxes.length){
					if(dojo.html.gravity(this.childBoxes[0].node, e) & gravity){
						return this.insert(e, this.childBoxes[0].node, "before");
					}else{
						return this.insert(e, this.childBoxes[this.childBoxes.length-1].node, "after");
					}
				}
				return this.insert(e, this.domNode, "append");
			}

			var child = this.childBoxes[i];
			if(dojo.html.gravity(child.node, e) & gravity){
				return this.insert(e, child.node, "before");
			}else{
				return this.insert(e, child.node, "after");
			}
		},

		insert: function(e, refNode, position){
			var node = e.dragObject.domNode;

			if(position == "before"){
				return dojo.html.insertBefore(node, refNode);
			}else if(position == "after"){
				return dojo.html.insertAfter(node, refNode);
			}else if(position == "append"){
				refNode.appendChild(node);
				return true;
			}

			return false;
		}
	},

	function(node, types){
		if(arguments.length == 0){ return; }
		this.domNode = dojo.byId(node);
		dojo.dnd.DropTarget.call(this);
		if(types && dojo.lang.isString(types)) {
			types = [types];
		}
		this.acceptedTypes = types || [];
		dojo.dnd.dragManager.registerDropTarget(this);
	}
);

dojo.provide("dojo.dnd.*");

dojo.provide("dojo.dnd.HtmlDragMove");

dojo.declare("dojo.dnd.HtmlDragMoveSource", dojo.dnd.HtmlDragSource, {
	onDragStart: function(){
		var dragObj =  new dojo.dnd.HtmlDragMoveObject(this.dragObject, this.type);
		if (this.constrainToContainer) {
			dragObj.constrainTo(this.constrainingContainer);
		}
		return dragObj;
	},
	/*
	 * see dojo.dnd.HtmlDragSource.onSelected
	 */
	onSelected: function() {
		for (var i=0; i<this.dragObjects.length; i++) {
			dojo.dnd.dragManager.selectedSources.push(new dojo.dnd.HtmlDragMoveSource(this.dragObjects[i]));
		}
	}
});

dojo.declare("dojo.dnd.HtmlDragMoveObject", dojo.dnd.HtmlDragObject, {
	onDragStart: function(e){
		dojo.html.clearSelection();

		this.dragClone = this.domNode;

		// Record drag start position, where "position" is simply the top/left style values for
		// the node (the meaning of top/left is dependent on whether node is position:absolute or
		// position:relative, and also on the container).
		// Not sure if we should support moving nodes that aren't position:absolute,
		// but supporting it for now
		if(dojo.html.getComputedStyle(this.domNode, 'position') != 'absolute'){
			this.domNode.style.position = "relative";
		}
		var left = parseInt(dojo.html.getComputedStyle(this.domNode, 'left'));
		var top = parseInt(dojo.html.getComputedStyle(this.domNode, 'top'));
		this.dragStartPosition = {
			x: isNaN(left) ? 0 : left,
			y: isNaN(top) ? 0 : top
		};

		this.scrollOffset = dojo.html.getScroll().offset;

		// used to convert mouse position into top/left value for node
		this.dragOffset = {y: this.dragStartPosition.y - e.pageY,
			x: this.dragStartPosition.x - e.pageX};

		// since the DragObject's position is relative to the containing block, for our purposes
		// the containing block's position is just (0,0)
		this.containingBlockPosition = {x:0, y:0};

		if (this.constrainToContainer) {
			this.constraints = this.getConstraints();
		}

		// shortly the browser will fire an onClick() event,
		// but since this was really a drag, just squelch it
		dojo.event.connect(this.domNode, "onclick", this, "_squelchOnClick");
	},

	onDragEnd: function(e){
	},

	setAbsolutePosition: function(x, y){
		// summary: Set the top & left style attributes of the drag node (TODO: function is poorly named)
		if(!this.disableY) { this.domNode.style.top = y + "px"; }
		if(!this.disableX) { this.domNode.style.left = x + "px"; }
	},

	_squelchOnClick: function(e){
		// summary
		//	this function is called to squelch this onClick() event because
		//	it's the result of a drag (ie, it's not a real click)

		dojo.event.browser.stopEvent(e);
		dojo.event.disconnect(this.domNode, "onclick", this, "_squelchOnClick");
	}
});

dojo.provide("dojo.widget.Dialog");


dojo.declare(
	"dojo.widget.ModalDialogBase",
	null,
	{
		// summary
		//	Mixin for widgets implementing a modal dialog

		isContainer: true,

		// focusElement: String
		//	provide a focusable element or element id if you need to
		//	work around FF's tendency to send focus into outer space on hide
		focusElement: "",

		// bgColor: String
		//	color of viewport when displaying a dialog
		bgColor: "black",

		// bgOpacity: Number
		//	opacity (0~1) of viewport color (see bgColor attribute)
		bgOpacity: 0.4,

		// followScroll: Boolean
		//	if true, readjusts the dialog (and dialog background) when the user moves the scrollbar
		followScroll: true,

		// closeOnBackgroundClick: Boolean
		//	clicking anywhere on the background will close the dialog
		closeOnBackgroundClick: false,

		trapTabs: function(/*Event*/ e){
			// summary
			//	callback on focus
			if(e.target == this.tabStartOuter) {
				if(this._fromTrap) {
					this.tabStart.focus();
					this._fromTrap = false;
				} else {
					this._fromTrap = true;
					this.tabEnd.focus();
				}
			} else if (e.target == this.tabStart) {
				if(this._fromTrap) {
					this._fromTrap = false;
				} else {
					this._fromTrap = true;
					this.tabEnd.focus();
				}
			} else if(e.target == this.tabEndOuter) {
				if(this._fromTrap) {
					this.tabEnd.focus();
					this._fromTrap = false;
				} else {
					this._fromTrap = true;
					this.tabStart.focus();
				}
			} else if(e.target == this.tabEnd) {
				if(this._fromTrap) {
					this._fromTrap = false;
				} else {
					this._fromTrap = true;
					this.tabStart.focus();
				}
			}
		},

		clearTrap: function(/*Event*/ e) {
			// summary
			//	callback on blur
			var _this = this;
			setTimeout(function() {
				_this._fromTrap = false;
			}, 100);
		},

		postCreate: function() {
			// summary
			//	if the target mixin class already defined postCreate,
			//	dojo.widget.ModalDialogBase.prototype.postCreate.call(this)
			//	should be called in its postCreate()
			with(this.domNode.style){
				position = "absolute";
				zIndex = 999;
				display = "none";
				overflow = "visible";
			}
			var b = dojo.body();
			b.appendChild(this.domNode);

			// make background (which sits behind the dialog but above the normal text)
			this.bg = document.createElement("div");
			this.bg.className = "dialogUnderlay";
			with(this.bg.style){
				position = "absolute";
				left = top = "0px";
				zIndex = 998;
				display = "none";
			}
			b.appendChild(this.bg);
			this.setBackgroundColor(this.bgColor);

			this.bgIframe = new dojo.html.BackgroundIframe();
            if(this.bgIframe.iframe){
				with(this.bgIframe.iframe.style){
					position = "absolute";
					left = top = "0px";
					zIndex = 90;
					display = "none";
				}
			}

			if(this.closeOnBackgroundClick){
				dojo.event.kwConnect({srcObj: this.bg, srcFunc: "onclick",
					adviceObj: this, adviceFunc: "onBackgroundClick", once: true});
			}
		},

		uninitialize: function(){
			this.bgIframe.remove();
			dojo.html.removeNode(this.bg, true);
		},

		setBackgroundColor: function(/*String*/ color) {
			// summary
			//	changes background color specified by "bgColor" parameter
			//	usage:
			//		setBackgroundColor("black");
			//		setBackgroundColor(0xff, 0xff, 0xff);
			if(arguments.length >= 3) {
				color = new dojo.gfx.color.Color(arguments[0], arguments[1], arguments[2]);
			} else {
				color = new dojo.gfx.color.Color(color);
			}
			this.bg.style.backgroundColor = color.toString();
			return this.bgColor = color;	// String: the color
		},

		setBackgroundOpacity: function(/*Number*/ op) {
			// summary
			//	changes background opacity set by "bgOpacity" parameter
			if(arguments.length == 0) { op = this.bgOpacity; }
			dojo.html.setOpacity(this.bg, op);
			try {
				this.bgOpacity = dojo.html.getOpacity(this.bg);
			} catch (e) {
				this.bgOpacity = op;
			}
			return this.bgOpacity;	// Number: the opacity
		},

		_sizeBackground: function() {
			if(this.bgOpacity > 0) {

				var viewport = dojo.html.getViewport();
				var h = viewport.height;
				var w = viewport.width;
				with(this.bg.style){
					width = w + "px";
					height = h + "px";
				}
				var scroll_offset = dojo.html.getScroll().offset;
				this.bg.style.top = scroll_offset.y + "px";
				this.bg.style.left = scroll_offset.x + "px";
				// process twice since the scroll bar may have been removed
				// by the previous resizing
				var viewport = dojo.html.getViewport();
				if (viewport.width != w) { this.bg.style.width = viewport.width + "px"; }
				if (viewport.height != h) { this.bg.style.height = viewport.height + "px"; }
			}
			this.bgIframe.size(this.bg);
		},

		_showBackground: function() {
			if(this.bgOpacity > 0) {
				this.bg.style.display = "block";
			}
			if(this.bgIframe.iframe){
				this.bgIframe.iframe.style.display = "block";
			}
		},

		placeModalDialog: function() {
			// summary: position modal dialog in center of screen

			var scroll_offset = dojo.html.getScroll().offset;
			var viewport_size = dojo.html.getViewport();

			// find the size of the dialog (dialog needs to be showing to get the size)
			var mb;
			if(this.isShowing()){
				mb = dojo.html.getMarginBox(this.domNode);
			}else{
				dojo.html.setVisibility(this.domNode, false);
				dojo.html.show(this.domNode);
				mb = dojo.html.getMarginBox(this.domNode);
				dojo.html.hide(this.domNode);
				dojo.html.setVisibility(this.domNode, true);
			}

			var x = scroll_offset.x + (viewport_size.width - mb.width)/2;
			var y = scroll_offset.y + (viewport_size.height - mb.height)/2;
			with(this.domNode.style){
				left = x + "px";
				top = y + "px";
			}
		},

		_onKey: function(/*Event*/ evt){
			if (evt.key){
				// see if the key is for the dialog
				var node = evt.target;
				while (node != null){
					if (node == this.domNode){
						return; // yes, so just let it go
					}
					node = node.parentNode;
				}
				// this key is for the disabled document window
				if (evt.key != evt.KEY_TAB){ // allow tabbing into the dialog for a11y
					dojo.event.browser.stopEvent(evt);
				// opera won't tab to a div
				}else if (!dojo.render.html.opera){
					try {
						this.tabStart.focus();
					} catch(e){}
				}
			}
		},

		showModalDialog: function() {
			// summary
			//	call this function in show() of subclass before calling superclass.show()
			if (this.followScroll && !this._scrollConnected){
				this._scrollConnected = true;
				dojo.event.connect(window, "onscroll", this, "_onScroll");
			}
			dojo.event.connect(document.documentElement, "onkey", this, "_onKey");

			this.placeModalDialog();
			this.setBackgroundOpacity();
			this._sizeBackground();
			this._showBackground();
			this._fromTrap = true;

			// set timeout to allow the browser to render dialog
			setTimeout(dojo.lang.hitch(this, function(){
				try{
					this.tabStart.focus();
				}catch(e){}
			}), 50);

		},

		hideModalDialog: function(){
			// summary
			//	call this function in hide() of subclass

			// workaround for FF focus going into outer space
			if (this.focusElement) {
				dojo.byId(this.focusElement).focus();
				dojo.byId(this.focusElement).blur();
			}

			this.bg.style.display = "none";
			this.bg.style.width = this.bg.style.height = "1px";
            if(this.bgIframe.iframe){
				this.bgIframe.iframe.style.display = "none";
			}

			dojo.event.disconnect(document.documentElement, "onkey", this, "_onKey");
			if (this._scrollConnected){
				this._scrollConnected = false;
				dojo.event.disconnect(window, "onscroll", this, "_onScroll");
			}
		},

		_onScroll: function(){
			var scroll_offset = dojo.html.getScroll().offset;
			this.bg.style.top = scroll_offset.y + "px";
			this.bg.style.left = scroll_offset.x + "px";
			this.placeModalDialog();
		},

		checkSize: function() {
			if(this.isShowing()){
				this._sizeBackground();
				this.placeModalDialog();
				this.onResized();
			}
		},

		onBackgroundClick: function(){
			// summary
			//		Callback on background click.
			//		Clicking anywhere on the background will close the dialog, but only
			//		if the dialog doesn't have an explicit close button, and only if
			//		the dialog doesn't have a blockDuration.
			if(this.lifetime - this.timeRemaining >= this.blockDuration){ return; }
			this.hide();
		}
	});

dojo.widget.defineWidget(
	"dojo.widget.Dialog",
	[dojo.widget.ContentPane, dojo.widget.ModalDialogBase],
	{
		// summary
		//	Pops up a modal dialog window, blocking access to the screen and also graying out the screen
		//	Dialog is extended from ContentPane so it supports all the same parameters (href, etc.)

		templatePath: dojo.uri.dojoUri("src/widget/templates/Dialog.html"),

		// blockDuration: Integer
		//	number of seconds for which the user cannot dismiss the dialog
		blockDuration: 0,

		// lifetime: Integer
		//	if set, this controls the number of seconds the dialog will be displayed before automatically disappearing
		lifetime: 0,

		// closeNode: String
		//	Id of button or other dom node to click to close this dialog
		closeNode: "",

		postMixInProperties: function(){
			dojo.widget.Dialog.superclass.postMixInProperties.apply(this, arguments);
			if(this.closeNode){
				this.setCloseControl(this.closeNode);
			}
		},

		postCreate: function(){
			dojo.widget.Dialog.superclass.postCreate.apply(this, arguments);
			dojo.widget.ModalDialogBase.prototype.postCreate.apply(this, arguments);
		},

		show: function() {
			if(this.lifetime){
				this.timeRemaining = this.lifetime;
				if(this.timerNode){
					this.timerNode.innerHTML = Math.ceil(this.timeRemaining/1000);
				}
				if(this.blockDuration && this.closeNode){
					if(this.lifetime > this.blockDuration){
						this.closeNode.style.visibility = "hidden";
					}else{
						this.closeNode.style.display = "none";
					}
				}
				if (this.timer) {
					clearInterval(this.timer);
				}
				this.timer = setInterval(dojo.lang.hitch(this, "_onTick"), 100);
			}

			this.showModalDialog();
			dojo.widget.Dialog.superclass.show.call(this);
		},

		onLoad: function(){
			// when href is specified we need to reposition
			// the dialog after the data is loaded
			this.placeModalDialog();
			dojo.widget.Dialog.superclass.onLoad.call(this);
		},

		fillInTemplate: function(){
			// dojo.event.connect(this.domNode, "onclick", this, "killEvent");
		},

		hide: function(){
			this.hideModalDialog();
			dojo.widget.Dialog.superclass.hide.call(this);

			if(this.timer){
				clearInterval(this.timer);
			}
		},

		setTimerNode: function(node){
			// summary
			//	specify into which node to write the remaining # of seconds
			// TODO: make this a parameter too
			this.timerNode = node;
		},

		setCloseControl: function(/*String|DomNode*/ node) {
			// summary
			//	Specify which node is the close button for this dialog.
			//	If no close node is specified then clicking anywhere on the screen will close the dialog.
			this.closeNode = dojo.byId(node);
			dojo.event.connect(this.closeNode, "onclick", this, "hide");
		},

		setShowControl: function(/*String|DomNode*/ node) {
			// summary
			//	when specified node is clicked, show this dialog
			// TODO: make this a parameter too
			node = dojo.byId(node);
			dojo.event.connect(node, "onclick", this, "show");
		},

		_onTick: function(){
			// summary
			//	callback every second that the timer clicks
			if(this.timer){
				this.timeRemaining -= 100;
				if(this.lifetime - this.timeRemaining >= this.blockDuration){
					// TODO: this block of code is executing over and over again, rather than just once
					if(this.closeNode){
						this.closeNode.style.visibility = "visible";
					}
				}
				if(!this.timeRemaining){
					clearInterval(this.timer);
					this.hide();
				}else if(this.timerNode){
					this.timerNode.innerHTML = Math.ceil(this.timeRemaining/1000);
				}
			}
		}
	}
);

dojo.provide("dojo.widget.ResizeHandle");


dojo.widget.defineWidget(
	"dojo.widget.ResizeHandle",
	dojo.widget.HtmlWidget,
{
	// summary
	//	The handle on the bottom-right corner of FloatingPane or other widgets that allows
	//	the widget to be resized.
	//	Typically not used directly.

	// targetElmId: String
	//	id of the Widget OR DomNode that I will size
	targetElmId: '',

	templateCssPath: dojo.uri.dojoUri("src/widget/templates/ResizeHandle.css"),
	templateString: '<div class="dojoHtmlResizeHandle"><div></div></div>',

	postCreate: function(){
		dojo.event.connect(this.domNode, "onmousedown", this, "_beginSizing");
	},

	_beginSizing: function(/*Event*/ e){
		if (this._isSizing){ return false; }

		// get the target dom node to adjust.  targetElmId can refer to either a widget or a simple node
		this.targetWidget = dojo.widget.byId(this.targetElmId);
		this.targetDomNode = this.targetWidget ? this.targetWidget.domNode : dojo.byId(this.targetElmId);
		if (!this.targetDomNode){ return; }

		this._isSizing = true;
		this.startPoint  = {'x':e.clientX, 'y':e.clientY};
		var mb = dojo.html.getMarginBox(this.targetDomNode);
		this.startSize  = {'w':mb.width, 'h':mb.height};

		dojo.event.kwConnect({
			srcObj: dojo.body(),
			srcFunc: "onmousemove",
			targetObj: this,
			targetFunc: "_changeSizing",
			rate: 25
		});
		dojo.event.connect(dojo.body(), "onmouseup", this, "_endSizing");

		e.preventDefault();
	},

	_changeSizing: function(/*Event*/ e){
		// On IE, if you move the mouse above/to the left of the object being resized,
		// sometimes clientX/Y aren't set, apparently.  Just ignore the event.
		try{
			if(!e.clientX  || !e.clientY){ return; }
		}catch(e){
			// sometimes you get an exception accessing above fields...
			return;
		}
		var dx = this.startPoint.x - e.clientX;
		var dy = this.startPoint.y - e.clientY;

		var newW = this.startSize.w - dx;
		var newH = this.startSize.h - dy;

		// minimum size check
		if (this.minSize) {
			var mb = dojo.html.getMarginBox(this.targetDomNode);
			if (newW < this.minSize.w) {
				newW = mb.width;
			}
			if (newH < this.minSize.h) {
				newH = mb.height;
			}
		}

		if(this.targetWidget){
			this.targetWidget.resizeTo(newW, newH);
		}else{
			dojo.html.setMarginBox(this.targetDomNode, { width: newW, height: newH});
		}

		e.preventDefault();
	},

	_endSizing: function(/*Event*/ e){
		dojo.event.disconnect(dojo.body(), "onmousemove", this, "_changeSizing");
		dojo.event.disconnect(dojo.body(), "onmouseup", this, "_endSizing");

		this._isSizing = false;
	}


});

dojo.provide("dojo.widget.FloatingPane");


dojo.declare(
	"dojo.widget.FloatingPaneBase",
	null,
	{
		// summary
		//	Base class for FloatingPane, ModalFloatingPane

		// title: String
		//	text to display in floating pane's title bar (ex: "My Window")
		title: '',

		// iconSrc: String
		//	path of icon to display in floating pane's title bar
		iconSrc: '',

		// hasShadow: Boolean
		//	if true, display a shadow behind the floating pane
		hasShadow: false,

		// constrainToContainer: Boolean
		//	if true, and the floating pane is inside another container (ContentPane, another FloatingPane, etc.),
		//	then don't allow the floating pane to be dragged outside of it's container
		constrainToContainer: false,

		// taskBarId: String
		//	widget id of TaskBar widget;
		//	if specified, then an icon for this FloatingPane will be added to the specified TaskBar
		taskBarId: "",

		// resizable: Boolean
		//	if true, allow user to resize floating pane
		resizable: true,

		// titleBarDisplay: Boolean
		//	if true, display title bar for this floating pane
		titleBarDisplay: true,

		// windowState: String
		//	controls whether window is initially not displayed ("minimized"), displayed full screen ("maximized"),
		//	or just displayed normally ("normal").
		// Values
		//	"normal", "maximized", "minimized"
		windowState: "normal",

		// displayCloseAction: Boolean
		//	display button to close window
		displayCloseAction: false,

		// displayMinimizeAction: Boolean
		//	display button to minimize window (ie, window disappears so only the taskbar item remains)
		displayMinimizeAction: false,

		// displayMaximizeAction: Boolean
		//	display button to maximize window (ie, to take up the full screen)
		displayMaximizeAction: false,

		// Related to connecting to taskbar
		// TODO: use topics rather than repeated connect attempts?
		_max_taskBarConnectAttempts: 5,
		_taskBarConnectAttempts: 0,

		templatePath: dojo.uri.dojoUri("src/widget/templates/FloatingPane.html"),
		templateCssPath: dojo.uri.dojoUri("src/widget/templates/FloatingPane.css"),

		fillInFloatingPaneTemplate: function(args, frag){
			// summary: this should be called by fillInTemplate() of the widget that I'm mixed into

			// Copy style info from input node to output node
			var source = this.getFragNodeRef(frag);
			dojo.html.copyStyle(this.domNode, source);

			// necessary for safari, khtml (for computing width/height)
			dojo.body().appendChild(this.domNode);

			// if display:none then state=minimized, otherwise state=normal
			if(!this.isShowing()){
				this.windowState="minimized";
			}

			// <img src=""> can hang IE!  better get rid of it
			if(this.iconSrc==""){
				dojo.html.removeNode(this.titleBarIcon);
			}else{
				this.titleBarIcon.src = this.iconSrc.toString();// dojo.uri.Uri obj req. toString()
			}

			if(this.titleBarDisplay){
				this.titleBar.style.display="";
				dojo.html.disableSelection(this.titleBar);

				this.titleBarIcon.style.display = (this.iconSrc=="" ? "none" : "");

				this.minimizeAction.style.display = (this.displayMinimizeAction ? "" : "none");
				this.maximizeAction.style.display=
					(this.displayMaximizeAction && this.windowState!="maximized" ? "" : "none");
				this.restoreAction.style.display=
					(this.displayMaximizeAction && this.windowState=="maximized" ? "" : "none");
				this.closeAction.style.display= (this.displayCloseAction ? "" : "none");

				this.drag = new dojo.dnd.HtmlDragMoveSource(this.domNode);
				if (this.constrainToContainer) {
					this.drag.constrainTo();
				}
				this.drag.setDragHandle(this.titleBar);

				var self = this;

				dojo.event.topic.subscribe("dragMove",
					function (info){
						if (info.source.domNode == self.domNode){
							dojo.event.topic.publish('floatingPaneMove', { source: self } );
						}
					}
				);
			}

			if(this.resizable){
				this.resizeBar.style.display="";
				this.resizeHandle = dojo.widget.createWidget("ResizeHandle", {targetElmId: this.widgetId, id:this.widgetId+"_resize"});
				this.resizeBar.appendChild(this.resizeHandle.domNode);
			}

			// add a drop shadow
			if(this.hasShadow){
				this.shadow=new dojo.lfx.shadow(this.domNode);
			}

			// Prevent IE bleed-through problem
			this.bgIframe = new dojo.html.BackgroundIframe(this.domNode);

			if( this.taskBarId ){
				this._taskBarSetup();
			}

			// counteract body.appendChild above
			dojo.body().removeChild(this.domNode);
		},

		postCreate: function(){
			if (dojo.hostenv.post_load_) {
				this._setInitialWindowState();
			} else {
				dojo.addOnLoad(this, "_setInitialWindowState");
			}
		},

		maximizeWindow: function(/*Event*/ evt) {
			// summary: maximize the window
			var mb = dojo.html.getMarginBox(this.domNode);
			this.previous={
				width: mb.width || this.width,
				height: mb.height || this.height,
				left: this.domNode.style.left,
				top: this.domNode.style.top,
				bottom: this.domNode.style.bottom,
				right: this.domNode.style.right
			};
			if(this.domNode.parentNode.style.overflow.toLowerCase() != 'hidden'){
				this.parentPrevious={
					overflow: this.domNode.parentNode.style.overflow
				};
				dojo.debug(this.domNode.parentNode.style.overflow);
				this.domNode.parentNode.style.overflow = 'hidden';
			}

			this.domNode.style.left =
				dojo.html.getPixelValue(this.domNode.parentNode, "padding-left", true) + "px";
			this.domNode.style.top =
				dojo.html.getPixelValue(this.domNode.parentNode, "padding-top", true) + "px";

			if ((this.domNode.parentNode.nodeName.toLowerCase() == 'body')) {
				var viewport = dojo.html.getViewport();
				var padding = dojo.html.getPadding(dojo.body());
				this.resizeTo(viewport.width-padding.width, viewport.height-padding.height);
			} else {
				var content = dojo.html.getContentBox(this.domNode.parentNode);
				this.resizeTo(content.width, content.height);
			}
			this.maximizeAction.style.display="none";
			this.restoreAction.style.display="";

			//disable resize and drag
			if(this.resizeHandle){
				this.resizeHandle.domNode.style.display="none";
			}
			this.drag.setDragHandle(null);

			this.windowState="maximized";
		},

		minimizeWindow: function(/*Event*/ evt) {
			// summary: hide the window so that only the icon in the taskbar is shown
			this.hide();
			for(var attr in this.parentPrevious){
				this.domNode.parentNode.style[attr] = this.parentPrevious[attr];
			}
			this.lastWindowState = this.windowState;
			this.windowState = "minimized";
		},

		restoreWindow: function(/*Event*/ evt) {
			// summary: set the winow to normal size (neither maximized nor minimized)
			if (this.windowState=="minimized") {
				this.show();
				if(this.lastWindowState == "maximized"){
					this.domNode.parentNode.style.overflow = 'hidden';
					this.windowState="maximized";
				}else{ //normal
					this.windowState="normal";
				}
			} else if (this.windowState=="maximized"){
				for(var attr in this.previous){
					this.domNode.style[attr] = this.previous[attr];
				}
				for(var attr in this.parentPrevious){
					this.domNode.parentNode.style[attr] = this.parentPrevious[attr];
				}
				this.resizeTo(this.previous.width, this.previous.height);
				this.previous=null;
				this.parentPrevious=null;

				this.restoreAction.style.display="none";
				this.maximizeAction.style.display=this.displayMaximizeAction ? "" : "none";

				if(this.resizeHandle){
					this.resizeHandle.domNode.style.display="";
				}
				this.drag.setDragHandle(this.titleBar);
				this.windowState="normal";
			} else { //normal
				// do nothing
			}
		},

		toggleDisplay: function(){
			// summary: switch between hidden mode and displayed mode (either maximized or normal, depending on state before window was minimized)
			if(this.windowState=="minimized"){
				this.restoreWindow();
			}else{
				this.minimizeWindow();
			}
		},

		closeWindow: function(/*Event*/ evt) {
			// summary: destroy this window
			dojo.html.removeNode(this.domNode);
			this.destroy();
		},

		onMouseDown: function(/*Event*/ evt) {
			// summary: callback when user clicks anywhere on the floating pane
			this.bringToTop();
		},

		bringToTop: function() {
			// summary
			//	all the floating panes are stacked in z-index order; bring this floating pane to the top of that stack,
			//	so that it's displayed in front of all the other floating panes
			var floatingPanes= dojo.widget.manager.getWidgetsByType(this.widgetType);
			var windows = [];
			for (var x=0; x<floatingPanes.length; x++) {
				if (this.widgetId != floatingPanes[x].widgetId) {
						windows.push(floatingPanes[x]);
				}
			}

			windows.sort(function(a,b) {
				return a.domNode.style.zIndex - b.domNode.style.zIndex;
			});

			windows.push(this);

			var floatingPaneStartingZ = 100;
			for (x=0; x<windows.length;x++) {
				windows[x].domNode.style.zIndex = floatingPaneStartingZ + x*2;
			}
		},

		_setInitialWindowState: function() {
			if(this.isShowing()){
				this.width=-1;	// force resize
				var mb = dojo.html.getMarginBox(this.domNode);
				this.resizeTo(mb.width, mb.height);
			}
			if (this.windowState == "maximized") {
				this.maximizeWindow();
				this.show();
				return;
			}

			if (this.windowState=="normal") {
				this.show();
				return;
			}

			if (this.windowState=="minimized") {
				this.hide();
				return;
			}

			this.windowState="minimized";
		},

		_taskBarSetup: function() {
			// summary: add icon to task bar, connected to me
			var taskbar = dojo.widget.getWidgetById(this.taskBarId);
			if (!taskbar){
				if (this._taskBarConnectAttempts <  this._max_taskBarConnectAttempts) {
					dojo.lang.setTimeout(this, this._taskBarSetup, 50);
					this._taskBarConnectAttempts++;
				} else {
					dojo.debug("Unable to connect to the taskBar");
				}
				return;
			}
			taskbar.addChild(this);
		},

		showFloatingPane: function(){
			// summary:
			//	bring this floating pane to the top
			this.bringToTop();
		},

		onFloatingPaneShow: function(){
			// summary: callback for when someone calls FloatingPane.show
			var mb = dojo.html.getMarginBox(this.domNode);
			this.resizeTo(mb.width, mb.height);
		},

		// summary: set the floating pane to the given size
		resizeTo: function(/*Integer*/ width, /*Integer*/ height){
			dojo.html.setMarginBox(this.domNode, { width: width, height: height });

			dojo.widget.html.layout(this.domNode,
				[
				  {domNode: this.titleBar, layoutAlign: "top"},
				  {domNode: this.resizeBar, layoutAlign: "bottom"},
				  {domNode: this.containerNode, layoutAlign: "client"}
				] );

			// If any of the children have layoutAlign specified, obey it
			dojo.widget.html.layout(this.containerNode, this.children, "top-bottom");

			this.bgIframe.onResized();
			if(this.shadow){ this.shadow.size(width, height); }
			this.onResized();
		},

		checkSize: function() {
			// summary
			//	checkSize() is called when the user has resized the browser window,
			// 	but that doesn't affect this widget (or this widget's children)
			// 	so it can be safely ignored...
			// TODO: unless we are maximized.  then we should resize ourself.
		},
		destroyFloatingPane: function() {
			if(this.resizeHandle){
				this.resizeHandle.destroy();
				this.resizeHandle = null;
			}
		}
	}
);

dojo.widget.defineWidget(
	"dojo.widget.FloatingPane",
	[dojo.widget.ContentPane, dojo.widget.FloatingPaneBase],
{
	// summary
	//	A non-modal floating window.
	//	Attaches to a Taskbar which has an icon for each window.
	//	Must specify size (like style="width: 500px; height: 500px;"),

	fillInTemplate: function(args, frag){
		this.fillInFloatingPaneTemplate(args, frag);
		dojo.widget.FloatingPane.superclass.fillInTemplate.call(this, args, frag);
	},
	postCreate: function(){
		dojo.widget.FloatingPaneBase.prototype.postCreate.apply(this, arguments);
		dojo.widget.FloatingPane.superclass.postCreate.apply(this, arguments);
	},
	show: function(){
		dojo.widget.FloatingPane.superclass.show.apply(this, arguments);
		this.showFloatingPane();
	},
	onShow: function(){
		dojo.widget.FloatingPane.superclass.onShow.call(this);
		this.onFloatingPaneShow();
	},
	destroy: function(){
		this.destroyFloatingPane();
		dojo.widget.FloatingPane.superclass.destroy.apply(this, arguments);
	}
});


dojo.widget.defineWidget(
	"dojo.widget.ModalFloatingPane",
	[dojo.widget.FloatingPane, dojo.widget.ModalDialogBase],
	{
		// summary
		//	A modal floating window.
		//	This widget is similar to the Dialog widget, but the window, unlike the Dialog, can be moved.
		//	Must specify size (like style="width: 500px; height: 500px;"),

		windowState: "minimized",
		displayCloseAction: true,
		postCreate: function(){
			dojo.widget.ModalDialogBase.prototype.postCreate.call(this);
			dojo.widget.ModalFloatingPane.superclass.postCreate.call(this);
		},
		show: function(){
			this.showModalDialog();
			dojo.widget.ModalFloatingPane.superclass.show.apply(this, arguments);
			//place the background div under this modal pane
			this.bg.style.zIndex = this.domNode.style.zIndex-1;
		},
		hide: function(){
			this.hideModalDialog();
			dojo.widget.ModalFloatingPane.superclass.hide.apply(this, arguments);
		},
		closeWindow: function(){
			this.hide();
			dojo.widget.ModalFloatingPane.superclass.closeWindow.apply(this, arguments);
		}
	}
);

dojo.provide("dojo.widget.Editor2Plugin.AlwaysShowToolbar");

//dojo.widget.Editor2Manager.registerPerInstancePlugin("dojo.widget.Editor2Plugin.AlwaysShowToolbar");

dojo.event.topic.subscribe("dojo.widget.Editor2::onLoad", function(editor){
	if(editor.toolbarAlwaysVisible){
		var p = new dojo.widget.Editor2Plugin.AlwaysShowToolbar(editor);
	}
});
dojo.declare("dojo.widget.Editor2Plugin.AlwaysShowToolbar", null,
	function(editor){
		this.editor = editor;
		this.editor.registerLoadedPlugin(this);
		this.setup();
	},
	{
	_scrollSetUp: false,
	_fixEnabled: false,
	_scrollThreshold: false,
	_handleScroll: true,

	setup: function(){
		var tdn = this.editor.toolbarWidget;
		if(!tdn.tbBgIframe){
			tdn.tbBgIframe = new dojo.html.BackgroundIframe(tdn.domNode);
			tdn.tbBgIframe.onResized();
		}
		this.scrollInterval = setInterval(dojo.lang.hitch(this, "globalOnScrollHandler"), 100);

		dojo.event.connect("before", this.editor.toolbarWidget, "destroy", this, "destroy");
	},

	globalOnScrollHandler: function(){
		var isIE = dojo.render.html.ie;
		if(!this._handleScroll){ return; }
		var dh = dojo.html;
		var tdn = this.editor.toolbarWidget.domNode;
		var db = dojo.body();

		if(!this._scrollSetUp){
			this._scrollSetUp = true;
			var editorWidth =  dh.getMarginBox(this.editor.domNode).width;
			this._scrollThreshold = dh.abs(tdn, true).y;
			// dojo.debug("threshold:", this._scrollThreshold);
			if((isIE)&&(db)&&(dh.getStyle(db, "background-image")=="none")){
				with(db.style){
					backgroundImage = "url(" + dojo.uri.dojoUri("src/widget/templates/images/blank.gif") + ")";
					backgroundAttachment = "fixed";
				}
			}
		}

		var scrollPos = (window["pageYOffset"]) ? window["pageYOffset"] : (document["documentElement"]||document["body"]).scrollTop;

		// FIXME: need to have top and bottom thresholds so toolbar doesn't keep scrolling past the bottom
		if(scrollPos > this._scrollThreshold){
			// dojo.debug(scrollPos);
			if(!this._fixEnabled){
				var tdnbox = dojo.html.getMarginBox(tdn);
				this.editor.editorObject.style.marginTop = tdnbox.height+"px";

				if(isIE){
					// FIXME: should we just use setBehvior() here instead?
					tdn.style.left = dojo.html.abs(tdn, dojo.html.boxSizing.MARGIN_BOX).x;
					if(tdn.previousSibling){
						this._IEOriginalPos = ['after',tdn.previousSibling];
					}else if(tdn.nextSibling){
						this._IEOriginalPos = ['before',tdn.nextSibling];
					}else{
						this._IEOriginalPos = ['',tdn.parentNode];
					}
					dojo.body().appendChild(tdn);

					dojo.html.addClass(tdn, "IEFixedToolbar");
				}else{
					with(tdn.style){
						position = "fixed";
						top = "0px";
					}
				}

				tdn.style.width = tdnbox.width + "px";
				tdn.style.zIndex = 1000;
				this._fixEnabled = true;
			}
			// if we're showing the floating toolbar, make sure that if
			// we've scrolled past the bottom of the editor that we hide
			// the toolbar for this instance of the editor.

			// TODO: when we get multiple editor toolbar support working
			// correctly, ensure that we check this against the scroll
			// position of the bottom-most editor instance.
			if(!dojo.render.html.safari){
				// safari reports a bunch of things incorrectly here
				var eHeight = (this.height) ? parseInt(this.editor.height) : this.editor._lastHeight;
				if(scrollPos > (this._scrollThreshold+eHeight)){
					tdn.style.display = "none";
				}else{
					tdn.style.display = "";
				}
			}
		}else if(this._fixEnabled){
			(this.editor.object || this.editor.iframe).style.marginTop = null;
			with(tdn.style){
				position = "";
				top = "";
				zIndex = "";
				display = "";
			}
			if(isIE){
				tdn.style.left = "";
				dojo.html.removeClass(tdn, "IEFixedToolbar");
				if(this._IEOriginalPos){
					dojo.html.insertAtPosition(tdn, this._IEOriginalPos[1], this._IEOriginalPos[0]);
					this._IEOriginalPos = null;
				}else{
					dojo.html.insertBefore(tdn, this.editor.object||this.editor.iframe);
				}
			}
			tdn.style.width = "";
			this._fixEnabled = false;
		}
	},

	destroy: function(){
		this._IEOriginalPos = null;
		this._handleScroll = false;
		clearInterval(this.scrollInterval);
		this.editor.unregisterLoadedPlugin(this);

		if(dojo.render.html.ie){
			dojo.html.removeClass(this.editor.toolbarWidget.domNode, "IEFixedToolbar");
		}
	}
});
dojo.provide("dojo.widget.Editor2");


dojo.widget.Editor2Manager = new dojo.widget.HandlerManager;
dojo.lang.mixin(dojo.widget.Editor2Manager,
{
	// summary: Manager of current focused Editor2 Instance and available editor2 commands

	_currentInstance: null,

	// commandState: Object: state a command may be in
	commandState: {Disabled: 0, Latched: 1, Enabled: 2},

	getCurrentInstance: function(){
		// summary: Return the current focused Editor2 instance
		return this._currentInstance;
	},
	setCurrentInstance: function(/*Widget*/inst){
		// summary: Set current focused Editor2 instance
		this._currentInstance = inst;
	},
	getCommand: function(/*dojo.widget.Editor2*/editor,/*String*/name){
		// summary: Return Editor2 command with the given name
		// name: name of the command (case insensitive)
		var oCommand;
		name = name.toLowerCase();
		for(var i=0;i<this._registeredHandlers.length;i++){
			oCommand = this._registeredHandlers[i](editor, name);
			if(oCommand){
				return oCommand;
			}
		}
		switch(name){
			case 'htmltoggle':
				//Editor2 natively provide the htmltoggle functionalitity
				//and it is treated as a builtin command
				oCommand = new dojo.widget.Editor2BrowserCommand(editor, name);
				break;
			case 'formatblock':
				oCommand = new dojo.widget.Editor2FormatBlockCommand(editor, name);
				break;
			case 'anchor':
				oCommand = new dojo.widget.Editor2Command(editor, name);
				break;

			//dialog command
			case 'createlink':
				oCommand = new dojo.widget.Editor2DialogCommand(editor, name,
						{contentFile: "dojo.widget.Editor2Plugin.CreateLinkDialog",
							contentClass: "Editor2CreateLinkDialog",
							title: "Insert/Edit Link", width: "300px", height: "200px"});
				break;
			case 'insertimage':
				oCommand = new dojo.widget.Editor2DialogCommand(editor, name,
						{contentFile: "dojo.widget.Editor2Plugin.InsertImageDialog",
							contentClass: "Editor2InsertImageDialog",
							title: "Insert/Edit Image", width: "400px", height: "270px"});
				break;
			// By default we assume that it is a builtin simple command.
			default:
				var curtInst = this.getCurrentInstance();
				if((curtInst && curtInst.queryCommandAvailable(name)) ||
					(!curtInst && dojo.widget.Editor2.prototype.queryCommandAvailable(name))){
					oCommand = new dojo.widget.Editor2BrowserCommand(editor, name);
				}else{
					dojo.debug("dojo.widget.Editor2Manager.getCommand: Unknown command "+name);
					return;
				}
		}
		return oCommand;
	},
	destroy: function(){
		// summary: Cleaning up. This is called automatically on page unload.
		this._currentInstance = null;
		dojo.widget.HandlerManager.prototype.destroy.call(this);
	}
});

dojo.addOnUnload(dojo.widget.Editor2Manager, "destroy");

dojo.lang.declare("dojo.widget.Editor2Command",null,
	function(editor,name){
		// summary:
		//		dojo.widget.Editor2Command is the base class for all commands in Editor2

		this._editor = editor;
		this._updateTime = 0;
		this._name = name;
	},
{
		_text: 'Unknown',
		execute: function(para){
			// summary: Execute the command. should be implemented in subclass
			// description: this function should be re-implemented in subclass
			dojo.unimplemented("dojo.widget.Editor2Command.execute");
		},
		getText: function(){
			// summary: return the text name of this command
			return this._text;
		},
		getState: function(){
			// summary:
			//		Return the state of the command. The default behavior is
			//		to always return Enabled
			return dojo.widget.Editor2Manager.commandState.Enabled;
		},
		destroy: function(){
			// summary: Destructor
		}
	}
);

dojo.widget.Editor2BrowserCommandNames={
			'bold': 'Bold',
			'copy': 'Copy',
			'cut': 'Cut',
			'Delete': 'Delete',
			'indent': 'Indent',
			'inserthorizontalrule': 'Horizental Rule',
			'insertorderedlist': 'Numbered List',
			'insertunorderedlist': 'Bullet List',
			'italic': 'Italic',
			'justifycenter': 'Align Center',
			'justifyfull': 'Justify',
			'justifyleft': 'Align Left',
			'justifyright': 'Align Right',
			'outdent': 'Outdent',
			'paste': 'Paste',
			'redo': 'Redo',
			'removeformat': 'Remove Format',
			'selectall': 'Select All',
			'strikethrough': 'Strikethrough',
			'subscript': 'Subscript',
			'superscript': 'Superscript',
			'underline': 'Underline',
			'undo': 'Undo',
			'unlink': 'Remove Link',
			'createlink': 'Create Link',
			'insertimage': 'Insert Image',
			'htmltoggle': 'HTML Source',
			'forecolor': 'Foreground Color',
			'hilitecolor': 'Background Color',
			'plainformatblock': 'Paragraph Style',
			'formatblock': 'Paragraph Style',
			'fontsize': 'Font Size',
			'fontname': 'Font Name'//,
//			'inserttable': 'Insert Table',
//			'insertcell':
//			'insertcol':
//			'insertrow':
//			'deletecells':
//			'deletecols':
//			'deleterows':
//			'mergecells':
//			'splitcell':
//			'inserthtml':
//			'blockdirltr':
//			'blockdirrtl':
//			'dirltr':
//			'dirrtl':
//			'inlinedirltr':
//			'inlinedirrtl':
}

dojo.lang.declare("dojo.widget.Editor2BrowserCommand", dojo.widget.Editor2Command,
	function(editor,name){
		// summary:
		//		dojo.widget.Editor2BrowserCommand is the base class for all the browser built
		//		in commands

		var text = dojo.widget.Editor2BrowserCommandNames[name.toLowerCase()];
		if(text){
			this._text = text;
		}
	},
{
		execute: function(para){
			this._editor.execCommand(this._name, para);
		},
		getState: function(){
			if(this._editor._lastStateTimestamp > this._updateTime || this._state == undefined){
				this._updateTime = this._editor._lastStateTimestamp;
				try{
					if(this._editor.queryCommandEnabled(this._name)){
						if(this._editor.queryCommandState(this._name)){
							this._state = dojo.widget.Editor2Manager.commandState.Latched;
						}else{
							this._state = dojo.widget.Editor2Manager.commandState.Enabled;
						}
					}else{
						this._state = dojo.widget.Editor2Manager.commandState.Disabled;
					}
				}catch (e) {
					//dojo.debug("exception when getting state for command "+this._name+": "+e);
					this._state = dojo.widget.Editor2Manager.commandState.Enabled;
				}
			}
			return this._state;
		},
		getValue: function(){
			try{
				return this._editor.queryCommandValue(this._name);
			}catch(e){}
		}
	}
);

dojo.lang.declare("dojo.widget.Editor2FormatBlockCommand", dojo.widget.Editor2BrowserCommand, {
		/* In none-ActiveX mode under IE, <p> and no <p> text can not be distinguished
		getCurrentValue: function(){
			var curInst = dojo.widget.Editor2Manager.getCurrentInstance();
			if(!curInst){ return ''; }

			var h = dojo.render.html;

			// safari f's us for selection primitives
			if(h.safari){ return ''; }

			var selectedNode = (h.ie) ? curInst.document.selection.createRange().parentElement() : curInst.window.getSelection().anchorNode;
			// make sure we actuall have an element
			while((selectedNode)&&(selectedNode.nodeType != 1)){
				selectedNode = selectedNode.parentNode;
			}
			if(!selectedNode){ return ''; }

			var formats = ["p", "pre", "h1", "h2", "h3", "h4", "h5", "h6", "address"];
			// gotta run some specialized updates for the various
			// formatting options
			var type = formats[dojo.lang.find(formats, selectedNode.nodeName.toLowerCase())];
			while((selectedNode!=curInst.editNode)&&(!type)){
				selectedNode = selectedNode.parentNode;
				if(!selectedNode){ break; }
				type = formats[dojo.lang.find(formats, selectedNode.nodeName.toLowerCase())];
			}
			if(!type){
				type = "";
			}
			return type;
		}*/
	}
);

dojo.widget.defineWidget(
	"dojo.widget.Editor2Dialog",
	[dojo.widget.HtmlWidget, dojo.widget.FloatingPaneBase, dojo.widget.ModalDialogBase],
	{
		// summary:
		//		Provides a Dialog which can be modal or normal for the Editor2.

		templatePath: dojo.uri.dojoUri("src/widget/templates/Editor2/EditorDialog.html"),

		// modal: Boolean: Whether this is a modal dialog. True by default.
		modal: true,

//		refreshOnShow: true, //for debug for now

		// width: String: Width of the dialog. None by default.
		width: "",

		// height: String: Height of the dialog. None by default.
		height: "",

		// windowState: String: startup state of the dialog
		windowState: "minimized",

		displayCloseAction: true,

		// contentFile: String
		//	TODO
		contentFile: "",

		// contentClass: String
		//	TODO
		contentClass: "",

		fillInTemplate: function(args, frag){
			this.fillInFloatingPaneTemplate(args, frag);
			dojo.widget.Editor2Dialog.superclass.fillInTemplate.call(this, args, frag);
		},
		postCreate: function(){
			if(this.contentFile){
				dojo.require(this.contentFile);
			}
			if(this.modal){
				dojo.widget.ModalDialogBase.prototype.postCreate.call(this);
			}else{
				with(this.domNode.style) {
					zIndex = 999;
					display = "none";
				}
			}
			dojo.widget.FloatingPaneBase.prototype.postCreate.apply(this, arguments);
			dojo.widget.Editor2Dialog.superclass.postCreate.call(this);
			if(this.width && this.height){
				with(this.domNode.style){
					width = this.width;
					height = this.height;
				}
			}
		},
		createContent: function(){
			if(!this.contentWidget && this.contentClass){
				this.contentWidget = dojo.widget.createWidget(this.contentClass);
				this.addChild(this.contentWidget);
			}
		},
		show: function(){
			if(!this.contentWidget){
				//buggy IE: if the dialog is hidden, the button widgets
				//in the dialog can not be shown, so show it temporary (as the
				//dialog may decide not to show it in loadContent() later)
				dojo.widget.Editor2Dialog.superclass.show.apply(this, arguments);
				this.createContent();
				dojo.widget.Editor2Dialog.superclass.hide.call(this);
			}

			if(!this.contentWidget || !this.contentWidget.loadContent()){
				return;
			}
			this.showFloatingPane();
			dojo.widget.Editor2Dialog.superclass.show.apply(this, arguments);
			if(this.modal){
				this.showModalDialog();
			}
			if(this.modal){
				//place the background div under this modal pane
				this.bg.style.zIndex = this.domNode.style.zIndex-1;
			}
		},
		onShow: function(){
			dojo.widget.Editor2Dialog.superclass.onShow.call(this);
			this.onFloatingPaneShow();
		},
		closeWindow: function(){
			this.hide();
			dojo.widget.Editor2Dialog.superclass.closeWindow.apply(this, arguments);
		},
		hide: function(){
			if(this.modal){
				this.hideModalDialog();
			}
			dojo.widget.Editor2Dialog.superclass.hide.call(this);
		},
		//modified from ModalDialogBase.checkSize to call _sizeBackground conditionally
		checkSize: function(){
			if(this.isShowing()){
				if(this.modal){
					this._sizeBackground();
				}
				this.placeModalDialog();
				this.onResized();
			}
		}
	}
);

dojo.widget.defineWidget(
	"dojo.widget.Editor2DialogContent",
	dojo.widget.HtmlWidget,
{
	// summary:
	//		dojo.widget.Editor2DialogContent is the actual content of a Editor2Dialog.
	//		This class should be subclassed to provide the content.

	widgetsInTemplate: true,

	loadContent:function(){
		// summary: Load the content. Called by Editor2Dialog when first shown
		return true;
	},
	cancel: function(){
		// summary: Default handler when cancel button is clicked.
		this.parent.hide();
	}
});

dojo.lang.declare("dojo.widget.Editor2DialogCommand", dojo.widget.Editor2BrowserCommand,
	function(editor, name, dialogParas){
		// summary:
		//		Provides an easy way to popup a dialog when
		//		the command is executed.
		this.dialogParas = dialogParas;
	},
{
	execute: function(){
		if(!this.dialog){
			if(!this.dialogParas.contentFile || !this.dialogParas.contentClass){
				alert("contentFile and contentClass should be set for dojo.widget.Editor2DialogCommand.dialogParas!");
				return;
			}
			this.dialog = dojo.widget.createWidget("Editor2Dialog", this.dialogParas);

			dojo.body().appendChild(this.dialog.domNode);

			dojo.event.connect(this, "destroy", this.dialog, "destroy");
		}
		this.dialog.show();
	},
	getText: function(){
		return this.dialogParas.title || dojo.widget.Editor2DialogCommand.superclass.getText.call(this);
	}
});

dojo.widget.Editor2ToolbarGroups = {
	// summary: keeping track of all available share toolbar groups
};

dojo.widget.defineWidget(
	"dojo.widget.Editor2",
	dojo.widget.RichText,
	function(){
		this._loadedCommands={};
	},
	{
		// summary:
		//		dojo.widget.Editor2 is the WYSIWYG editor in dojo with toolbar. It supports a plugin
		//		framework which can be used to extend the functionalities of the editor, such as
		//		adding a context menu, table operation etc.
		// description:
		//		Plugins are available using dojo's require syntax. Please find available built-in plugins
		//		under src/widget/Editor2Plugin.

//		// saveUrl: String: url to which save action should send content to
//		saveUrl: "",
//		// saveMethod: String: HTTP method for save (post or get)
//		saveMethod: "post",
//		saveArgName: "editorContent",
//		closeOnSave: false,

		// toolbarAlwaysVisible: Boolean: Whether the toolbar should scroll to keep it in the view
		toolbarAlwaysVisible: false,

//		htmlEditing: false,

		toolbarWidget: null,
		scrollInterval: null,

		// toolbarTemplatePath: dojo.uri.Uri
		//		to specify the template file for the toolbar
		toolbarTemplatePath: dojo.uri.dojoUri("src/widget/templates/EditorToolbarOneline.html"),

		// toolbarTemplateCssPath: dojo.uri.Uri
		//		to specify the css file for the toolbar
		toolbarTemplateCssPath: null,

		// toolbarPlaceHolder: String
		//		element id to specify where to attach the toolbar
		toolbarPlaceHolder: '',

//		toolbarTemplatePath: dojo.uri.dojoUri("src/widget/templates/Editor2/EditorToolbarFCKStyle.html"),
//		toolbarTemplateCssPath: dojo.uri.dojoUri("src/widget/templates/Editor2/FCKDefault/EditorToolbarFCKStyle.css"),

		_inSourceMode: false,
		_htmlEditNode: null,

		// toolbarGroup: String:
		//		This instance of editor will share the same toolbar with other editor with the same toolbarGroup.
		//		By default, toolbarGroup is empty and standalone toolbar is used for this instance.
		toolbarGroup: '',

		// shareToolbar: Boolean: Whether to share toolbar with other instances of Editor2. Deprecated in favor of toolbarGroup
		shareToolbar: false,

		// contextMenuGroupSet: String: specify which context menu set should be used for this instance. Include ContextMenu plugin to use this
		contextMenuGroupSet: '',

		editorOnLoad: function(){
			// summary:
			//		Create toolbar and other initialization routines. This is called after
			//		the finish of the loading of document in the editing element
//			dojo.profile.start("dojo.widget.Editor2::editorOnLoad");

			dojo.event.topic.publish("dojo.widget.Editor2::preLoadingToolbar", this);
			if(this.toolbarAlwaysVisible){
				dojo.require("dojo.widget.Editor2Plugin.AlwaysShowToolbar");
			}

			if(this.toolbarWidget){
				this.toolbarWidget.show();
				//re-add the toolbar to the new domNode (caused by open() on another element)
				dojo.html.insertBefore(this.toolbarWidget.domNode, this.domNode.firstChild);
			}else{
				if(this.shareToolbar){
					dojo.deprecated("Editor2:shareToolbar is deprecated in favor of toolbarGroup", "0.5");
					this.toolbarGroup = 'defaultDojoToolbarGroup';
				}
				if(this.toolbarGroup){
					if(dojo.widget.Editor2ToolbarGroups[this.toolbarGroup]){
						this.toolbarWidget = dojo.widget.Editor2ToolbarGroups[this.toolbarGroup];
					}
				}
				if(!this.toolbarWidget){
						var tbOpts = {shareGroup: this.toolbarGroup, parent: this};
						tbOpts.templatePath = this.toolbarTemplatePath;
						if(this.toolbarTemplateCssPath){
							tbOpts.templateCssPath = this.toolbarTemplateCssPath;
						}
						if(this.toolbarPlaceHolder){
							this.toolbarWidget = dojo.widget.createWidget("Editor2Toolbar", tbOpts, dojo.byId(this.toolbarPlaceHolder), "after");
						}else{
							this.toolbarWidget = dojo.widget.createWidget("Editor2Toolbar", tbOpts, this.domNode.firstChild, "before");
						}
						if(this.toolbarGroup){
							dojo.widget.Editor2ToolbarGroups[this.toolbarGroup] = this.toolbarWidget;
						}
						dojo.event.connect(this, "close", this.toolbarWidget, "hide");

						this.toolbarLoaded();
				}
			}

			dojo.event.topic.registerPublisher("Editor2.clobberFocus", this, "clobberFocus");
			dojo.event.topic.subscribe("Editor2.clobberFocus", this, "setBlur");

			dojo.event.topic.publish("dojo.widget.Editor2::onLoad", this);
//			dojo.profile.end("dojo.widget.Editor2::editorOnLoad");
		},

		//event for plugins to use
		toolbarLoaded: function(){
			// summary:
			//		Fired when the toolbar for this editor is created.
			//		This event is for plugins to use
		},

		//TODO: provide a query mechanism about loaded plugins?
		registerLoadedPlugin: function(/*Object*/obj){
			// summary: Register a plugin which is loaded for this instance
			if(!this.loadedPlugins){
				this.loadedPlugins = [];
			}
			this.loadedPlugins.push(obj);
		},
		unregisterLoadedPlugin: function(/*Object*/obj){
			// summary: Delete a loaded plugin for this instance
			for(var i in this.loadedPlugins){
				if(this.loadedPlugins[i] === obj){
					delete this.loadedPlugins[i];
					return;
				}
			}
			dojo.debug("dojo.widget.Editor2.unregisterLoadedPlugin: unknow plugin object: "+obj);
		},

		//overload the original ones to provide extra commands
		execCommand: function(/*String*/command, argument){
			switch(command.toLowerCase()){
				case 'htmltoggle':
					this.toggleHtmlEditing();
					break;
				default:
					dojo.widget.Editor2.superclass.execCommand.apply(this, arguments);
			}
		},
		queryCommandEnabled: function(/*String*/command, argument){
			switch(command.toLowerCase()){
				case 'htmltoggle':
					return true;
				default:
					if(this._inSourceMode){ return false;}
					return dojo.widget.Editor2.superclass.queryCommandEnabled.apply(this, arguments);
			}
		},
		queryCommandState: function(/*String*/command, argument){
			switch(command.toLowerCase()){
				case 'htmltoggle':
					return this._inSourceMode;
				default:
					return dojo.widget.Editor2.superclass.queryCommandState.apply(this, arguments);
			}
		},

		onClick: function(/*Event*/e){
			dojo.widget.Editor2.superclass.onClick.call(this, e);
			//if Popup is used, call dojo.widget.PopupManager.onClick
			//manually when click in the editing area to close all
			//open popups (dropdowns)
			if(dojo.widget.PopupManager){
				if(!e){ //IE
					e = this.window.event;
				}
				dojo.widget.PopupManager.onClick(e);
			}
		},

		clobberFocus: function(){
			// summary: stub to signal other instances to clobber focus
		},
		toggleHtmlEditing: function(){
			// summary: toggle between WYSIWYG mode and HTML source mode
			if(this===dojo.widget.Editor2Manager.getCurrentInstance()){
				if(!this._inSourceMode){
					var html = this.getEditorContent();
					this._inSourceMode = true;

					if(!this._htmlEditNode){
						this._htmlEditNode = dojo.doc().createElement("textarea");
						dojo.html.insertAfter(this._htmlEditNode, this.editorObject);
					}

					this._htmlEditNode.style.display = "";
					this._htmlEditNode.style.width = "100%";
					this._htmlEditNode.style.height = dojo.html.getBorderBox(this.editNode).height+"px";
					this._htmlEditNode.value = html;

					//activeX object (IE) doesn't like to be hidden, so move it outside of screen instead
					with(this.editorObject.style){
						position = "absolute";
						left = "-2000px";
						top = "-2000px";
					}
				}else{
					this._inSourceMode = false;

					//In IE activeX mode, if _htmlEditNode is focused,
					//when toggling, an error would occur, so unfocus it
					this._htmlEditNode.blur();

					with(this.editorObject.style){
						position = "";
						left = "";
						top = "";
					}
					var html = this._htmlEditNode.value;

					dojo.lang.setTimeout(this, "replaceEditorContent", 1, html);
					this._htmlEditNode.style.display = "none";
					this.focus();
				}
				this.onDisplayChanged(null, true);
			}
		},

		setFocus: function(){
			// summary: focus is set on this instance
//			dojo.debug("setFocus: start "+this.widgetId);
			if(dojo.widget.Editor2Manager.getCurrentInstance() === this){ return; }

			this.clobberFocus();
//			dojo.debug("setFocus:", this);
			dojo.widget.Editor2Manager.setCurrentInstance(this);
		},

		setBlur: function(){
			// summary: focus on this instance is lost
//			 dojo.debug("setBlur:", this);
			//dojo.event.disconnect(this.toolbarWidget, "exec", this, "execCommand");
		},

		saveSelection: function(){
			// summary: save the current selection for restoring it
			this._bookmark = null;
			this._bookmark = dojo.withGlobal(this.window, dojo.html.selection.getBookmark);
		},
		restoreSelection: function(){
			// summary: restore the last saved selection
			if(this._bookmark){
				this.focus(); //require for none-activeX IE
				dojo.withGlobal(this.window, "moveToBookmark", dojo.html.selection, [this._bookmark]);
				this._bookmark = null;
			}else{
				dojo.debug("restoreSelection: no saved selection is found!");
			}
		},

		_updateToolbarLastRan: null,
		_updateToolbarTimer: null,
		_updateToolbarFrequency: 500,

		updateToolbar: function(/*Boolean*/force){
			// summary: update the associated toolbar of this Editor2
			if((!this.isLoaded)||(!this.toolbarWidget)){ return; }

			// keeps the toolbar from updating too frequently
			// TODO: generalize this functionality?
			var diff = new Date() - this._updateToolbarLastRan;
			if( (!force)&&(this._updateToolbarLastRan)&&
				((diff < this._updateToolbarFrequency)) ){

				clearTimeout(this._updateToolbarTimer);
				var _this = this;
				this._updateToolbarTimer = setTimeout(function() {
					_this.updateToolbar();
				}, this._updateToolbarFrequency/2);
				return;

			}else{
				this._updateToolbarLastRan = new Date();
			}
			// end frequency checker

			//IE has the habit of generating events even when this editor is blurred, prevent this
			if(dojo.widget.Editor2Manager.getCurrentInstance() !== this){ return; }

			this.toolbarWidget.update();
		},

		destroy: function(/*Boolean*/finalize){
			this._htmlEditNode = null;
			dojo.event.disconnect(this, "close", this.toolbarWidget, "hide");
			if(!finalize){
				this.toolbarWidget.destroy();
			}
			dojo.widget.Editor2.superclass.destroy.call(this);
		},

		_lastStateTimestamp: 0,
		onDisplayChanged: function(/*Object*/e, /*Boolean*/forceUpdate){
			this._lastStateTimestamp = (new Date()).getTime();
			dojo.widget.Editor2.superclass.onDisplayChanged.call(this,e);
			this.updateToolbar(forceUpdate);
		},

		onLoad: function(){
			try{
				dojo.widget.Editor2.superclass.onLoad.call(this);
			}catch(e){ // FIXME: debug why this is throwing errors in IE!
				dojo.debug(e);
			}
			this.editorOnLoad();
		},

		onFocus: function(){
			dojo.widget.Editor2.superclass.onFocus.call(this);
			this.setFocus();
		},

		//overload to support source editing mode
		getEditorContent: function(){
			if(this._inSourceMode){
				return this._htmlEditNode.value;
			}
			return dojo.widget.Editor2.superclass.getEditorContent.call(this);
		},

		replaceEditorContent: function(html){
			if(this._inSourceMode){
				this._htmlEditNode.value = html;
				return;
			}
			dojo.widget.Editor2.superclass.replaceEditorContent.apply(this,arguments);
		},
		getCommand: function(/*String*/name){
			// summary: return a command associated with this instance of editor
			if(this._loadedCommands[name]){
				return this._loadedCommands[name];
			}
			var cmd = dojo.widget.Editor2Manager.getCommand(this, name);
			this._loadedCommands[name] = cmd;
			return cmd;
		},
		// Array: Commands shortcuts. Each element can has up to 3 fields:
		//		1. String: the name of the command
		//		2. String Optional: the char for shortcut key, by default the first char from the command name is used
		//		3. Int Optional: specify the modifier of the shortcut, by default ctrl is used
		shortcuts: [['bold'],['italic'],['underline'],['selectall','a'],['insertunorderedlist','\\']],
		setupDefaultShortcuts: function(){
			// summary: setup default shortcuts using Editor2 commands
			var exec = function(cmd){ return function(){ cmd.execute(); } };
//			if(!dojo.render.html.ie){
//				this.shortcuts.push(['redo','Z']);
//			}
			var self = this;
			dojo.lang.forEach(this.shortcuts, function(item){
				var cmd = self.getCommand(item[0]);
				if(cmd){
					self.addKeyHandler(item[1]?item[1]:item[0].charAt(0), item[2]==undefined?self.KEY_CTRL:item[2], exec(cmd));
				}
			});
//			this.addKeyHandler("s", ctrl, function () { this.save(true); });
		}
		/*,
		// FIXME: probably not needed any more with new design, but need to verify
		_save: function(e){
			// FIXME: how should this behave when there's a larger form in play?
			if(!this.isClosed){
				dojo.debug("save attempt");
				if(this.saveUrl.length){
					var content = {};
					content[this.saveArgName] = this.getEditorContent();
					dojo.io.bind({
						method: this.saveMethod,
						url: this.saveUrl,
						content: content
					});
				}else{
					dojo.debug("please set a saveUrl for the editor");
				}
				if(this.closeOnSave){
					this.close(e.getName().toLowerCase() == "save");
				}
			}
		}*/
	}
);
