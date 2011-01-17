<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:msxsl="urn:schemas-microsoft-com:xslt">
<xsl:output method="html" encoding="UTF-8"/>
<xsl:key name="cat" match="category" use="@name"/>
<xsl:template match="/">
<xsl:for-each select="site">
	<html>
	<head>
	<title>ogre4j Eclipse Update Site</title>
	</head>
	<body>
	<p><xsl:value-of select="description"/></p>
	<table class="out">
	<xsl:for-each select="category-def">
		<xsl:sort select="@label" order="ascending" case-order="upper-first"/>
		<xsl:sort select="@name" order="ascending" case-order="upper-first"/>
	<xsl:if test="count(key('cat',@name)) != 0">
			<tr>
				<th>
					<xsl:value-of select="@name"/>
				</th>
				<th>
					<xsl:value-of select="@label"/>
				</th>
			</tr>
			<xsl:for-each select="key('cat',@name)">
			<xsl:sort select="ancestor::feature//@version" order="ascending"/>
			<xsl:sort select="ancestor::feature//@id" order="ascending" case-order="upper-first"/>
			<tr>
				
				<td id="indent">
						<xsl:choose>
						<xsl:when test="ancestor::feature//@label">
							<a href="{ancestor::feature//@url}"><xsl:value-of select="ancestor::feature//@label"/></a>
							<br/>
							<div id="indent">
							(<xsl:value-of select="ancestor::feature//@id"/> - <xsl:value-of select="ancestor::feature//@version"/>)
							</div>
						</xsl:when>
						<xsl:otherwise>
						<a href="{ancestor::feature//@url}"><xsl:value-of select="ancestor::feature//@id"/> - <xsl:value-of select="ancestor::feature//@version"/></a>
						</xsl:otherwise>
						</xsl:choose>
						<br/>
				</td>
				<td>
					<table class="inner">
						<xsl:if test="ancestor::feature//@os">
							<tr><td id="indent"><strong>Operating Systems:</strong></td>
							<td id="indent"><xsl:value-of select="ancestor::feature//@os"/></td>
							</tr>
						</xsl:if>
						<xsl:if test="ancestor::feature//@ws">
							<tr><td id="indent"><strong>Windows Systems:</strong></td>
							<td id="indent"><xsl:value-of select="ancestor::feature//@ws"/></td>
							</tr>
						</xsl:if>
						<xsl:if test="ancestor::feature//@nl">
							<tr><td id="indent"><strong>Languages:</strong></td>
							<td id="indent"><xsl:value-of select="ancestor::feature//@nl"/></td>
							</tr>
						</xsl:if>
						<xsl:if test="ancestor::feature//@arch">
							<tr><td id="indent"><strong>Architecture:</strong></td>
							<td id="indent"><xsl:value-of select="ancestor::feature//@arch"/></td>
							</tr>
						</xsl:if>
					</table>
				</td>
			</tr>
			</xsl:for-each>
		</xsl:if>
	</xsl:for-each>
	<xsl:if test="count(feature)  &gt; count(feature/category)">
	<tr>
		<td colspan="2">
		Uncategorized
		</td>
	</tr>
	</xsl:if>
	<xsl:choose>
	<xsl:when test="function-available('msxsl:node-set')">
	   <xsl:variable name="rtf-nodes">
		<xsl:for-each select="feature[not(category)]">
			<xsl:sort select="@id" order="ascending" case-order="upper-first"/>
			<xsl:sort select="@version" order="ascending"/>
			<xsl:value-of select="."/>
			<xsl:copy-of select="."/>
		</xsl:for-each>
	   </xsl:variable>
	   <xsl:variable name="myNodeSet" select="msxsl:node-set($rtf-nodes)/*"/>
	<xsl:for-each select="$myNodeSet">
	<tr>
		<xsl:choose>
		<xsl:when test="position() mod 2 = 1">
		<xsl:attribute name="class">dark-row</xsl:attribute>
		</xsl:when>
		<xsl:otherwise>
		<xsl:attribute name="class">light-row</xsl:attribute>
		</xsl:otherwise>
		</xsl:choose>
		<td id="indent">
			<xsl:choose>
			<xsl:when test="@label">
				<a href="{@url}"><xsl:value-of select="@label"/></a>
				<br/>
				<div id="indent">
				(<xsl:value-of select="@id"/> - <xsl:value-of select="@version"/>)
				</div>
			</xsl:when>
			<xsl:otherwise>
				<a href="{@url}"><xsl:value-of select="@id"/> - <xsl:value-of select="@version"/></a>
			</xsl:otherwise>
			</xsl:choose>
			<br/><br/>
		</td>
		<td>
			<table>
				<xsl:if test="@os">
					<tr><td id="indent"><strong>Operating Systems:</strong></td>
					<td id="indent"><xsl:value-of select="@os"/></td>
					</tr>
				</xsl:if>
				<xsl:if test="@ws">
					<tr><td id="indent"><strong>Windows Systems:</strong></td>
					<td id="indent"><xsl:value-of select="@ws"/></td>
					</tr>
				</xsl:if>
				<xsl:if test="@nl">
					<tr><td id="indent"><strong>Languages:</strong></td>
					<td id="indent"><xsl:value-of select="@nl"/></td>
					</tr>
				</xsl:if>
				<xsl:if test="@arch">
					<tr><td id="indent"><strong>Architecture:</strong></td>
					<td id="indent"><xsl:value-of select="@arch"/></td>
					</tr>
				</xsl:if>
			</table>
		</td>
	</tr>
	</xsl:for-each>
	</xsl:when>
	<xsl:otherwise>
	<xsl:for-each select="feature[not(category)]">
	<xsl:sort select="@id" order="ascending" case-order="upper-first"/>
	<xsl:sort select="@version" order="ascending"/>
	<tr>
		<xsl:choose>
		<xsl:when test="count(preceding-sibling::feature[not(category)]) mod 2 = 1">
		<xsl:attribute name="class">dark-row</xsl:attribute>
		</xsl:when>
		<xsl:otherwise>
		<xsl:attribute name="class">light-row</xsl:attribute>
		</xsl:otherwise>
		</xsl:choose>
		<td id="indent">
			<xsl:choose>
			<xsl:when test="@label">
				<a href="{@url}"><xsl:value-of select="@label"/></a>
				<br/>
				<div id="indent">
				(<xsl:value-of select="@id"/> - <xsl:value-of select="@version"/>)
				</div>
			</xsl:when>
			<xsl:otherwise>
				<a href="{@url}"><xsl:value-of select="@id"/> - <xsl:value-of select="@version"/></a>
			</xsl:otherwise>
			</xsl:choose>
			<br/><br/>
		</td>
		<td>
			<table>
				<xsl:if test="@os">
					<tr><td><strong>Operating Systems:</strong></td>
					<td id="indent"><xsl:value-of select="@os"/></td>
					</tr>
				</xsl:if>
				<xsl:if test="@ws">
					<tr><td><strong>Windows Systems:</strong></td>
					<td id="indent"><xsl:value-of select="@ws"/></td>
					</tr>
				</xsl:if>
				<xsl:if test="@nl">
					<tr><td><strong>Languages:</strong></td>
					<td id="indent"><xsl:value-of select="@nl"/></td>
					</tr>
				</xsl:if>
				<xsl:if test="@arch">
					<tr><td><strong>Architecture:</strong></td>
					<td id="indent"><xsl:value-of select="@arch"/></td>
					</tr>
				</xsl:if>
			</table>
		</td>
	</tr>
	</xsl:for-each>
	</xsl:otherwise>
	</xsl:choose>
	</table>
	</body>
	</html>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>