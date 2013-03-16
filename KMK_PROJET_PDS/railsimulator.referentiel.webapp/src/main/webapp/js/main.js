
var RnoSubs = new Array();
var mac = (navigator.userAgent.toLowerCase().indexOf("mac")>=0);
var ie6 = 0;
var resizing = null;
var RnoSubTMHide = 6000; // time before submenus dissapear without any action (in ms)
var curSub = null;
var RnoTableLineSH = '';

// Prototype getElementsByClassName
function getElementsByClassName(oElm, strTagName, oClassNames){
    var arrElements = (strTagName == "*" && oElm.all)? oElm.all : oElm.getElementsByTagName(strTagName);
    var arrReturnElements = new Array();
    var arrRegExpClassNames = new Array();
    if(typeof oClassNames == "object"){
        for(var i=0; i<oClassNames.length; i++){
            arrRegExpClassNames.push(new RegExp("(^|\\s)" + oClassNames[i].replace(/\-/g, "\\-") + "(\\s|$)"));
        }
    }
    else{
        arrRegExpClassNames.push(new RegExp("(^|\\s)" + oClassNames.replace(/\-/g, "\\-") + "(\\s|$)"));
    }
    var oElement;
    var bMatchesAll;
    for(var j=0; j<arrElements.length; j++){
        oElement = arrElements[j];
        bMatchesAll = true;
        for(var k=0; k<arrRegExpClassNames.length; k++){
            if(!arrRegExpClassNames[k].test(oElement.className)){
                bMatchesAll = false;
                break;                      
            }
        }
        if(bMatchesAll){
            arrReturnElements.push(oElement);
        }
    }
    return (arrReturnElements)
}

function _false(){
	return false;
}

// Sections Init 
function InitRnoSections(){
	col = getElementsByClassName(document, "H3", "RnoSectionTitle");
	for(i=0;i<col.length;i++){
		o = col[i];
		if(o.getElementsByTagName("SPAN").length==0)
			continue;
		o.evt = o.getElementsByTagName("SPAN")[0];
		o.evt.cont = o.parentNode.getElementsByTagName("DIV")[0];
		o.evt.onclick = function(){
			if(this.className=="RnoSectionClosed"){
				this.className = "RnoSectionOpen";
				this.cont.style.display = "block";
			}
			else{
				this.cont.style.display = "none";
				this.className = "RnoSectionClosed";
			}
		}
		if(o.evt.className=="RnoSectionClosed")
			o.evt.cont.style.display = 'none';
	}
}

// Show Tab Contents
function RnoTabContentsOn(o){
	if(o.RTsprt!=null){
		o.RTsprt.className = 'on';
		o.lib.innerHTML = o.innerHTML;
		o.RTsprt.childNodes[0]._declick();
	}
	o.RTobj.cur = o;
	o.RTobj.cur.RTprt.className = 'on';
	o.RTobj.cur.RTtrg.style.display = "block";
	InitRnoFindSubsPos();
	return false;
}

// Hide Tab Contents
function RnoTabContentsOff(o){
	o.RTobj.cur.RTtrg.style.display = "none";
	o.RTobj.cur.RTprt.className = 'off';
	if(o.RTsprt!=null)
		o.RTsprt.className = 'off';
	o.RTobj.cur = null;
}

// Init Tab Contents
function InitRnoTabContents(){
	if(document.getElementById("RnoTabs")==null)
		return;
	RTobj = document.getElementById("RnoTabs");
	RTobj.cur = null;
	RTevtcol = RTobj.getElementsByTagName("A");
	RTtrgcol = getElementsByClassName(document, "DIV", "RnoTabContent");
	for(i=0;i<RTevtcol.length;i++){
		lnkpos = RTevtcol[i].href.indexOf('#');
		if((lnkpos<0)||(lnkpos==RTevtcol[i].href.length))
			continue;
		hr = RTevtcol[i].href.substring(lnkpos+1, RTevtcol[i].href.length);
		if(document.getElementById(hr)!=null)
			RTevtcol[i].RTtrg = document.getElementById(hr);
		RTevtcol[i].RTobj = RTobj;
		RTevtcol[i].RTprt = RTevtcol[i].parentNode;
		RTevtcol[i].RTsprt = null;
		if(RTevtcol[i].RTprt.parentNode.parentNode!=RTevtcol[i].RTobj){
			RTevtcol[i].RTsprt = RTevtcol[i].RTprt.parentNode.parentNode;
			RTevtcol[i].lib = RTevtcol[i].RTsprt.getElementsByTagName("SPAN")[0];
		}
			
		if(RTevtcol[i].RTtrg==null) // last element */
			continue;

		RTevtcol[i].onclick = function(){
			this.blur();
			if(this.RTobj.cur!=null){
				if(this.RTobj.cur!=this)
					RnoTabContentsOff(this.RTobj.cur);
			}
			return RnoTabContentsOn(this);
		}
		if(RTevtcol[i].RTprt.className=='on')
			RnoTabContentsOn(RTevtcol[i]);
	}
}

