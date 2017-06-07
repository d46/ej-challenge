/* global VERSION */
import 'normalize.css';
import React, {Component} from 'react';
import {Button} from "../components/button/index";
import {user} from "./models/User";
import {badgeProgress} from "./models/BadgeProgress"

class Homepage extends Component {
	constructor(props) {
		super(props)
		this.handleClicker = this.handleClicker.bind(this);
	}

	handleClicker() {
		badgeProgress.set({
			action: "CLICKER"
		}).record();
	}

	render() {
		return (
			<section>
				<h1>Home Page</h1>
				{ this.renderUserMessage() ? "Welcome" : "Login Please" }
				<p>
					Draenor, the homeworld of the orcs, is being torn apart by a mysterious force known as fel magic.
					Gul'dan, a powerful orc warlock, unites the orc clans and forms the Horde, and creates a portal to
					the world of Azeroth. The orcs begin to use fel magic to drain the life out of captive draenei in
					order to sustain the portal. Once it is operational, Gul'dan leads a small warband to capture
					prisoners on Azeroth and sacrifice them to bring the rest of the Horde through the portal. Durotan,
					the chieftain of the Frostwolf Clan, his pregnant mate Draka, and his friend Orgrim Doomhammer join
					this initial warband. While crossing through the portal, Draka goes into labor. When the orcs arrive
					on Azeroth, Gul'dan assists Draka with giving birth, but the baby is stillborn. Gul'dan then drains
					the life out of a nearby deer to revive and infuse fel magic into the baby, which Durotan later
					names Go'el.
				</p>
				<br/>
				<p>
					The orcs raid several settlements throughout Azeroth. Anduin Lothar, the military commander of the
					human forces in the Stormwind Kingdom, looks over some of the men that were killed, and finds a
					trespassing mage named Khadgar, who explains that he was investigating the dead bodies because they
					contained traces of fel magic. Khadgar persuades Stormwind's king, Llane Wrynn, to consult Medivh,
					the renowned Guardian of Tirisfal, and Llane sends Anduin and Khadgar to Medivh's stronghold,
					Karazhan, to inform him of the fel magic's presence on Azeroth. In the Karazhan library, a ghostly
					shadow leads Khadgar to a mysterious book, which he takes.
				</p>
				<br/>
				<p>
					Anduin, Khadgar, and Medivh join a scouting team following traces of fel magic, but are ambushed by
					orcs. Medivh uses a spell to kill the fel-corrupted orcs, leaving the Horde's warchief, Blackhand,
					to flee along with Durotan and Orgrim. Khadgar restrains a half-orc slave, Garona, and the soldiers
					take her prisoner. King Llane frees Garona in exchange for loyalty to Stormwind, and she leads the
					humans to spy on the orc camp, where they learn of Gul'dan's plan to bring the Horde to Azeroth.
					Meanwhile, Durotan realizes that the fel magic is responsible for the destruction of Draenor, and if
					Gul'dan is not thwarted, Azeroth will suffer the same fate. Despite Orgrim's objections, Durotan
					invites Llane to a secret meeting so that the Frostwolf Clan and the humans can unite to defeat
					Gul'dan. While studying the book he took from Karazhan, Khadgar learns that Gul'dan could not have
					opened the portal on his own; he had help from someone on Azeroth. He is confronted by Medivh, who
					burns Khadgar's research when Khadgar offers to help him with his work.
				</p>
				<br/>
				<Button label='Clicker' onRippleEnded={this.handleClicker} raised primary/>
				<br/>
				<br/>
				<p>
					The Frostwolf Clan meets with the humans to negotiate an alliance, but the group is ambushed by
					Blackhand. As the humans retreat, Medivh forms a magical barrier to protect them, but Lothar's son
					Callan is separated from the rest of the group and killed by Blackhand. Medivh is severely weakened,
					and Garona and Khadgar take him back to Karazhan to recover. After noticing Medivh's eyes shine
					green, showing that he is infected by fel magic, Khadgar returns to his former home, Dalaran, to
					seek help from the Kirin Tor, the authority of human and high elven mages. The Kirin Tor facilitate
					a meeting with Alodi, revealed to be the shadow who led Khadgar to the book; she confirms that
					Medivh has indeed been corrupted by fel magic and turned into a demon. At the orc camp, Blackhand
					purges the Frostwolf Clan. Orgrim helps Draka to escape, and she sends Go'el down a river in a
					basket, but is then found and killed by another orc. Durotan challenges Gul'dan to Mak'gora, a
					traditional orcish duel to the death for leadership of a clan – in this case, all of the orcs.
					During the fight, Gul'dan violates the honorable combat rules by draining the life out of Durotan
					with his magic, killing him and earning the disapproval of the orcs watching, and he empowers
					Blackhand with the same magic. Medivh, now in a half-demonic state, starts to open the portal to
					Draenor, and Gul'dan begins sacrificing the captured human villagers to allow the rest of the Horde
					to enter Azeroth.
				</p>
				<br/>
				<p>
					Llane leads the human army in an assault on the orc camp, while Anduin and Khadgar fight Medivh and
					destroy the demon that had begun to manifest on the outside. Medivh is left mortally wounded, and
					uses the last of his strength to close the portal to Draenor and instead open a portal to Stormwind,
					allowing Llane to evacuate most of the freed prisoners. When Medivh eventually dies, the portal
					closes, leaving Llane, Garona and a small number of human soldiers to fight the orcs. Llane secretly
					orders Garona to kill him, bringing her honor among the orcs and putting her in a position of power
					to bring peace between the two races. Garona reluctantly does so, and is welcomed into the Horde by
					Gul'dan. As the orcs celebrate, Lothar arrives to retrieve King Llane's body and discovers Garona's
					knife still in Llane's neck, realizing that it was she who had killed their king. Blackhand
					challenges Lothar to Mak'gora, and Lothar quickly disposes of him. Against Gul'dan's demands, the
					orcs, bound by tradition, allow Lothar to depart with Llane's body. At Llane's funeral in Stormwind,
					the leaders of the other human nations, along with the high elves and dwarves, proclaim an alliance
					against the orcs and rally behind Lothar as the leader of the Alliance forces. Elsewhere, Orgrim
					takes one of Durotan's tusks to one day give to Go'el, and the basket containing Go'el is found by a
					human.
				</p>
				<br/>
				<Button style={{float: 'right'}} label='Clicker' onRippleEnded={this.handleClicker} raised primary/>
				<br/>
				<br/>
			</section>
		);
	}

	renderUserMessage() {
		return user.get('isAuthenticated')
	}

}

export default Homepage;
