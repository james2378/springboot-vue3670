git init
ssh -t dokkuser@changsheng.local dokku --force apps:destroy prewviewurl
git add .
git commit -m "project preview"
git remote add dokku dokku@changsheng.local:prewviewurl
ssh -t dokkuser@changsheng.local dokku apps:create prewviewurl
git push dokku master