<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ss="http://jw.nju.edu.cn/schema" xmlns:aa="http://www.nju.edu.cn/schema">
	<xsl:output method="xml" indent="yes" version="1.0" encoding="UTF-8"/>
	<xsl:template match="/">
		<xsl:element name="课程成绩列表" namespace="http://jw.nju.edu.cn/schema">
			<xsl:for-each select="ss:StudentList">
				<xsl:for-each select="./ss:Student">
					<xsl:for-each select="./ss:CourseList/ss:Course">
						<xsl:for-each select="./ss:MyScore">
							<xsl:apply-templates/>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	<xsl:template match="//ss:NormalScore">
		<xsl:element name="课程成绩" namespace="http://jw.nju.edu.cn/schema">
			<xsl:attribute name="课程编号"><xsl:value-of select="./parent::ss:MyScore/parent::ss:Course/@CourseID"/></xsl:attribute>
			<xsl:attribute name="成绩性质">平时成绩</xsl:attribute>
			<xsl:element name="成绩" namespace="http://jw.nju.edu.cn/schema">
				<xsl:element name="学号" namespace="http://jw.nju.edu.cn/schema">
					<xsl:value-of select="./parent::ss:MyScore/parent::ss:Course/parent::ss:CourseList/parent::ss:Student/aa:personInfo/aa:id"/>
				</xsl:element>
				<xsl:element name="得分" namespace="http://jw.nju.edu.cn/schema">
					<xsl:value-of select="."/>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<xsl:template name="testscore" match="//ss:TestScore">
		<xsl:element name="课程成绩" namespace="http://jw.nju.edu.cn/schema">
			<xsl:attribute name="课程编号"><xsl:value-of select="./../../@CourseID"/></xsl:attribute>
			<xsl:attribute name="成绩性质">期末成绩</xsl:attribute>
			<xsl:element name="成绩" namespace="http://jw.nju.edu.cn/schema">
				<xsl:element name="学号" namespace="http://jw.nju.edu.cn/schema">
					<xsl:value-of select="./../../../../aa:personInfo/aa:id"/>
				</xsl:element>
				<xsl:element name="得分" namespace="http://jw.nju.edu.cn/schema">
					<xsl:value-of select="."/>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<xsl:template name="finalscore" match="//ss:FinalScore">
		<xsl:element name="课程成绩" namespace="http://jw.nju.edu.cn/schema">
			<xsl:attribute name="课程编号" ><xsl:value-of select="./../../@CourseID"/></xsl:attribute>
			<xsl:attribute name="成绩性质">总评成绩</xsl:attribute>
			<xsl:element name="成绩" namespace="http://jw.nju.edu.cn/schema">
				<xsl:element name="学号" namespace="http://jw.nju.edu.cn/schema">
					<xsl:value-of select="./../../../../aa:personInfo/aa:id"/>
				</xsl:element>
				<xsl:element name="得分" namespace="http://jw.nju.edu.cn/schema">
					<xsl:value-of select="."/>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
