wsdl http://localhost:8080/WS/WSEmetteurService?wsdl
mcs Client.cs WSEmetteurService.cs -r:System.Web.Services
./Client.exe
sleep 3
subl ~/domain1/logs/server.log
