(function ($, Coral) {
    "use strict";

    $(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
        selector: "[data-validation=geeks-firstname-validation]",
        validate: function (element) {
            let el = $(element);
            let value = el.val();
            let pattern = /[0-9a-z]/; // pattern to check lowercase or digits
            if (pattern.test(value)) {
                return "Please add only Upper Case Letters in First name";
            }
        }
    });

})(jQuery, Coral);
