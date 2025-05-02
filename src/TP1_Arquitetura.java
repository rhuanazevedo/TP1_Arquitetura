public class TP1_Arquitetura {

    public static void main(String[] args) {

        //é necessário modificar as probabilidades e avaliar o desempenho
        Canal canal = new Canal(0.8);

        Transmissor transm = new Transmissor("Call me Ishmael. Some years ago—never mind how long precisely—having\n" +
                "little or no money in my purse, and nothing particular to interest me\n" +
                "on shore, I thought I would sail about a little and see the watery part\n" +
                "of the world. It is a way I have of driving off the spleen and\n" +
                "regulating the circulation. Whenever I find myself growing grim about\n" +
                "the mouth; whenever it is a damp, drizzly November in my soul; whenever\n" +
                "I find myself involuntarily pausing before coffin warehouses, and\n" +
                "bringing up the rear of every funeral I meet; and especially whenever\n" +
                "my hypos get such an upper hand of me, that it requires a strong moral\n" +
                "principle to prevent me from deliberately stepping into the street, and\n" +
                "methodically knocking people’s hats off—then, I account it high time to\n" +
                "get to sea as soon as I can. This is my substitute for pistol and ball.\n" +
                "With a philosophical flourish Cato throws himself upon his sword; I\n" +
                "quietly take to the ship. There is nothing surprising in this. If they\n" +
                "but knew it, almost all men in their degree, some time or other,\n" +
                "cherish very nearly the same feelings towards the ocean with me.\n" +
                "\n" +
                "There now is your insular city of the Manhattoes, belted round by\n" +
                "wharves as Indian isles by coral reefs—commerce surrounds it with her\n" +
                "surf. Right and left, the streets take you waterward. Its extreme\n" +
                "downtown is the battery, where that noble mole is washed by waves, and\n" +
                "cooled by breezes, which a few hours previous were out of sight of\n" +
                "land. Look at the crowds of water-gazers there.\n" +
                "\n" +
                "Circumambulate the city of a dreamy Sabbath afternoon. Go from Corlears\n" +
                "Hook to Coenties Slip, and from thence, by Whitehall, northward. What\n" +
                "do you see?—Posted like silent sentinels all around the town, stand\n" +
                "thousands upon thousands of mortal men fixed in ocean reveries. Some\n" +
                "leaning against the spiles; some seated upon the pier-heads; some\n" +
                "looking over the bulwarks of ships from China; some high aloft in the\n" +
                "rigging, as if striving to get a still better seaward peep. But these\n" +
                "are all landsmen; of week days pent up in lath and plaster—tied to\n" +
                "counters, nailed to benches, clinched to desks. How then is this? Are\n" +
                "the green fields gone? What do they here?\n" +
                "\n" +
                "But look! here come more crowds, pacing straight for the water, and\n" +
                "seemingly bound for a dive. Strange! Nothing will content them but the\n" +
                "extremest limit of the land; loitering under the shady lee of yonder\n" +
                "warehouses will not suffice. No. They must get just as nigh the water\n" +
                "as they possibly can without falling in. And there they stand—miles of\n" +
                "them—leagues. Inlanders all, they come from lanes and alleys, streets\n" +
                "and avenues—north, east, south, and west. Yet here they all unite. Tell\n" +
                "me, does the magnetic virtue of the needles of the compasses of all\n" +
                "those ships attract them thither?\n" +
                "\n" +
                "Once more. Say you are in the country; in some high land of lakes. Take\n" +
                "almost any path you please, and ten to one it carries you down in a\n" +
                "dale, and leaves you there by a pool in the stream. There is magic in\n" +
                "it. Let the most absent-minded of men be plunged in his deepest\n" +
                "reveries—stand that man on his legs, set his feet a-going, and he will\n" +
                "infallibly lead you to water, if water there be in all that region.\n" +
                "Should you ever be athirst in the great American desert, try this\n" +
                "experiment, if your caravan happen to be supplied with a metaphysical\n" +
                "professor. Yes, as every one knows, meditation and water are wedded for\n" +
                "ever.\n" +
                "\n" +
                "But here is an artist. He desires to paint you the dreamiest, shadiest,\n" +
                "quietest, most enchanting bit of romantic landscape in all the valley\n" +
                "of the Saco. What is the chief element he employs? There stand his\n" +
                "trees, each with a hollow trunk, as if a hermit and a crucifix were\n" +
                "within; and here sleeps his meadow, and there sleep his cattle; and up\n" +
                "from yonder cottage goes a sleepy smoke. Deep into distant woodlands\n" +
                "winds a mazy way, reaching to overlapping spurs of mountains bathed in\n" +
                "their hill-side blue. But though the picture lies thus tranced, and\n" +
                "though this pine-tree shakes down its sighs like leaves upon this\n" +
                "shepherd’s head, yet all were vain, unless the shepherd’s eye were\n" +
                "fixed upon the magic stream before him. Go visit the Prairies in June,\n" +
                "when for scores on scores of miles you wade knee-deep among\n" +
                "Tiger-lilies—what is the one charm wanting?—Water—there is not a drop\n" +
                "of water there! Were Niagara but a cataract of sand, would you travel\n" +
                "your thousand miles to see it? Why did the poor poet of Tennessee, upon\n" +
                "suddenly receiving two handfuls of silver, deliberate whether to buy\n" +
                "him a coat, which he sadly needed, or invest his money in a pedestrian\n" +
                "trip to Rockaway Beach? Why is almost every robust healthy boy with a\n" +
                "robust healthy soul in him, at some time or other crazy to go to sea?\n" +
                "Why upon your first voyage as a passenger, did you yourself feel such a\n" +
                "mystical vibration, when first told that you and your ship were now out\n" +
                "of sight of land? Why did the old Persians hold the sea holy? Why did\n" +
                "the Greeks give it a separate deity, and own brother of Jove? Surely\n" +
                "all this is not without meaning. And still deeper the meaning of that\n" +
                "story of Narcissus, who because he could not grasp the tormenting, mild\n" +
                "image he saw in the fountain, plunged into it and was drowned. But that\n" +
                "same image, we ourselves see in all rivers and oceans. It is the image\n" +
                "of the ungraspable phantom of life; and this is the key to it all.\n" +
                "\n" +
                "Now, when I say that I am in the habit of going to sea whenever I begin\n" +
                "to grow hazy about the eyes, and begin to be over conscious of my\n" +
                "lungs, I do not mean to have it inferred that I ever go to sea as a\n" +
                "passenger. For to go as a passenger you must needs have a purse, and a\n" +
                "purse is but a rag unless you have something in it. Besides, passengers\n" +
                "get sea-sick—grow quarrelsome—don’t sleep of nights—do not enjoy\n" +
                "themselves much, as a general thing;—no, I never go as a passenger;\n" +
                "nor, though I am something of a salt, do I ever go to sea as a\n" +
                "Commodore, or a Captain, or a Cook. I abandon the glory and distinction\n" +
                "of such offices to those who like them. For my part, I abominate all\n" +
                "honorable respectable toils, trials, and tribulations of every kind\n" +
                "whatsoever. It is quite as much as I can do to take care of myself,\n" +
                "without taking care of ships, barques, brigs, schooners, and what not.\n" +
                "And as for going as cook,—though I confess there is considerable glory\n" +
                "in that, a cook being a sort of officer on ship-board—yet, somehow, I\n" +
                "never fancied broiling fowls;—though once broiled, judiciously\n" +
                "buttered, and judgmatically salted and peppered, there is no one who\n" +
                "will speak more respectfully, not to say reverentially, of a broiled\n" +
                "fowl than I will. It is out of the idolatrous dotings of the old\n" +
                "Egyptians upon broiled ibis and roasted river horse, that you see the\n" +
                "mummies of those creatures in their huge bake-houses the pyramids.\n" +
                "\n" +
                "No, when I go to sea, I go as a simple sailor, right before the mast,\n" +
                "plumb down into the forecastle, aloft there to the royal mast-head.\n" +
                "True, they rather order me about some, and make me jump from spar to\n" +
                "spar, like a grasshopper in a May meadow. And at first, this sort of\n" +
                "thing is unpleasant enough. It touches one’s sense of honor,\n" +
                "particularly if you come of an old established family in the land, the\n" +
                "Van Rensselaers, or Randolphs, or Hardicanutes. And more than all, if\n" +
                "just previous to putting your hand into the tar-pot, you have been\n" +
                "lording it as a country schoolmaster, making the tallest boys stand in\n" +
                "awe of you. The transition is a keen one, I assure you, from a\n" +
                "schoolmaster to a sailor, and requires a strong decoction of Seneca and\n" +
                "the Stoics to enable you to grin and bear it. But even this wears off\n" +
                "in time.\n" +
                "\n" +
                "What of it, if some old hunks of a sea-captain orders me to get a broom\n" +
                "and sweep down the decks? What does that indignity amount to, weighed,\n" +
                "I mean, in the scales of the New Testament? Do you think the archangel\n" +
                "Gabriel thinks anything the less of me, because I promptly and\n" +
                "respectfully obey that old hunks in that particular instance? Who ain’t\n" +
                "a slave? Tell me that. Well, then, however the old sea-captains may\n" +
                "order me about—however they may thump and punch me about, I have the\n" +
                "satisfaction of knowing that it is all right; that everybody else is\n" +
                "one way or other served in much the same way—either in a physical or\n" +
                "metaphysical point of view, that is; and so the universal thump is\n" +
                "passed round, and all hands should rub each other’s shoulder-blades,\n" +
                "and be content.\n" +
                "\n" +
                "Again, I always go to sea as a sailor, because they make a point of\n" +
                "paying me for my trouble, whereas they never pay passengers a single\n" +
                "penny that I ever heard of. On the contrary, passengers themselves must\n" +
                "pay. And there is all the difference in the world between paying and\n" +
                "being paid. The act of paying is perhaps the most uncomfortable\n" +
                "infliction that the two orchard thieves entailed upon us. But _being\n" +
                "paid_,—what will compare with it? The urbane activity with which a man\n" +
                "receives money is really marvellous, considering that we so earnestly\n" +
                "believe money to be the root of all earthly ills, and that on no\n" +
                "account can a monied man enter heaven. Ah! how cheerfully we consign\n" +
                "ourselves to perdition!\n" +
                "\n" +
                "Finally, I always go to sea as a sailor, because of the wholesome\n" +
                "exercise and pure air of the fore-castle deck. For as in this world,\n" +
                "head winds are far more prevalent than winds from astern (that is, if\n" +
                "you never violate the Pythagorean maxim), so for the most part the\n" +
                "Commodore on the quarter-deck gets his atmosphere at second hand from\n" +
                "the sailors on the forecastle. He thinks he breathes it first; but not\n" +
                "so. In much the same way do the commonalty lead their leaders in many\n" +
                "other things, at the same time that the leaders little suspect it. But\n" +
                "wherefore it was that after having repeatedly smelt the sea as a\n" +
                "merchant sailor, I should now take it into my head to go on a whaling\n" +
                "voyage; this the invisible police officer of the Fates, who has the\n" +
                "constant surveillance of me, and secretly dogs me, and influences me in\n" +
                "some unaccountable way—he can better answer than any one else. And,\n" +
                "doubtless, my going on this whaling voyage, formed part of the grand\n" +
                "programme of Providence that was drawn up a long time ago. It came in\n" +
                "as a sort of brief interlude and solo between more extensive\n" +
                "performances. I take it that this part of the bill must have run\n" +
                "something like this:\n" +
                "\n" +
                "“_Grand Contested Election for the Presidency of the United States._\n" +
                "“WHALING VOYAGE BY ONE ISHMAEL. “BLOODY BATTLE IN AFFGHANISTAN.”\n" +
                "\n" +
                "Though I cannot tell why it was exactly that those stage managers, the\n" +
                "Fates, put me down for this shabby part of a whaling voyage, when\n" +
                "others were set down for magnificent parts in high tragedies, and short\n" +
                "and easy parts in genteel comedies, and jolly parts in farces—though I\n" +
                "cannot tell why this was exactly; yet, now that I recall all the\n" +
                "circumstances, I think I can see a little into the springs and motives\n" +
                "which being cunningly presented to me under various disguises, induced\n" +
                "me to set about performing the part I did, besides cajoling me into the\n" +
                "delusion that it was a choice resulting from my own unbiased freewill\n" +
                "and discriminating judgment.\n" +
                "\n" +
                "Chief among these motives was the overwhelming idea of the great whale\n" +
                "himself. Such a portentous and mysterious monster roused all my\n" +
                "curiosity. Then the wild and distant seas where he rolled his island\n" +
                "bulk; the undeliverable, nameless perils of the whale; these, with all\n" +
                "the attending marvels of a thousand Patagonian sights and sounds,\n" +
                "helped to sway me to my wish. With other men, perhaps, such things\n" +
                "would not have been inducements; but as for me, I am tormented with an\n" +
                "everlasting itch for things remote. I love to sail forbidden seas, and\n" +
                "land on barbarous coasts. Not ignoring what is good, I am quick to\n" +
                "perceive a horror, and could still be social with it—would they let\n" +
                "me—since it is but well to be on friendly terms with all the inmates of\n" +
                "the place one lodges in.\n" +
                "\n" +
                "By reason of these things, then, the whaling voyage was welcome; the\n" +
                "great flood-gates of the wonder-world swung open, and in the wild\n" +
                "conceits that swayed me to my purpose, two and two there floated into\n" +
                "my inmost soul, endless processions of the whale, and, mid most of them\n" +
                "all, one grand hooded phantom, like a snow hill in the air.", canal, Estrategia.HAMMING);
        //é necessário modificar a estratégia e avaliar o desempenho
        Receptor receber = new Receptor(canal, Estrategia.HAMMING);

        canal.conectaTransmissor(transm);
        canal.conectaReceptor(receber);

        //mensurando o tempo de execução
        long tempoI = System.currentTimeMillis();
        transm.enviaDado();
        long tempoF = System.currentTimeMillis();

        System.out.println("Tempo total: " + (tempoF - tempoI));

        System.out.println(receber.getMensagem());
    }
}
