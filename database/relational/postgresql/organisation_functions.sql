-- organisation functions --

--------------------
------- CRUD -------
--------------------

-- create
create or replace function create_organisation(_organisation_name text)
returns integer as $func$
declare _organisation_id integer;

begin
insert into organisation(name) values(_organisation_name)
returning id into _organisation_id;
return _organisation_id;
end
$func$ language plpgsql;


-- read
create or replace function read_organisation(_organisation_name text)
returns table(id integer, name text) as
$$
    select id, name
    from organisation
    where name = _organisation_name;
$$ language sql;

-- update
create or replace function update_organisation(_organisation_id integer, _organisation_name text)
returns table(id integer, name text) as
$$
    update organisation
    set name = _organisation_name
    returning id, name;
$$ language sql;

-- delete
create or replace function delete_organisation(_organisation_id integer)
-- returns table (id integer, name text) will not work, needs to be setof
returns setof organisation as
$$
    delete from organisation
    where id = _organisation_id
    returning *;
$$ language sql;


--------------------
-- OTHER FUNCTIONS -
--------------------


