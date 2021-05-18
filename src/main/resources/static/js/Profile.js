function openEdit(button) {
    let panelEdit = document.getElementsByClassName('panel-edit').item(0);
    let panel = document.getElementsByClassName('panel').item(0);

    panel.style.display = 'none';
    panelEdit.style.display = 'flex';

}