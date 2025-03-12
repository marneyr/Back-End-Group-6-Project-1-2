--
-- PostgreSQL database
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-03-11 20:40:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Check for the postgres user - should already exist by default
DO $$
BEGIN
  IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'postgres') THEN
    RAISE NOTICE 'postgres user not found - this is unusual. Creating it...';
    CREATE USER postgres WITH SUPERUSER PASSWORD 'database1';
  ELSE
    -- Update password for postgres if you want to ensure consistent credentials
    ALTER USER postgres WITH PASSWORD 'database1';
  END IF;
END
$$;

DROP DATABASE IF EXISTS "Group6";
--
-- TOC entry 4910 (class 1262 OID 16389)
-- Name: Group6; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Group6" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en-US';


ALTER DATABASE "Group6" OWNER TO postgres;

\connect "Group6"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16402)
-- Name: recipes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recipes (
    recipe_id integer NOT NULL,
    user_id integer NOT NULL,
    name character varying(255) NOT NULL,
    ingredients text,
    instructions text,
    cooking_time character varying(50),
    difficulty character varying(20),
    servings integer
);


ALTER TABLE public.recipes OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16401)
-- Name: recipes_recipe_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recipes_recipe_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recipes_recipe_id_seq OWNER TO postgres;

--
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 219
-- Name: recipes_recipe_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recipes_recipe_id_seq OWNED BY public.recipes.recipe_id;


--
-- TOC entry 218 (class 1259 OID 16391)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16390)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 4748 (class 2604 OID 16405)
-- Name: recipes recipe_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes ALTER COLUMN recipe_id SET DEFAULT nextval('public.recipes_recipe_id_seq'::regclass);


