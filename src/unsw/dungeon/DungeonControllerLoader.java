package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image unlitBombImage;
    private Image plateImage;
    private Image boulderImage;
    private Image treasureImage;
    private Image swordImage;
    private Image potionImage;
    private Image exitImage;
    private Image enemyImage;
    private Image keyImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image houndImage;
    private Image bomb1Image;
    private Image bomb2Image;
    private Image bomb3Image;
    private Image bomb4Image;
    private Image redDoorImage;
    private Image blueDoorImage;
    private Image redKeyImage;
    private Image blueKeyImage;
    private Image houndAlertImage;
    private Image playerInvin;
    private Image playerDead;
    // Variables used for listeners
    static int itemCount = 1;
    static int itemCount2 = 5;
    private boolean flag = false;
    private Dungeon d;

    public DungeonControllerLoader(String filename, boolean multiplayer)
            throws FileNotFoundException {
        super(filename, multiplayer);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        unlitBombImage = new Image("/bomb_unlit.png");
        plateImage = new Image("/pressure_plate.png");
        boulderImage = new Image("/boulder.png");
        treasureImage = new Image("/gold_pile.png");
        potionImage = new Image("/brilliant_blue_new.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        exitImage = new Image("/exit.png");
        swordImage = new Image("/greatsword_1_new.png");
        keyImage = new Image("/key.png");
        closedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        houndImage = new Image("/hound_sleep.png");
        bomb1Image = new Image("/bomb_lit_1.png");
        bomb2Image = new Image("/bomb_lit_2.png");
        bomb3Image = new Image("/bomb_lit_3.png");
        bomb4Image = new Image("/bomb_lit_4.png");
        redDoorImage = new Image("/closed_door_red.png");
        blueDoorImage = new Image("/closed_door_blue.png");
        redKeyImage = new Image("/key_red.png");
        blueKeyImage = new Image("/key_blue.png");
        houndAlertImage = new Image("/hound_alert.png");
        playerInvin = new Image("/human_invinc.png");
        playerDead = new Image("/human_dead.png");
    }

    /**
     * All the onLoad functions link the entities to their pictures
     */
    
    @Override
    public void onLoad(Entity player, boolean b, Dungeon dungeon) {    	
    	d = dungeon;
    	ImageView view = new ImageView(playerImage);
        if (b) {
        	flag = true;
        }
        addEntity(player, view, b);
    }

    @Override
    public void onLoad(Wall wall, boolean b) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view, b);
    }
    
    @Override
    public void onLoad(Bomb unlitBomb, boolean b) {
        ImageView view = new ImageView(unlitBombImage);
        addEntity(unlitBomb, view, b);
    }
    
    @Override
    public void onLoad(Plate plate, boolean b) {
        ImageView view = new ImageView(plateImage);
        addEntity(plate, view, b);
    }
    
    @Override
    public void onLoad(Boulder boulder, boolean b) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view, b);
    }
    
    @Override
    public void onLoad(Treasure treasure, boolean b) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view, b);
    }
    
    @Override
    public void onLoad(Exit exit, boolean b) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view, b);
    }
    
    @Override
    public void onLoad(Enemy enemy, boolean b) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view, b);
    }
    
    // Loads different images for the key depending on the id number
    @Override
    public void onLoad(Key key, boolean b) {
        ImageView view = new ImageView(keyImage);
        if (key.getKeyNo() == 1) {
    		view.setImage(redKeyImage);
    	} else if (key.getKeyNo() == 2) {
    		view.setImage(blueKeyImage);
    	}
        addEntity(key, view, b);
    }
    
    // Loads different images for the door depending on the id number
    @Override
    public void onLoad(Door closedDoor, boolean b) {
    	ImageView view = new ImageView(closedDoorImage);
    	if (closedDoor.getDoorNo() == 1) {
    		view.setImage(redDoorImage);
    	} else if (closedDoor.getDoorNo() == 2) {
    		view.setImage(blueDoorImage);
    	}
        addEntity(closedDoor, view, b);
    }

    @Override
    public void onLoad(Sword sword, boolean b) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view, b);
    }
    
    @Override
	public void onLoad(Potion potion, boolean b) {
    	ImageView view = new ImageView(potionImage);
        addEntity(potion, view, b);
	}
    
    @Override
    public void onLoad(EnemyHound hound, boolean b) {
        ImageView view = new ImageView(houndImage);
        addEntity(hound, view, b);
    }
    
    /**
     * Function that adds an entity onto the board with the right picture
     * @param entity - the entity being added
     * @param view - the image linked to the entity
     * @param b - A boolean representing whether or not it is multiplayer
     */
    private void addEntity(Entity entity, ImageView view, boolean b) {
    	trackPosition(entity, view, b);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity - the entity being tracked
     * @param node - the node representing the view of the entity
     * @param b  - boolean representing whether or not it is multiplayer
     * Also tracks whether or not an item has been removed from the board,
     * tracks if an item is used, tracks if the player has died, tracks if an the hound is awake,
     * tracks if the door is open, tracks the player's inventory and
     * tracks the Bomb's state to animate the explosion
     */
    private void trackPosition(Entity entity, Node node, boolean b) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        Image orig_img = ((ImageView)node).getImage();
        
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.onBoard().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
            		Boolean oldValue, Boolean newValue) {
            	if (entity.onBoard().get() == false) {if (!entity.isItem()) {
            			((ImageView) node).setImage(null);
            		} else {
            			if (entity instanceof Potion) {
            				((ImageView) node).setImage(null);
            				return;
            			}
            			if (flag && d.getP1().getX() == entity.getX() && d.getP1().getY() == entity.getY()) {
            				entity.x().set(5+itemCount2);
                    		entity.y().set(0);
                    		itemCount2++;
                    		flag = true;
            			} else {
            				entity.x().set(itemCount);
                    		entity.y().set(0);
                    		itemCount++;
            			}
        			}
            	} else {
            		((ImageView) node).setImage(orig_img);
            	}
            }
        });
        
        if (entity instanceof Key) {
        	((Key) entity).keyUsed().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable,
                		Boolean oldValue, Boolean newValue) {
                	((ImageView) node).setImage(null);
                }
            });
        }
        if (entity instanceof Sword) {
        	((Sword) entity).getSwings().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                		Number oldValue, Number newValue) {
                	if (((Sword) entity).getSwings().get() == 0) {
                		((ImageView) node).setImage(null);
                	}
                }
            });
        }
        
        if (entity instanceof Player) {
        	((Player)entity).playerInvincibleProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable,
                		Boolean oldValue, Boolean newValue) {
                	if (((Player)entity).playerIsInvincible()) {
                		((ImageView) node).setImage(playerInvin);
                	} else {
                		((ImageView) node).setImage(orig_img);
                	}
                }
            });
        	((Player)entity).getInvenSize().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                		Number oldValue, Number newValue) {
                	itemCount = 1;
                	itemCount2 = 5;
            		for (Entity item: ((Player)entity).getInventory()) {
        				if (b) {
        					item.x().set(5+itemCount2);
            				itemCount2++;
        				} else {
        					item.x().set(itemCount);
            				itemCount++;
        				}
            			
            		}
                }
            });
        	((Player)entity).isAliveProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable,
                		Boolean oldValue, Boolean newValue) {
            		((ImageView) node).setImage(playerDead);
                }
            });
        }
        if (entity instanceof EnemyHound) {
        	((EnemyHound)entity).isAwake().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable,
                		Boolean oldValue, Boolean newValue) {
            		((ImageView) node).setImage(houndAlertImage);
                }
            });
        }
        
        if (entity instanceof Door) {
        	((Door)entity).isOpen().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable,
                		Boolean oldValue, Boolean newValue) {
            		((ImageView) node).setImage(openDoorImage);
                }
            });
        }
        
        if (entity instanceof Bomb) {
        	((Bomb) entity).turnsLeft().addListener(new ChangeListener<Number>() {
		        @Override
		        public void changed(ObservableValue<? extends Number> observable,
		                Number oldValue, Number newValue) {
		        	if (((Bomb) entity).turnsLeft().get() == 3) {
		        		((ImageView) node).setImage(bomb1Image);
		        	}
		        	if (((Bomb) entity).turnsLeft().get() == 2) {
		        		((ImageView) node).setImage(bomb2Image);
		        	}
		        	if (((Bomb) entity).turnsLeft().get() == 1) {
		        		((ImageView) node).setImage(bomb3Image);
		        	}
		        	if (((Bomb) entity).turnsLeft().get() == 0) {
		        		((ImageView) node).setImage(bomb4Image);
		        	}
		        	if (((Bomb) entity).turnsLeft().get() < 0) {
		        		((ImageView) node).setImage(null);
		        	}
		        }
		    });
        }
        
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }
}
