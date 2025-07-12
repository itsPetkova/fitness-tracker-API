# 🏋️‍♂️ Fitness Tracker API

A simple RESTful API for managing user fitness profiles and tracking fitness entries. Built as a project during a course on [Hyperskill](https://hyperskill.org), this API is designed for developers to register apps, obtain API keys, and interact with fitness data securely.

---

## 🚀 Features

- User registration with email and password
- App registration for developers
- API key issuance per app
- Authenticated endpoints using API keys
- Fitness entry creation and retrieval

---

## 📋 API Overview

### 🔑 Authentication Flow

1. **User Registration**
   - `POST /api/developers/signup` – create a user with email and password

2. **App Registration**
   - `POST /api/applications/register` – register a new fitness app for a user  
   - Response includes: `apiKey`

3. **Posting Fitness Entries**
   - `POST /api/tracker`
   - Requires: `x-api-key` header

4. **Retrieving Fitness Entries**
   - `GET /api/tracker`
   - Requires: `x-api-key` header

---
