
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type"	content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css"  />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css" media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Visualisation R�seau - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>

		<!-- Sets the basepath for the library if not in same directory--> 
		<script type="text/javascript">
			mxBasePath = '';
		</script>
	
		<!-- Loads and initializes the library -->
		<script type="text/javascript" src="js/mxClient.js"></script>
		
		<script type="text/javascript">
			// Program starts here. Creates a sample graph in the
			// DOM node with the specified ID. This function is invoked
			// from the onLoad event handler of the document (see below).
			function main(container)
			{
				// Checks if the browser is supported
				if (!mxClient.isBrowserSupported())
				{
					// Displays an error message if the browser is not supported.
					mxUtils.error('Browser is not supported!', 200, false);
				}
				else
				{
					// Creates the graph inside the given container
					var graph = createGraph(container);
	
					// Creates a process display using the activity names as IDs to refer to the elements
					var xml = '<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/>'+
						'<mxCell id="2" value="Claim Handling Process" style="swimlane" vertex="1" parent="1"><mxGeometry x="1" width="840" height="400" as="geometry"/></mxCell>'+
						'<mxCell id="3" value="Claim Manager" style="swimlane" vertex="1" parent="2"><mxGeometry x="24" width="816" height="200" as="geometry"/></mxCell>'+
						'<mxCell id="5" value="" style="start" vertex="1" parent="3"><mxGeometry x="40" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="AuthorizeClaim" value="Authorize&#xa;Claim" vertex="1" parent="3"><mxGeometry x="90" y="80" width="100" height="40" as="geometry"/></mxCell>'+
						'<mxCell id="6" value="X" style="step" vertex="1" parent="3"><mxGeometry x="210" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="ApproveClaim" value="Approve&#xa;Claim" vertex="1" parent="3"><mxGeometry x="260" y="80" width="100" height="40" as="geometry"/></mxCell>'+
						'<mxCell id="7" value="X" style="step" vertex="1" parent="3"><mxGeometry x="380" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="8" value="" edge="1" parent="3" source="5" target="AuthorizeClaim"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="9" value="" edge="1" parent="3" source="AuthorizeClaim" target="6"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="10" value="" edge="1" parent="3" source="6" target="ApproveClaim"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="11" value="" edge="1" parent="3" source="ApproveClaim" target="7"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="12" value="" edge="1" parent="3" source="7" target="AuthorizeClaim"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="140" y="40"/></Array></mxGeometry></mxCell>'+
						'<mxCell id="ReviewClaim" value="Review&#xa;Claim" vertex="1" parent="3"><mxGeometry x="480" y="80" width="100" height="40" as="geometry"/></mxCell>'+
						'<mxCell id="22" value="X" style="step" vertex="1" parent="3"><mxGeometry x="600" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="23" value="" edge="1" parent="3" source="ReviewClaim" target="22"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="ApproveReviewedClaim" value="Approve Rev.&#xa;Claim" vertex="1" parent="3"><mxGeometry x="650" y="80" width="100" height="40" as="geometry"/></mxCell>'+
						'<mxCell id="26" value="" edge="1" parent="3" source="22" target="ApproveReviewedClaim"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="27" value="X" style="step" vertex="1" parent="3"><mxGeometry x="770" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="28" value="" edge="1" target="27" parent="3" source="ApproveReviewedClaim"><mxGeometry relative="1" as="geometry"><mxPoint x="740" y="100" as="sourcePoint"/><mxPoint x="760" y="100" as="targetPoint"/></mxGeometry></mxCell>'+
						'<mxCell id="32" value="" edge="1" parent="3" source="27" target="ReviewClaim"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="665" y="160"/></Array></mxGeometry></mxCell>'+
						'<mxCell id="4" value="Accountant" style="swimlane" vertex="1" parent="2"><mxGeometry x="24" y="200" width="816" height="200" as="geometry"/></mxCell>'+
						'<mxCell id="EnterAccountingData" value="Enter&#xa;Data" vertex="1" parent="4"><mxGeometry x="430" y="80" width="100" height="40" as="geometry"/></mxCell>'+
						'<mxCell id="14" value="X" style="step" vertex="1" parent="4"><mxGeometry x="550" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="15" value="" edge="1" parent="4" source="EnterAccountingData" target="14"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="CheckAccountingData" value="Check&#xa;Data" vertex="1" parent="4"><mxGeometry x="600" y="80" width="100" height="40" as="geometry"/></mxCell>'+
						'<mxCell id="16" value="" edge="1" parent="4" source="14" target="CheckAccountingData"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="17" value="X" style="step" vertex="1" parent="4"><mxGeometry x="720" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="18" value="" edge="1" parent="4" source="CheckAccountingData" target="17"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="19" value="" style="end" vertex="1" parent="4"><mxGeometry x="770" y="85" width="30" height="30" as="geometry"/></mxCell>'+
						'<mxCell id="20" value="" edge="1" parent="4" source="17" target="19"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="31" value="" edge="1" parent="4" source="17" target="EnterAccountingData"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="625" y="160"/></Array></mxGeometry></mxCell>'+
						'<mxCell id="13" value="" edge="1" parent="2" source="7" target="EnterAccountingData"><mxGeometry relative="1" as="geometry"/></mxCell>'+
						'<mxCell id="24" value="" edge="1" parent="2" source="14" target="ReviewClaim" style="edgeStyle=none"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="589" y="180"/><mxPoint x="480" y="180"/><mxPoint x="480" y="100"/></Array></mxGeometry></mxCell>'+
						'<mxCell id="29" value="" edge="1" parent="2" source="22" target="EnterAccountingData"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="469" y="40"/></Array></mxGeometry></mxCell>'+
						'<mxCell id="30" value="" edge="1" parent="2" source="27" target="EnterAccountingData"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="469" y="40"/></Array></mxGeometry></mxCell>'+
						'<mxCell id="33" value="" edge="1" parent="2" source="6" target="EnterAccountingData"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="249" y="200"/></Array></mxGeometry></mxCell>'+
						'</root></mxGraphModel>';
					var doc = mxUtils.parseXml(xml);
					var codec = new mxCodec(doc);
					codec.decode(doc.documentElement, graph.getModel());
				}
				
				// Creates a button to invoke the refresh function
				document.body.appendChild(mxUtils.button('Update', function(evt)
				{
					// XML is normally fetched from URL at server using mxUtils.get - this is a client-side
					// string with randomized states to demonstrate the idea of the workflow monitor
					var xml = '<process><update id="ApproveClaim" state="'+getState()+'"/><update id="AuthorizeClaim" state="'+getState()+'"/>'+
						'<update id="CheckAccountingData" state="'+getState()+'"/><update id="ReviewClaim" state="'+getState()+'"/>'+
						'<update id="ApproveReviewedClaim" state="'+getState()+'"/><update id="EnterAccountingData" state="'+getState()+'"/></process>';
					update(graph, xml);
				}));
			};
	
			/**
			 * Updates the display of the given graph using the XML data
			 */
			function update(graph, xml)
			{
				if (xml != null &&
					xml.length > 0)
				{
					var doc = mxUtils.parseXml(xml);
					
					if (doc != null &&
						doc.documentElement != null)
					{
						var model = graph.getModel();
						var nodes = doc.documentElement.getElementsByTagName('update');
						
						if (nodes != null &&
							nodes.length > 0)
						{
						
							model.beginUpdate();
							try
							{
								for (var i = 0; i < nodes.length; i++)
								{
									// Processes the activity nodes inside the process node
									var id = nodes[i].getAttribute('id');
									var state = nodes[i].getAttribute('state');
									
									// Gets the cell for the given activity name from the model
									var cell = model.getCell(id);
									
									// Updates the cell color and adds some tooltip information
									if (cell != null)
									{
										// Resets the fillcolor and the overlay
										graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, 'white', [cell]);
										graph.removeCellOverlays(cell);
				
										// Changes the cell color for the known states
										if (state == 'Running')
										{
											graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, '#2DFF2C', [cell]);
										}
										else if (state == 'Waiting')
										{
											graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, '#FFAF1B', [cell]);
										}
										else if (state == 'Completed')
										{
											graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, '#FFFF10', [cell]);
										}
										
										// Adds tooltip information using an overlay icon
										if (state != 'Init')
										{
											// Sets the overlay for the cell in the graph
											graph.addCellOverlay(cell, createOverlay(graph.warningImage, 'State: '+state));
										}
									}
								} // for
							}
							finally
							{
								model.endUpdate();
							}
						}
					}
				}
			};
			
			/**
			 * Creates an overlay object using the given tooltip and text for the alert window
			 * which is being displayed on click.
			 */
			function createOverlay(image, tooltip)
			{
				var overlay = new mxCellOverlay(image, tooltip);
	
				// Installs a handler for clicks on the overlay
				overlay.addListener(mxEvent.CLICK, function(sender, evt)
				{
					mxUtils.alert(tooltip+'\n'+'Last update: '+new Date());
				});
				
				return overlay;
			};
			
			/**
			 * Creates and returns an empty graph inside the given container.
			 */
			function createGraph(container)
			{
				var graph = new mxGraph(container);
				graph.setTooltips(true);
				graph.setEnabled(false);
				
				// Disables folding
				graph.isCellFoldable = function(cell, collapse)
				{
					return false;
				};
	
				// Creates the stylesheet for the process display
				var style = graph.getStylesheet().getDefaultVertexStyle();
				style[mxConstants.STYLE_FONTSIZE] = '12';
				style[mxConstants.STYLE_FONTCOLOR] = 'black';
				style[mxConstants.STYLE_STROKECOLOR] = 'black';
				style[mxConstants.STYLE_FILLCOLOR] = 'white';
				style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
				style[mxConstants.STYLE_GRADIENT_DIRECTION] = mxConstants.DIRECTION_EAST;
				style[mxConstants.STYLE_ROUNDED] = true;
				style[mxConstants.STYLE_SHADOW] = true;
				style[mxConstants.STYLE_FONTSTYLE] = 1;
				
				style = graph.getStylesheet().getDefaultEdgeStyle();
				style[mxConstants.STYLE_EDGE] = mxEdgeStyle.ElbowConnector;
				style[mxConstants.STYLE_STROKECOLOR] = 'black';
				style[mxConstants.STYLE_ROUNDED] = true;
								
				style = [];
				style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_SWIMLANE;
				style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
				style[mxConstants.STYLE_STROKECOLOR] = 'gray';
				style[mxConstants.STYLE_FONTCOLOR] = 'black';
				style[mxConstants.STYLE_FILLCOLOR] = '#E0E0DF';
				style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
				style[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER;
				style[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_TOP;
				style[mxConstants.STYLE_STARTSIZE] = 24;
				style[mxConstants.STYLE_FONTSIZE] = '12';
				style[mxConstants.STYLE_FONTSTYLE] = 1;
				style[mxConstants.STYLE_HORIZONTAL] = false;
				graph.getStylesheet().putCellStyle('swimlane', style);
				
				style = [];
				style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RHOMBUS;
				style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RhombusPerimeter;
				style[mxConstants.STYLE_STROKECOLOR] = 'gray';
				style[mxConstants.STYLE_FONTCOLOR] = 'gray';
				style[mxConstants.STYLE_FILLCOLOR] = '#91BCC0';
				style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
				style[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER;
				style[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE;
				style[mxConstants.STYLE_FONTSIZE] = '14';
				graph.getStylesheet().putCellStyle('step', style);
				
				style = [];
				style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE;
				style[mxConstants.STYLE_PERIMETER] = mxPerimeter.EllipsePerimeter;
				style[mxConstants.STYLE_STROKECOLOR] = 'gray';
				style[mxConstants.STYLE_FONTCOLOR] = 'gray';
				style[mxConstants.STYLE_FILLCOLOR] = '#A0C88F';
				style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
				style[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER;
				style[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE;
				style[mxConstants.STYLE_FONTSIZE] = '14';
				graph.getStylesheet().putCellStyle('start', style);
				
				style = mxUtils.clone(style);
				style[mxConstants.STYLE_FILLCOLOR] = '#DACCBC';
				style[mxConstants.STYLE_STROKECOLOR] = '#AF7F73';
				style[mxConstants.STYLE_STROKEWIDTH] = 3;
				graph.getStylesheet().putCellStyle('end', style);
				
				return graph;
			};
			
			/**
			 * Returns a random state.
			 */
			function getState()
			{
				var state = 'Init';
				var rnd = Math.random() * 4;
				
				if (rnd > 3)
				{
					state = 'Completed';
				}
				else if (rnd > 2)
				{
					state = 'Running';
				}
				else if (rnd > 1)
				{
					state = 'Waiting';
				}
				
				return state;
			};
		</script>
		
	</head>
		
	<body>
		<!-- Page Layout -->
		<table id="RnoPage" class="RnoLayout-1col">
			<tr>
				<td id="RnoPageWidthRange">
					<!-- Global Links Top -->
					<div id="RnoGlobalLinksTop" class="sc">
						<div>
							<ul>
								<li>
									<a href="#">Manuel</a>
								</li>
								<li>
									<a href="#">Demande d'�volution</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- /Global Links Top -->
					<!-- Page Header -->
					<div id="RnoBranding" class="sc"> 
						<a href="#">
							<img src="images/logos/kmk-rail.jpg" id="RnoLogo" alt="Kamikaze" width="63" height="63"/>
						</a>
						<h1 id="RnoApplicationName">
							<img src="images/header/kmk-header.jpg" alt="KAMIKAZE PROJECT - Rail Simulator"/>
						</h1>
						<span>
							<img src="images/branding-coin.gif" alt="" width="31" height="31"/>
						</span>
					</div>
					 
					<!-- Body Content -->
						
						<!-- Acts as a container for the graph -->
						<div id="graphContainer" style="overflow:hidden;position:relative;width:861px;height:406px;cursor:default;"></div>
						<br>
					
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>