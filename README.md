# VirusTag-1.15.2
VirusTag is a team tag minigame that uses the social distance mechanic introduced in SocialDistance.

**Rules** <br/>
The hunter team has a limited amount of time to eliminate the runner team within the boundaries of the world border by maintaining a certain danger distance between them and the runners to bring the runners' heath to zero. Each hunter has a compass that points to the nearest runner with a set frequency. The game ends when every player in either team is eliminated, or when the time limit expires.

**Commands**
* vtstart: starts a game
* vtend: stops the current game
* vtstatus: checks the status of the plugin
* vtsetradius <radius>: sets the danger radius in range [1,127]
* vtsetduration <seconds>: sets the infection duration in range [1,1200]
* vtsetamplifier <amplifier>: sets infection amplifier in ranger [0,5]
* vtsetborder <length> sets world border length
* vtsetgrace <seconds>: sets the grace period for runner invulnerability
* vtsettimelimit <seconds>: sets the time limit for a game
* vtsetcompassdelay <seconds>: sets compass tracking delay
* vttogglemobs: toggles infection of mobs
