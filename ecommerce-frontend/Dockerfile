# Use an official Node.js runtime as a parent image
FROM node:16 AS build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json for npm install
COPY package*.json ./

# Install the app dependencies
RUN npm install

# Copy the rest of your application
COPY . .

# Build the React app for production
RUN npm run build

# Serve the React app with Nginx
FROM nginx:alpine

# Copy the built React app to the Nginx folder
COPY --from=build /app/build /usr/share/nginx/html

# Expose the Nginx port
EXPOSE 3000

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
