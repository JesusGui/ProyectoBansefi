;
(function(win, $) {
    $.widget("babel.tablabansefi", {

        settings: {
            hiddenColumns: true, //Ocultar Columnas
            miniMap: true, // Show minimap.     	
        },


        _create: function() {
            var that = this;
           

            if (typeof TablaBansefi === "undefined") {
                TablaBansefi = {};
            }
            if (!TablaBansefi.config) {
                TablaBansefi.config = {};
            }
            that.originalTable = {
                el: that.element,
                selectedHandle: $(),
                sortOrder: {},
                startIndex: 0,
                endIndex: 0
            };


            that.bindTo = that.originalTable.el.find('th');
            
            
            that.createSwipeTable(that.element);
            
            $(".ui-chkbox-box").on("click", function() {
            	$(win).resize();
            });
            $(that.element).prev('.table-bar').prependTo($('.ui-datatable-header'));
            
        },
        
        
        
        createMiniMap: function($table) {
            var that = this;
            var $btns = $('<div class="table-advance minimap">'),
                $dotNav = $('<ul class="table-advance-dots">').appendTo($btns),
                hideDot = 'table-advance-dots-hide',
                $headerCells = $table.find('thead th');

            // populate dots
            $headerCells.each(function() {
                $dotNav.append('<li><i></i></li>');
            });
            //TODO que alguien revise esto, funciona pero no es lo mas optimo
            $btns.insertAfter($table.prev('.table-bar').find('.table-nav-btn.left'));

            function showHideNav() {

                $btns.show();

                // show/hide dots
                var dots = $dotNav.find("li").removeClass(hideDot);
                $table.find("thead th").each(function(i) {
                    if ($(this).css("display") === "none") {
                        dots.eq(i).addClass(hideDot);
                    }
                });
            }

            // run on init and resize
            showHideNav();
            $(win).on("resize", showHideNav);
            $table
                .bind("tablecolumns.minimap", function() {
                    showHideNav();
                })
                .bind("tabledestroy.minimap", function() {
                    var $t = $(this);

                    $t.prev('.table-bar').find('.table-advance').remove();
                    $(win).off("resize", showHideNav);

                    $t.unbind(".minimap");
                });
        },

        createSwipeTable: function($table) {
            var that = this;

            $table[pluginName]();

            var $btns = $("<div class='table-advance'></div>"),
                $prevBtn = $("<a href='#' class='table-nav-btn btn btn-micro left' title='Columna Anterior'></a>").appendTo($btns),
                $nextBtn = $("<a href='#' class='table-nav-btn btn btn-micro right' title='Columna Siguiente'></a>").appendTo($btns),
                hideBtn = 'disabled',
                persistWidths = 'table-fix-persist',
                $headerCells = $table.find("thead th"),
                $headerCellsNoPersist = $headerCells.not('[data-table-priority="persist"]'),
                headerWidths = [],
                $head = $(document.head || 'head'),
                tableId = $table.attr('id'),
                // TODO switch this to an nth-child feature test
                isIE8 = $('html').is('.ie-lte8');

            if (!$headerCells.length) {
                throw new Error("table swipe: no header cells found. Are you using <th> inside of <thead>?");
            }
            
            // Calculate initial widths
            if($headerCells.length <= 6){
            	var width = Math.floor(100 / $headerCells.length).toString().concat("%");
            	$table.css('width', width);
            } else {
            	$table.css('width', 'auto');
            }
                        
            $headerCells.each(function() {
                headerWidths.push($(this).outerWidth());
            });
            $table.css('width', '');

            $btns.appendTo($table.prev('.table-bar'));

            $table.addClass("table-swipe");

            if (!tableId) {
                tableId = 'tableswipe-' + Math.round(Math.random() * 10000);
                $table.attr('id', tableId);
            }

            function $getCells(headerCell) {
                return $(headerCell.cells).add(headerCell);
            }

            function showColumn(headerCell) {
                $getCells(headerCell).removeClass('table-cell-hidden');
            }

            function hideColumn(headerCell) {
                $getCells(headerCell).addClass('table-cell-hidden');
            }

            function persistColumn(headerCell) {
                $getCells(headerCell).addClass('table-cell-persist');
            }

            function isPersistent(headerCell) {
                return $(headerCell).is('[data-table-priority="persist"]');
            }

            function unmaintainWidths() {
                $table.removeClass(persistWidths);
                $('th[id$=-persist').remove();
            }

            function maintainWidths() {
                var prefix = '#' + tableId + '.table-swipe ',
                    styles = [],
                    tableWidth = $table.width(),
                    hash = [],
                    newHash;

                $headerCells.each(function(index) {
                    var width;
                    if (isPersistent(this)) {
                        width = $(this).outerWidth();

                        // Only save width on non-greedy columns (take up less than 75% of table width)
                        if (width < tableWidth * 0.75) {
                            hash.push(index + '-' + width);
                            styles.push(prefix + ' .table-cell-persist:nth-child(' + (index + 1) + ') { width: ' + width + 'px; }');
                        }
                    }
                });
                newHash = hash.join('_');

                $table.addClass(persistWidths);
                
                var $style = $('th[id$=-persist');
                // If style element not yet added OR if the widths have changed
                if (!$style.length || $style.data('hash') !== newHash) {
                    // Remove existing
                    $style.remove();

                    if (styles.length) {
                        $('<style>' + styles.join("\n") + '</style>')
                            .attr('id', tableId + '-persist')
                            .data('hash', newHash)
                            .appendTo($head);
                    }
                }
            }

            function getNext() {
                var next = [],
                    checkFound;

                $headerCellsNoPersist.each(function(i) {
                    var $t = $(this),
                        isHidden =  $t.is(".table-cell-hidden");

                    if (!isHidden && !checkFound) {
                        checkFound = true;
                        next[0] = i;
                    } else if (isHidden && checkFound) {
                        next[1] = i;

                        return false;
                    }
                });

                return next;
            }

            function getPrev() {
                var next = getNext();
                return [next[1] - 1, next[0] - 1];
            }

            function nextpair(fwd) {
                return fwd ? getNext() : getPrev();
            }

            function canAdvance(pair) {
                return pair[1] > -1 && pair[1] < $headerCellsNoPersist.length;
            }

            function matchesMedia() {
                var matchMedia = $table.attr("data-table-swipe-media");
                return !matchMedia || ("matchMedia" in win) && win.matchMedia(matchMedia).matches;
            }

            function fakeBreakpoints() {
                if (!matchesMedia()) {
                    return;
                }

                var extraPaddingPixels = 20,
                    containerWidth = $table.parent().width(),
                    persist = [],
                    sum = 0,
                    sums = [],
                    visibleNonPersistantCount = $headerCells.length;

                $headerCells.each(function(index) {
                    var $t = $(this),
                        isPersist = $t.is('[data-table-priority="persist"]');

                    persist.push(isPersist);

                    sum += headerWidths[index] + (isPersist ? 0 : extraPaddingPixels);
                    sums.push(sum);

                    // is persistent or is hidden
                    if (isPersist || sum > containerWidth) {
                        visibleNonPersistantCount--;
                    }
                });

                var needsNonPersistentColumn = visibleNonPersistantCount === 0;

                $headerCells.each(function(index) {
                    if (persist[index]) {

                        // for visual box-shadow
                        persistColumn(this);
                        return;
                    }

                    if (sums[index] <= containerWidth || needsNonPersistentColumn) {
                        needsNonPersistentColumn = false;
                        showColumn(this);
                    } else {
                        hideColumn(this);
                    }
                });

                if (!isIE8) {
                    unmaintainWidths();
                }
                $table.trigger('tablecolumns');
            }

            function advance(fwd) {
                var pair = nextpair(fwd);
                if (canAdvance(pair)) {
                    if (isNaN(pair[0])) {
                        if (fwd) {
                            pair[0] = 0;
                        } else {
                            pair[0] = $headerCellsNoPersist.length - 1;
                        }
                    }

                    if (!isIE8) {
                        maintainWidths();
                    }

                    hideColumn($headerCellsNoPersist.get(pair[0]));
                    showColumn($headerCellsNoPersist.get(pair[1]));

                    $table.trigger('tablecolumns');
                }
            }

            $prevBtn.add($nextBtn).click(function(e) {
                advance(!!$(e.target).closest($nextBtn).length);
                e.preventDefault();
            });

            function getCoord(event, key) {
                return (event.touches || event.originalEvent.touches)[0][key];
            }

            $table
                .bind("touchstart.swipetoggle", function(e) {
                    var originX = getCoord(e, 'pageX'),
                        originY = getCoord(e, 'pageY'),
                        x,
                        y;

                    $(win).off("resize", fakeBreakpoints);
                    $(this)
                        .bind("touchmove", function(e) {
                            x = getCoord(e, 'pageX');
                            y = getCoord(e, 'pageY');

                        })
                        .bind("touchend.swipetoggle", function() {


                            window.setTimeout(function() {
                                $(win).on("resize", fakeBreakpoints);
                            }, 300);
                            $(this).unbind("touchmove touchend");
                        });

                })
                .bind("tablecolumns.swipetoggle", function() {
                    $prevBtn[canAdvance(getPrev()) ? "removeClass" : "addClass"](hideBtn);
                    $nextBtn[canAdvance(getNext()) ? "removeClass" : "addClass"](hideBtn);
                })
                .bind("tablenext.swipetoggle", function() {
                    advance(true);
                })
                .bind("tableprev.swipetoggle", function() {
                    advance(false);
                })
                .bind("tabledestroy.swipetoggle", function() {
                    var $t = $(this);

                    $t.removeClass('table-swipe');
                    $t.prev('.table-bar').find('.table-advance').remove();
                    $(win).off("resize", fakeBreakpoints);
                    $t.unbind(".swipetoggle");
                });

            if($headerCells.length > 6){
            	fakeBreakpoints();
            }
            $(win).on("resize", fakeBreakpoints);

            that.createMiniMap(that.element);
        }

    });

	var pluginName = "table",
            classes = {
                toolbar: "table-bar"
            },
            events = {
                create: "create",
                destroy: "destroy",
                refresh: "trefresh"
            };

        var Table = function(element) {

            this.table = element;
            this.$table = $(element);

            this.init();
        };

        Table.prototype.init = function() {

            if (!this.$table.attr("id")) {
                this.$table.attr("id", pluginName + "-" + Math.round(Math.random() * 10000));
            }

            this.createToolbar();

            var colstart = this._initCells();

            this.$table.trigger(events.create, [this, colstart]);
        };

        Table.prototype._initCells = function() {
            var colstart,
                thrs = this.table.querySelectorAll("thead tr"),
                self = this;

            $(thrs).each(function() {
                var coltally = 0;

                $(this).children().each(function() {
                    var span = parseInt(this.getAttribute("colspan"), 10),
                        sel = ":nth-child(" + (coltally + 1) + ")";

                    colstart = coltally + 1;

                    if (span) {
                        for (var k = 0; k < span - 1; k++) {
                            coltally++;
                            sel += ", :nth-child(" + (coltally + 1) + ")";
                        }
                    }

                    // Store "cells" data on header as a reference to all cells in the same column as this TH
                    this.cells = self.$table.find("tr").not($(thrs).eq(0)).not(this).children(sel);
                    coltally++;
                });
            });

            return colstart;
        };

        Table.prototype.refresh = function() {
            this._initCells();

            this.$table.trigger(events.refresh);
        };

        Table.prototype.createToolbar = function() {
            // Insert the toolbar
            // TODO move this into a separate component
            var $toolbar = this.$table.prev('.' + classes.toolbar);
            if (!$toolbar.length) {
                $toolbar = $('<div>')
                    .addClass(classes.toolbar)
                    .insertBefore(this.$table);
            }
            this.$toolbar = $toolbar;

           
        };

        // Collection method.
        $.fn[pluginName] = function() {
            return this.each(function() {
                var $t = $(this);

                if ($t.data(pluginName)) {
                    return;
                }

                var table = new Table(this);
                $t.data(pluginName, table);
            });
        };
        

})(window, jQuery);

