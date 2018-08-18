-- user functions --

--------------------
------- CRUD -------
--------------------

-- create
create or replace function create_user(
_user_name text,
_organisation_name text)
returns void as $$

declare _user_id integer;
declare _organisation_id integer;

begin
-- create the user
insert into "user"(name) values(_user_name)
returning id into _user_id;

select id into _organisation_id from organisation where name = _organisation_name;

-- make a relation between user and organisation
insert into user_organisation(organisation_id, user_id) values(_organisation_id, _user_id);

end
$$ language plpgsql;

-- read
create or replace function read_user_by_name(_name text)
returns table(id integer, name text) as
$$
  select id, name
  from "user"
  where name = _name;
$$ language sql;

create or replace function read_user_by_id(_id integer)
returns table(id integer, name text) as
$$
  select id, name
  from "user"
  where id = _id;
$$ language sql;

-- DOESN'T WORK PROPERLY
create or replace function read_user_from_organisation_by_name(_organisation_name text, _user_name text)
returns table (id integer, name text) as
$$
    select u.id, u.name
    from "user" u
    inner join user_organisation on user_id = u.id
    where u.name = _user_name
    and organisation_id = (select id from organisation where name = _organisation_name);
$$ language sql;

-- update
create or replace function update_user(_id integer, _name text)
returns table(if integer, name text) as
$$
update "user"
set name = _name
returning id, name;
$$ language sql;

-- delete
create or replace function delete_user_by_id(_id integer)
returns setof "user" as
$$
  delete from "user"
  where id = _id
  returning *;
$$ language sql;

-- delete
create or replace function delete_user_by_name(_name text)
returns setof "user" as
$$
  delete from "user"
  where name = _name
  returning *;
$$ language sql;


-- delete user from organisation
create or replace function delete_user_from_organisation(_user_id integer, _organisation_id integer)
returns void as $$
delete from user_organisation
where user_id = _user_id and organisation_id = _organisation_id;
$$ language sql;