function RnoBrwCols(o){
	for(t=1;t<arguments.length;t++){
		col = o.getElementsByTagName(arguments[t]);
		for(i=0;i<col.length;i++){
			col[i].sprt = o;
			if(!RnoFindSubs(col[i]))
				continue;
			RnoSubOverOut(col[i]);
		}
	}
}

// Find Dynamic Menus by object
function RnoFindSubs(o){
	op = o.parentNode;
	if(op.parentNode.parentNode!=o.sprt)
		return false;
	if(op.getElementsByTagName("UL").length==0)
		return false;
	o.innerHTML = '<span>'+o.innerHTML+'</span>';
	o.prt = op.prt = op;
	o.chld = op.chld = op.getElementsByTagName("UL")[0];
	RnoSubs[RnoSubs.length] = o;
	RnoFindSubPos(o);
	return true;	
}

// Place Dynamic Menus
function RnoFindSubPos(o){
	if(o.adjustLeft==undefined)
		o.adjustLeft = 3*(o.parentNode.parentNode.className.indexOf('RnoBtn')>=0);
	if(!document.all)
		o.chld.style.left = 0;
	o.sprt.tw = parseInt(o.sprt.offsetWidth);
	o.tl = parseInt(o.prt.offsetLeft);
	ifr = null;
	if(ie6){
		if(o.ifr==null){
			o.ifr = document.createElement("IFRAME");
			o.ifr.frameBorder = '0';
			o.ifr.scrolling = 'no';
			o.ifr.className = 'ie6ifr';
			o.ifr.ins = 0;
			o.ifr.zIndex = o.chld.zIndex - 1;
		}
		o.chld.style.marginTop = parseInt(o.offsetHeight) + 'px';
		acol = o.chld.getElementsByTagName("A");
		maw = 0;
		for(a=0;a<acol.length;a++){
			acol[a].style.display = 'inline-block';
			acol[a].parentNode.style.width = '1240px';
			maw = Math.max(maw, parseInt(acol[a].offsetWidth));
			acol[a].parentNode.style.width = 'auto';
			acol[a].style.display = 'block';
		}
		o.chld.style.width = o.ifr.style.width = maw + 'px';
		o.ifr.style.height = parseInt(o.chld.offsetHeight) + 'px';
		o.ifr.style.top = parseInt(o.chld.offsetTop) + 'px';
		if(o.ifr.ins==0){
			o.insertAdjacentElement('afterBegin', o.ifr);
			o.ifr.ins = 1;
			o.prt.ifr = o.ifr;
		}
	}
	else
		o.ifr = o.chld;
	o.tw = parseInt(o.chld.offsetWidth);
	tr = o.sprt.tw - o.tl - o.tw;
	if(tr<0){
		o.chld.style.left = o.tl + tr + o.adjustLeft + 'px';
	}
	else{
		o.chld.style.left = o.tl + o.adjustLeft + 'px';
	}
	if(o.ifr!=null){
		o.ifr.style.left = o.chld.style.left;
	}
}

// Dynamic Menus Placement Init
function InitRnoFindSubsPos(){
	for(s in RnoSubs)
		RnoFindSubPos(RnoSubs[s]);
	resizing = null;
}

