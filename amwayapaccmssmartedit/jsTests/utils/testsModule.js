/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
customMatchers = function() {
    jasmine.addMatchers({
        toEqualData: function(util, customEqualityTesters) {
            return {
                compare: function(actual, expected) {
                    var passed = angular.equals(actual, expected);

                    return {
                        pass: passed,
                        message: 'Expected ' + actual + (passed ? '' : ' not') + ' to equal ' + expected
                    };
                }
            };
        },
        toHaveClass: function(util, customEqualityTesters) {
            return {
                compare: function(element, className) {
                    var passed = element.hasClass(className);

                    return {
                        pass: passed,
                        message: 'Expected ' + element + (passed ? '' : ' not') + ' to have class ' + className
                    };
                }
            };
        },
        fail: function(util, customEqualityTesters) {
            return {
                compare: function(actual, errorMessage) {
                    return {
                        pass: false,
                        message: errorMessage
                    };
                }
            };
        },
        toHaveThatManyAlerts: function(util, customEqualityTesters) {
            return {
                compare: function(element, expected) {
                    var actual = element.find("div.alert span").length;
                    var passed = (actual === expected);

                    return {
                        pass: passed,
                        message: 'Expected ' + element + (passed ? '' : ' not') + ' to have ' + expected + ' alert(s)'
                    };
                }
            };
        },
        messageToBe: function(util, customEqualityTesters) {
            return {
                compare: function(element, expected) {
                    var actual = element.find("div.alert-success span").text();
                    var passed = (actual === expected);

                    return {
                        pass: passed,
                        message: 'Expected message' + (passed ? '' : ' not') + ' to be ' + expected
                    };
                }
            };
        },
        alertToBe: function(util, customEqualityTesters) {
            return {
                compare: function(element, expected) {
                    var actual = element.find("div.alert-danger span").text();
                    var passed = (actual === expected);

                    return {
                        pass: passed,
                        message: 'Expected alert' + (passed ? '' : ' not') + ' to be ' + expected
                    };
                }
            };
        },
        inputToBe: function(util, customEqualityTesters) {
            return {
                compare: function(element, expected) {
                    var actual = element.find("div input[type=text]").val();
                    var passed = (actual === expected);

                    return {
                        pass: passed,
                        message: 'Expected input' + (passed ? '' : ' not') + ' to be ' + expected
                    };
                }
            };
        },
        displayToBe: function(util, customEqualityTesters) {
            return {
                compare: function(element, expected) {
                    var actual = element.find('span').html();
                    var passed = (actual === expected);

                    return {
                        pass: passed,
                        message: 'Expected ' + actual + (passed ? '' : ' not') + ' to be ' + expected
                    };
                }
            };
        },
        flagToBeTrue: function(util, customEqualityTesters) {
            return {
                compare: function(element) {
                    var passed = (element.find("> input[src='http/images/tick.png']").length === 1);

                    return {
                        pass: passed,
                        message: 'Expected flag' + (passed ? '' : ' not') + ' to be true'
                    };
                }
            };
        },
        flagToBeFalse: function(util, customEqualityTesters) {
            return {
                compare: function(element) {
                    var passed = (element.find("> input[src='http/images/no-tick.png']").length === 1);

                    return {
                        pass: passed,
                        message: 'Expected flag' + (passed ? '' : ' not') + ' to be false'
                    };
                }
            };
        },
        flagToBeUndetermined: function(util, customEqualityTesters) {
            return {
                compare: function(element) {
                    var passed = (element.find("> input[src='http/images/question.png']").length === 1);

                    return {
                        pass: passed,
                        message: 'Expected flag' + (passed ? '' : ' not') + ' to be undetermined'
                    };
                }
            };
        },
        toBeInEditMode: function(util, customEqualityTesters) {
            return {
                compare: function(element) {
                    var passed = (element.find("> div > input[type=text][data-ng-model='editor.temp']").length === 1);

                    return {
                        pass: passed,
                        message: 'Expected' + (passed ? '' : ' not') + ' to be in edit mode'
                    };
                }
            };
        },
        calendarToBeDisplayed: function(util, customEqualityTesters) {
            return {
                compare: function(element) {
                    var passed = (element.find("ul.dropdown-menu").css('display') === 'block');

                    return {
                        pass: passed,
                        message: 'Expected calendar' + (passed ? '' : ' not') + ' to be displayed'
                    };
                }
            };
        }
    });
};

$.fn.extend({
    sendKeys: function(keys) {
        return this.each(function() {
            $(this).find("div input").val(keys).trigger('input');
        });
    },
    openCalendar: function() {
        return this.each(function() {
            $(this).find(".datepickerbutton").click();
        });
    },
    selectDate: function(dateNumber) {
        return this.each(function() {
            $(this).find("span:contains('" + dateNumber + "')").click();
        });
    },
    pressEnter: function() {
        return this.each(function() {
            // $(this).trigger('keypress')
            $(this).trigger($.Event('keypress', {
                which: 13
            }));
        });
    },
    reset: function() {
        return this.each(function() {
            $(this).find("input[type=image]").click();
        });
    }
});
$.extend({
    getOptions: function(element) {
        return element.find("div.dropdown span");
    },
    getTristateOptions: function(element) {
        return element.find("div.dropdown > span"); // > input[type=image]
    }

});

$.extend($.expr[':'], {
    "block": function(a, i, m) {
        return $(a).css("display") == "block";
    }
});
