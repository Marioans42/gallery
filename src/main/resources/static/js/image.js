async function uploadFile() {
    let formData = new FormData();
    let fileUpload = document.getElementById("image");
    let rubric = document.getElementById("rubric");
    let name = document.getElementById("name");

    formData.append("file", fileUpload.files[0]);
    formData.append("idRubrique", rubric.value);
    //formData.append("idRubric", rubric);

    let response = await fetch('/save', {
        method: "POST",
        body: formData
    });

    if (response.status == 200) {
        alert("File successfully uploaded.");
    }
}