// Dynamic Menus behavior
function RnoSubOverOut(o){
	op = o.parentNode;
	o.onclick = o._click = function(){
		this.blur();
		if(this.refc=='')
			this.className = 'on';
		this.chld.style.visibility = this.ifr.style.visibility = 'visible';
//		this.tm = window.setTimeout(function(){o._declick();}, RnoSubTMHide);
		this.onclick = this._declick;
		if(curSub!=null){
			if(curSub!=this){
				curSub._declick();
				curSub = null;
			}
		}
		curSub = this;
		document.onmousedown = function(e){
			trgt = (e)?e.target:window.event.srcElement;
			if((curSub!=trgt)&&(curSub!=trgt.parentNode))
				curSub._declick();
			return false;
		}
		return false;
	}
	o._declick = function(){
		this.blur();
/*		window.clearTimeout(this.tm);
		this.tm = null;*/
		this.ifr.style.visibility = this.chld.style.visibility = 'hidden';
		if(this.className=='on')
			this.className = '';
		this.onclick = this._click;
		return false;
	}
	o.acol = o.chld.getElementsByTagName("A");
	for(a=0;a<o.acol.length;a++){
		aobj = o.acol[a];
		aobj.lprt = o;
		if(aobj.parentNode.className.indexOf("RnoVSep")>=0)
			aobj.parentNode.sep = "RnoVSep ";
		else
			aobj.parentNode.sep = '';
			
		if(aobj.parentNode.className.indexOf("on")>=0)
			aobj.lprt.cur = aobj.parentNode;
		aobj.onmousedown = function(){
			document.onmousedown = function(){
				return true;
			}
		}
		aobj.onclick = function(){
			if(this.lprt.cur!=null){
				if(this.lprt.cur!=this){
					this.lprt.cur.className = this.lprt.cur.sep + "off";
				}
			}
			this.lprt.cur = this.parentNode;
			this.lprt.cur.className = this.lprt.cur.sep + "on";
			this.lprt._declick();
			RnoFindSubPos(this.lprt);
			return true;
		}
	}
}

// Find Dynamic Menus Main
function InitRnoSubMenus(){
	if(document.getElementById("RnoNav1Top")!=null)
		RnoBrwCols(document.getElementById("RnoNav1Top"), "A");
	if(document.getElementById("RnoNav2Top")!=null)
		RnoBrwCols(document.getElementById("RnoNav2Top"), "A");
	if(document.getElementById("RnoTabs")!=null)
		RnoBrwCols(document.getElementById("RnoTabs"), "A");
	ucol = getElementsByClassName(document, "UL", "RnoBtn");
	for(i=0;i<ucol.length;i++){
		ucol[i].getElementsByTagName("LI")[0].style.paddingLeft = 0;
		ucol[i].getElementsByTagName("LI")[0].childNodes[0].adjustLeft = 0;
		ucol[i].parentNode.style.position = 'relative';
		uacol = ucol[i].getElementsByTagName("A");
		for(j=0;j<uacol.length;j++){
			aobj = uacol[j];
			aobj.sprt = ucol[i].parentNode;
			aobj.refc = aobj.className;
			if(aobj.parentNode.parentNode!=ucol[i])
				continue;
			if(!RnoFindSubs(aobj))
				continue;
			if(aobj.parentNode.className.indexOf('RnoDis')>=0){
				continue;
			}
			RnoSubOverOut(aobj);
		}
	}
}

function FindRnoDisabledButtons(){
	ucol = getElementsByClassName(document, "UL", "RnoBtn");
	for(i=0;i<ucol.length;i++){
		licol = ucol[i].getElementsByTagName("LI");
		for(j=0;j<licol.length;j++){
			if(licol[j].className.indexOf('RnoDis')>=0){
				licol[j].getElementsByTagName("A")[0].onclick = function(){
					return RnoBtnDis(this);
				}
			}
		}
	}
}

// Disabled Button
function RnoBtnDis(o){
	o.blur();
	return false;
}

var RnoSectionsToClose = new Array();

// Data Table Dynamic Lines Init
function InitRnoDataTableLines(o){
	arbo = (o.innerHTML.indexOf("RnoSub")>=0);
	lcol = o.getElementsByTagName("TR");
	altcmpt = 0;
	subarr = new Array();
	curpad = 5;
	subindex = -1;
	for(l=1;l<lcol.length;l++){
		line = lcol[l];
		line.clcol = line.getElementsByTagName("TD");
		if(line.className.indexOf("RnoDataTableSectionTitle")>=0){
			subindex = 0;
			if(line.className.indexOf("RnoSub")>=0){ // sub section
				subindex = line.className.substring(line.className.indexOf(" Sub")+4, line.className.length);
				subarr[subindex-1][subarr[subindex-1].length] = line;
				curpad = 20*(subindex);
				line.clcol[0].style.paddingLeft = curpad + 'px';
			}
			line.subindex = subindex;
			line.cont = new Array();
			subarr[subindex] = line.cont;
			altcmpt = 0;
			line._openclose = function(d){
					DispRnoDataTableLine(this, d);
			}
			mstl = line;
			continue;
		}
		if(subindex>=0)
			subarr[subindex][subarr[subindex].length] = line;
		if(subindex>=1)
			line.clcol[0].style.paddingLeft = curpad + 50 + 'px';
		line.onmouseover = function(){
			this.className = 'RnoDataTableLineOver';
		}
		line.onmouseout = function(){
			this.className = this.refc;
		}
		line._showhide = function(){
			d = !((this.mstl.evt.className=="RnoDataTableSectionClosed")||(this.mstl.clcol[0].style.display=='none'));
			DispRnoDataTableLine(this, d);
		}
		if(altcmpt&&!arbo)
			line.className = 'RnoDataTableLineAlt';
		line.refc = line.className;
		altcmpt = 1 - altcmpt;
	}
	for(l=1;l<lcol.length;l++){
		line = lcol[l];
		if(line.cont==null)
			continue;
		for(j=0;j<line.cont.length;j++)
			line.cont[j].mstl = line;
	}
}

