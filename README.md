# Project for keycloak 22 integration with Java 21
##

Keycloak server configuration used in this project is described on my blog:
https://damiankumor.blogspot.com/2023/07/keycloak-dev-in-4-minutes-windows.html


## Unsecured endpoint can be requested with this <i>curl</i> command:
```bash
curl http://localhost:8081/example/unsecured
```

## Secured endpoint can be requested with this <i>curl</i> command:
```bash
curl http://localhost:8081/example/secured -H "Authorization:Bearer YOUR_TOKEN"
```
where token can be obtained by keycloak login request, i.e.:
```bash
curl -X POST http://localhost:8080/realms/custom-realm/protocol/openid-connect/token \
  -H 'Content-Type:application/x-www-form-urlencoded' \
  --data "client_id=custom-client&username=user&password=user&grant_type=password" 
```

With access token request for secured endpoint looks like this:
```bash
curl http://localhost:8081/example/secured \
  -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ4b1BmNWlPZloxbGVPcUdkeGNQQ0VBWE1mdERCSk80bnlaMzNGTXhHcUo0In0.eyJleHAiOjE2ODcxNzU4NTcsImlhdCI6MTY4NzE3NTU1NywianRpIjoiN2YzYzA0ZDEtNTQ4ZS00OTYwLThlYmMtNjMyMWQwOGFjNjE1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9jdXN0b20tcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiYjQ4Mjg4NjktNzljMC00MmQzLWJiMjYtMTFkYTU1NDMzNWJkIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY3VzdG9tLWNsaWVudCIsInNlc3Npb25fc3RhdGUiOiI2MzZlMWI3OS1lN2M4LTQ1NzQtYWY0YS04NjIyNWEzYzIyMjUiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1jdXN0b20tcmVhbG0iLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiNjM2ZTFiNzktZTdjOC00NTc0LWFmNGEtODYyMjVhM2MyMjI1IiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIiLCJnaXZlbl9uYW1lIjoiIiwiZmFtaWx5X25hbWUiOiIifQ.KvVG-NjyyMbLtitE_Jfz2zQVd4UZyjiKQeoI8sk6BZAJXsXVs7ZiVjPiAZHeK1a28xNf6R0T-3UY-S_hH2QC8NO4layVLkreK4fCPfO2GtFlHye4QL0LdGPx_Wh4osVyab8v5Ut-cMnHIilx2a_eHZWtHWL0QFdcus_Y0HOMr1goucocfZFGMmmoDkB-o6ozlLi2AQbOh-UsagD2aD2ZxAPRCz66XwSfuQcvnETGcRU2RGc3p1alvoVayOJlpfST8zAwHPKXoQh35LA92QzgMrczcnhBys7yvECHy3Jf_qsB_323IYUuwnUzb0R4Cclpnp6EKBWdk9gy769ezuv87A"
```

## Token analysis
If you have some troubles and need to analyze your token properties like expiration time etc. this site can be very helpful:<br>
https://jwt.io/