const loginBtn=document.querySelector(".logBtn");
const signUpBtn=document.querySelector(".signUpBtn");
const loginOverlay=document.getElementById("loginOverlay");
const signUpOverlay=document.getElementById("signUpOverlay");
const loginCloseBtn=document.getElementById("loginCloseBtn");
const signUpCloseBtn=document.getElementById("signUpCloseBtn");
const registerBtn=document.getElementById("registerBtn");
const signUpForm=document.getElementById("signUpForm");
const loginForm=document.getElementById("loginForm");
const signUpUsernameInput=document.getElementById("signUpUsernameInput");
const signUpEmailInput=document.getElementById("signUpEmailInput");
const signUpPasswordInput=document.getElementById("signUpPasswordInput");
const signUpUsernameError=document.getElementById("signUpUsernameError");
const signUpEmailError=document.getElementById("signUpEmailError");
const signUpPasswordError=document.getElementById("signUpPasswordError");
const loginEmailInput=document.getElementById("loginEmailInput");
const loginPasswordInput=document.getElementById("loginPasswordInput");
const loginEmailError=document.getElementById("loginEmailError");
const loginPasswordError=document.getElementById("loginPasswordError");
const logoutBtn=document.querySelector(".logoutBtn");
const loggedSec=document.getElementById("loggedSec");
const heroSec=document.getElementById("heroSec");
const heroSecAfterAuth=document.getElementById("heroSecAfterAuth");
const main=document.getElementById("main");
const imageSec=document.getElementById("imageSec");
const beforeSearchImageSec=document.getElementById("beforeSearchImageSec");
const afterSearchImageSec=document.getElementById("afterSearchImageSec");
const bsLoadMoreBtn=document.getElementById("bsLoadMoreBtn");
const searchInput=document.getElementById("searchInput");
const searchBtn=document.getElementById("searchBtn");
const asLoadMoreBtn=document.getElementById("asLoadMoreBtn");
const beforeSearchSec=document.getElementById("beforeSearch");
const afterSearchSec=document.getElementById("afterSearch");


//Overlay display changes
loginBtn.addEventListener("click",()=>loginOverlay.style.display="flex");
loginCloseBtn.addEventListener("click",()=>loginOverlay.style.display="none");
signUpBtn.addEventListener("click",()=>signUpOverlay.style.display="flex");
signUpCloseBtn.addEventListener("click",()=>signUpOverlay.style.display="none");
registerBtn.addEventListener("click",()=>signUpOverlay.style.display="flex");

checkAuth();
//if jwt is in local storage,which means user logged in so changes accordingly
function checkAuth() {
  const token = localStorage.getItem("jwtToken");
  if (token) {
    loginBtn.style.display = "none";
    signUpBtn.style.display = "none";
    loggedSec.style.display = "inline";
    heroSec.style.display="none";
    main.style.padding="0";
    heroSecAfterAuth.style.display="flex";
  } else {
    loginBtn.style.display = "inline";
    signUpBtn.style.display = "inline";
    loggedSec.style.display = "none";
    heroSec.style.display="flex";
    heroSecAfterAuth.style.display="none";
  }
}


// SignUp Validation
signUpUsernameInput.addEventListener("input",()=>{
        if(signUpUsernameInput.value.trim().length<3){
          signUpUsernameError.textContent="Username must be atleast 3 characters";
        }else{
          signUpUsernameError.textContent="";
        }
});
signUpEmailInput.addEventListener("input",()=>{
        if(signUpEmailInput.value.trim().length<12||!signUpEmailInput.value.trim().includes('@')){
          signUpEmailError.textContent="Enter valid Email";
        }else{
          signUpEmailError.textContent="";
        }
});
signUpPasswordInput.addEventListener("input",()=>{
        if(signUpPasswordInput.value.trim().length<8){
          signUpPasswordError.textContent="Password must be atleast 8 characters";
        }else{
          signUpPasswordError.textContent="";
        }
});
signUpForm.addEventListener("submit",(e)=>{
  e.preventDefault();
        const username = signUpUsernameInput.value.trim();
    const email = signUpEmailInput.value.trim();
    const password = signUpPasswordInput.value.trim();
 if (username.length < 3 || email.length < 5 || !email.includes('@') || password.length < 8) {
        alert("Please Enter Valid Credentials.");
        return; // stop further execution
    }
           const User = {
                 username: username,
                 email: email,
                password: password
           };
          userSignUp(User);
        });
