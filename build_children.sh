body='{
"request": {
  "branch":"master"
}}'

curl -s -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -H "Travis-API-Version: 3" \
  -H "Authorization: token Iit6_JxttCJ5JfEGQUyjRQ" \
  -d "$body" \
  https://api.travis-ci.org/Pearson-Higher-Ed/travis-test/travis-ci%2Ftravis-core/requests
