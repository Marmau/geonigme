// Autosize 1.13 - jQuery plugin for textareas
// (c) 2012 Jack Moore - jacklmoore.com
// license: www.opensource.org/licenses/mit-license.php
(function(e){var t="hidden",n="border-box",r="lineHeight",i='<textarea tabindex="-1" style="position:absolute; top:-9999px; left:-9999px; right:auto; bottom:auto; -moz-box-sizing:content-box; -webkit-box-sizing:content-box; box-sizing:content-box; word-wrap:break-word; height:0 !important; min-height:0 !important; overflow:hidden;"/>',s=["fontFamily","fontSize","fontWeight","fontStyle","letterSpacing","textTransform","wordSpacing","textIndent"],o="oninput",u="onpropertychange",a=e(i)[0];a.setAttribute(o,"return"),e.isFunction(a[o])||u in a?(e(a).css(r,"99px"),e(a).css(r)==="99px"&&s.push(r),e.fn.autosize=function(r){return r=r||{},this.each(function(){function b(){var e,n,i;p||(p=!0,l.value=a.value,l.style.overflowY=a.style.overflowY,i=parseInt(a.style.height,10),l.style.width=f.css("width"),l.scrollTop=0,l.scrollTop=9e4,e=l.scrollTop,n=t,e>h?(e=h,n="scroll"):e<c&&(e=c),e+=m,a.style.overflowY=n,i!==e&&(a.style.height=e+"px",y&&r.callback.call(a)),setTimeout(function(){p=!1},1))}var a=this,f=e(a),l,c=f.height(),h=parseInt(f.css("maxHeight"),10),p,d=s.length,v,m=0,g=a.value,y=e.isFunction(r.callback);if(f.css("box-sizing")===n||f.css("-moz-box-sizing")===n||f.css("-webkit-box-sizing")===n)m=f.outerHeight()-f.height();if(f.data("mirror")||f.data("ismirror"))return;l=e(i).data("ismirror",!0).addClass(r.className||"autosizejs")[0],v=f.css("resize")==="none"?"none":"horizontal",f.data("mirror",e(l)).css({overflow:t,overflowY:t,wordWrap:"break-word",resize:v}),h=h&&h>0?h:9e4;while(d--)l.style[s[d]]=f.css(s[d]);e("body").append(l),u in a?o in a?a[o]=a.onkeyup=b:a[u]=b:(a[o]=b,a.value="",a.value=g),e(window).resize(b),f.bind("autosize",b),b()})}):e.fn.autosize=function(e){return this}})(jQuery);