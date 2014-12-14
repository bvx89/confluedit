jQuery(function ($) {
    var _EDITOR_PREFIX = '-editor'
    var initEditor = function () {
        var counter = 0;
        $('#editors').children().each(function() {
            // set ID and initiate
            var $this = $(this).prop('id', ++counter + _EDITOR_PREFIX),
                editor = ace.edit(counter + _EDITOR_PREFIX);

            editor.setTheme("ace/theme/monokai");
            editor.getSession().setMode("ace/mode/java");


        });
    };

    $(document).ready(function(){
        initEditor();
    });
});