async function userSignUp(User) {
          const url="http://localhost:8080/api/auth/signup";
          try{
          const res=await fetch(url,{
                method:'POST',
                headers:{
                  "Content-Type":"application/json"
                },
                body:JSON.stringify(User)
          });
          if(res.ok){
            const data=await res.json();
            alert("Signup Successful!");
            localStorage.setItem("jwtToken",data.jwtToken);
            localStorage.setItem("email",User.email);
            window.location.href="/dashboard.html";
          }else{
            alert("Signup Failed!");
          }
          }catch(e){
               console.log("oops..something went wrong",e)
        }
}

//Login Validation
loginEmailInput.addEventListener("input",()=>{
        if(loginEmailInput.value.trim().length<12||!loginEmailInput.value.trim().includes('@')){
          loginEmailError.textContent="Enter valid Email";
        }else{
          loginEmailError.textContent="";
        }
});
loginPasswordInput.addEventListener("input",()=>{
        if(loginPasswordInput.value.trim().length<8){
          loginPasswordError.textContent="Password must be atleast 8 characters";
        }else{
          loginPasswordError.textContent="";
        }
});
loginForm.addEventListener("submit",(e)=>{
              e.preventDefault();
        const email=loginEmailInput.value.trim();
        const password=loginPasswordInput.value.trim();
        if(email.length<12||!email.includes('@')||password.length<8){
                e.preventDefault();
                alert("Please Enter valid Credentials");
                return;
        }
        const User={
                email:email,
                password:password
        }
        userLogin(User);
});
  async function userLogin(User) {
             const url="http://localhost:8080/api/auth/login";
             try{
            const res= await fetch(url,{
                    method:"POST",
                    headers:{
                        "Content-Type":"application/json"
                    },
                    body:JSON.stringify(User)
             });
             if(res.ok){
                   const data=await res.json();
                   alert("Login Successful!");
                   localStorage.setItem("jwtToken",data.jwtToken);
                   localStorage.setItem("email",User.email);
                   checkAuth();
                    window.location.href="/dashboard.html";
             }else{
                 alert("Login Failed..");
             }
            }catch(err){
                 console.log("something went wrong..");
            }
  }

//logout functionality
logoutBtn.addEventListener("click",()=>{
          localStorage.removeItem("jwtToken");
          localStorage.removeItem("email");
           checkAuth();
});

//images in home page
let bs_page=1;
let as_page=1;
let size=10;
beforeSearch();
async function beforeSearch() {
  const query="random";
  const accessToken="imageapiaccesstoken";
  const url=`http://localhost:8080/api/search?accessToken=${accessToken}&query=${query}&page=${bs_page}&size=${size}`;
  try{
  const res=await fetch(url);
  if(res.ok){
  const data=await res.json();
 if (bs_page === 1) beforeSearchImageSec.textContent = "";
  console.log("API URL:", url, "Response:", data);
    data.images.forEach(image=>{
              const img=document.createElement("img");
              img.classList.add("displaySearchImage");
              img.src=image.url;
              beforeSearchImageSec.append(img);
    });
    if(bs_page>=data.totalPages){
      bsLoadMoreBtn.style.display="none";
    }else{
      bsLoadMoreBtn.style.display="block";
    }
  }else{
    console.log("Something went wrong..failed to search images");
  }
}catch(err){
  console.log("oops..",err);
}
}
bsLoadMoreBtn.addEventListener("click",()=>{
          bs_page++;
          beforeSearch();
});

async function afterSearch(query) {
  const accessToken="imageapiaccesstoken";
  const url=`http://localhost:8080/api/search?accessToken=${accessToken}&query=${query}&page=${as_page}&size=${size}`;
  try{
  const res=await fetch(url);
  if(res.ok){
  const data=await res.json();
  console.log("API URL:", url, "Response:", data);
  if (as_page === 1) afterSearchImageSec.textContent = "";
    data.images.forEach(image=>{
              const img=document.createElement("img");
              img.classList.add("displaySearchImage");
              img.src=image.url;
              afterSearchImageSec.append(img);
    });
    if(as_page>=data.totalPages){
      asLoadMoreBtn.style.display="none";
    }else{
      asLoadMoreBtn.style.display="block";
    }
  }else{
    console.log("Something went wrong..failed to search images");
  }
}catch(err){
  console.log("oops..",err);
}
}


searchBtn.addEventListener("click",()=>{
      const input=searchInput.value.trim();
      if(input.length>0){
        bs_page=1;
        afterSearchImageSec.textContent="";
        beforeSearchSec.style.display="none";
        afterSearchSec.style.display="flex";
        as_page=1;
        afterSearch(input);
      }else{
        as_page=1;
       afterSearchSec.style.display="none";
        beforeSearchSec.style.display="flex";
        bs_page=1;
        beforeSearch();
      }

});

asLoadMoreBtn.addEventListener("click",()=>{
          as_page++;
          const input=searchInput.value.trim();
          afterSearch(input);
})