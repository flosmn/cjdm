<?php require('simplepie.inc'); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
      <html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>

<script type="text/javascript">
    <!--
    if (navigator.appName.indexOf("Explorer") != -1){
        if (navigator.appVersion.indexOf("MSIE 6.0") != -1){
            document.write("<link href=\".\/css\/styleIE6.css\" rel=\"stylesheet\" type=\"text\/css\"\/>");
        }
        else{
            document.write("<link href=\".\/css\/style.css\" rel=\"stylesheet\" type=\"text\/css\"\/>");
        }
    }
    else{
        document.write("<link href=\".\/css\/style.css\" rel=\"stylesheet\" type=\"text\/css\"\/>");
    }
    -->
</script>

<title>ogre4j | ogre4j - Java Native Interfaces for OGRE</title>
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
					<a href="http://ogre4j.sourceforge.net/"><img src="images/logo.gif" alt="Ogre4J Logo"/></a>
				</div>
				<div id="logotxt">
					<h1>Java Native Interfaces for OGRE</h1>
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
								<li><a href="#project" title="Project Summary">Project Summary</a></li>
								<li><a href="#news" title="News">News</a></li>
								<li><a href="#file" title="File Releases">File Releases</a></li>
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
							<p><a href="http://ogre3d.org/"><img src="images/ogre3DLogo.gif" alt="Ogre3D-Logo"/></a></p>
						</div>
						<div class="nav_foot"></div>
					</div>
				</div>
				
				<div class="content">
					<div class="content_inner">
					
						<div class="content_head">
							<h2><a name="project">Project Summary</a></h2>
							<div class="head_menu"><p><a href="#top"><img src="images/upArrow.png" alt="Top"/> Top</a> | <a href="https://sourceforge.net/export/rss2_projsummary.php?group_id=143864"><img src="images/rss.gif" title="Project Summary RSS Feed" alt="rssFeed"/></a></p></div>
						</div>
						<div class="content_main">
							
							<p>ogre4j is a project that enables the use of the <a href="http://ogre3d.org/">OGRE (Object-Oriented Graphics Rendering Engine)</a> libraries in Java applications.<br />
							The first approach was made by Ivica Aracic aka bytelord <a href="http://www.bytelords.de/">(http://www.bytelords.de)</a>. Thanks to him for taking the first steps!</p>
							<p>The last stable CVS version located at the OGRE SourceForge.net project page is based on his code but the new team is working on a complete new version that will be more powerful (hopefully). 
							To ease the pain for those who are using OGRE in C++ the usage of ogre4j will be
							straight forward. Every public interface of the OGRE library will be available through <a href="http://java.sun.com/j2se/1.5.0/docs/guide/jni/">Java
							Native Interface (JNI)</a> in the Java world.</p>
							
							<?php

							$projsummary = new SimplePie();
							$projsummary->enable_cache(false);
							$projsummary->set_feed_url('http://sourceforge.net/export/rss2_projsummary.php?group_id=143864');
							//$projsummary->set_cache_location('/home/groups/o/og/ogre4j/htdocs/cache');
							$projsummary->enable_order_by_date(false);
							$projsummary->init();
							$projsummary->handle_content_type();
							if ($projsummary->data) { ?>
							
							<div class="psum">
							<p>
							    <?php
							    foreach($projsummary->get_items() as $item) {
							    ?>
								   <?php 
									list ($eins, $zwei) = split(':', $item->get_title() );
									echo '<strong>'.$eins.': </strong>';
									echo '<a href="'.$item->get_permalink().'">'.$zwei.'</a>';
								   ?>
								<br/>
							    <?php } ?>
							<?php } ?>
                                                        
							</p>
							</div>
						</div>
						<div class="content_foot"></div>
						
						<div class="content_head">
							<h2><a name="news">News</a></h2>
							<div class="head_menu"><p><a href="#top"><img src="images/upArrow.png" alt="Top"/> Top</a> | <a href="https://sourceforge.net/export/rss2_projnews.php?group_id=143864"><img src="images/rss.gif" title="Project News RSS Feed" alt="rssFeed"/></a></p></div>
						</div>
						<div class="content_main">
							
							<?php
							$newsfeed = new SimplePie();
							$newsfeed->enable_cache(false);
							$newsfeed->set_feed_url('http://sourceforge.net/export/rss2_projnews.php?group_id=143864&amp;rss_fulltext=1');
							$newsfeed->init();
							$newsfeed->handle_content_type();
							if ($newsfeed->data) { ?>
							
							<?php
							    $max = $newsfeed->get_item_quantity(5);
							    for ($x = 0; $x < $max; $x++) {
								    $item = $newsfeed->get_item($x);
							    ?>
								<h3>
								    <a href="<?php echo $item->get_permalink(); ?>">
									<?php echo $item->get_title(); ?>
								    </a>
								</h3>
								<div class="head3_menu">
									<p>
									    <a href="<?php echo $item->add_to_delicious(); ?>"><img src="images/delLogo.png" alt="Del.icio.us Logo" title="Del.icio.us" /></a> 
									    <a href="<?php echo $item->add_to_digg(); ?>"><img src="images/diggLogo.gif" alt="Digg this! Logo" title="Digg this!" /></a> 
									    <a href="<?php echo $item->add_to_newsvine(); ?>"><img src="images/newsvLogo.gif" alt="Newsvine Logo" title="Newsvine" /></a>
									</p>
								</div>
								<p><?php echo $item->get_date('j M Y'); ?></p>
								<p><?php echo $item->get_description(); ?></p>
								<br/>
							    <?php } ?>
							<?php } ?>
							
							<p><a href="https://sourceforge.net/news/?group_id=143864">News archive &raquo;</a></p>
						</div>
						<div class="content_foot"></div>
						
						<div class="content_head">
							<h2><a name="file">File Releases</a></h2>
							<div class="head_menu"><p><a href="#top"><img src="images/upArrow.png" alt="Top"/> Top</a> | <a href="https://sourceforge.net/export/rss2_projfiles.php?group_id=143864"><img src="images/rss.gif" title="File Releases RSS Feed" alt="rssFeed"/></a></p></div>
						</div>
						<div class="content_main">
							
							<?php
							$projfiles = new SimplePie();
							$projfiles->enable_cache(false);
							$projfiles->set_feed_url('http://sourceforge.net/export/rss2_projfiles.php?group_id=143864');
							$projfiles->init();
							$projfiles->handle_content_type();
							if ($projfiles->data) { ?>
							
							<?php
							    $max = $projfiles->get_item_quantity(5);
							    for ($x = 0; $x < $max; $x++) {
								    $item = $projfiles->get_item($x);
							    ?>
								<h3>
								    <a href="<?php echo $item->get_permalink(); ?>">
									<?php echo $item->get_title(); ?>
								    </a>
								</h3>
								<div class="head3_menu">
									<p>
										<a href="<?php echo $item->add_to_delicious(); ?>"><img src="images/delLogo.png" alt="Del.icio.us Logo" title="Del.icio.us" /></a> 
										<a href="<?php echo $item->add_to_digg(); ?>"><img src="images/diggLogo.gif" alt="Digg this! Logo" title="Digg this!" /></a> 
										<a href="<?php echo $item->add_to_newsvine(); ?>"><img src="images/newsvLogo.gif" alt="Newsvine Logo" title="Newsvine" /></a>
									</p>
								</div>
								<p><?php echo $item->get_date('j M Y'); ?></p>
								<?php 
								$camp = str_replace("&", "&amp;", $item->get_description());
								echo '<p>'.$camp.'</p>';
								?>
								<br/>
							    <?php } ?>
							<?php } ?>
                                                        
							
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
