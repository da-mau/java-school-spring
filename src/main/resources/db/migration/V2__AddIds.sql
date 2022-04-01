ALTER TABLE contact_information ADD contact_information_id int4 NOT NULL  GENERATED ALWAYS AS IDENTITY;
ALTER TABLE contact_information ADD CONSTRAINT contact_information_pk PRIMARY KEY (contact_information_id);
ALTER TABLE "position" ADD position_id int4 NOT NULL  GENERATED ALWAYS AS IDENTITY;
ALTER TABLE "position" ADD CONSTRAINT position_pk PRIMARY KEY (position_id);
