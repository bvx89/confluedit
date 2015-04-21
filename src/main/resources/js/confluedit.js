var Confluedit = (function($, loc) {
    // jQuery DOM
    var $container,
        $parentDiv,
        $frame,
        $login,
        $start,

    // Connection URLS
        PATH_LOGIN = 'login.action?os_destination=',
        PATH_REST = 'confluence/rest/editor/1.0/connect/address',
        baseURL,
        restURL,
        loginURL


    var initConnection = function() {
        var emfsText = $('#confluedit-xtext').text().trim(),
            dataElements = $('#confluedit-editor').find('input[type=hidden]'),
            dataInformation = {},
            el;

        for (var i = dataElements.length -1; i >= 0; i--) {
            el = dataElements[i];
            dataInformation[el.id] = el.value;
        }

        dataInformation['emfs'] = emfsText;

        restURL = '/' + PATH_REST;
        $.ajax({
          type: 'POST',
          url: restURL,
          contentType: 'application/json; charset=utf-8',
          data: JSON.stringify(dataInformation)
        }).done(connectionInitialized);

        // Set loading gif and remove content
        $parentDiv.fadeOut(function() {
            $container.html('');
        });

    },

    connectionInitialized = function(response) {
        $frame = $('<iframe />');
        $frame.attr('src', response)
            .attr('width', '100%')
            .attr('height', '100%');

        $parentDiv.stop().remove();
        $container.animate({
            width: '100%',
        }, function() {
            $frame.css('display', 'hidden');
            $container.css('background', 'none').html($frame);
            $frame.fadeIn('slow');
        });
    }

    createLoginURL = function() {
        if (loginURL != null) {
            return loginURL;
        }

        // Find index for the first path in the URL
        splitIndex = loc.pathname.indexOf('/', 1),

        // Construct the two last paths
        loginPath = loc.pathname.substring(0, splitIndex +1) + PATH_LOGIN,
        redirectPath = encodeURIComponent(loc.pathname.substring(splitIndex +1)) || '',

        // Combine in to the final path
        totalLoginURL = baseURL + loginPath + redirectPath;

        return loginURL = totalLoginURL;
    }

    return {
        init : function() {
            baseURL = loc.protocol + '//' + loc.hostname + ':' + loc.port;

            $container = $('#confluedit-container');
            $parentDiv = $('#confluedit-editor');
            $login = $parentDiv.children('#confluedit-login');
            $start = $parentDiv.children('#confluedit-start');

            $login.click(function(ev) {
                window.open(createLoginURL());
            });

            $start.click(initConnection);
        }

    };

})(jQuery, window.location);




$(document).ready(function() {
    Confluedit.init();
});
