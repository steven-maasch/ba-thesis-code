# Erstellung der View released_beacon

CREATE VIEW p5809_5648.released_beacon 
AS SELECT b.fs_id, b.beacon_id, b.uuid, b.major, b.minor 
FROM p5809_5648.beacon b 
WHERE b.fs_release_to = 9223372036854775807;

# Erstellung der View released_facility

CREATE VIEW p2761_2742.released_facility 
AS SELECT facility.fs_id, facility.firm_name, facility.firm_name2, 
facility.street, facility.postal_code, facility.city 
FROM p2761_2742.facility
WHERE facility.fs_release_to = 9223372036854775807;

# Erstellung der View released_beacon_position

CREATE VIEW p5809_5648.released_beacon_position 
AS SELECT p.fs_id, p.name ,p.beac_fs_id3, p.faci_fs_id1 
FROM p5809_5648.beacon_position p 
WHERE p.fs_release_to = 9223372036854775807;

# Erstellung der View released_content

CREATE VIEW p2761_2742.released_content 
AS SELECT content.fs_id, content.page_reference, 
content.app_message, content.show_after_visits 
FROM p2761_2742.content 
WHERE content.fs_release_to = 9223372036854775807;

# Erstellung der View released_keywords

CREATE VIEW p5809_5648.released_keyword 
AS SELECT kw.fs_id, kw.name 
FROM p5809_5648.keyword kw 
WHERE kw.fs_release_to = 9223372036854775807;

# Erstellung der View released_rt_beacon_position_content_beacon_positionlist

CREATE VIEW p2761_2742.released_rt_beacon_position_content_beacon_positionlist 
AS SELECT b.beac_fs_id, b.cont_fs_id 
FROM p2761_2742.rt_beacon_position_content_beacon_positionlist b 
WHERE b.fs_release_to = 9223372036854775807;

# Erstellung der View released_rt_content_keyword_keywordlist

CREATE VIEW p2761_2742.released_rt_content_keyword_keywordlist 
AS SELECT rt.cont_fs_id, rt.keyw_fs_id 
FROM p2761_2742.rt_content_keyword_keywordlist rt 
WHERE rt.fs_release_to = 9223372036854775807;

# Erstellung der View released_config

CREATE VIEW p2761_2742.released_config
AS SELECT conf.property, conf.value_ 
FROM p5809_5648.config conf 
WHERE conf.fs_release_to = 9223372036854775807;

# ------------------------------------------------------
# ------------ Abfragen der Schnittstelle --------------
# ------------------------------------------------------

# Abfrage einer BeaconPosition über UUID, Major und Minor

SELECT bp.fs_id 
FROM p5809_5648.released_beacon_position bp
WHERE bp.beac_fs_id3 = (
SELECT b.fs_id FROM p5809_5648.released_beacon b 
WHERE b.uuid = 'f7826da6-4fa2-4e98-8024-bc5b71e0893e' 
AND b.major = 9530 
AND b.minor = 26824
);

# Abfrage von Content über id

SELECT c.fs_id, c.relative_url, c.last_modification, c.show_after_visits 
FROM p5809_5648.released_content c 
JOIN p5809_5648.released_rt_beacon_position_content_beacon_positionlist bpl 
ON c.fs_id = bpl.cont_fs_id
WHERE bpl.beac_fs_id = 257;


# JOIN-Abfrage der Tabellen released_content und released_keyword

SELECT kw.name, kwl.cont_fs_id FROM p5809_5648.released_keyword 
kw JOIN p5809_5648.released_rt_content_keyword_keywordlist kwl 
ON kw.fs_id = kwl.keyw_fs_id;