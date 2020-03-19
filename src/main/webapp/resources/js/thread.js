function enterEditingMode(regularContentId, editingContentId) {
    var regularContentElement = document.getElementById(regularContentId);
    var editingContentElement = document.getElementById(editingContentId);

    regularContentElement.style.display = "none";
    editingContentElement.style.visibility = "visible";
    editingContentElement.style.height = "100px";
}

function exitEditingMode(regularContentId, editingContentId) {
    var regularContentElement = document.getElementById(regularContentId);
    var editingContentElement = document.getElementById(editingContentId);
    
    regularContentElement.style.display = "block";
    editingContentElement.style.visibility = "hidden";
    editingContentElement.style.height = "0px";
}