--
-- TOC entry 4747 (class 2604 OID 16394)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 4904 (class 0 OID 16402)
-- Dependencies: 220
-- Data for Name: recipes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recipes (recipe_id, user_id, name, ingredients, instructions, cooking_time, difficulty, servings) FROM stdin;
1	1	Boeuf Bourguignon Magnifique	["3 pounds beef for stewing, cut into 2-inch cubes", "1/4 pound bacon, diced", "1 large carrot, sliced", "1 large onion, sliced", "2 tablespoons flour", "3 cups full-bodied red wine", "2 to 3 cups beef stock", "1 tablespoon tomato paste", "2 cloves mashed garlic", "1/2 teaspoon thyme", "1 bay leaf", "18 to 24 small white onions", "1 pound fresh mushrooms, quartered", "3 tablespoons butter", "Salt and pepper to taste"]	["First, dry your beef thoroughly with paper towels. Remember, my dears, meat won't brown if it's damp!", "In a large, heavy Dutch oven, sauté the bacon until crisp and brown. Remove and set aside.", "In the same fat, brown the beef on all sides in batches. Don't crowd the pan — this isn't a time for togetherness!", "Add the sliced vegetables to the pan and brown lightly.", "Return the beef to the pot, sprinkle with flour, and toss to coat.", "Place uncovered in the middle position of a preheated 450°F oven for 4 minutes. Toss the meat again and return to oven for 4 more minutes.", "Reduce oven to 325°F. Add wine, stock, tomato paste, garlic and herbs. Bring to a simmer on the stovetop.", "Cover and place in oven for 3 hours, or until meat is tender when pierced with a fork.", "While the beef is cooking, prepare the onions and mushrooms. Sauté them separately in butter until browned.", "When the meat is done, add the bacon, onions, and mushrooms. Simmer for 2 to 3 more minutes.", "Correct the seasoning with salt and pepper, and serve with potatoes, noodles, or crusty French bread. Voilà — bon appétit!"]	3 hours 30 minutes	Medium	6
2	1	Coq au Vin Classique	["1 roasting chicken (about 4-5 pounds), cut into serving pieces", "1/4 cup cognac", "3 cups good red wine", "2 cups brown chicken stock", "1 tablespoon tomato paste", "1/4 teaspoon thyme", "1 bay leaf", "20 pearl onions", "1/2 pound mushrooms, quartered", "1/4 pound lardons (salt pork cut into matchsticks)", "3 tablespoons flour", "2 tablespoons butter", "3 tablespoons fresh parsley, chopped", "Salt and pepper to taste"]	["Dry the chicken pieces thoroughly with paper towels. Season generously with salt and pepper.", "In a heavy, flameproof casserole, sauté the lardons until crisp and brown. Remove with a slotted spoon.", "Brown the chicken pieces in the hot fat, turning to achieve a rich, golden color on all sides. This is the key to flavor, my dears!", "Flambé with cognac! Stand back, and do have courage!", "Add the wine, stock, tomato paste, and herbs. Bring to a simmer.", "Cover and simmer slowly for about 30 minutes, until the chicken is tender when pierced with a fork.", "While the chicken cooks, prepare the onions and mushrooms. Sauté them separately in butter until nicely browned.", "When the chicken is done, remove it to a side dish and keep warm.", "Skim excess fat from the cooking liquid. Blend the butter and flour together into a paste (beurre manié) and whisk into the liquid. Cook for 1-2 minutes until thickened.", "Add the chicken, lardons, onions, and mushrooms back to the sauce. Simmer gently for 5 minutes.", "Garnish with chopped parsley and serve with buttered parsley potatoes. Remember, if you're afraid of butter, use cream!"]	1 hour 15 minutes	Intermediate	4
3	1	Ratatouille Niçoise	["1 pound eggplant, cut into 3/4-inch cubes", "1 pound zucchini, cut into 3/4-inch cubes", "1 large onion, sliced", "2 bell peppers (red and green), cut into strips", "3 ripe tomatoes, peeled and chopped", "3 cloves garlic, minced", "1/4 cup olive oil", "3 tablespoons fresh parsley, chopped", "1 bay leaf", "1 teaspoon thyme", "Salt and pepper to taste"]	["Begin by salting the eggplant cubes and letting them drain in a colander for 30 minutes. This removes any bitterness. Pat dry thoroughly.", "In a large skillet, heat the olive oil until quite hot. Sauté the eggplant until lightly browned. Remove to a side dish.", "In the same skillet, sauté the zucchini until lightly browned. Remove to the side dish with the eggplant.", "Cook the onions and peppers until softened, about 10 minutes.", "Add the garlic and cook for 1 minute more. Be careful not to burn it!", "Add the tomatoes, bay leaf, and thyme. Cook slowly for about 5 minutes, until the tomatoes have begun to render their juice.", "Now return the eggplant and zucchini to the skillet. Cover and simmer over low heat for about 10 minutes, or until the vegetables are tender but still retain their shape.", "Taste carefully for seasoning, adding salt and pepper as needed.", "Remove the bay leaf, sprinkle with chopped parsley, and serve hot, cold, or at room temperature. This improves with age, so make it ahead if you can!"]	45 minutes	Easy	6
4	1	Sole Meunière à la Julia	["4 sole fillets (about 6-8 ounces each)", "1/2 cup flour for dredging", "4 tablespoons clarified butter", "3 tablespoons fresh lemon juice", "4 tablespoons unsalted butter", "3 tablespoons fresh parsley, minced", "Lemon wedges", "Salt and white pepper to taste"]	["Pat the sole fillets dry with paper towels. This is essential for a proper browning!", "Season the fillets on both sides with salt and white pepper.", "Just before cooking, dredge the fillets in the flour, shaking off excess. The coating should be very light!", "In a large, heavy skillet, heat the clarified butter until it is very hot but not smoking.", "Add the fillets and cook for about 2 minutes on the first side, until lightly browned.", "Turn carefully with a spatula and cook for 1-2 minutes on the second side.", "Transfer the fish to a warm platter and keep warm.", "Wipe out the skillet, add the fresh butter, and heat until it begins to brown and gives off a nutty aroma.", "Add the lemon juice and parsley, swirling to combine.", "Pour this delicious sauce over the fish, garnish with lemon wedges, and serve immediately. The dish waits for no one!", "Accompany with boiled, parslied potatoes and a crisp white wine. Bon appétit!"]	15 minutes	Intermediate	4
5	1	Chocolate Soufflé Divinely Risen	["4 ounces semi-sweet chocolate", "3 tablespoons butter", "1/3 cup granulated sugar, plus more for coating dish", "3 egg yolks", "5 egg whites", "1/4 teaspoon cream of tartar", "1 tablespoon confectioners' sugar", "1/4 cup strong coffee (optional)", "1 tablespoon vanilla extract", "Pinch of salt"]	["Preheat your oven to 425°F. Position the rack in the lower third of the oven.", "Butter a 6-cup soufflé dish thoroughly and coat with granulated sugar, tapping out the excess.", "Melt the chocolate and butter together in a double boiler over simmering water. Stir in the coffee (if using) and vanilla. Let cool slightly.", "Beat the egg yolks with 2 tablespoons of the sugar until pale and thick. This should take about 3 minutes with an electric mixer.", "Fold the chocolate mixture into the egg yolks.", "In a separate bowl, beat the egg whites with salt and cream of tartar until soft peaks form.", "Gradually add the remaining sugar and beat until stiff, glossy peaks form. Don't overbeat, my dears!", "Stir one-quarter of the egg whites into the chocolate mixture to lighten it.", "Gently fold in the remaining egg whites, maintaining as much volume as possible. This is the secret to a good soufflé!", "Pour the mixture into the prepared soufflé dish. The mixture should come about three-quarters of the way up the sides. Run your thumb around the inside edge to create a small channel - this helps the soufflé rise straight up!", "Bake immediately for 20-25 minutes, until puffed and set but still slightly jiggly in the center.", "Dust with confectioners' sugar and serve at once! A soufflé waits for no one, and no one should wait for a soufflé!", "Remember, the soufflé will begin to fall within minutes of coming out of the oven, but it will still taste marvelous!"]	40 minutes	Challenging	4
\.


--
-- TOC entry 4902 (class 0 OID 16391)
-- Dependencies: 218
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, username, password, email) FROM stdin;
1	admin	$2a$10$Cvu4658elxEFvWUds1IbuOWNIS9ErhvuaEIXdcSjufShRM8WqZ3Zu	admin@example.com
2	chef1	$2a$10$Se9j37KkgFvNmbOeHJsfs.s8.4.MmenecgwpjpxsuMmDbKxhzvXXC	chef@example.com
\.


--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 219
-- Name: recipes_recipe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recipes_recipe_id_seq', 5, true);


--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 2, true);


--
-- TOC entry 4754 (class 2606 OID 16407)
-- Name: recipes recipes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes
    ADD CONSTRAINT recipes_pkey PRIMARY KEY (recipe_id);


--
-- TOC entry 4750 (class 2606 OID 16398)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4752 (class 2606 OID 16400)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 4755 (class 2606 OID 16408)
-- Name: recipes recipes_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes
    ADD CONSTRAINT recipes_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);

-- Output credentials reminder
\echo '========================================================'
\echo 'Database setup complete!'
\echo 'Database: Group6'
\echo 'Username: postgres'
\echo 'Password: database1'
\echo 'Default admin user:'
\echo '  Username: admin'
\echo '  Password: admin123'
\echo '========================================================'

-- Completed on 2025-03-11 20:40:48

--
-- PostgreSQL database dump complete
--

