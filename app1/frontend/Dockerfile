FROM node:lts AS development

WORKDIR /app1/frontend
COPY package.json /app1/frontend/package.json
COPY package-lock.json /app1/frontend/package-lock.json

RUN npm ci
COPY ./public /app1/frontend/public
COPY ./src /app1/frontend/src


ENV CI=true
ENV PORT=8001

CMD [ "npm", "start" ]