// Show/Hide a line (apply on each cell) 
function DispRnoDataTableLine(l, sh){
	if(document.all){
		if(sh){
			l.style.position = 'static';
			l.style.top = 0;
		}
		else{
			l.style.position = 'absolute';
			l.style.top = -10000;
		}
	}
//	else{
		if(sh)
			disp = RnoTableLineSH;
		else
			disp = 'none';
		for(j=0;j<l.clcol.length;j++)
			l.clcol[j].style.display = disp;
//	}
}

// Data Table Dynamic Sections Init
function InitRnoDataTableSections(){
	col = getElementsByClassName(document, "TR", "RnoDataTableSectionTitle");
	for(c=0;c<col.length;c++){
		o = col[c];
		if(o.getElementsByTagName("SPAN").length>0){
			o.evt = o.getElementsByTagName("SPAN")[0];
			o.evt.prtl = o;
			o.evt.onclick = function(){
				if(this.className=="RnoDataTableSectionClosed")
					this.prtl._openclose(1);
				else
					this.prtl._openclose(0);
			}
			o._openclose = function(d){
				this.evt.className = (d)?"RnoDataTableSectionOpen":"RnoDataTableSectionClosed";
				for(var i=0;i<this.cont.length;i++)
					this.cont[i]._showhide();
			}
			o._showhide = function(){
				d = !((this.mstl.evt.className=="RnoDataTableSectionClosed")||(this.mstl.clcol[0].style.display=='none'));
				DispRnoDataTableLine(this, d);
				for(var t=0;t<this.cont.length;t++)
					this.cont[t]._showhide();
			}
			toclose = (o.evt.className=="RnoDataTableSectionClosed");
		}
		if(toclose)
			RnoSectionsToClose[RnoSectionsToClose.length] = o;
	}
	for(s=0;s<RnoSectionsToClose.length;s++)
			RnoSectionsToClose[s]._openclose(0);
}

// Data Table Checvkboxes Init
function InitRnoDataTableChks(o){
	icol = o.getElementsByTagName("INPUT");
	msti = mrsti = ref = mref = null;
	for(i=0;i<icol.length;i++){
		inp = icol[i];
		if(inp.className!='RnoChk')
			continue;
		if(inp.parentNode.tagName=="TH"){ // Master Checkbox
			mref = inp;
			inp.chld = new Array();
			mrsti = inp.chld;
		}
		else if(inp.parentNode.parentNode.className=="RnoDataTableSectionTitle"){ // Section Checkbox
			if(mrsti!=null)
				mrsti[mrsti.length] = inp;
			inp.prt = mref;
			ref = inp;
			inp.chld = new Array();
			msti = inp.chld;
		}
		else{ // Child Checkbox
			if(msti!=null){
				msti[msti.length] = inp;
				inp.prt = ref;
			}
			else if(mrsti!=null){
				mrsti[mrsti.length] = inp;
				inp.prt = mref;
			}
		}
		inp._click = function(s){
			this.checked = s;
			if(this.chld==null)
				return;
			for(var i=0;i<this.chld.length;i++)
				this.chld[i]._click(s);
		}
		inp.onclick = function(){
			this.blur();
			if(!this.checked){
				o = this;
				while(o.prt!=null){
					o.prt.checked = 0;
					o = o.prt;
				}
			}
			this._click(this.checked);
		}
	}
}

// Main Data tables Init
function InitRnoDataTables(){
	tcol = getElementsByClassName(document, "DIV", "RnoDataTable");
	for(t in tcol){
		tobj = tcol[t].getElementsByTagName("TABLE")[0];
		InitRnoDataTableLines(tobj);
		InitRnoDataTableSections(tobj);
		InitRnoDataTableChks(tobj);
	}
}

