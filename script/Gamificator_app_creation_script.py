import requests
from hashlib import sha1
import hmac
import base64

urlMain = 'http://localhost:8080'

#Signature fonction
def addSignature(url, app_key, app_secret):
	s = (app_key + url).encode("UTF-8")
	h = hmac.new(app_secret.encode("UTF-8"), s, sha1)
	return base64.b64encode(h.hexdigest().encode("UTF-8")).decode("UTF-8")


#Create a PointScale
def creatPointScale(description, name, app_key, app_secret):
	url = urlMain + '/pointScales'
	obj = {
	  "description":description ,
	  "name":name
	}
	signature = addSignature(url, app_key, app_secret)
	return (requests.post(url, headers = {'signature':signature, 'x-api-key': app_key}, json = obj)).json()


#Create a Badge
def creatBadge(imageUrl, name, app_key, app_secret):
	url = urlMain + '/badges'
	obj = {
	  "imageUrl": imageUrl,
	  "name": name
	}
	signature = addSignature(url, app_key, app_secret)
	return (requests.post(url, headers = {'signature':signature, 'x-api-key': app_key}, json = obj)).json()


#Create a Rule
def creatRule(condition, badge, pointScaleName, points, app_key, app_secret):
	url = urlMain + '/rules'
	obj = {
	  "condition": {
		"type": condition
	  },
	  "then": {
		"awardBadges": [badge],
		"awardPoints": [
		  {
			"pointScaleName": pointScaleName,
			"value": points
		  }
		]
	  }
	}
	signature = addSignature(url, app_key, app_secret)
	return (requests.post(url, headers = {'signature':signature, 'x-api-key': app_key}, json = obj))


#######################
#Create the application
url = urlMain + '/applications'
obj = {
  "name": "StarkOverflow",
  "url": "http://localhost:9080/"
}

response = (requests.post(url, json = obj)).json()

app_id = str(response['id'])
app_key = response['key']
app_secret = response['secret']



##########Generation##########
#Point Scale : learning
creatPointScale("Reward you for learning somthing", "learning", app_key, app_secret)
#Point Scale : commentator
creatPointScale("Reward you for commenting", "commentator", app_key, app_secret)
#Point Scale : cookies
creatPointScale("Reward you for voting", "cookies", app_key, app_secret)

#Badge : liechti
creatBadge("https://avatars2.githubusercontent.com/u/1152779?s=460&v=4", "liechti", app_key, app_secret)
#Badge : cookie
creatBadge("https://i-reg.unimedias.fr/sites/art-de-vivre/files/styles/recipe/public/cookie-chocolat-gaetan-fiard_dr.jpg?auto=compress%2Cformat&crop=faces%2Cedges&cs=srgb&fit=crop&h=500&w=393", "cookie", app_key, app_secret)
#Badge : heart
creatBadge("https://previews.123rf.com/images/azzardo/azzardo1801/azzardo180100008/93515266-key-with-heart-design.jpg", "heart", app_key, app_secret)

#Rule : createaquestion
creatRule("create a question", "", "learning", 2, app_key, app_secret)
#Rule : commentaquestion
creatRule("comment a question", "", "commentator", 1, app_key, app_secret)
#Rule : validanswer
creatRule("get approval for one of your answer", "liechti", "", 0, app_key, app_secret)
#Rule : cookies
creatRule("vote for an answer", "cookie", "cookies", 1, app_key, app_secret)
#Rule : findthekey
creatRule("but where is the key ?", "heart", "", 0, app_key, app_secret)



#Update docker-compose
f = open("../docker/environment/prod/unmodified/docker-compose.yml", "r")
contents = f.readlines()
f.close()

contents.insert(32, '      APP_ID: '+app_id+'\n')
contents.insert(32, '      APP_KEY: '+app_key+'\n')
contents.insert(32, '      APP_SECRET: '+app_secret+'\n')

f = open("../docker/environment/prod/docker-compose.yml", "w")
contents = "".join(contents)
f.write(contents)
f.close()