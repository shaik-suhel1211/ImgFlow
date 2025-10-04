const email=localStorage.getItem("email");
const jwtToken=localStorage.getItem("jwtToken");

const accessTokenSec=document.getElementById("accessTokenSec");
const copyTokenBtn=document.getElementById("copyTokenBtn");
const copyMsg=document.getElementById("copyMsg");
const logoutBtn=document.getElementById("logoutBtn");
const imageTitle=document.getElementById("imageTitle");
const fileInput=document.getElementById("fileInput");
const urlInput=document.getElementById("urlInput");
const uploadBtn=document.getElementById("uploadBtn");
const imageSec=document.getElementById("imageSec");

//copy the access token when click using navigator
copyTokenBtn.addEventListener("click",()=>{
      try{
    navigator.clipboard.writeText(accessTokenSec.value).then(()=>{
             copyMsg.style.display="inline";
             setTimeout(()=>{
                 copyMsg.style.display="none";
             },3000)
    });
}catch(err){
   console.error("Failed to copy",err);
}
});

//logout functionality
logoutBtn.addEventListener("click",()=>{
    localStorage.removeItem("jwtToken");
    window.location.href="/home.html";
});

//for access token jwt is sent in header from local storage to server for validating token 
async function getAccessToken(){
const url="http://localhost:8080/api/getUser/"+email;
 try{
const res=await fetch(url,{
    method:"GET",
    headers:{
        "Content-Type":"application/json",
        "Authorization":"Bearer "+jwtToken
    }
});
if(res.ok){
    const data=await res.json();
    accessTokenSec.value=data.accessToken;
}
 }catch(err){
    console.log(err);
 }
}
getAccessToken();

//Image Upload 
uploadBtn.addEventListener("click",async (e)=>{
            e.preventDefault();
         const url="http://localhost:8080/api/images/upload";
         const formData=new FormData();
         formData.append("title",imageTitle.value);
         if(fileInput.files.length>0){
            formData.append("file",fileInput.files[0]);
         }else if(urlInput.value.trim() !==""){
               formData.append("url",urlInput.value.trim());
         }else{
            alert("Please Select a file or enter an image URL.");
            return;
         }
           try{
                const res=await fetch(url,{
             method:"POST",
              headers:{
              "Authorization":"Bearer "+jwtToken
                },
                body:formData
                 });
                 if(res.ok){
                    const data=await res.json();
                    alert("Image Uploaded Successfully");
                    getAllImages();
                    } else {
                      alert("Image Upload Failed");
                }
                }catch(err){
                    console.log(err);
                    }
});

//image section
 async function getAllImages() {
         const url="http://localhost:8080/api/images/myImages";
         try{
         const res=await fetch(url,{
                 method:"GET",
                 headers:{
                    "Content-Type":"application/json",
                     "Authorization":"Bearer "+jwtToken
                 }
         });
         if(res.ok){
            const data=await res.json();
            imageSec.textContent="";
            data.forEach(image=>{
                      const img=document.createElement("img");
                      img.classList.add("uploadedImage");
                      img.src=image.url;
                      const div=document.createElement("div");
                     const btn=document.createElement("button");
                     btn.classList.add("deleteImageBtn");
                     btn.textContent="Delete";
                     div.append(img,btn);
                     imageSec.append(div);
                //delete btn functionality
                      btn.addEventListener("click", async () => {
                const delRes = await fetch("http://localhost:8080/api/images/delete/"+image.id, {
                    method: "DELETE",
                    headers: {
                        "Authorization": "Bearer " + jwtToken
                    }
                   });
                     if (delRes.ok) {
                       div.remove();
                     } else {
                    alert("Failed to delete image");
                    }
            });

            })
         }
        }catch(err){
            console.log("Something went wrong..",err);
        }
 }

getAllImages();