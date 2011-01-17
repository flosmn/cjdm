<?php require('../simplepie.inc'); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
      <html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>

<script type="text/javascript">
    <!--
    if (navigator.appName.indexOf("Explorer") != -1){
        if (navigator.appVersion.indexOf("MSIE 6.0") != -1){
            document.write("<link href=\"..\/css\/styleIE6.css\" rel=\"stylesheet\" type=\"text\/css\"\/>");
        }
        else{
            document.write("<link href=\"..\/css\/style.css\" rel=\"stylesheet\" type=\"text\/css\"\/>");
        }
    }
    else{
        document.write("<link href=\"..\/css\/style.css\" rel=\"stylesheet\" type=\"text\/css\"\/>");
    }
    
    var returnval = 0;
	var stylesheet, xmlFile, cache, doc;
	function init(){
		// NSCP 7.1+ / Mozilla 1.4.1+ / Safari
		// Use the standard DOM Level 2 technique, if it is supported
		if (document.implementation && document.implementation.createDocument) {
			xmlFile = document.implementation.createDocument("", "", null);
			stylesheet = document.implementation.createDocument("", "", null);
			if (xmlFile.load){
				xmlFile.load("site.xml");
				stylesheet.load("web/site.xsl");
			} else {
				alert("Document could not be loaded by browser.");
			}
			xmlFile.addEventListener("load", transform, false);
			stylesheet.addEventListener("load", transform, false);
		}
		//IE 6.0+ solution
		else if (window.ActiveXObject) {
			xmlFile = new ActiveXObject("msxml2.DOMDocument.3.0");
			xmlFile.async = false;
			xmlFile.load("site.xml");
			stylesheet = new ActiveXObject("msxml2.FreeThreadedDOMDocument.3.0");
			stylesheet.async = false;
			stylesheet.load("web/site.xsl");
			cache = new ActiveXObject("msxml2.XSLTemplate.3.0");
			cache.stylesheet = stylesheet;
			transformData();
		}
	}
	// separate transformation function for IE 6.0+
	function transformData(){
		var processor = cache.createProcessor();
		processor.input = xmlFile;
		processor.transform();
		data.innerHTML = processor.output;
	}
	// separate transformation function for NSCP 7.1+ and Mozilla 1.4.1+ 
	function transform(){
		returnval+=1;
		if (returnval==2){
			var processor = new XSLTProcessor();
			processor.importStylesheet(stylesheet); 
			doc = processor.transformToDocument(xmlFile);
			document.getElementById("data").innerHTML = doc.documentElement.innerHTML;
		}
	}
    
    -->
</script>

<title>ogre4j | updates</title>
<meta name="description" content="ogre4j is a project that enables the use of the OGRE libraries in Java applications" />
<meta name="keywords" content="ogre4j, Java, 3D, visualization, OGRE, object, oriented, graphics, rendering, engine, c++, native, interface, jni, swt, eclipse, windows, linux, download, bug, tracker, plugin, sourceforge, version, current, documentation, gallery, screenshots, status, powerful, application, library, netAllied, environment, ant, jdk, LGPL, lesser, gnu, public, license" />
<meta name="robots" content="index, follow" />
<meta http-equiv="Content-type" content="text/html;charset=iso-8859-1"/>
<link href="./images/fav.ico" rel="SHORTCUT ICON" type="image/x-icon"/>
<link rel="alternate" type="application/rss+xml" title="RSS" href="http://sourceforge.net/export/rss2_projnews.php?group_id=143864" />

