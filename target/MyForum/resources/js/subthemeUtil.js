function _SubThemeUtil() {
    this.deleteSubTheme = function(id) {
        if (confirm("Are you sure?")) {
            window.location = "/deleteSubTheme/" + id;
        }
    }
}

var SubThemeUtil = new _SubThemeUtil();