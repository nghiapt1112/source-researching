#cd ../
#zip -r example.zip ../source-code
#mv example.zip ./iac
####
####next time need to add a source code file to example.zip
###
#zip example.zip ../source-code/index.js
#aws s3 cp ./example.zip s3://nathan-tf-nodejs-express-gw1/v1.0.5/example.zip
cd ./iac
terraform apply -var="app_version=1.0.6" --auto-approve
#terraform apply --auto-approve