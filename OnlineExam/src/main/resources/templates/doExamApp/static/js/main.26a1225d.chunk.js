(this["webpackJsonpdo-exam-app"]=this["webpackJsonpdo-exam-app"]||[]).push([[0],{11:function(t,e,n){},13:function(t,e,n){},15:function(t,e,n){"use strict";n.r(e);var c=n(0),r=n.n(c),a=n(6),s=n.n(a),u=(n(11),n(2)),o=n.n(u),i=n(4),p=n(5),f=(n(13),{api:{test:"//localhost:8080/api/students/test",getExam:"//localhost:8080/api/students/getExam"}}),h=n(1);var j=function(){var t=Object(c.useState)(""),e=Object(p.a)(t,2),n=e[0],r=e[1],a=Object(c.useState)(""),s=Object(p.a)(a,2),u=s[0];return s[1],Object(c.useEffect)((function(){fetch(f.api.test).then(function(){var t=Object(i.a)(o.a.mark((function t(e){return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",e.text());case 1:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()).then((function(t){r(t)})),fetch(f.api.getExam).then(function(){var t=Object(i.a)(o.a.mark((function t(e){return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",e.json());case 1:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()).then((function(t){console.log(JSON.stringify(t))}))})),Object(h.jsxs)("div",{className:"App",children:["Hello from React App",Object(h.jsx)("p",{children:n}),Object(h.jsx)("p",{children:u})]})},l=function(t){t&&t instanceof Function&&n.e(3).then(n.bind(null,16)).then((function(e){var n=e.getCLS,c=e.getFID,r=e.getFCP,a=e.getLCP,s=e.getTTFB;n(t),c(t),r(t),a(t),s(t)}))};s.a.render(Object(h.jsx)(r.a.StrictMode,{children:Object(h.jsx)(j,{})}),document.getElementById("root")),l()}},[[15,1,2]]]);
//# sourceMappingURL=main.26a1225d.chunk.js.map