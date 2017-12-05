! function(e, t) {
    if ("object" == typeof exports && "object" == typeof module) module.exports = t(require("react"), require("react-dom"));
    else if ("function" == typeof define && define.amd) define(["react", "react-dom"], t);
    else {
        var n = "object" == typeof exports ? t(require("react"), require("react-dom")) : t(e.React, e.ReactDOM);
        for (var r in n)("object" == typeof exports ? exports : e)[r] = n[r]
    }
}(this, function(e, t) {
    return function(e) {
        function t(r) {
            if (n[r]) return n[r].exports;
            var o = n[r] = {
                i: r,
                l: !1,
                exports: {}
            };
            return e[r].call(o.exports, o, o.exports, t), o.l = !0, o.exports
        }
        var n = {};
        return t.m = e, t.c = n, t.i = function(e) {
            return e
        }, t.d = function(e, n, r) {
            t.o(e, n) || Object.defineProperty(e, n, {
                configurable: !1,
                enumerable: !0,
                get: r
            })
        }, t.n = function(e) {
            var n = e && e.__esModule ? function() {
                return e.default
            } : function() {
                return e
            };
            return t.d(n, "a", n), n
        }, t.o = function(e, t) {
            return Object.prototype.hasOwnProperty.call(e, t)
        }, t.p = "/pagination", t(t.s = 3)
    }({
        "./demo/eventing.js": function(e, t, n) {
            "use strict";

            function r(e) {
                return e && e.__esModule ? e : {
                    default: e
                }
            }
            var o = n(1),
                a = r(o),
                i = n(0),
                s = r(i),
                u = n("./index.js");
            document.body.addEventListener("o.InitPagination", function(e) {
                a.default.render(s.default.createElement(u.PaginationContainer, e.detail.props, e.detail.props.children), document.getElementById(e.detail.elementId))
            })
        },
        "./index.js": function(e, t, n) {
            "use strict";

            function r(e) {
                return e && e.__esModule ? e : {
                    default: e
                }
            }
            Object.defineProperty(t, "__esModule", {
                value: !0
            }), t.PaginationContainer = t.Pagination = void 0;
            var o = n("./src/js/Pagination.js"),
                a = r(o),
                i = n("./src/js/PaginationContainer.js"),
                s = r(i);
            t.Pagination = a.default, t.PaginationContainer = s.default
        },
        "./node_modules/css-loader/index.js!./node_modules/sass-loader/lib/loader.js!./src/scss/pagination.scss": function(e, t, n) {
            t = e.exports = n("./node_modules/css-loader/lib/css-base.js")(), t.push([e.i, ".pe-pagination button{min-width:44px;min-height:44px;padding:0;color:#6a7070;background-color:transparent;border:0}.pe-pagination button.ellipsis{min-width:32px}.pe-pagination button:focus span,.pe-pagination button:hover span{border-bottom:2px solid #6a7070}.pe-pagination button[aria-current] span{color:#252525;border-bottom:2px solid #252525;font-weight:700}.pe-pagination button:first-child,.pe-pagination button:last-child{min-width:28px;min-height:28px;border:1px solid #c7c7c7;border-radius:50%}.pe-pagination button:first-child:focus span,.pe-pagination button:first-child:hover span,.pe-pagination button:last-child:focus span,.pe-pagination button:last-child:hover span{border-bottom:0}.pe-pagination button:first-child:disabled,.pe-pagination button:last-child:disabled{color:#c7c7c7}.pe-pagination .compact-text{margin:0 17px}", ""])
        },
        "./node_modules/css-loader/lib/css-base.js": function(e, t) {
            e.exports = function() {
                var e = [];
                return e.toString = function() {
                    for (var e = [], t = 0; t < this.length; t++) {
                        var n = this[t];
                        n[2] ? e.push("@media " + n[2] + "{" + n[1] + "}") : e.push(n[1])
                    }
                    return e.join("")
                }, e.i = function(t, n) {
                    "string" == typeof t && (t = [
                        [null, t, ""]
                    ]);
                    for (var r = {}, o = 0; o < this.length; o++) {
                        var a = this[o][0];
                        "number" == typeof a && (r[a] = !0)
                    }
                    for (o = 0; o < t.length; o++) {
                        var i = t[o];
                        "number" == typeof i[0] && r[i[0]] || (n && !i[2] ? i[2] = n : n && (i[2] = "(" + i[2] + ") and (" + n + ")"), e.push(i))
                    }
                }, e
            }
        },
        "./node_modules/fbjs/lib/emptyFunction.js": function(e, t, n) {
            "use strict";

            function r(e) {
                return function() {
                    return e
                }
            }
            var o = function() {};
            o.thatReturns = r, o.thatReturnsFalse = r(!1), o.thatReturnsTrue = r(!0), o.thatReturnsNull = r(null), o.thatReturnsThis = function() {
                return this
            }, o.thatReturnsArgument = function(e) {
                return e
            }, e.exports = o
        },
        "./node_modules/fbjs/lib/invariant.js": function(e, t, n) {
            "use strict";

            function r(e, t, n, r, a, i, s, u) {
                if (o(t), !e) {
                    var c;
                    if (void 0 === t) c = new Error("Minified exception occurred; use the non-minified dev environment for the full error message and additional helpful warnings.");
                    else {
                        var l = [n, r, a, i, s, u],
                            p = 0;
                        c = new Error(t.replace(/%s/g, function() {
                            return l[p++]
                        })), c.name = "Invariant Violation"
                    }
                    throw c.framesToPop = 1, c
                }
            }
            var o = function(e) {};
            e.exports = r
        },
        "./node_modules/prop-types/factoryWithThrowingShims.js": function(e, t, n) {
            "use strict";
            var r = n("./node_modules/fbjs/lib/emptyFunction.js"),
                o = n("./node_modules/fbjs/lib/invariant.js"),
                a = n("./node_modules/prop-types/lib/ReactPropTypesSecret.js");
            e.exports = function() {
                function e(e, t, n, r, i, s) {
                    s !== a && o(!1, "Calling PropTypes validators directly is not supported by the `prop-types` package. Use PropTypes.checkPropTypes() to call them. Read more at http://fb.me/use-check-prop-types")
                }

                function t() {
                    return e
                }
                e.isRequired = e;
                var n = {
                    array: e,
                    bool: e,
                    func: e,
                    number: e,
                    object: e,
                    string: e,
                    symbol: e,
                    any: e,
                    arrayOf: t,
                    element: e,
                    instanceOf: t,
                    node: e,
                    objectOf: t,
                    oneOf: t,
                    oneOfType: t,
                    shape: t,
                    exact: t
                };
                return n.checkPropTypes = r, n.PropTypes = n, n
            }
        },
        "./node_modules/prop-types/index.js": function(e, t, n) {
            e.exports = n("./node_modules/prop-types/factoryWithThrowingShims.js")()
        },
        "./node_modules/prop-types/lib/ReactPropTypesSecret.js": function(e, t, n) {
            "use strict";
            e.exports = "SECRET_DO_NOT_PASS_THIS_OR_YOU_WILL_BE_FIRED"
        },
        "./node_modules/style-loader/addStyles.js": function(e, t) {
            function n(e, t) {
                for (var n = 0; n < e.length; n++) {
                    var r = e[n],
                        o = f[r.id];
                    if (o) {
                        o.refs++;
                        for (var a = 0; a < o.parts.length; a++) o.parts[a](r.parts[a]);
                        for (; a < r.parts.length; a++) o.parts.push(u(r.parts[a], t))
                    } else {
                        for (var i = [], a = 0; a < r.parts.length; a++) i.push(u(r.parts[a], t));
                        f[r.id] = {
                            id: r.id,
                            refs: 1,
                            parts: i
                        }
                    }
                }
            }

            function r(e) {
                for (var t = [], n = {}, r = 0; r < e.length; r++) {
                    var o = e[r],
                        a = o[0],
                        i = o[1],
                        s = o[2],
                        u = o[3],
                        c = {
                            css: i,
                            media: s,
                            sourceMap: u
                        };
                    n[a] ? n[a].parts.push(c) : t.push(n[a] = {
                        id: a,
                        parts: [c]
                    })
                }
                return t
            }

            function o(e, t) {
                var n = h(),
                    r = g[g.length - 1];
                if ("top" === e.insertAt) r ? r.nextSibling ? n.insertBefore(t, r.nextSibling) : n.appendChild(t) : n.insertBefore(t, n.firstChild), g.push(t);
                else {
                    if ("bottom" !== e.insertAt) throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");
                    n.appendChild(t)
                }
            }

            function a(e) {
                e.parentNode.removeChild(e);
                var t = g.indexOf(e);
                t >= 0 && g.splice(t, 1)
            }

            function i(e) {
                var t = document.createElement("style");
                return t.type = "text/css", o(e, t), t
            }

            function s(e) {
                var t = document.createElement("link");
                return t.rel = "stylesheet", o(e, t), t
            }

            function u(e, t) {
                var n, r, o;
                if (t.singleton) {
                    var u = b++;
                    n = m || (m = i(t)), r = c.bind(null, n, u, !1), o = c.bind(null, n, u, !0)
                } else e.sourceMap && "function" == typeof URL && "function" == typeof URL.createObjectURL && "function" == typeof URL.revokeObjectURL && "function" == typeof Blob && "function" == typeof btoa ? (n = s(t), r = p.bind(null, n), o = function() {
                    a(n), n.href && URL.revokeObjectURL(n.href)
                }) : (n = i(t), r = l.bind(null, n), o = function() {
                    a(n)
                });
                return r(e),
                    function(t) {
                        if (t) {
                            if (t.css === e.css && t.media === e.media && t.sourceMap === e.sourceMap) return;
                            r(e = t)
                        } else o()
                    }
            }

            function c(e, t, n, r) {
                var o = n ? "" : r.css;
                if (e.styleSheet) e.styleSheet.cssText = y(t, o);
                else {
                    var a = document.createTextNode(o),
                        i = e.childNodes;
                    i[t] && e.removeChild(i[t]), i.length ? e.insertBefore(a, i[t]) : e.appendChild(a)
                }
            }

            function l(e, t) {
                var n = t.css,
                    r = t.media;
                if (r && e.setAttribute("media", r), e.styleSheet) e.styleSheet.cssText = n;
                else {
                    for (; e.firstChild;) e.removeChild(e.firstChild);
                    e.appendChild(document.createTextNode(n))
                }
            }

            function p(e, t) {
                var n = t.css,
                    r = t.sourceMap;
                r && (n += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(r)))) + " */");
                var o = new Blob([n], {
                        type: "text/css"
                    }),
                    a = e.href;
                e.href = URL.createObjectURL(o), a && URL.revokeObjectURL(a)
            }
            var f = {},
                d = function(e) {
                    var t;
                    return function() {
                        return void 0 === t && (t = e.apply(this, arguments)), t
                    }
                },
                v = d(function() {
                    return /msie [6-9]\b/.test(self.navigator.userAgent.toLowerCase())
                }),
                h = d(function() {
                    return document.head || document.getElementsByTagName("head")[0]
                }),
                m = null,
                b = 0,
                g = [];
            e.exports = function(e, t) {
                if ("undefined" != typeof DEBUG && DEBUG && "object" != typeof document) throw new Error("The style-loader cannot be used in a non-browser environment");
                t = t || {}, void 0 === t.singleton && (t.singleton = v()), void 0 === t.insertAt && (t.insertAt = "bottom");
                var o = r(e);
                return n(o, t),
                    function(e) {
                        for (var a = [], i = 0; i < o.length; i++) {
                            var s = o[i],
                                u = f[s.id];
                            u.refs--, a.push(u)
                        }
                        if (e) {
                            n(r(e), t)
                        }
                        for (var i = 0; i < a.length; i++) {
                            var u = a[i];
                            if (0 === u.refs) {
                                for (var c = 0; c < u.parts.length; c++) u.parts[c]();
                                delete f[u.id]
                            }
                        }
                    }
            };
            var y = function() {
                var e = [];
                return function(t, n) {
                    return e[t] = n, e.filter(Boolean).join("\n")
                }
            }()
        },
        "./node_modules/uuid/rng-browser.js": function(e, t, n) {
            (function(t) {
                var n;
                if (t.crypto && crypto.getRandomValues) {
                    var r = new Uint8Array(16);
                    n = function() {
                        return crypto.getRandomValues(r), r
                    }
                }
                if (!n) {
                    var o = new Array(16);
                    n = function() {
                        for (var e, t = 0; t < 16; t++) 0 == (3 & t) && (e = 4294967296 * Math.random()), o[t] = e >>> ((3 & t) << 3) & 255;
                        return o
                    }
                }
                e.exports = n
            }).call(t, n("./node_modules/webpack/buildin/global.js"))
        },
        "./node_modules/uuid/uuid.js": function(e, t, n) {
            function r(e, t, n) {
                var r = t && n || 0,
                    o = 0;
                for (t = t || [], e.toLowerCase().replace(/[0-9a-f]{2}/g, function(e) {
                        o < 16 && (t[r + o++] = c[e])
                    }); o < 16;) t[r + o++] = 0;
                return t
            }

            function o(e, t) {
                var n = t || 0,
                    r = u;
                return r[e[n++]] + r[e[n++]] + r[e[n++]] + r[e[n++]] + "-" + r[e[n++]] + r[e[n++]] + "-" + r[e[n++]] + r[e[n++]] + "-" + r[e[n++]] + r[e[n++]] + "-" + r[e[n++]] + r[e[n++]] + r[e[n++]] + r[e[n++]] + r[e[n++]] + r[e[n++]]
            }

            function a(e, t, n) {
                var r = t && n || 0,
                    a = t || [];
                e = e || {};
                var i = void 0 !== e.clockseq ? e.clockseq : d,
                    s = void 0 !== e.msecs ? e.msecs : (new Date).getTime(),
                    u = void 0 !== e.nsecs ? e.nsecs : h + 1,
                    c = s - v + (u - h) / 1e4;
                if (c < 0 && void 0 === e.clockseq && (i = i + 1 & 16383), (c < 0 || s > v) && void 0 === e.nsecs && (u = 0), u >= 1e4) throw new Error("uuid.v1(): Can't create more than 10M uuids/sec");
                v = s, h = u, d = i, s += 122192928e5;
                var l = (1e4 * (268435455 & s) + u) % 4294967296;
                a[r++] = l >>> 24 & 255, a[r++] = l >>> 16 & 255, a[r++] = l >>> 8 & 255, a[r++] = 255 & l;
                var p = s / 4294967296 * 1e4 & 268435455;
                a[r++] = p >>> 8 & 255, a[r++] = 255 & p, a[r++] = p >>> 24 & 15 | 16, a[r++] = p >>> 16 & 255, a[r++] = i >>> 8 | 128, a[r++] = 255 & i;
                for (var m = e.node || f, b = 0; b < 6; b++) a[r + b] = m[b];
                return t || o(a)
            }

            function i(e, t, n) {
                var r = t && n || 0;
                "string" == typeof e && (t = "binary" == e ? new Array(16) : null, e = null), e = e || {};
                var a = e.random || (e.rng || s)();
                if (a[6] = 15 & a[6] | 64, a[8] = 63 & a[8] | 128, t)
                    for (var i = 0; i < 16; i++) t[r + i] = a[i];
                return t || o(a)
            }
            for (var s = n("./node_modules/uuid/rng-browser.js"), u = [], c = {}, l = 0; l < 256; l++) u[l] = (l + 256).toString(16).substr(1), c[u[l]] = l;
            var p = s(),
                f = [1 | p[0], p[1], p[2], p[3], p[4], p[5]],
                d = 16383 & (p[6] << 8 | p[7]),
                v = 0,
                h = 0,
                m = i;
            m.v1 = a, m.v4 = i, m.parse = r, m.unparse = o, e.exports = m
        },
        "./node_modules/webpack/buildin/global.js": function(e, t) {
            var n;
            n = function() {
                return this
            }();
            try {
                n = n || Function("return this")() || (0, eval)("this")
            } catch (e) {
                "object" == typeof window && (n = window)
            }
            e.exports = n
        },
        "./src/js/Pagination.js": function(e, t, n) {
            "use strict";

            function r(e) {
                return e && e.__esModule ? e : {
                    default: e
                }
            }

            function o(e) {
                if (Array.isArray(e)) {
                    for (var t = 0, n = Array(e.length); t < e.length; t++) n[t] = e[t];
                    return n
                }
                return Array.from(e)
            }

            function a(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }

            function i(e, t) {
                if (!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
                return !t || "object" != typeof t && "function" != typeof t ? e : t
            }

            function s(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        enumerable: !1,
                        writable: !0,
                        configurable: !0
                    }
                }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
            }
            Object.defineProperty(t, "__esModule", {
                value: !0
            });
            var u = function() {
                    function e(e, t) {
                        var n = [],
                            r = !0,
                            o = !1,
                            a = void 0;
                        try {
                            for (var i, s = e[Symbol.iterator](); !(r = (i = s.next()).done) && (n.push(i.value), !t || n.length !== t); r = !0);
                        } catch (e) {
                            o = !0, a = e
                        } finally {
                            try {
                                !r && s.return && s.return()
                            } finally {
                                if (o) throw a
                            }
                        }
                        return n
                    }
                    return function(t, n) {
                        if (Array.isArray(t)) return t;
                        if (Symbol.iterator in Object(t)) return e(t, n);
                        throw new TypeError("Invalid attempt to destructure non-iterable instance")
                    }
                }(),
                c = function() {
                    function e(e, t) {
                        for (var n = 0; n < t.length; n++) {
                            var r = t[n];
                            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
                        }
                    }
                    return function(t, n, r) {
                        return n && e(t.prototype, n), r && e(t, r), t
                    }
                }();
            n("./src/scss/pagination.scss");
            var l = n(0),
                p = r(l),
                f = n("./node_modules/prop-types/index.js"),
                d = r(f),
                v = n("./src/js/PaginationButton.js"),
                h = r(v),
                m = n("./node_modules/uuid/uuid.js"),
                b = r(m),
                g = function(e) {
                    function t() {
                        return a(this, t), i(this, (t.__proto__ || Object.getPrototypeOf(t)).apply(this, arguments))
                    }
                    return s(t, e), c(t, [{
                        key: "createPrevNext",
                        value: function() {
                            var e = "_" + b.default.v1();
                            return [p.default.createElement(h.default, {
                                key: "prev",
                                active: !1,
                                disabled: 1 === this.props.activePage,
                                onSelect: this.props.onSelect,
                                eventKey: this.props.activePage - 1
                            }, p.default.createElement("span", {
                                className: "pagination-prev"
                            }, p.default.createElement("svg", {
                                focusable: "false",
                                role: "img",
                                "aria-labelledby": e,
                                className: "pe-icon--chevron-back-sm-18"
                            }, p.default.createElement("title", {
                                id: e
                            }, this.props.prevTitle), p.default.createElement("use", {
                                xlinkHref: "#chevron-back-sm-18"
                            })))), p.default.createElement(h.default, {
                                key: "next",
                                active: !1,
                                disabled: this.props.activePage === this.props.pages,
                                onSelect: this.props.onSelect,
                                eventKey: this.props.activePage + 1
                            }, p.default.createElement("span", {
                                className: "pagination-next"
                            }, p.default.createElement("svg", {
                                focusable: "false",
                                role: "img",
                                "aria-labelledby": "pe" + e,
                                className: "pe-icon--chevron-next-sm-18"
                            }, p.default.createElement("title", {
                                id: "pe" + e
                            }, this.props.nextTitle), p.default.createElement("use", {
                                xlinkHref: "#chevron-next-sm-18"
                            }))))]
                        }
                    }, {
                        key: "createEllipsis",
                        value: function(e) {
                            return p.default.createElement(h.default, {
                                key: e,
                                disabled: !0,
                                className: "ellipsis"
                            }, p.default.createElement("svg", {
                                focusable: "false",
                                role: "img",
                                className: "pe-icon--ellipsis-18"
                            }, p.default.createElement("use", {
                                xlinkHref: "#ellipsis-18"
                            })))
                        }
                    }, {
                        key: "createFirstLast",
                        value: function() {
                            return [p.default.createElement(h.default, {
                                key: "firstItem",
                                active: 1 === this.props.activePage,
                                onSelect: this.props.onSelect,
                                eventKey: 1
                            }, p.default.createElement("span", null, "1")), p.default.createElement(h.default, {
                                key: "maxpages",
                                active: this.props.activePage === this.props.pages,
                                onSelect: this.props.onSelect,
                                eventKey: this.props.pages
                            }, p.default.createElement("span", null, this.props.pages))]
                        }
                    }, {
                        key: "renderPageButtons",
                        value: function() {
                            var e = this,
                                t = this.createPrevNext(),
                                n = u(t, 2),
                                r = n[0],
                                a = n[1];
                            if ("compact" === this.props.paginationType) {
                                return [r, p.default.createElement("span", {
                                    key: "compact",
                                    className: "compact-text"
                                }, this.props.compactText), a]
                            }
                            var i = this.createFirstLast(),
                                s = u(i, 2),
                                c = s[0],
                                l = s[1],
                                f = [].concat(o(Array(this.props.pages))).map(function(e, t) {
                                    return t
                                }),
                                d = this.props.maxButtons;
                            this.props.pages < this.props.maxButtons + 2 && (d = this.props.pages - 2);
                            var v = this.props.activePage - 1 - parseInt(d / 2, 10);
                            this.props.activePage <= parseInt(d / 2, 10) + 1 && (v = 1), this.props.activePage >= this.props.pages - parseInt(d / 2, 10) && (v = this.props.pages - d - 1);
                            var m = v + d,
                                b = f.slice(v, m).map(function(t) {
                                    return p.default.createElement(h.default, {
                                        active: e.props.activePage === t + 1,
                                        key: t + 1,
                                        eventKey: t + 1,
                                        onSelect: e.props.onSelect
                                    }, p.default.createElement("span", null, t + 1))
                                });
                            return v > 1 && (b[0] = this.createEllipsis("frontEllipses")), d > 0 && l.props.eventKey > b[d - 1].props.eventKey + 1 && (b[d - 1] = this.createEllipsis("backEllipses")), [r, c].concat(o(b), [l, a])
                        }
                    }, {
                        key: "render",
                        value: function() {
                            return p.default.createElement("nav", {
                                "aria-label": "pagination",
                                "data-reactroot": "",
                                className: "pe-pagination"
                            }, this.renderPageButtons())
                        }
                    }]), t
                }(p.default.Component);
            g.propTypes = {
                pages: d.default.number.isRequired,
                activePage: d.default.number,
                onSelect: d.default.func.isRequired,
                maxButtons: d.default.number,
                prevTitle: d.default.string,
                nextTitle: d.default.string,
                compactText: d.default.string,
                paginationType: d.default.oneOf(["standard", "compact"])
            }, g.defaultProps = {
                maxButtons: 5,
                activePage: 1,
                prevTitle: "previous",
                nextTitle: "next",
                paginationType: "standard"
            }, t.default = g
        },
        "./src/js/PaginationButton.js": function(e, t, n) {
            "use strict";
            Object.defineProperty(t, "__esModule", {
                value: !0
            });
            var r = Object.assign || function(e) {
                    for (var t = 1; t < arguments.length; t++) {
                        var n = arguments[t];
                        for (var r in n) Object.prototype.hasOwnProperty.call(n, r) && (e[r] = n[r])
                    }
                    return e
                },
                o = n(0),
                a = function(e) {
                    return e && e.__esModule ? e : {
                        default: e
                    }
                }(o),
                i = function(e) {
                    var t = e.children,
                        n = e.active,
                        o = e.onSelect,
                        i = e.disabled,
                        s = e.className,
                        u = e.eventKey,
                        c = function(e) {
                            return o(u, e)
                        };
                    return a.default.createElement("button", r({
                        className: s,
                        type: "button"
                    }, n ? {
                        "aria-current": "page"
                    } : {}, {
                        onClick: c,
                        disabled: i
                    }), t)
                };
            t.default = i
        },
        "./src/js/PaginationContainer.js": function(e, t, n) {
            "use strict";

            function r(e) {
                return e && e.__esModule ? e : {
                    default: e
                }
            }

            function o(e, t) {
                if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
            }

            function a(e, t) {
                if (!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
                return !t || "object" != typeof t && "function" != typeof t ? e : t
            }

            function i(e, t) {
                if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
                e.prototype = Object.create(t && t.prototype, {
                    constructor: {
                        value: e,
                        enumerable: !1,
                        writable: !0,
                        configurable: !0
                    }
                }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
            }
            Object.defineProperty(t, "__esModule", {
                value: !0
            });
            var s = function() {
                    function e(e, t) {
                        for (var n = 0; n < t.length; n++) {
                            var r = t[n];
                            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
                        }
                    }
                    return function(t, n, r) {
                        return n && e(t.prototype, n), r && e(t, r), t
                    }
                }(),
                u = n(0),
                c = r(u),
                l = n("./src/js/Pagination.js"),
                p = r(l),
                f = function(e) {
                    function t(e) {
                        o(this, t);
                        var n = a(this, (t.__proto__ || Object.getPrototypeOf(t)).call(this, e));
                        return n.setActive = function(e) {
                            n.setState({
                                activePage: e
                            })
                        }, n.state = {
                            activePage: e.activePage
                        }, n
                    }
                    return i(t, e), s(t, [{
                        key: "render",
                        value: function() {
                            return c.default.createElement(p.default, {
                                pages: this.props.pages,
                                activePage: this.state.activePage,
                                onSelect: this.setActive,
                                maxButtons: this.props.maxButtons,
                                prevTitle: this.props.prevTitle,
                                nextTitle: this.props.nextTitle,
                                compactText: this.props.compactText,
                                paginationType: this.props.paginationType
                            })
                        }
                    }]), t
                }(c.default.Component);
            t.default = f
        },
        "./src/scss/pagination.scss": function(e, t, n) {
            var r = n("./node_modules/css-loader/index.js!./node_modules/sass-loader/lib/loader.js!./src/scss/pagination.scss");
            "string" == typeof r && (r = [
                [e.i, r, ""]
            ]);
            n("./node_modules/style-loader/addStyles.js")(r, {});
            r.locals && (e.exports = r.locals)
        },
        0: function(t, n) {
            t.exports = e
        },
        1: function(e, n) {
            e.exports = t
        },
        3: function(e, t, n) {
            e.exports = n("./demo/eventing.js")
        }
    })
});
sourceMappingURL=eventInterface.pagination.js.map