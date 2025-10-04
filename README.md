# ImgFlow
ImgFlow is an image API service built with Spring Boot (backend) and a simple HTML, CSS, JS (frontend).
It allows developers or users to register, authenticate, upload, and manage images securely, while also providing an access token based system for retrieving images.

## [Demo Video] (https://shaik-suhel1211.github.io/project-demo-video/)

### Features
- **User Authentication**
  - Register and login with JWT authentication
  - JWT required for protected actions like upload, delete, and dashboard access
  - Secure dashboard access right after registration
  - JWT is automatically stored in local storage and sent with requests that require authentication
- **Image Management**
  - Upload images (stored in Cloudinary)
  - Delete your own images
  - View your uploaded images
  - Image URLs are only exposed to valid users
- **API Access**
  - Retrieve images using an Access Token (no JWT required)
  - Search images with queries
  - Pagination support (query, page, size)
  - Uploading or deleting images requires JWT token in headers

### Tech Stack
 - **Backend:** Spring Boot
   - Spring Boot Web (REST APIs)
   - Spring Boot Data JPA (database handling)
   - Spring Boot Security with JWT
 - **Database:** MySQL (stores user info and image info with URLs)
 - **Storage:** Cloudinary (for images)
 - **Frontend:** HTML, CSS, JavaScript

# API Endpoints
### Authentication
 - POST /api/auth/signup
   - Register a new user
   - Returns a JWT token
 - POST /api/auth/login
   - Login with credentials
   - Returns a JWT token
### Image Access (Access Token Required)
   - GET /api/search?accessToken=&query=&page=&size=
   - Retrieve images using an access token
   - Supports search and pagination
### Image Management (JWT Required in Headers - JWT is automatically stored in local storage and sent with requests that require authentication)
   - POST /api/images/upload
     - Upload an image (requires JWT in headers)
   - GET /api/images/myImages
     - Get a list of images uploaded by the authenticated user
   - DELETE /api/images/delete/{id}
     - Delete an image by ID (only if owned by user)

  
[Demo Video] (https://shaik-suhel1211.github.io/project-demo-video/)

 ### How It Works
- **Register →** User signs up and receives a JWT
- **Login →** User logs in and JWT is stored in local storage
- **Dashboard →** Accessible after authentication
- **Upload / Delete Images →** Use JWT in request headers
- **Retrieve / Search Images →** Provide an Access Token in query params

## Developed By 
## SHAIK SUHEL AHMED
[[My LinkedIn Profile]](https://www.linkedin.com/in/shaiksuhelahmed03/)
<a href="https://www.linkedin.com/in/shaiksuhelahmed03/" target="_blank">
 <img src="https://img.shields.io/badge/LinkedIn-Connect-blue?logo=linkedin&style=flat-square" alt="LinkedIn Badge" />
 </a>
