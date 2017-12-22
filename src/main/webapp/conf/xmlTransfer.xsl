<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<meta charset="utf-8"/>
    			<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
				<title>jobcard</title>
				<link href="/ems/ui/plugins/annotation/css/annotator.min.css" rel="stylesheet" type="text/css" />
				<link href="/ems/ui/plugins/annotation/css/annotator.style.css" rel="stylesheet" type="text/css" />
				<style type="text/css">
					table {
					border-collapse: collapse;
					border: 1px solid #000;
					width:100%;
					}
					table td {
					border: 1px solid #000;
					}
					table td:last-child {
					text-align:center;
					}
					table th {
					text-align:center;
					}
					table th:first-child {
					width: 80%;
					}
				</style>
			</head>
			<body>
				<xsl:apply-templates select="//instructions" />
			</body>
			<script src="/ems/ui/js/jquery-1.9.1.min.js"></script>
			<script src="/ems/ui/plugins/annotation/js/annotator-full.min.js"></script>
			<script type="text/javascript">
				<!-- 获取url中的文件名 -->
				var url = window.location.href;
				var docOid = url.replace(/(.*\/)*([^.]+).*/ig,"$2");
				<!-- end -->
				
				$(document.body).annotator().annotator('addPlugin', 'Store', {
					prefix: '/ems/annotator',
					loadFromSearch: {
						'limit': 0,
						'all_fields': 1,
						'uri': '/ems/annotator/search?oid='+docOid
			 		},
					urls: {
						// These are the default URLs.
						create: '/annotations',
						update: '/annotations/:id',
						destroy: '/annotations/:id',
						search: '/search?oid='+docOid
					}
				 });
			</script>
		</html>
	</xsl:template>

	<xsl:template match="//instructions">
		<table>
			<thead>
				<tr>
					<xsl:for-each select="./table/tgroup/thead/row/entry">
						<th>
							<span>
								<xsl:value-of select="./para[1]" />
							</span>
							<br />
							<span>
								<xsl:value-of select="./para[2]" />
							</span>
						</th>
					</xsl:for-each>
				</tr>
			</thead>
			<tbody>
				<xsl:apply-templates select="./table/tgroup/tbody" />
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="//instructions/table/tgroup/tbody">
		<xsl:for-each select="row">
			<tr>
				<xsl:for-each select="entry">
					<xsl:choose>
						<xsl:when test="position() = last()">
							<td colspan="3">
								<xsl:apply-templates />
							</td>
						</xsl:when>
						<xsl:otherwise>
							<td>
								<xsl:apply-templates />
							</td>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:for-each>
			</tr>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="//row/entry/topic">
		<ul>
			<li>
				<xsl:apply-templates select="./title-zh" />
				<br />
				<xsl:apply-templates select="./title" />
			</li>
		</ul>
	</xsl:template>

	<xsl:template match="//row/entry/task">
		<p style="color:red">
			<xsl:value-of select="./effect/@efftext" />
		</p>
		<p>
			<xsl:text>Task </xsl:text>
			<xsl:value-of select="./@chapnbr" />
			<xsl:text>-</xsl:text>
			<xsl:value-of select="./@sectnbr" />
			<xsl:text>-</xsl:text>
			<xsl:value-of select="./@subjnbr" />
			<xsl:text>-</xsl:text>
			<xsl:value-of select="./@func" />
			<xsl:text>-</xsl:text>
			<xsl:value-of select="./@seq" />
			<xsl:if test="./@confltr">
				<xsl:text>-</xsl:text>
				<xsl:value-of select="./@confltr" />
			</xsl:if>
		</p>
		<p style="color:red">
			<xsl:value-of select="./effect/@efftext" />
		</p>
		<ul>
			<li>
				<xsl:apply-templates select="./title-zh" />
				<br />
				<xsl:apply-templates select="./title" />
				<br />
				<span style="color:red">
					<xsl:if test="warning-zh[1]">
						<xsl:text>警	告：</xsl:text>
						<xsl:value-of select="warning-zh[1]" />
						<br />
						<xsl:text>WARNING：</xsl:text>
						<xsl:value-of select="warning[1]" />
						<br />
					</xsl:if>
					<xsl:if test="warning-zh[2]">
						<xsl:text>警	告：</xsl:text>
						<xsl:value-of select="warning-zh[2]" />
						<br />
						<xsl:text>WARNING：</xsl:text>
						<xsl:value-of select="warning[2]" />
						<br />
					</xsl:if>
				</span>
				<br />
				<xsl:if test="assodata">
					<xsl:for-each select="assodata/einlst/eindata">
						<label style="color:red;">
							<xsl:value-of select="./effect/@effrg" />
						</label>
						<br />
						<label style="text-decoration: underline;color:blue;">
							<xsl:value-of select="./ein" />
						</label>
						<br />
					</xsl:for-each>
				</xsl:if>
			</li>
		</ul>
		<xsl:apply-templates select="./tfmatr" />
	</xsl:template>

	<xsl:template match="//row/entry/task/tfmatr/pretopic">
		<ul>
			<li>
				<xsl:apply-templates select="./title-zh" />
				<br />
				<xsl:apply-templates select="./title" />
				<br />
				<br />
				<xsl:if test="para[1]">
					<label style="color:fuchsia;">
						<xsl:apply-templates select="para[1]" />
					</label>
					<br />
					<br />
				</xsl:if>
				<xsl:if test="para[2]">
					<xsl:apply-templates select="para[2]" />
					<br />
				</xsl:if>
				<xsl:if test="para[3]">
					<xsl:apply-templates select="para[3]" />
					<br />
				</xsl:if>
			</li>
		</ul>
		<xsl:apply-templates select="./list1" />
	</xsl:template>

	<xsl:template match="//row/entry/task/tfmatr/pretopic/list1">
		<ul>
			<ul>
				<xsl:for-each select="./l1item">
					<li>
						<xsl:value-of select="para[1]" />
						<br />
						<xsl:value-of select="para[2]" />
						<br />
						<xsl:if test="note-zh">
							<span style="color:red">
								<xsl:text>注 意:</xsl:text>
								<xsl:value-of select="note-zh" />
								<br />
								<xsl:text>Note: </xsl:text>
								<xsl:value-of select="note" />
							</span>
						</xsl:if>
						<xsl:if test="table">
							<br />
							<table style="border-collapse:collapse;border: 1px dashed #000">
								<thead>
									<tr>
										<xsl:for-each select="table/tgroup/thead/row/entry">
											<th style="width: 10%;">
												<xsl:value-of select="para" />
											</th>
										</xsl:for-each>
									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="table/tgroup/tbody/row">
										<tr>
											<xsl:for-each select="entry">
												<td style="border: 1px dashed #000">
													<xsl:apply-templates select="." />
												</td>
											</xsl:for-each>
										</tr>
									</xsl:for-each>
								</tbody>
							</table>
						</xsl:if>
					</li>
					<xsl:if test="list2">
						<ul>
							<xsl:for-each select="list2/l2item">
								<li>
									<xsl:value-of select="para[1]" />
									<br />
									<xsl:value-of select="para[2]" />
									<br />
									<span style="color:red">
										<xsl:text>注 意:</xsl:text>
										<xsl:value-of select="note-zh" />
										<br />
										<xsl:text>Note: </xsl:text>
										<xsl:value-of select="note" />
									</span>
								</li>
							</xsl:for-each>
						</ul>
					</xsl:if>
				</xsl:for-each>
			</ul>
		</ul>
	</xsl:template>

	<xsl:template match="//row/entry/subtask">
		<p>
			&#160;&#160;
			<font size="1">
				<xsl:text>SUBTASK </xsl:text>
				<xsl:value-of select="./@chapnbr" />
				<xsl:text>-</xsl:text>
				<xsl:value-of select="./@sectnbr" />
				<xsl:text>-</xsl:text>
				<xsl:value-of select="./@subjnbr" />
				<xsl:text>-</xsl:text>
				<xsl:value-of select="./@func" />
				<xsl:text>-</xsl:text>
				<xsl:value-of select="./@seq" />
			</font>
		</p>
		<p style="color:red">
			<xsl:apply-templates select="./effect/@efftext" />
		</p>
		<xsl:apply-templates select="./list1" />
	</xsl:template>

	<xsl:template match="//instructions/table/tgroup/tbody/row/entry/para">
		<ul>
			<ul style="list-style: none;">
				<li>
					<xsl:value-of select="." />
					<xsl:if test="./input">
						<input>
							<xsl:attribute name="type">text</xsl:attribute>
							<xsl:attribute name="xpath"><xsl:value-of
								select="./input/@xpath" /></xsl:attribute>
							<xsl:attribute name="value"><xsl:value-of
								select="./input/@value" /></xsl:attribute>
						</input>
					</xsl:if>
				</li>
			</ul>
		</ul>
	</xsl:template>

	<xsl:template match="//row/entry/subtask/list1">
		<ul>
			<ul>
				<xsl:for-each select="./l1item">
					<li>
						<xsl:if test="./regulatory/regulation[1]">
							<xsl:value-of select="./regulatory/regulation[1]" />
							<br />
						</xsl:if>
						<xsl:if test="./regulatory/regulation[2]">
							<xsl:value-of select="./regulatory/regulation[2]" />
							<br />
						</xsl:if>
						<xsl:if test="warning-zh">
							<span style="color:red;">
								<xsl:text>警	告：</xsl:text>
								<xsl:value-of select="warning-zh" />
								<br />
								<xsl:text>WARNING：</xsl:text>
								<xsl:value-of select="warning" />
								<br />
							</span>
						</xsl:if>
						<xsl:value-of select="para[1]" />
						<br />
						<xsl:value-of select="para[2]" />
						<br />
						<span style="color:red">
							<xsl:if test="note-zh[1]">
								<xsl:text>注 意:</xsl:text>
								<xsl:value-of select="note-zh[1]" />
								<br />
								<xsl:text>Note: </xsl:text>
								<xsl:value-of select="note[1]" />
								<br />
							</xsl:if>
							<xsl:if test="note-zh[2]">
								<xsl:text>注 意:</xsl:text>
								<xsl:value-of select="note-zh[2]" />
								<br />
								<xsl:text>Note: </xsl:text>
								<xsl:value-of select="note[2]" />
								<br />
							</xsl:if>
						</span>
						<xsl:if test="table">
							<br />
							<table style="border-collapse:collapse;border: 1px dashed #000">
								<thead>
									<tr>
										<xsl:for-each select="table/tgroup/thead/row/entry">
											<th style="width: 10%;">
												<xsl:value-of select="para" />
											</th>
										</xsl:for-each>
									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="table/tgroup/tbody/row">
										<tr>
											<xsl:for-each select="entry">
												<td style="border: 1px dashed #000">
													<xsl:apply-templates select="." />
												</td>
											</xsl:for-each>
										</tr>
									</xsl:for-each>
								</tbody>
							</table>
						</xsl:if>
						<xsl:if test="cblst">
							<br />
							<table style="border-collapse:collapse;border: 1px dashed #000">
								<thead>
									<tr>
										<th style="width:10%;">PANEL</th>
										<th>DESCRIPTION</th>
										<th>FIN</th>
										<th>LOCATION</th>
									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="cblst/cbsublst/cbdata">
										<tr>
											<td style="border: 1px dashed #000;">
												<xsl:apply-templates select="./pan" />
											</td>
											<td style="border: 1px dashed #000;">
												<xsl:apply-templates select="./cbname" />
											</td>
											<td style="border: 1px dashed #000;">
												<xsl:apply-templates select="./cb" />
											</td>
											<td style="border: 1px dashed #000;">
												<xsl:apply-templates select="./cbloc" />
											</td>
										</tr>
									</xsl:for-each>
								</tbody>
							</table>
						</xsl:if>
					</li>
					<xsl:if test="list2">
						<ul>
							<xsl:for-each select="list2/l2item">
								<li>
									<xsl:if test="effect">
										<p style="color:red">
											(
											<xsl:value-of select="./effect/@efftext" />
											)
										</p>
									</xsl:if>
									<xsl:if test="./regulatory/regulation[1]">
										<xsl:value-of select="./regulatory/regulation[1]" />
										<br />
									</xsl:if>
									<xsl:if test="./regulatory/regulation[2]">
										<xsl:value-of select="./regulatory/regulation[2]" />
										<br />
									</xsl:if>
									<xsl:if test="warning-zh">
										<span style="color:red;">
											<xsl:text>警	告：</xsl:text>
											<xsl:value-of select="warning-zh" />
											<br />
											<xsl:text>WARNING：</xsl:text>
											<xsl:value-of select="warning" />
											<br />
										</span>
									</xsl:if>
									<xsl:value-of select="para[1]" />
									<br />
									<xsl:value-of select="para[2]" />
									<br />
									<span style="color:red">
										<xsl:if test="note-zh[1]">
											<xsl:text>注 意:</xsl:text>
											<xsl:value-of select="note-zh[1]" />
											<br />
											<xsl:text>Note: </xsl:text>
											<xsl:value-of select="note[1]" />
											<br />
										</xsl:if>
										<xsl:if test="note-zh[2]">
											<xsl:text>注 意:</xsl:text>
											<xsl:value-of select="note-zh[2]" />
											<br />
											<xsl:text>Note: </xsl:text>
											<xsl:value-of select="note[2]" />
											<br />
										</xsl:if>
										<xsl:if test="note-zh[3]">
											<xsl:text>注 意:</xsl:text>
											<xsl:value-of select="note-zh[3]" />
											<br />
											<xsl:text>Note: </xsl:text>
											<xsl:value-of select="note[3]" />
											<br />
										</xsl:if>
									</span>

									<xsl:if test="table">
										<br />
										<table style="border-collapse:collapse;border: 1px dashed #000">
											<thead>
												<tr>
													<xsl:for-each select="table/tgroup/thead/row/entry">
														<th style="width: 10%;">
															<xsl:value-of select="para" />
														</th>
													</xsl:for-each>
												</tr>
											</thead>
											<tbody>
												<xsl:for-each select="table/tgroup/tbody/row">
													<tr>
														<xsl:for-each select="entry">
															<td style="border: 1px dashed #000">
																<xsl:apply-templates select="." />
															</td>
														</xsl:for-each>
													</tr>
												</xsl:for-each>
											</tbody>
										</table>
									</xsl:if>
								</li>
								<xsl:if test="list3">
									<ul>
										<xsl:for-each select="list3/l3item">
											<li>
												<xsl:if test="effect">
													<p style="color:red">
														(
														<xsl:value-of select="./effect/@efftext" />
														)
													</p>
												</xsl:if>
												<xsl:if test="./regulatory/regulation[1]">
													<xsl:value-of select="./regulatory/regulation[1]" />
													<br />
												</xsl:if>
												<xsl:if test="./regulatory/regulation[2]">
													<xsl:value-of select="./regulatory/regulation[2]" />
													<br />
												</xsl:if>
												<xsl:value-of select="para[1]" />
												<br />
												<xsl:value-of select="para[2]" />
												<br />
												<span style="color:red">
													<xsl:if test="note-zh[1]">
														<xsl:text>注 意:</xsl:text>
														<xsl:value-of select="note-zh[1]" />
														<br />
														<xsl:text>Note: </xsl:text>
														<xsl:value-of select="note[1]" />
														<br />
													</xsl:if>
													<xsl:if test="note-zh[2]">
														<xsl:text>注 意:</xsl:text>
														<xsl:value-of select="note-zh[2]" />
														<br />
														<xsl:text>Note: </xsl:text>
														<xsl:value-of select="note[2]" />
														<br />

													</xsl:if>
													<xsl:if test="note-zh[3]">
														<xsl:text>注 意:</xsl:text>
														<xsl:value-of select="note-zh[3]" />
														<br />
														<xsl:text>Note: </xsl:text>
														<xsl:value-of select="note[3]" />
														<br />
													</xsl:if>
												</span>
												<xsl:if test="table/tgroup[@cols='2']">
													<br />
													<table style="border-collapse:collapse;border: 1px dashed #000">
														<tbody>
															<xsl:for-each select="table/tgroup/tbody/row">
																<tr>
																	<xsl:for-each select="entry">
																		<td style="border: 1px dashed #000;width:5%;">
																			<xsl:apply-templates select="." />
																		</td>
																	</xsl:for-each>
																</tr>
															</xsl:for-each>
														</tbody>
													</table>
												</xsl:if>
											</li>
											<xsl:if test="list4">
												<ul>
													<xsl:for-each select="list4/l4item">
														<li>
															<xsl:if test="./regulatory/regulation[1]">
																<xsl:value-of select="./regulatory/regulation[1]" />
																<br />
															</xsl:if>
															<xsl:if test="./regulatory/regulation[2]">
																<xsl:value-of select="./regulatory/regulation[2]" />
																<br />
															</xsl:if>
															<xsl:value-of select="para[1]" />
															<br />
															<xsl:value-of select="para[2]" />
															<br />
															<span style="color:red">
																<xsl:if test="note-zh[1]">
																	<xsl:text>注 意:</xsl:text>
																	<xsl:value-of select="note-zh[1]" />
																	<br />
																	<xsl:text>Note: </xsl:text>
																	<xsl:value-of select="note[1]" />
																	<br />
																</xsl:if>
																<xsl:if test="note-zh[2]">
																	<xsl:text>注 意:</xsl:text>
																	<xsl:value-of select="note-zh[2]" />
																	<br />
																	<xsl:text>Note: </xsl:text>
																	<xsl:value-of select="note[2]" />
																	<br />
																</xsl:if>
																<xsl:if test="note-zh[3]">
																	<xsl:text>注 意:</xsl:text>
																	<xsl:value-of select="note-zh[3]" />
																	<br />
																	<xsl:text>Note: </xsl:text>
																	<xsl:value-of select="note[3]" />
																	<br />
																</xsl:if>
															</span>
														</li>
														<xsl:if test="list5">
															<ul>
																<xsl:for-each select="list5/l5item">
																	<li>
																		<xsl:if test="./regulatory/regulation[1]">
																			<xsl:value-of select="./regulatory/regulation[1]" />
																			<br />
																		</xsl:if>
																		<xsl:if test="./regulatory/regulation[2]">
																			<xsl:value-of select="./regulatory/regulation[2]" />
																			<br />
																		</xsl:if>
																		<xsl:value-of select="para[1]" />
																		<br />
																		<xsl:value-of select="para[2]" />
																		<br />
																		<span style="color:red">
																			<xsl:if test="note-zh[1]">
																				<xsl:text>注 意:</xsl:text>
																				<xsl:value-of select="note-zh[1]" />
																				<br />
																				<xsl:text>Note: </xsl:text>
																				<xsl:value-of select="note[1]" />
																				<br />
																			</xsl:if>
																			<xsl:if test="note-zh[2]">
																				<xsl:text>注 意:</xsl:text>
																				<xsl:value-of select="note-zh[2]" />
																				<br />
																				<xsl:text>Note: </xsl:text>
																				<xsl:value-of select="note[2]" />
																				<br />
																			</xsl:if>
																			<xsl:if test="note-zh[3]">
																				<xsl:text>注 意:</xsl:text>
																				<xsl:value-of select="note-zh[3]" />
																				<br />
																				<xsl:text>Note: </xsl:text>
																				<xsl:value-of select="note[3]" />
																				<br />
																			</xsl:if>
																		</span>
																	</li>
																</xsl:for-each>
															</ul>
														</xsl:if>
													</xsl:for-each>
												</ul>
											</xsl:if>
										</xsl:for-each>
									</ul>
								</xsl:if>
							</xsl:for-each>
						</ul>
					</xsl:if>
				</xsl:for-each>
			</ul>
		</ul>
	</xsl:template>
</xsl:stylesheet>