<?php require('../simplepie.inc'); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
      <html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>

<script type="text/javascript" src="flash/swfobject.js"></script>
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
    
    // some variables to save
			var currentPosition;
			var currentVolume;
			var currentItem;

			// these functions are caught by the JavascriptView object of the player.
			function sendEvent(typ,prm) { thisMovie("mpl").sendEvent(typ,prm); };
			function getUpdate(typ,pr1,pr2,pid) {
				if(typ == "time") { currentPosition = pr1; }
				else if(typ == "volume") { currentVolume = pr1; }
				else if(typ == "item") { currentItem = pr1; setTimeout("getItemData(currentItem)",100); }
				var id = document.getElementById(typ);
				id.innerHTML = typ+ ": "+Math.round(pr1);
				pr2 == undefined ? null: id.innerHTML += ", "+Math.round(pr2);
				if(pid != "null") {
					document.getElementById("pid").innerHTML = "(received from the player with id <i>"+pid+"</i>)";
				}
			};

			// These functions are caught by the feeder object of the player.
			function loadFile(obj) { thisMovie("mpl").loadFile(obj); };
			function addItem(obj,idx) { thisMovie("mpl").addItem(obj,idx); }
			function removeItem(idx) { thisMovie("mpl").removeItem(idx); }
			function getItemData(idx) {
				var obj = thisMovie("mpl").itemData(idx);
				var nodes = "";
				for(var i in obj) { 
					nodes += "<li>"+i+": "+obj[i]+"</li>"; 
				}
				document.getElementById("data").innerHTML = nodes;
			};

			// This is a javascript handler for the player and is always needed.
			function thisMovie(movieName) {
			    if(navigator.appName.indexOf("Microsoft") != -1) {
					return window[movieName];
				} else {
					return document[movieName];
				}
			};
    
    -->
</script>

<title>ogre4j | videos</title>
<meta name="description" content="ogre4j is a project that enables the use of the OGRE libraries in Java applications" />
<meta name="keywords" content="ogre4j, Java, 3D, visualization, OGRE, object, oriented, graphics, rendering, engine, c++, native, interface, jni, swt, eclipse, windows, linux, download, bug, tracker, plugin, sourceforge, version, current, documentation, gallery, screenshots, status, powerful, application, library, netAllied, environment, ant, jdk, LGPL, lesser, gnu, public, license" />
<meta name="robots" content="index, follow" />
<meta http-equiv="Content-type" content="text/html;charset=iso-8859-1"/>
<link href="./images/fav.ico" rel="SHORTCUT ICON" type="image/x-icon"/>
<link rel="alternate" type="application/rss+xml" title="RSS" href="http://sourceforge.net/export/rss2_projnews.php?group_id=143864" />

</head>
<body><p><a name="top"></a></p>
	<div id="wrap">
		<div id="header">
			<div class="head_c"></div>
			<div class="head_l"></div>
			<div class="head_content">
				<div id="logo">
					<a href="http://ogre4j.sourceforge.net/"><img src="../images/logo.gif" alt="Ogre4J Logo"/></a>
				</div>
				<div id="logotxt">
					<h1>Java Native Interfaces for OGRE - Videos</h1>
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
							<h2><a name="project">ogre4j Video Examples</a></h2>
						</div>
						<div class="content_main">
                                                
                                                      <p id="player"><a href="http://www.macromedia.com/go/getflashplayer">Get the Flash Player</a> to see this player.</p>
                                                      <script type="text/javascript">
                                                              var so = new SWFObject("flash/mediaplayer.swf","mpl","557","439","2");
                                                              so.addParam("allowfullscreen","true");
                                                              so.addVariable("enablejs","true");
                                                              so.addVariable("file","../videos/video1.flv");
                                                              so.addVariable("image","images/video1.png");
                                                              so.addVariable("javascriptid","mpl");
                                                              so.addVariable("displayheight","419");
                                                              so.write("player");
                                                </script>
                                                      
                                                      <?php

                                                          $videoRoot="./videos/";
                                                          $imagesRoot="./images";
                                                          $imagesHandle = opendir($imagesRoot);
                                                          $videoHandle = opendir($videoRoot);
                                                          $images = "";
                                                          
                                                          while($image = readdir($imagesHandle)){
                                                                  $images .= $image;
                                                          }
                              
                                                          while($file = readdir($videoHandle)){
                              
                                                              if ($file != "." && $file != ".." && $file != "index.php" && strpos($file, ".flv") != false) {
                                                                  $dateiname = str_replace(".flv", "", $file);
                                                                  
                                                                  $preview = "noPreview";
                                                                  
                                                                  if(strpos($images, $dateiname) != false){
                                                                      $preview = $dateiname;
                                                                  }
                                                                  
                                                                  print "<h3><a href=\"javascript:loadFile({file:'../videos/".$file."',title:'".$dateiname."',id:'1',image:'images/".$preview.".png'})\">".$dateiname."</a></h3>
                                                                        <br/>
                                                                        <p><a href=\"javascript:loadFile({file:'../videos/".$file."',title:'".$dateiname."',id:'1',image:'images/".$preview.".png'})\"><img src=\"images/".$preview.".png\" height=\"120\" width=\"160\" alt=\"".$dateiname." - Preview\"/></a></p>";
                                                              }
                                                              
                                                          }
                                                          closedir($imagesHandle);
                                                          closedir($videoHandle);
                                                      ?>
		
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