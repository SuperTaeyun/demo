FROM node:20-alpine AS builder
WORKDIR /app

# 의존선 파일 복사
COPY package.json package-lock.json ./
RUN npm ci

# 전체 소스 코드 복사 및 정적 파일로 빌드(dist 폴더 생성)
COPY . .

RUN npm run build

# 실행 스테이지
FROM nginx:alpine

# nginx.conf 적용
COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]