var RnoGlobal = null, RnoModalF = null, RnoModalS = null, RnoModalB = null;

// Modal dialog boxes
function ShowModal(mdobj){
	if(RnoModalF==null){
		RnoGlobal = document.getElementById('RnoMargins');
		RnoModalF = document.getElementById('RnoModalFreeze');
		RnoModalS = document.getElementById('RnoModalScroll');
	}
	if(ie6){
		RnoModalF.SelCol = document.getElementById("RnoPage").getElementsByTagName("SELECT");
		for(i=0;i<RnoModalF.SelCol.length;i++){
			RnoModalF.SelCol[i].refv = RnoModalF.SelCol[i].style.visibility;
			if(RnoModalF.SelCol[i].refv=='')
				RnoModalF.SelCol[i].refv = 'inherit';
			RnoModalF.SelCol[i].style.visibility = 'hidden';
		}
	}
	RnoModalB = document.getElementById(mdobj);
	if(ie6){
		if(getElementsByClassName(RnoModalB, "DIV", "RnoModalContent").length>0){
			modcont = getElementsByClassName(RnoModalB, "DIV", "RnoModalContent")[0];
			if(modcont.style.height=='')
				modcont.className = '';
		}
	}
	ResizeModal();
}

function HideModal(){
	RnoModalB.style.display = 'none';
	RnoModalS.style.display = RnoModalF.style.display = 'none';
	if(ie6){
		for(i=0;i<RnoModalF.SelCol.length;i++)
			RnoModalF.SelCol[i].style.visibility = RnoModalF.SelCol[i].refv;
	}
	RnoModalB = null;
	window.onscroll = null;
}

function ResizeModal(){
	if(RnoModalB==null)
		return;
	RnoModalB.style.display = 'none';
	RnoModalF.style.width = RnoModalS.style.width = '1px';
	RnoModalF.style.height = RnoModalS.style.height = '1px';
	RnoModalF.style.width = RnoModalS.style.width = parseInt(document.documentElement.scrollWidth) + 'px';
	bodyH = parseInt(document.documentElement.scrollHeight);
	if(document.all)
		windowH = parseInt(document.documentElement.clientHeight);
	else
		windowH = window.innerHeight;
	freezeH = Math.max(bodyH, windowH);
	RnoModalF.style.display = RnoModalS.style.display = 'block';
	RnoModalB.style.marginTop = 0;
	RnoModalB.style.display = 'block';
	modalH = parseInt(RnoModalB.offsetHeight);
	RnoModalF.style.height = RnoModalS.style.height = Math.max(freezeH,modalH) + 'px';
	if(freezeH>modalH){
		mrg = Math.max(0, ((windowH - modalH)/2)) + parseInt(document.documentElement.scrollTop);
		if((mrg+modalH)>freezeH)
			mrg = (freezeH - modalH);
		RnoModalB.style.marginTop = mrg + 'px';
		window.onscroll = function(e){
			if(document.all||(mac&&(e.target.tagName=="HTML"))||!mac)
				ResizeModal();
		}
	}
	else if(windowH<modalH){
		document.documentElement.scrollTop = 0;
		window.onscroll = null;
	}
}

// Steps content vertical align
function InitRnoSteps(){
	if(document.getElementById("RnoSteps")==null)
		return;
	steps = document.getElementById("RnoSteps");
	licol = steps.getElementsByTagName("LI");
	for(i=0;i<licol.length;i++){
		if(licol[i].getElementsByTagName("BR").length==0)
			licol[i].getElementsByTagName("SPAN")[0].className += ' RnoSteps1Line';
	}
}


var won = '';

// Main Page Load
window.onload = function(){
	ie6 = (document.all&&(typeof document.body.style.maxHeight=="undefined")); // IE6 detection
	RnoTableLineSH = ((document.all)?'inline-block':'table-cell');
	InitRnoSteps();
	InitRnoSubMenus();
	InitRnoSections();
	InitRnoTabContents();
	InitRnoDataTables();
	FindRnoDisabledButtons();
	eval(won);
}

// Add Inline Custom Function (call from page HTML code)
function RnoAddOnloadFunction(f_string){
	won += f_string;
}

// Main Page Resize
window.onresize = function(){
	ResizeModal();
	if(resizing==null)
		resizing = window.setTimeout("InitRnoFindSubsPos();ResizeModal();", 10);
}