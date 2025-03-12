#!/bin/bash
nginx -c /opt/render/project/src/frontend/nginx.conf -g 'daemon off;'
