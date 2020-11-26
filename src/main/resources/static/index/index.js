$(function() {
    var icons = {
      header: "ui-icon-circle-arrow-e",
      activeHeader: "ui-icon-circle-arrow-s"
    };

    $("#toggle").button().on("click", function() {
      if ($("#accordion").accordion("option", "icons")) {
        $("#accordion").accordion("option", "icons", null);
      } else {
        $("#accordion").accordion("option", "icons", icons);
      }
    });

    $("#accordion").accordion({
        collapsible: true,
        icons: icons,
        header: "> div > h3"
    }).sortable({
        axis: "y",
        handle: "h3",
        stop: function(event, ui) {
            ui.item.children("h3").triggerHandler("focusout");
            $(this).accordion("refresh");
        }
    });
});



