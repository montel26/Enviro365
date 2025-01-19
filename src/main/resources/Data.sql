-- Insert Waste Categories
INSERT INTO waste_category (name, description, disposal_guidelines, recycling_tips) VALUES
    ('Plastic', 'All types of plastic materials including bottles, containers, and packaging','Rinse plastic containers to remove food residue or liquids. Do not recycle plastic items like cling wrap, polystyrene foam, or contaminated plastic bags unless explicitly accepted by your local facility.','Use plastic containers for storage, crafts, or repurposing before recycling.'),
    ('Paper', 'Paper products including cardboard, newspapers, and magazines','Throw in designated waste bins','High-quality paper (e.g., office paper) should be recycled separately from low-quality paper (e.g., newspapers) for better processing.'),
    ('Glass', 'Glass bottles, jars, and other glass containers','If glass is broken, wrap it in newspaper or cardboard and mark it to prevent injuries during handling.','Reuse jars and bottles for storage'),
    ('Metal', 'Metal cans, aluminum foil, and other metal items','Rinse cans, foil, or containers to remove any food or residue. Take larger metal items like appliances or scrap to designated recycling centers or scrap yards.','Save small metal items (e.g., bottle caps, nails) in a container to recycle together. Clean and reuse aluminum foil before recycling.'),
    ('Organic', 'Food waste, yard trimmings, and other biodegradable materials','Separate food scraps, yard trimmings, and other biodegradable materials from non-organic waste. Place organic waste in a compost bin or create a compost pile in your yard.','Take advantage of municipal organic waste collection services if available.');

-- Insert Waste Disposal Methods
INSERT INTO waste_disposal_entity (method_name, description, waste_category_id, environmental_impact, safety_precautions) VALUES
    ('Recycling Center Dropoff', 'Take clean plastics to local recycling center', (SELECT id FROM waste_category WHERE name = 'Plastic'), 'Reduces landfill waste', 'Clean items before recycling'),
    ('Curbside Recycling', 'Place in blue recycling bin', (SELECT id FROM waste_category WHERE name = 'Plastic'), 'Reduces waste and energy usage', 'Follow local sorting guidelines'),
    ('Paper Recycling', 'Collect and bundle paper products', (SELECT id FROM waste_category WHERE name = 'Paper'), 'Saves trees and reduces landfill use', 'Remove any plastic or metal attachments'),
    ('Glass Collection', 'Separate by color and take to collection point', (SELECT id FROM waste_category WHERE name = 'Glass'), 'Highly recyclable material', 'Handle with care, wear gloves'),
    ('Composting', 'Process organic waste into fertilizer', (SELECT id FROM waste_category WHERE name = 'Organic'), 'Creates natural fertilizer', 'Maintain proper balance of materials');

-- Insert Recycling Tips
INSERT INTO recycling_tip (process_name, description, waste_category_id, benefits_description, processing_steps, resource_savings) VALUES
    ('Plastic Bottle Recycling', 'How to properly recycle plastic bottles', (SELECT id FROM waste_category WHERE name = 'Plastic'), 'Reduces oil consumption and landfill space', 'Clean, remove cap, crush, bin', 'Saves 2L of water per bottle'),
    ('Paper Sorting', 'Effective paper waste sorting', (SELECT id FROM waste_category WHERE name = 'Paper'), 'Saves trees and reduces pollution', 'Sort by type, remove staples, bundle', 'One ton saves 17 trees'),
    ('Glass Recycling', 'Proper glass recycling process', (SELECT id FROM waste_category WHERE name = 'Glass'), 'Infinitely recyclable material', 'Sort by color, clean, remove lids', 'Reduces mining impact'),
    ('Metal Can Prep', 'Preparing metal cans for recycling', (SELECT id FROM waste_category WHERE name = 'Metal'), 'Saves mining resources', 'Clean, crush, remove labels', 'Saves 95% energy vs new production'),
    ('Home Composting', 'Creating compost from kitchen waste', (SELECT id FROM waste_category WHERE name = 'Organic'), 'Creates rich soil amendment', 'Layer greens and browns, turn regularly', 'Reduces methane emissions');
