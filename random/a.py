"""
man   = 0.55
woman = 0.45

age_18_25 = 0.15
age_26_35 = 0.25
age_36_45 = 0.20
age_46_55 = 0.25
age_56___ = 0.15

edu_high = 0.20
edu_mid  = 0.50
edu_low  = 0.30

status_married  = 0.50
status_divorsed = 0.15
status_bachlor  = 0.30
status_partner  = 0.05

house 	= 0.25
rent    = 0.35
parents = 0.25
credit  = 0.15
"""

n = 1370
gender  = [0.55, 0.45]
age	    = [0.15, 0.25, 0.20, 0.25, 0.15]
edu	    = [0.20, 0.50, 0.30]
status  = [0.50, 0.15, 0.30, 0.05]
home    = [0.25, 0.35, 0.25, 0.15]

for i in range(0, len(gender)):
    for j in range(0, len(age)):
        for u in range(0, len(edu)):
            for z in range(0, len(status)):
                for x in range(0, len(home)):
                    print(round(n * gender[i] * age[j] * edu[u] * status[z] * home[x], 8))
