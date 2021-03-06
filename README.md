CHAI Location Plugin
===

This plugin is used in [Clinton Health Access Initiative][CHAI] Grails projects.

It provides a location structure for the country, and a sync mechanism that integrates with Rwanda Ministry of Health facility registry as part of the [Rwanda Health Enterprise Architecture][RHEA] project.

Structure
---

We distinguish **locations** and **data locations**. **Locations** are structural entities, for example the regions of a country (provinces, districts or states). **Locations** are all associated to a certain **location level**. A **location level** could be for example the country, province or district. 

**Data locations** are entities where data is collected, they could be, if we take the example of health sector data, hospitals, health centers or pharmacies, or in the education sector, they would be schools. **Data locations** are associated to a **data location type**, which could be, in the health sector example, a district hospital or a pharmacy.

For this documentation, we will take the example of health sector data in Rwanda, so the data locations will be health centers, hospitals, pharmacies, etc… and the locations will be provinces and districts (as given by Rwanda's administrative structure). Let's for example take the structure below :

	Location Level		Location / Data Location (Data Location Type)
	-------------		---------------------------------------------
	country						   Rwanda
								   /    \
	province					North    South
								/		   \\
	district				Burera		   Butare DH (hospital)
					 	   //	 \\
						 //		Kivuye HC (health center)
					Butaro DH (hospital)
	
In this diagram, single links (with ```/```) denote a structural relationship between locations, and double links (with ```//```) denote a link between a data location and its corresponding location.

Configuration
---

The endpoint used to sync the locations can be configured using the following configuration options. Find below the endpoints and the corresponding URL in the staging server.

	// URL of the activity feed, has to display all the pages
	sync.activity.feed.url = "http://resmap-stg.instedd.org/api/activity.rss?collection_ids[]=694&page=all"
	
	// URL of the paged full list, does not have to display all the pages since the service goes through the pages
	sync.full.list.url = "http://resmap-stg.instedd.org/api/collections/694.json"
	
	// URL of individual site information, where ${itemid} will be replaced by the actual site id
	sync.site.url = "http://resmap-stg.instedd.org/api/sites/${itemid}.json"
	
	// username and password of a user who has access to the URLs above
	sync.site.username = "username	"
	sync.site.password = "password"

Apart from that, the mapping "facility registry type code" to "Data Location code" must be specified, as well as ignored types.

	// this list of types will be ignored by the sync
	sync.type.ignore = []
	
	// this specifies the mapping
	sync.type.mapping = [
		"CS": "Health Center",
		"DH": "District Hospital"
	]
	
If using activity feed sync with a specific date from which to sync, the date format of the activity feed should be specified here.
	
	// the format of the date in the activity feed - defaults to value below
	sync.date.format = "EEE, d MMM yyyy HH:mm:ss Z"
	

License
---

The DHSST is licensed under the terms of the [BSD 3-clause License][BSD 3-clause License].

[BSD 3-clause License]: http://www.w3.org/Consortium/Legal/2008/03-bsd-license.html
[RHEA]: https://jembiprojects.jira.com/wiki/display/RHEAPILOT/Home
[CHAI]: http://www.clintonhealthaccess.org