</head>
<body onload="init();"><p><a name="top"></a></p>
	<div id="wrap">
		<div id="header">
			<div class="head_c"></div>
			<div class="head_l"></div>
			<div class="head_content">
				<div id="logo">
					<a href="http://ogre4j.sourceforge.net/"><img src="../images/logo.gif" alt="Ogre4J Logo"/></a>
				</div>
				<div id="logotxt">
					<h1>Java Native Interfaces for OGRE - Updates</h1>
				</div>
			</div>
			<div class="head_r"></div>
		</div>
		<div id="menu">
			<div class="main_content">
				<div class="menu_content">
					<div id="navigation_l"></div>
						<ul id="navigation">
							<li><a href="http://ogre4j.wiki.sourceforge.net/" title="A Documentation about ogre4j">Wiki</a></li>    
							<li><a href="http://sourceforge.net/forum/?group_id=143864" title="The official Forums for ogre4j">Forums</a></li>
							<li><a href="http://sourceforge.net/project/screenshots.php?group_id=143864" title="Screenshots of ogre4j">Screenshots</a></li>
							<li><a href="http://sourceforge.net/svn/?group_id=143864" title="Browse the ogre4j code repository">Code</a></li>
							<li><a href="http://sourceforge.net/project/showfiles.php?group_id=143864" title="Download the latest Version of ogre4j">Downloads</a></li>
							<li><a href="http://sourceforge.net/tracker/?group_id=143864" title="Tell us a bug">BugTracker</a></li>
							<li><a href="http://sourceforge.net/search/?group_id=143864&amp;type_of_search=pervasive" title="SF.net Search">Search</a></li>
						</ul>
					<div id="navigation_r"></div>
				</div>
			</div>
		</div>
		<div id="main">
			<div class="main_content">
				<div class="sidebar">
					<div class="sidebar_nav">
						<div class="nav_head">
							<h3>Menu</h3>
						</div>
						<div class="nav_main">
							<ul>
								<li><a href="../#project" title="Project Summary">Project Summary</a></li>
								<li><a href="../#news" title="News">News</a></li>
								<li><a href="../#file" title="File Releases">File Releases</a></li>
								<li><a href="/webstart" title="Webstart">Webstart</a></li>
                                                                <li><a href="/videos" title="Videos">Videos</a></li>
								<li><a href="/updates" title="Updates">Updates</a></li>
							</ul>
						</div>
						<div class="nav_foot"></div>
					</div>
					
					<div class="sidebar_nav">
						<div class="nav_head">
							<h3>Project Status</h3>
						</div>
						<div class="nav_main">
							<ul>
								<li>Status: 93%</li>
								<li>Last release:<br/>
								
								<?php
									$projfiles = new SimplePie();
									$projfiles->enable_cache(false);
									$projfiles->set_feed_url('http://sourceforge.net/export/rss2_projfiles.php?group_id=143864');
									$projfiles->init();
									$projfiles->handle_content_type();
									if ($projfiles->data) { 

										$item = $projfiles->get_item(0);
										list($version, $head) =  split("released", $item->get_title());
										echo $version;
										
									}
									 
								?>
								
								</li>
							</ul>
						</div>
						<div class="nav_foot"></div>
					</div>
					
					<div class="sidebar_nav">
						<div class="sub_single">
							<h3><a href="https://sourceforge.net/project/platformdownload.php?group_id=143864" title="Download last release">Download now!</a></h3>
						</div>
					</div>
					
					<div class="sidebar_nav">
						<div class="nav_head">
							<h3>Links</h3>
						</div>
						<div class="nav_main">
							<p><a href="http://sourceforge.net"><img src="http://sflogo.sourceforge.net/sflogo.php?group_id=143864&amp;type=1" width="88" height="31" border="0" alt="SourceForge.net Logo" /></a></p>
							<p><a href="http://ogre3d.org/"><img src="../images/ogre3DLogo.gif" alt="Ogre3D-Logo"/></a></p>
						</div>
						<div class="nav_foot"></div>
					</div>
				</div>
				
				<div class="content">
					<div class="content_inner">
					
						<div class="content_head">
							<h2><a name="project">ogre4j Eclipse Update Site</a></h2>
						</div>
						<div class="content_main">
							
                                                       <div id="data"><p><!-- this is where the transformed data goes --></p></div>
							
						</div>
						<div class="content_foot"></div>
						
					</div>
				</div>
			</div>
		</div>
		<div id="footer">
			<div class="footer_content">
				<p>Copyright &copy; 2005-2007 the ogre4j team | <a href="http://displacer.net/">artwork</a> | 
				<a href="http://validator.w3.org/check?uri=referer" title="Valid XHTML 1.0 Strict">XHTML 1.0 Strict</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>