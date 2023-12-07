#cd ../
#zip -r example.zip ../source-code
#mv example.zip ./iac
####
####next time need to add a source code file to example.zip
###
#zip example.zip ../source-code/index.js
#aws s3 cp ./example.zip s3://nathan-tf-nodejs-express-gw1/v1.0.5/example.zip
cd /Users/ptnghia1/data/sources/nghia/source-researching/tf-beginner/service-3-nodejs-express/iac
terraform apply -var="app_version=1.0.7" --auto-approve
#terraform apply --auto-approve
curl "$(terraform output -raw base_url)/service2"
curl "$(terraform output -raw base_url)/service3"
